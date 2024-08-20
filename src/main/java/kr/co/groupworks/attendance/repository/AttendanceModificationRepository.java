package kr.co.groupworks.attendance.repository;

import kr.co.groupworks.attendance.entity.Attendance;
import kr.co.groupworks.attendance.entity.AttendanceModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceModificationRepository extends JpaRepository<AttendanceModification, Long> {

    // 부서 ID에 속하는 직원들의 모든 수정 요청을 조회합니다.
    List<AttendanceModification> findAllByAttendance_Employee_Department_DepartmentIdAndStatusIsNull(Long departmentId);


}
