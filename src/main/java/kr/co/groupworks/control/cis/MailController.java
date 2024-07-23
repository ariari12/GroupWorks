package kr.co.groupworks.control.cis;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.cis.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.runtime.powerassert.SourceTextNotAvailableException;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    private final int PAGE_SIZE = 10;

    Page<Mail> mailPage = null;
    Pageable pageable = null;

    //    받은 메일함
    @GetMapping("/receive")
    public String receive(HttpSession session, Model model,
                          @RequestParam(value = "keytype", required = false) String keytype,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        String receiverEmail = employeeDTO.getEmail();
        log.info(receiverEmail + "의 받은 메일함 컨트롤러 동작 중");

        pageable = PageRequest.of(pageNumber, PAGE_SIZE);

        if (keytype != null && keyword != null) {
            log.info("검색어 타입은 " + keytype + " 내용은 " + keyword);
            log.info(receiverEmail + "의 받은 메일 중 검색결과 목록");

            if (keytype.equals("제목")) {
                mailPage = mailService.getEmailListByReceiverEmailAndMailTitle(receiverEmail, keyword, pageable);
            } else if (keytype.equals("발신자이름"))  {
                mailPage = mailService.getEmailListByReceiverEmailAndMailSenderName(receiverEmail, keyword, pageable);
            }
        } else {
            mailPage = mailService.getEmailListByReceiverEmail(receiverEmail, pageable);
        }

        model.addAttribute("mailList", mailPage);
        return "cis/mail/receive";
    }

    //    보낸 메일함 (검색기능을 포함한)
    @GetMapping("/send")
    public String send(HttpSession session, Model model,
                       @RequestParam(value = "keytype",required = false) String keytype,
                       @RequestParam(value = "keyword",required = false) String keyword,
                       @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber) {

        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");

        //        로그인 한 사람이 보낸 메일들 가져오기
        String senderEmail = employeeDTO.getEmail();
        log.info(senderEmail + "이 보낸 메일함 컨트롤러 동작 중");

        pageable = PageRequest.of(pageNumber, PAGE_SIZE);


        //        만약 키워드가 주워졌다면
        if(keytype!=null && keyword!=null){
            log.info("검색어 타입은 " + keytype + " 내용은 " + keyword);
//            키워드가 제목일 때
            if(keytype.equals("제목")){
                mailPage = mailService.getEmailListBySenderEmailAndMailTitle(senderEmail, keyword, pageable);
//            키워드가 발신자 이름일 때
            }else if(keytype.equals("수신자이름")){
                mailPage = mailService.getEmailListBySenderEmailAndMailReceiverName(senderEmail, keyword, pageable);
            }
            log.info(senderEmail + "의 보낸 메일 중 검색결과 목록");
            log.info(mailPage.toString());
        }else{
            mailPage = mailService.getEmailListBySenderEmail(senderEmail, pageable);
        }

        model.addAttribute("mailList", mailPage);
        return "cis/mail/send";
    }


    //    중요 메일함
    @GetMapping("/important")
    public String important(HttpSession session, Model model,
                            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");

//        로그인 한 사람이 받은 메일들 가져오기
        String receiverEmail = employeeDTO.getEmail();
        log.info(receiverEmail + "의 중요 메일함 컨트롤러 동작 중");

        pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        mailPage =
                mailService.getImportantEmailListByReceiverEmail(receiverEmail, pageable);

        log.info(receiverEmail + "의 받은 메일 목록");
        log.info(mailPage.toString());
        model.addAttribute("mailList", mailPage);
        return "cis/mail/important";
    }

    //    휴지통 메일함
    @GetMapping("/trash")
    public String trash(HttpSession session, Model model,
                        @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber) {

        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        String receiverEmail = employeeDTO.getEmail();
        log.info(receiverEmail + "의 휴지통 컨트롤러 동작 중");
        pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        mailPage =
                mailService.getTrashEmailListTrashByReceiverEmail(receiverEmail, pageable);

        log.info(receiverEmail + "의 휴디통 메일 목록");
        log.info(mailPage.toString());
        model.addAttribute("mailList", mailPage);
        return "cis/mail/trash";
    }

    //    메일 쓰기 (메일 쓰기 버튼 누를 때 writeForm으로 이동)
    @GetMapping("/write")
    public String write() {
        return "cis/mail/writeForm";
    }

    //    메일 쓰기 완료 후 받은 메일함으로 redirect
    @PostMapping("/write")
    public String writePost(@ModelAttribute MailDTO mailDTO, HttpSession session) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");

        mailDTO.setMailSender(employeeService.findByEmployeeId(employeeDTO.getEmployeeId()).getEmail());
        mailDTO.setMailSenderName(employeeService.findByEmployeeId(employeeDTO.getEmployeeId()).getEmployeeName());

//        받는 사람 이메일에 해당하는 사람의 이름
        try {
            mailDTO.setMailReceiverName(employeeService.findByEmployeeEmail((mailDTO.getMailReceiver())).getEmployeeName());
        }catch(Exception e) {
            log.info("받는 사람 이메일 설정이 잘못되었습니다.");
        }

//        참조되는 사람 이메일에 해당하는 사람의 이름
        try {
            mailDTO.setMailReferrerName(employeeService.findByEmployeeEmail((mailDTO.getMailReferrer())).getEmployeeName());

        }catch(Exception e) {
            log.info("참조되는 사람 이메일 설정이 잘못되었습니다.");
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        mailDTO.setMailSendTime(formatter.format(date));

        mailDTO.setMailStatus(0);
        mailDTO.setMailIsRead(0);

        log.info("메일 작성 : " + mailDTO.toString());

        mailService.saveOne(mailDTO);
        return "redirect:/mail/send";
    }

    //    휴지통 메일 지우기
    @PostMapping("/delete")
    public String delete(HttpSession session, @RequestParam("individualCheck") List<String> deleteMailList) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        log.info(employeeDTO.getEmployeeName() +"의 삭제 요청 이메일 리스트");
        log.info(deleteMailList.toString());

        mailService.deleteMailById(deleteMailList);
        return "redirect:/mail/trash";
    }

//    휴지통 메일 복구하기
    @PostMapping("/restore")
    public String restore(HttpSession session, @RequestParam("individualCheck") List<String> restoreMailList) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        log.info(employeeDTO.getEmployeeName() + "의 복구 요청 이메일 리스트");
        log.info(restoreMailList.toString());

        mailService.restoreMailById(restoreMailList);
        return "redirect:/mail/trash";
    }

    //    메일 상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id , Model model) {

        log.info(id + "메일 상세보기 컨트롤러 작동 중");
//        아이디로 메일 찾기
        MailDTO mailDTO = mailService.getEmailById(id);
        model.addAttribute("mailDTO",mailDTO);
        return "cis/mail/detail";
    }

//      중요메일 표시하기
    @PostMapping("/updateMailStatus")
    @ResponseBody
    public ResponseEntity<String> updateMailStatus(@RequestBody Map<String, Object> mail) {
        log.info(mail.get("id") + " " + mail.get("status"));
        if(mailService.updateMailStatus((String)mail.get("id"),(Integer) mail.get("status"))){
            return ResponseEntity.ok("메일 상태가 업데이트되었습니다.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메일을 찾을 수 없습니다.");
        }

    }
}
