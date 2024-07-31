package kr.co.groupworks.board.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String board(Model model) {
        model.addAttribute("msg", "test");
        return "yhj/board/list";
    }

    @GetMapping("/write")
    public String write() {
        return "yhj/board/write";
    }

    @GetMapping("/detail")
    public String detail() {
        return "yhj/board/detail";
    }

    @GetMapping("/edit")
    public String edit() {
        return "yhj/board/edit";
    }

}