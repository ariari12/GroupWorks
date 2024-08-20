package kr.co.groupworks.common;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        System.out.println("DatabaseInitializer init() method called");

        // Calendar 데이터 생성
        generateCalendarData();

        // Vacation 데이터 생성
        generateVacationData();
    }

    private void generateCalendarData() {
        System.out.println("Generating Calendar Data");

        // SQL template for inserting Calendar data
        String sql = "INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title) " +
                "VALUES (NULL, ?, NULL, 'Calendar', ?, ?, ?, ?)";

        // 기본 날짜 설정 (시작일)
        LocalDate baseDate = LocalDate.of(2024, 1, 1);

        for (int i = 1; i <= 10000; i++) {
            // 각 데이터의 날짜를 고유하게 만들기 위해 baseDate에 일수를 더함
            LocalDate startDate = baseDate.plusDays(i - 1);
            LocalDate endDate = startDate;

            // 데이터 삽입
            jdbcTemplate.update(sql, 1, "Contents for event " + i, endDate.toString(), startDate.toString(), "Event Title " + i);
        }

        System.out.println("Calendar Data generation complete");
    }

    private void generateVacationData() {
        System.out.println("Generating Vacation Data");

        // Vacation 데이터를 삽입하는 SQL
        String sql = "INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // 랜덤으로 vacation_type을 선택하기 위해 Random 객체 생성
        Random random = new Random();
        String[] vacationTypes = {"ANNUAL", "OTHER", "SICK"};

        for (int i = 1; i <= 10000; i++) {
            String selectedVacationType = vacationTypes[random.nextInt(vacationTypes.length)];

            // 데이터 삽입
            jdbcTemplate.update(sql, 1, i, null, null, null, "APPROVED", selectedVacationType);
        }

        System.out.println("Vacation Data generation complete");
    }
}