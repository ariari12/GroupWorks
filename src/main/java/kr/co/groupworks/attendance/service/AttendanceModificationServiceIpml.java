package kr.co.groupworks.attendance.service;


import kr.co.groupworks.attendance.dto.AttendanceModificationDTO;
import kr.co.groupworks.attendance.entity.Attendance;
import kr.co.groupworks.attendance.entity.AttendanceModification;
import kr.co.groupworks.attendance.repository.AttendanceModificationRepository;
import kr.co.groupworks.attendance.repository.AttendanceRepository;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceModificationServiceIpml implements AttendanceModificationService{

    private final AttendanceRepository attendanceRepository;
    private final AttendanceModificationRepository attendanceModificationRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<AttendanceModificationDTO> getModificationsByDepartmentId(Long departmentId) {

        // 부서 ID로 모든 수정 요청 조회
        List<AttendanceModification> modifications = attendanceModificationRepository.
                findAllByAttendance_Employee_Department_DepartmentIdAndStatusIsNull(departmentId);

        // AttendanceModification 엔티티를 AttendanceModificationDTO로 변환
        return modifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 승인, 반려 업데이트.
    @Override
    public void modifyAttendance(AttendanceModificationDTO dto) {

        // 사원을 못찾았을때.
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 직원입니다."));

        // 사원 업데이트
        Attendance attendance = attendanceRepository.findById(dto.getAttendanceId()).get();
        attendance.updateStatus(dto.getStatus());

        // 승인자 업데이트
        AttendanceModification attendanceModification =
                attendanceModificationRepository.findById(dto.getModifyId()).get();
        attendanceModification.updateStatus(dto.getStatus(), employee);


    }

    private AttendanceModificationDTO convertToDTO(AttendanceModification modification) {
        return AttendanceModificationDTO.builder()
                .modifyId(modification.getModifyId())
                .employeeId(modification.getAttendance().getEmployee().getEmployeeId())
                .attendanceId(modification.getAttendance().getAttendanceId())
                .requestDate(modification.getRequestDate())
                .requestedClockIn(modification.getRequestedClockIn())
                .requestedClockOut(modification.getRequestedClockOut())
                .reason(modification.getReason())
                .workHours(modification.getWorkHours())
                .overtimeHours(modification.getOvertimeHours())
                .status(modification.getStatus())
                .approvalDate(modification.getApprovalDate())
                .employeeName(modification.getEmployeeName())
                .build();
    }

}
