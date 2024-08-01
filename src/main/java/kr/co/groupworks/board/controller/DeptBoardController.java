package kr.co.groupworks.board.controller;


import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/board")
public class DeptBoardController {

    @GetMapping("{departmentId}/list")
    public String deptBoard(@PathVariable("departmentId") Long departmentId, Model model,
                        @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }
        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "");
        return "board/list";
    }

    @GetMapping("{departmentId}/write")
    public String deptWrite(@PathVariable("departmentId") Long departmentId, Model model,
                        @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }

        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "글쓰기");
        return "board/write";
    }

    @GetMapping("{departmentId}/detail/{no}")
    public String deptDetail(@PathVariable("departmentId") Long departmentId, Model model,
                         @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }

        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "");
        return "board/detail";
    }

    @GetMapping("{departmentId}/edit")
    public String deptEdit(@PathVariable("departmentId") Long departmentId, Model model,
                       @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }

        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "글수정");
        return "board/edit";
    }

}
