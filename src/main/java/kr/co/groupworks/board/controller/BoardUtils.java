package kr.co.groupworks.board.controller;

import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import org.springframework.ui.Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class BoardUtils {

    static final String NOTICE_BOARD = "공지 게시판";
    static final String DEPARTMENT_BOARD = "부서 게시판";

    // 타이틀 설정 메소드.
    public static void setTitleAndType(Model model, String title, String subtitle) {
        model.addAttribute("title", title);
        model.addAttribute("subtitle", subtitle);
    }

    // 부서 접근 권한 메소드
    public static boolean hasAccessToDepartment (SessionEmployeeDTO sessionEmployeeDTO, Long departmentId) {
        Long empDepartmentId = sessionEmployeeDTO.getDepartment().getDepartmentId();
        return empDepartmentId.equals(departmentId);
    }

    public static String formatRelativeTime(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        long minutes = duration.toMinutes();
        if (minutes < 1) {
            return "방금 전";
        } else if (minutes < 60) {
            return minutes + "분 전";
        } else if (minutes < 1440) { // 1440 minutes in a day
            long hours = duration.toHours();
            return hours + "시간 전";
        } else if (minutes < 10080) { // 10080 minutes in a week
            long days = duration.toDays();
            return days + "일 전";
        } else {
            return dateTime.toLocalDate().toString(); // or return a formatted string
        }
    }

}
