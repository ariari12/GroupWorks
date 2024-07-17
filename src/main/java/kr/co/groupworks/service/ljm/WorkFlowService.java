package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowInsertDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface WorkFlowService {
    /* 사원 정보 EmployeeDTO */
    EmployeeDTO getEmployee(long employeeId);

    /* 사원 정보 WorkFlowInsertDTO */
    WorkFlowInsertDTO getWorkflowDto(long employeeId);

    /* 사원 정보 WorkFlowInsertDTO */
    ApproverDTO getApproverDTO(long employeeId);

    /* 전체 사원 정보 */
    List<EmployeeDTO> getEmployeeAll();

    /* WorkFlowEntity Save */
    long setWorkFlowRequest(WorkFlowInsertDTO workFlowInsertDTO);

    /* ApproverEntityList Save */
    boolean setApproverList(List<ApproverDTO> approverList);

    /* AttachFileList Save */
    boolean setAttachmentFileList(MultipartFile[] files, long pk);

    /* AttachmentFile Select */
    Map<String, Object> getAttachmentFile(long fileId);
}
