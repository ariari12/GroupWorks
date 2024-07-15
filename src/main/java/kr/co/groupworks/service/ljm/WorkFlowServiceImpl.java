package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowInsertDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.ljm.ApproverRepository;
import kr.co.groupworks.repository.ljm.AttachFileRepository;
import kr.co.groupworks.repository.ljm.WorkFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkFlowServiceImpl implements WorkFlowService {
    private final EmployeeRepository employeeRepository;
    private final WorkFlowRepository workFlowRepository;
    private final ApproverRepository approverRepository;
    private final AttachFileRepository attachFileRepository;

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
    public void setApproverList(List<ApproverDTO> approverList) {
        approverRepository.saveAll(approverList.stream().map(ApproverDTO::dtoToEntity).toList());
    }

    /* AttachFileList Save */
    @Override
    public void setAttachmentFileList(MultipartFile[] files) {
        log.info("upload dir: " + uploadDir);
        String fileUploadDir = uploadDir + File.separator + UUID.randomUUID();
        log.info("fileUploadDir: " + fileUploadDir);
        File uploadDirFile = new File(fileUploadDir);
        if(uploadDirFile.exists() || uploadDirFile.mkdirs()) {
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
                                    .setFileName(fileName)
                                    .setSavePath(filePath)
                    );
                }
                attachFileRepository.saveAll(attachmentFileList.stream()
                        .map(AttachmentFileDTO::dtoToEntity).toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        

    }

}
