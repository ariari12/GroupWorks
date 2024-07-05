package kr.co.groupworks.control.cis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {

    //    받은 메일함
    @GetMapping("/receive")
    public String receive() {
        return "mail/receive";
    }

    //    보낸 메일함
    @GetMapping("/send")
    public String send() {
        return "mail/send";
    }

    //    중요 메일함
    @GetMapping("/important")
    public String important() {
        return "mail/important";
    }

    //    휴지통 메일함
    @GetMapping("/trash")
    public String trash() {
        return "mail/trash";
    }

    //    메일 쓰기 (메일 쓰기 버튼 누를 때 writeForm으로 이동)
    @GetMapping("/write")
    public String write() {
        return "mail/writeForm";
    }

    //    메일 쓰기 완료 후 받은 메일함으로 redirect
    @PostMapping("/write")
    public String writePost() {
        return "redirect:/mail/receive";
    }

    //    휴지통 메일 지우기
    @GetMapping("/delete")
    public String delete() {
        return "redirect:/mail/receive";
    }
}
