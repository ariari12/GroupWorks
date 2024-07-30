package kr.co.groupworks.workflow.service;

import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.workflow.entity.ApproverEntity;
import kr.co.groupworks.workflow.repository.ApproversRepository;
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
    private final ApproversRepository approverRepository;

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
//                        .setDepartment(approver.getDepartment() + " -> " + employee.getDepartmentName())
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