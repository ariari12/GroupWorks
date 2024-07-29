package kr.co.groupworks.workflow.service;

import kr.co.groupworks.workflow.dto.employee.EmployeeDTO;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.dto.vo.WorkflowListVO;
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

    Object getWorkflowStatistics(long employeeId, long departmentId, int choise);
}
