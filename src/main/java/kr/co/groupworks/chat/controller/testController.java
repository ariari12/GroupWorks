package kr.co.groupworks.chat.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class testController {

    @GetMapping("/list")
    public String getTestChatPage() {
        return "chat/testChat";
    }

}
