package kr.co.groupworks.board.controller;

import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import org.springframework.ui.Model;

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
        Long empDepartmentId = sessionEmployeeDTO.getDepartmentId();
        return empDepartmentId.equals(departmentId);
    }
}
