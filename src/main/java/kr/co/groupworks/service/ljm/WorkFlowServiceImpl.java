package kr.co.groupworks.service.ljm;

import kr.co.groupworks.control.ljm.WorkFlowController;
import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.entity.ljm.AttachmentFileEntity;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.ljm.ApproversOnlyRepository;
import kr.co.groupworks.repository.ljm.AttachmentFileRepository;
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
    private final AttachmentFileRepository attachmentFileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /* EmployeeId -> EmployeeDTO */
    @Override
    public EmployeeDTO getEmployeeDTO(long employeeId) {
        return EmployeeDTO.entityToDto(employeeRepository.findByEmployeeId(employeeId));
    }

    /* All EmployeeDTO */
    @Override
    public List<EmployeeDTO> getEmployeeAllDTOList() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "departmentName"))
                .stream().map(EmployeeDTO::entityToDto).toList();
    }

    /* EmployeeId -> WorkFlowDTO */
    @Override
    public WorkFlowDTO getWorkflowDTO(long employeeId) {
        return EmployeeDTO.entityToWorkflowDTO(employeeRepository.findByEmployeeId(employeeId));
    }

    /* EmployeeId -> ApproverDTO */
    @Override
    public ApproverDTO getApproverDTO(long employeeId) {
        return EmployeeDTO.entityToApproverDto(employeeRepository.findByEmployeeId(employeeId));
    }

    /* WorkFlowEntity Save */
    @Override
    public long setWorkFlowDTO(WorkFlowDTO workFlowDTO) {
        return workFlowRepository.save(workFlowDTO.dtoToEntity()).getId();
    }

    /* EmployeeId -> WorkFLowDTOList */
    @Override
    public Map<String, List<WorkFlowDTO>> getMyWorkFlowDTOList(long employeeId) {
        /*
        * Referenced is drafter(Employee) of submitted approval request 
        * 사원번호로 결재 발송 내역 조회
        */
        List<WorkFlowDTO> workFlowList = workFlowRepository.findByEmployeeId(employeeId).stream().map(WorkFlowDTO::entityToDto).toList();

        /* approval(승인), progress(진행), rejection(반려) */
        Map<String, List<WorkFlowDTO>> result = new HashMap<>();
        result.put(WorkFlowController.AttributeName.PROGRESS.getStatus(), workFlowList.stream().filter(d -> d.getStatus() == 0).toList());
        result.put(WorkFlowController.AttributeName.APPROVAl.getStatus(), workFlowList.stream().filter(d -> d.getStatus() == 1).toList());
        result.put(WorkFlowController.AttributeName.REJECTION.getStatus(), workFlowList.stream().filter(d -> d.getStatus() == 2).toList());

        return result;
    }

    /* ApproverId -> WorkFlowDTOList */
    @Override
    public Map<String, List<WorkFlowDTO>> getApproverWorkFlowDTOList(long approverId) {
        /* Referenced is approver of submitted approval request */
        List<WorkFlowDTO> myWorkFlowDTOList;

        return null;
    }

    @Override
    public WorkFlowDTO getDetailWorkFlow(long workflowId) {
        return WorkFlowDTO.entityToDto(Objects.requireNonNull(workFlowRepository.findById(workflowId).orElse(null)));
    }

    /* ApproverEntityList Save */
    @Override
    public boolean setApproverDTOList(List<ApproverDTO> approverList) {
        Optional<ApproverDTO> finalApproverOption = approverList.stream().filter(i -> i.getApproverType() == 1).max(Comparator.comparingInt(ApproverDTO::getSequenceNum));
        ApproverDTO finalApprover;
        if(finalApproverOption.isEmpty()) return false;
        finalApprover = finalApproverOption.get();
        log.info("finalApprover: {}", finalApprover);

        Optional<WorkFlowEntity> workFlow = workFlowRepository.findById(finalApprover.getWorkFlowId());
        if(workFlow.isEmpty()) return false;

        WorkFlowDTO workFlowDTO = WorkFlowDTO.entityToDto(workFlow.get())
                .setFinalApprovalRank(finalApprover.getApproverRank())
                .setFinalApprovalDepartment(finalApprover.getDepartment())
                .setFinalApprovalName(finalApprover.getApproverName())
                .setStatus(0)
                .setApprovers(approverList)
                ;
        workFlowDTO = WorkFlowDTO.entityToDto(workFlowRepository.save(workFlowDTO.dtoToEntity()));
        log.info("workFlowDTO: {}", workFlowDTO);

        return true;
    }

    /* AttachFileList Save */
    @Override
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
                WorkFlowDTO workFlowDTO = WorkFlowDTO.entityToDto(workFlowEntity)
                        .setAttachmentFiles(attachmentFileList);
                workFlowRepository.save(workFlowDTO.dtoToEntity());
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
        AttachmentFileEntity fileEntity = attachmentFileRepository.findById(fileId).orElse(null);
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
