package kr.co.groupworks.attendance.service;

import kr.co.groupworks.attendance.dto.AttendanceModificationDTO;
import java.util.List;

public interface AttendanceModificationService {
    List<AttendanceModificationDTO> getModificationsByDepartmentId(Long departmentId);

    void modifyAttendance(AttendanceModificationDTO dto);
}
