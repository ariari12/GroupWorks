package kr.co.groupworks.attendance.repository;

import kr.co.groupworks.attendance.entity.Attendance;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByEmployee_EmployeeIdAndDateBetween(Long employeeId, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findAttendancesByEmployeeIdAndDateRange(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND a.clockInTime BETWEEN :startOfDay AND :endOfDay")
    Optional<Attendance> findClockInRecordForToday(
            @Param("employeeId") Long employeeId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND a.clockOutTime BETWEEN :startOfDay AND :endOfDay")
    Optional<Attendance> findClockOutRecordForToday(
            @Param("employeeId") Long employeeId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    boolean existsByEmployeeAndClockInTimeBetween(Employee employee, LocalDateTime start, LocalDateTime end);
    boolean existsByEmployeeAndClockOutTimeBetween(Employee employee, LocalDateTime start, LocalDateTime end);
}