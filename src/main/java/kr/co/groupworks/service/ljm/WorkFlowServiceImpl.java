package kr.co.groupworks.service.ljm;

import kr.co.groupworks.control.ljm.WorkFlowController;
import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.dto.ljm.vo.WorkflowListVO;
import kr.co.groupworks.entity.ljm.ApproverEntity;
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
    private static final String WORKFLOW_FILEPATH = "workflow" + File.separator;

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
    public Map<String, List<WorkflowListVO>> getMyWorkFlowDTOList(long employeeId) {
        /*
         * Referenced is drafter(Employee) of submitted approval request
         * 사원번호로 결재 발송 내역 조회
         * approval(승인), progress(진행), rejection(반려) *
         */
        final String[] PARAM = { WorkFlowController.AttributeName.PROGRESS.getStatus(), WorkFlowController.AttributeName.APPROVAl.getStatus(), WorkFlowController.AttributeName.REJECTION.getStatus() };
        Map<String, List<WorkflowListVO>> result = Map.of(PARAM[0], new ArrayList<>(), PARAM[1], new ArrayList<>(), PARAM[2], new ArrayList<>());
        workFlowRepository.findByEmployeeId(employeeId).stream().map(WorkflowListVO::new).forEach(vo ->  result.get(PARAM[vo.getStatus()]).add(vo));

        return result;
    }

    /* Workflow Detail */
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
//        log.info("finalApprover: {}", finalApprover);

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
    public boolean setAttachmentFileList(MultipartFile[] files, long workflowId, String employeeName) {
//        log.info("upload dir: " + uploadDir);
        String fileUploadDir = uploadDir + WORKFLOW_FILEPATH;
        WorkFlowEntity workFlowEntity = workFlowRepository.findById(workflowId).orElse(null);
        if(workFlowEntity != null) {
            fileUploadDir += employeeName + "-" + workFlowEntity.getTitle() + "-" + UUID.randomUUID();
//        log.info("fileUploadDir: " + fileUploadDir);
            File uploadDirFile = new File(fileUploadDir);
            if ((uploadDirFile.exists() || uploadDirFile.mkdirs())) {
                try {
                    List<AttachmentFileDTO> attachmentFileList = new ArrayList<>();
                    for (MultipartFile mlpFile : files) {
                        String fileName = mlpFile.getOriginalFilename();
                        String filePath = fileUploadDir + File.separator + fileName;
                        log.info("filePath: " + filePath);
                        log.info("fileName: " + fileName);

                        mlpFile.transferTo(new File(filePath));
                        attachmentFileList.add( new AttachmentFileDTO().setWorkFlowId(workflowId).setFileName(fileName).setSavePath(filePath) );
                    }
                    WorkFlowDTO workFlowDTO = WorkFlowDTO.entityToDto(workFlowEntity).setAttachmentFiles(attachmentFileList);
                    workFlowRepository.save(workFlowDTO.dtoToEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
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

    /* Approver Update Approve/Reject/Comment */
    @Override
    public boolean setApprover(ApproverDTO approverDTO) {
        if(approverDTO.getApproverType() == 1) {
            /* 결재자가 최종 결재자인지 확인 필요 */
            return true;
        } else if (approverDTO.getApproverType() == 2) {
            /* 협조자는 코멘트 추가 / 단, 결재가 완료되기 전까지... */

            return true;
        }
        return false;
    }

    /* ApproverId -> WorkFlowDTOList */
    @Override
    public Map<String, List<WorkflowListVO>> getWorkflowWaitList(long employeeId) {
        /* referenced employee Id is an Approver of a submitted approval request.
         * 참조된 사원 ID는 결재의 승인자
         * Rsult: Approve / Proceed / Reject
         * 결과: 승인한 결재 / 결재 (대기)진행 / 반려한 결재
         */
        Map<String, List<WorkflowListVO>> listMap = Map.of(
                WorkFlowController.AttributeName.APPROVAl.getStatus(), new ArrayList<>(),
                WorkFlowController.AttributeName.PROGRESS.getStatus(), new ArrayList<>(),
                WorkFlowController.AttributeName.REJECTION.getStatus(), new ArrayList<>()
        );

        workFlowRepository.workflowListFindByApproverId(employeeId, 1).forEach(list -> {
            for (ApproverEntity a : list.getApprovers()) {
                if(a.getEmployeeId() == employeeId) {
                    switch (a.getApproval()) {
                        case 1: listMap.get(WorkFlowController.AttributeName.APPROVAl.getStatus()).add(new WorkflowListVO(list)); break;
                        case 2: listMap.get(WorkFlowController.AttributeName.REJECTION.getStatus()).add(new WorkflowListVO(list)); break;
                        default: listMap.get(WorkFlowController.AttributeName.PROGRESS.getStatus()).add(new WorkflowListVO(list));
                    }
                }
            }
        });

        return listMap;
    }

    @Override
    public Map<String, List<WorkflowListVO>> getWorkflowReferrerList(long employeeId) {
        /* referenced employee Id is a collaborator or reference of a submitted approval request. */
        Map<String, List<WorkflowListVO>> listMap = new HashMap<>();
        listMap.put(WorkFlowController.AttributeName.COLLABORATORS.getStatus(), workFlowRepository.workflowListFindByApproverId(employeeId, 2).stream().map(e -> new WorkflowListVO(e)).toList());
        listMap.put(WorkFlowController.AttributeName.REFERRESRS.getStatus(), workFlowRepository.workflowListFindByApproverId(employeeId, 3).stream().map(e -> new WorkflowListVO(e)).toList());

        return listMap;
    }

}
