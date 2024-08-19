package kr.co.groupworks.attendance.repository;

import kr.co.groupworks.attendance.entity.AttendanceModification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceModificationRepository extends JpaRepository<AttendanceModification, Long> {
    List<AttendanceModification> findByEmployee_EmployeeId(Long employeeId);
}
