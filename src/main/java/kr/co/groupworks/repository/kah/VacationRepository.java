package kr.co.groupworks.repository.kah;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long>, VacationRepositoryCustom {
    @Query("select v from Vacation v where v.employee.employeeId = :id")
    List<Vacation> findAllByEmployeeId(@Param("id")Long id);


}
