package kr.co.groupworks.control.cis;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.cis.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    //    받은 메일함
    @GetMapping("/receive")
    public String receive() {
        return "cis/mail/receive";
    }

    //    보낸 메일함
    @GetMapping("/send")
    public String send() {
        return "cis/mail/send";
    }

    //    중요 메일함
    @GetMapping("/important")
    public String important() {
        return "cis/mail/important";
    }

    //    휴지통 메일함
    @GetMapping("/trash")
    public String trash() {
        return "cis/mail/trash";
    }

    //    메일 쓰기 (메일 쓰기 버튼 누를 때 writeForm으로 이동)
    @GetMapping("/write")
    public String write() {
        return "cis/mail/writeForm";
    }

    //    메일 쓰기 완료 후 받은 메일함으로 redirect
    @PostMapping("/write")
    public String writePost(@ModelAttribute Mail mail, HttpSession session) {
        mail.setMailSender(employeeService.findByEmployeeId((Long) session.getAttribute("employeeId")).getEmail());
        mail.setMailSenderName(employeeService.findByEmployeeId((Long) session.getAttribute("employeeId")).getEmployeeName());

//        받는 사람 이메일에 해당하는 사람의 이름
        mail.setMailReceiverName(employeeService.findByEmployeeEmail((mail.getMailReceiver())).getEmployeeName());
//        참조되는 사람 이메일에 해당하는 사람의 이름
        mail.setMailReferrerName(employeeService.findByEmployeeEmail((mail.getMailReferrer())).getEmployeeName());
        mail.setMailSendTime(LocalDateTime.now());
        mail.setMailStatus(0);
        mail.setMailIsRead(0);

        log.info("메일 작성 : " + mail.toString());

        mailService.saveOne(mail);
        return "redirect:/mail/receive";
    }

    //    휴지통 메일 지우기
    @GetMapping("/delete")
    public String delete() {
        return "redirect:/mail/receive";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id ) {

        return "cis/mail/detail";
    }
}
