package kr.co.groupworks.board.controller;


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
public class NoticeBoardController {

    @GetMapping("notice/list")
    public String noticeBoard(Model model) {
        BoardUtils.setTitleAndType(model, BoardUtils.NOTICE_BOARD, "");
        return "board/list";
    }

    @GetMapping("notice/write")
    public String noticeWrite(Model model) {
        BoardUtils.setTitleAndType(model, BoardUtils.NOTICE_BOARD, "글쓰기");
        return "board/write";
    }

    @GetMapping("notice/detail/{no}")
    public String noticeDetail(@PathVariable String no, Model model) {
        BoardUtils.setTitleAndType(model, BoardUtils.NOTICE_BOARD, "");
        return "board/detail";
    }

    @GetMapping("notice/edit")
    public String noticeEdit(Model model) {
        BoardUtils.setTitleAndType(model, BoardUtils.NOTICE_BOARD, "글수정");
        return "board/edit";
    }

}
