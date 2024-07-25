package kr.co.groupworks.service.workflow;

import kr.co.groupworks.dto.workflow.employee.EmployeeDTO;
import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.dto.workflow.dto.WorkFlowDTO;
import kr.co.groupworks.dto.workflow.vo.WorkflowListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface WorkFlowService {
    /* EmployeeId -> EmployeeDTO */
    EmployeeDTO getEmployeeDTO(long employeeId);

    /* EmployeeId -> WorkFlowDTO */
    WorkFlowDTO getWorkflowDTO(long employeeId);

    /* EmployeeId -> ApproverDTO */
    ApproverDTO getApproverDTO(long employeeId);

    /* All EmployeeDTO */
    List<EmployeeDTO> getEmployeeAllDTOList();

    /* WorkFlowEntity Save */
    long setWorkFlowDTO(WorkFlowDTO workFlowDTO);

    /* EmployeeId -> WorkFlowDTOList */
    Map<String, List<WorkflowListVO>> getMyWorkFlowDTOList(long employeeId);

    /* Workflow Detail */
    WorkFlowDTO getDetailWorkFlow(long workflowId);

    /* ApproverEntityList Save */
    boolean setApproverDTOList(List<ApproverDTO> approverList);

    /* AttachFileList Save */
    boolean setAttachmentFileList(MultipartFile[] files, long pk, String employeeName);

    /* AttachmentFile Select */
    Map<String, Object> getAttachmentFile(long fileId);

    boolean setApprover(ApproverDTO approverDTO);

    /* employeeId -> WorkFlowWait DTOList */
    Map<String, List<WorkflowListVO>> getWorkflowWaitList(long employeeId);

    /* employeeId -> WorkFlowReferrer DTOList */
    Map<String, List<WorkflowListVO>> getWorkflowReferrerList(long employeeId);

    boolean removeWorkflow(long workFlowId);

    Map<String, List<Object>> getWorkflowStatus(long employeeId, long departmentId);
}
