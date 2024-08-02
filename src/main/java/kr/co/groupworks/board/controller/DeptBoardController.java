package kr.co.groupworks.board.controller;


import jakarta.servlet.http.HttpServletRequest;
import kr.co.groupworks.board.dto.BoardDTO;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.board.entity.BoardStatus;
import kr.co.groupworks.board.service.BoardService;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class DeptBoardController {

    private final BoardService boardService;

    // 게시판 리스트
    @GetMapping("{departmentId}/list")
    public String deptBoard(@PathVariable("departmentId") Long departmentId, Model model,
                        @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }
        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "");
        List<BoardDTO> board = boardService.getAllBoards(departmentId);
        for (BoardDTO b : board) {
            b.setRelativeCreateDate(BoardUtils.formatRelativeTime(b.getCreateDate()));
        }
        model.addAttribute("board", board);
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

    @PostMapping("{departmentId}/write")
    public String deptwrite(@PathVariable("departmentId") Long departmentId,
                            @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO,
                            @ModelAttribute("boardDTO") BoardDTO dto,
                            HttpServletRequest request) {

        log.info("전달 받은 DTO : {}", dto);
        dto.setIpAddress(request.getRemoteAddr());
        dto.setDepartmentId(departmentId);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        log.info("설정한 DTO : {}", dto);
        boardService.register(dto);

        return "redirect:/board/" + departmentId + "/list";
    }

    @GetMapping("{departmentId}/detail/{no}")
    public String deptDetail(@PathVariable("departmentId") Long departmentId,
                             @PathVariable("no") Long no, Model model,
                         @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        if (!BoardUtils.hasAccessToDepartment(sessionEmployeeDTO, departmentId)) {
            return "redirect:/main?alert=invalid_department";
        }

        BoardUtils.setTitleAndType(model, BoardUtils.DEPARTMENT_BOARD, "");
        BoardDTO detail = boardService.getDetail(no, departmentId);

        model.addAttribute("board", detail);
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
