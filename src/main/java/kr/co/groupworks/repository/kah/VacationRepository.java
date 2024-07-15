package kr.co.groupworks.repository.kah;

import kr.co.groupworks.entity.kah.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Calendar, Long> {

}
