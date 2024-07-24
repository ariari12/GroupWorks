package kr.co.groupworks.service.workflow;

import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.workflow.ApproverEntity;
import kr.co.groupworks.repository.workflow.ApproversOnlyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppoverUpdateServiceImpl implements AppoverUpdateService {
    private final ApproversOnlyRepository approverRepository;

    public boolean setApproverUpdate(Employee employee) {
        List<ApproverEntity> approvalList = approverRepository.findByEmployeeIdAndApprovalMethodAndApproval(employee.getEmployeeId(), 0, 0);
//        approvalList.forEach(a -> {log.info("{}", ApproverDTO.entityToDto(a));});

        List<ApproverEntity> updateList = new ArrayList<>();
        if (!approvalList.isEmpty()) {
            approvalList.forEach(approver -> {
                updateList.add(ApproverDTO.entityToDto(approver)
                        .setApproverName(employee.getEmployeeName())
                        .setApproverEmail(employee.getEmail())
                        .setApproverPhone(employee.getPhoneNumber())
                        .setApproverRank(approver.getApproverRank() + " -> " + employee.getRankName())
                        .setDepartment(approver.getDepartment() + " -> " + employee.getDepartmentName())
                        .dtoToEntity()
                );
            });
//            updateList.forEach(a -> {log.info("{}", ApproverDTO.entityToDto(a));});

            approverRepository.saveAll(updateList);
            return true;
        } else {
            return false;
        }
    }
}
