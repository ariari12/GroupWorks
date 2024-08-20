package kr.co.groupworks.chat.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/list")
    public String chat(Model model) {
        return "chat/list";
    }
}
