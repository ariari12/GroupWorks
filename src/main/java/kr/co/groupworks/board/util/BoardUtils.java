package kr.co.groupworks.board.util;

import kr.co.groupworks.board.dto.BoardDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BoardUtils {

    private static final String NOTICE_BOARD = "공지 게시판";
    private static final String DEPARTMENT_BOARD = "부서 게시판";

    /**
     * 게시판 설정 및 접근 권한 확인 메서드.
     */
    public static Long initializeBoard(
            String boardType, SessionEmployeeDTO sessionEmployeeDTO, String boardTitle, Model model) {

        Long departmentId = getDepartmentId(boardType);
        String boardCategory = getBoardCategory(boardType);
        if (departmentId != null && !hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return null;
        }
        setTitleAndType(model, boardCategory, boardTitle);
        return departmentId;
    }

    /**
     * 타이틀 설정 메소드.
     * 모델에 타이틀과 서브타이틀을 설정.
     */
    private static void setTitleAndType(Model model, String title, String subtitle) {
        model.addAttribute("title", title);
        model.addAttribute("subtitle", subtitle);
    }

    /**
     * 부서 접근 권한 메소드.
     * 세션의 직원이 특정 부서에 접근할 수 있는지 확인.
     */
    private static boolean hasAccessToDepartment (SessionEmployeeDTO sessionEmployeeDTO, Long departmentId) {
        Long empDepartmentId = sessionEmployeeDTO.getDepartment().getDepartmentId();
        return empDepartmentId.equals(departmentId);
    }

    /**
     * boardType에 따라 부서 ID를 반환.
     * "notice"이면 null을 반환하고, 그 외에는 Long으로 변환하여 반환.
     */
    private static Long getDepartmentId(String boardType) {
        return boardType.equals("notice") ? null : Long.parseLong(boardType);
    }

    /**
     * boardType에 따라 게시판 카테고리를 반환.
     * "notice"이면 공지 게시판 카테고리를 반환하고, 그 외에는 부서 게시판 카테고리를 반환.
     */
    private static String getBoardCategory(String boardType) {
        return boardType.equals("notice") ? NOTICE_BOARD : DEPARTMENT_BOARD;
    }

    /**
     * Page<BoardDTO>에 대한 날짜 설정 메소드
     */
    public static void setDate(Page<BoardDTO> dto) {
        for (BoardDTO b : dto) {
            b.setRelativeCreateDate(BoardUtils.formatRelativeTime(b.getCreateDate()));
        }
    }

    /**
     * List<BoardDTO>에 대한 날짜 설정 메소드
     */
    public static void setDate(List<BoardDTO> dto) {
        for (BoardDTO b : dto) {
            b.setRelativeCreateDate(BoardUtils.formatRelativeTime(b.getCreateDate()));
        }
    }

    /**
     * 게시판 시간 포맷팅 메소드.
     * 주어진 시간과 현재 시간의 차이를 인간 친화적인 형식으로 반환.
     */
    private static String formatRelativeTime(LocalDateTime dateTime) {
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
