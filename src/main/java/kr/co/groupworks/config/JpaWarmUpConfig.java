package kr.co.groupworks.config;

import kr.co.groupworks.calendar.repository.CalendarRepository;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaWarmUpConfig {

    private final CalendarRepository calendarRepository;
    private final VacationHistoryRepository vacationHistoryRepository;


    @Bean
    public ApplicationRunner jpaWarmUpRunner() {
        return args -> {
            // JPA 엔티티를 미리 로드하여 초기화
            calendarRepository.findById(1L);
            vacationHistoryRepository.findById(1L);
            log.info("JPA warm-up completed.");
        };
    }
}