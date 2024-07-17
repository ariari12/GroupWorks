package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowInsertDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.entity.ljm.AttachmentFileOnlyEntity;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.ljm.ApproversOnlyRepository;
import kr.co.groupworks.repository.ljm.AttachmentFileOnlyRepository;
import kr.co.groupworks.repository.ljm.WorkFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkFlowServiceImpl implements WorkFlowService {
    private final EmployeeRepository employeeRepository;
    private final WorkFlowRepository workFlowRepository;

    private final ApproversOnlyRepository approversOnlyRepository;
    private final AttachmentFileOnlyRepository attachmentFileOnlyRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /* 사원 정보 EmployeeDTO */
    @Override
    public EmployeeDTO getEmployee(long employeeId) {
        return EmployeeDTO.entityToDto(employeeRepository.findByEmployeeId(employeeId));
    }

    /* 사원 정보 WorkFlowInsertDTO */
    @Override
    public WorkFlowInsertDTO getWorkflowDto(long employeeId) {
        return EmployeeDTO.entityToWorkflowDTO(employeeRepository.findByEmployeeId(employeeId));
    }

    /* 사원 정보 WorkFlowInsertDTO */
    @Override
    public ApproverDTO getApproverDTO(long employeeId) {
        return EmployeeDTO.entityToApproverDto(employeeRepository.findByEmployeeId(employeeId));
    }

    /* 전체 사원 정보 */
    @Override
    public List<EmployeeDTO> getEmployeeAll() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "departmentName"))
                .stream().map(EmployeeDTO::entityToDto).toList();
    }

    /* WorkFlowEntity Save */
    @Override
    public long setWorkFlowRequest(WorkFlowInsertDTO workFlowInsertDTO) {
        return workFlowRepository.save(workFlowInsertDTO.dtoToEntity()).getId();
    }


    /* ApproverEntityList Save */
    @Override
    @Transactional
    public boolean setApproverList(List<ApproverDTO> approverList) {
        Optional<ApproverDTO> finalApproverOption = approverList.stream().filter(i -> i.getApproverType() == 1).max(Comparator.comparingInt(ApproverDTO::getSequenceNum));
        ApproverDTO finalApprover;
        if(finalApproverOption.isEmpty()) return false;
        finalApprover = finalApproverOption.get();
        log.info("finalApprover: {}", finalApprover);

        Optional<WorkFlowEntity> workFlow = workFlowRepository.findById(finalApprover.getWorkFlowId());
        if(workFlow.isEmpty()) return false;

        WorkFlowInsertDTO workFlowInsertDTO = WorkFlowInsertDTO.entityToDto(workFlow.get())
                .setFinalApprovalRank(finalApprover.getApproverRank())
                .setFinalApprovalDepartment(finalApprover.getDepartment())
                .setFinalApprovalName(finalApprover.getApproverName())
                .setStatus(0)
                .setApprovers(approverList)
                ;
        workFlowInsertDTO = WorkFlowInsertDTO.entityToDto(workFlowRepository.save(workFlowInsertDTO.dtoToEntity()));
        log.info("workFlowDTO: {}", workFlowInsertDTO);

        return true;
    }

    /* AttachFileList Save */
    @Override
    @Transactional
    public boolean setAttachmentFileList(MultipartFile[] files, long workflowId) {
//        log.info("upload dir: " + uploadDir);
        String fileUploadDir = uploadDir + File.separator + UUID.randomUUID();
//        log.info("fileUploadDir: " + fileUploadDir);
        File uploadDirFile = new File(fileUploadDir);
        WorkFlowEntity workFlowEntity = workFlowRepository.findById(workflowId).orElse(null);
        if((uploadDirFile.exists() || uploadDirFile.mkdirs()) && workFlowEntity != null) {
            try {
                List<AttachmentFileDTO> attachmentFileList = new ArrayList<>();
                for (MultipartFile mlpFile : files) {
                    String fileName = mlpFile.getOriginalFilename();
                    String filePath = fileUploadDir + File.separator + fileName;
                    log.info("filePath: " + filePath);
                    log.info("fileName: " + fileName);

                    mlpFile.transferTo(new File(filePath));
                    attachmentFileList.add(
                            new AttachmentFileDTO()
                                    .setWorkFlowId(workflowId)
                                    .setFileName(fileName)
                                    .setSavePath(filePath)
                    );
                }
                WorkFlowInsertDTO workFlowDTO = WorkFlowInsertDTO.entityToDto(workFlowEntity)
                        .setAttachmentFiles(attachmentFileList);
                workFlowRepository.save(workFlowDTO.dtoToEntity());
//                attachFileRepository.saveAll(attachmentFileList.stream()
//                        .map(AttachmentFileDTO::dtoToEntity).toList());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    /* AttachmentFile Download */
    @Override
    public Map<String, Object> getAttachmentFile(long fileId) {
        Map<String, Object> result = new HashMap<>();
        AttachmentFileOnlyEntity fileEntity = attachmentFileOnlyRepository.findById(fileId).orElse(null);
        result.put("result", fileEntity != null);
        if(fileEntity != null) {
            File file = new File(fileEntity.getSavePath());
            result.put("fileResource", new FileSystemResource(file));
            result.put("fileName", URLEncoder.encode(fileEntity.getFileName(), StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
            result.put("fileSize", file.length());
        }
        return result;
    }

}
