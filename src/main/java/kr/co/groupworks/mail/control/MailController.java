package kr.co.groupworks.mail.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.employee.service.EmployeeService;
import kr.co.groupworks.mail.dto.MailDTO;
import kr.co.groupworks.mail.entity.Mail;
import kr.co.groupworks.mail.service.MailService;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Hidden
@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;
    private final NotificationService notificationService;

    private final int PAGE_SIZE = 10;

    Page<Mail> mailPage = null;
    Pageable pageable = null;

    //    받은 메일함(직접 받을때와 참조될 때 모두 포함)
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
        return "mail/receive";
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
        return "mail/send";
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
        model.addAttribute("employee",employeeDTO);
        return "mail/important";
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
        return "mail/trash";
    }

    //    메일 쓰기 (메일 쓰기 버튼 누를 때 writeForm으로 이동)
    @GetMapping("/write")
    public String write(@RequestParam(required = false,name = "email")String sendEmail, Model model) {
        log.info(sendEmail + " email send request");
        model.addAttribute("sendEmail", sendEmail);
        return "mail/writeForm";
    }

    //    메일 쓰기 완료 후 받은 메일함으로 redirect
    @PostMapping("/write")
    public String writePost(@ModelAttribute MailDTO mailDTO, MultipartHttpServletRequest request, HttpSession session) {
        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");

        mailDTO.setMailSenderId(employeeDTO.getEmployeeId());
        mailDTO.setMailSender(employeeService.findByEmployeeId(employeeDTO.getEmployeeId()).getEmail());
        mailDTO.setMailSenderName(employeeDTO.getEmployeeName());

//        받는 사람 이메일에 해당하는 사람의 이름과 employeeId
        EmployeeDTO receiveEmployeeDTO = employeeService.toEmployeeDTO(employeeService.findByEmployeeEmail(mailDTO.getMailReceiver()));
        try {
            mailDTO.setMailReceiverId(receiveEmployeeDTO.getEmployeeId());
            mailDTO.setMailReceiverName(receiveEmployeeDTO.getEmployeeName());
            log.info("mailDTO.getMailReceiverId() : " + mailDTO.getMailReceiverId());

        }catch(Exception e) {
            e.printStackTrace();
            log.info("받는 사람 이메일 설정이 잘못되었습니다.");
        }


        //        참조되는 사람 이메일에 해당하는 사람의 이름
        try {
            EmployeeDTO referrerEmployeeDTO = employeeService.toEmployeeDTO(employeeService.findByEmployeeEmail(mailDTO.getMailReferrer()));
            mailDTO.setMailReferrerId(referrerEmployeeDTO.getEmployeeId());
            mailDTO.setMailReferrerName(referrerEmployeeDTO.getEmployeeName());

        }catch(Exception e) {
            log.info("참조되는 사람 이메일 설정이 잘못되었습니다.");
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        mailDTO.setMailSendTime(formatter.format(date));

        mailDTO.setMailStatus(0);
        mailDTO.setMailIsRead(0);

        List<MultipartFile> files = request.getFiles("files");
        mailService.saveOne(mailDTO,files);

        log.info("메일 작성 : " + mailDTO.toString());

        sendNotification(mailDTO.getMailReceiverId(),mailDTO.getId());
        if(mailDTO.getMailReferrerId() != null){
            sendNotification(mailDTO.getMailReferrerId(),mailDTO.getId());
        }

        return "redirect:/mail/send";
    }

    public void sendNotification(Long mailReceiverId, String mailId) {

        Notification notification = Notification.builder()
                .receiverId(mailReceiverId)
                .title("메일이 도착했습니다.")
                .contents("확인하지 않은 메일이 있습니다.")
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .url("/mail/detail/" + mailId)
                .build();

        log.info(">>>>>>>>>>>>>>>>>>> notification: {} " , notification.toString());
        notificationService.sendNotificationOne(notification);
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
        return "mail/detail";
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

//    받는사람, 참조하는 이메일 쿼리로 찾기
    @GetMapping("/email-suggestions")
    @ResponseBody
    public ResponseEntity<List<String>> getEmailSuggestions(@RequestParam("query") String query) {
        List<String> suggestions = employeeService.getEmailsByQuery(query);
        return ResponseEntity.ok(suggestions);
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

//    첨부파일 다운로드
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename, @RequestParam("mailId") String mailId) throws UnsupportedEncodingException {

        String uploadDir2 = uploadDir;
        uploadDir2 +=  "/mail-files/" + mailId;
        log.info("uploadDir : " + uploadDir2 );
        log.info("mailId : " + mailId);

        File file = new File(uploadDir2 +"/"+filename);
        // 파일이 존재하지 않는다면
        if(!file.exists()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // 여기까지 오면 파일이 존재한다.
        Resource resource = new FileSystemResource(file);
        // 파일의 인코딩
        String encodingFileName =
                URLEncoder.encode(filename, "UTF-8").replaceAll("\\+","%20");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + encodingFileName + "\"");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        System.out.println("resouce: " + resource);

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
