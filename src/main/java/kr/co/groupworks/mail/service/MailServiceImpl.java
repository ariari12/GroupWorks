package kr.co.groupworks.mail.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.mail.dto.MailAttachmentFile;
import kr.co.groupworks.mail.dto.MailDTO;
import kr.co.groupworks.mail.entity.Mail;
import kr.co.groupworks.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MailServiceImpl implements MailService{
    private final MailRepository mailRepository;
    private static final List<String> VALID_FILE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "pdf","zip","txt","csv","json","xml","docx","hwp","xls");

    @Value("${file.upload-dir}")
    private String uploadDir;
//    메일 저장
    @Override
    public void saveOne(MailDTO mailDTO, List<MultipartFile> files) {

        Mail mail = mailDTO.toEntity();
        mailRepository.save(mail);
        mailDTO.setId(mail.getId());
        String fileUploadDir = uploadDir + "/mail-files/" + mail.getId();

        List<MailAttachmentFile> mailAttachmentFiles = new ArrayList<>();
        File uploadDirFile = new File(fileUploadDir);
        if (!uploadDirFile.exists() && uploadDirFile.mkdirs()) {
            System.out.println("Directory created: " + fileUploadDir); // Log the directory creation
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 파일 저장 로직 (파일 저장 경로와 파일 이름 설정)

                    String fileName = file.getOriginalFilename();
                    String fileExtension = getFileExtension(fileName);

                    if (!VALID_FILE_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                        System.out.println("Invalid file type: " + fileName);
                        continue; // 유효하지 않은 파일 형식은 무시
                    }
                    String filePath = fileUploadDir + File.separator + fileName;
                    file.transferTo(new File(filePath));

                    // MailAttachmentFile 객체 생성
                    MailAttachmentFile attachmentFile = new MailAttachmentFile();
                    attachmentFile.setFileName(fileName);
                    attachmentFile.setFilePath(filePath);
                    // 리스트에 추가
                    mailAttachmentFiles.add(attachmentFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        mailDTO.setMailAttachmentFiles(mailAttachmentFiles);

        mail = mailDTO.toEntity();
        mailRepository.save(mail);

    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

//    수신 메일 목록 자겨오기
    @Override
    public Page<Mail> getEmailListByReceiverEmail(String receiverEmail, Pageable pageable) {
        return mailRepository.findAllByMailReceiverOrMailReferrer(receiverEmail, pageable);
    }
//    수신 메일 제목으로 찾기
    @Override
    public Page<Mail> getEmailListByReceiverEmailAndMailTitle(String receiverEmail, String mailTitle, Pageable pageable) {
        return mailRepository.findAllByMailReceiverOrMailReferrerAndMailTitle(receiverEmail, mailTitle, pageable);
    }
//    수신 메일 보낸사람으로 찾기
    @Override
    public Page<Mail> getEmailListByReceiverEmailAndMailSenderName(String receiverEmail, String mailSenderName, Pageable pageable) {
        return mailRepository.findAllByMailReceiverOrMailReferrerAndMailSenderName(receiverEmail, mailSenderName, pageable);
    }

//    ========================================================================================================

//    발신 메일 목록 가져오기
    @Override
    public Page<Mail> getEmailListBySenderEmail(String senderEmail, Pageable pageable) {
        return mailRepository.findAllByMailSender(senderEmail, pageable);
    }
//    발신 메일 제목으로 찾기
    @Override
    public Page<Mail> getEmailListBySenderEmailAndMailTitle(String senderEmail, String mailTitle, Pageable pageable) {
        return mailRepository.findAllByMailSenderAndMailTitle(senderEmail, mailTitle, pageable);
    }
    //    발신 메일 받은사람으로 찾기
    @Override
    public Page<Mail> getEmailListBySenderEmailAndMailReceiverName(String senderEmail, String mailRecieverName, Pageable pageable) {
        return mailRepository.findAllByMailSenderAndMailReceiverName(senderEmail, mailRecieverName, pageable);
    }

//    ========================================================================================================

//    중요 메일 목록 가져오기
    @Override
    public Page<Mail> getImportantEmailListByReceiverEmail(String receiverEmail, Pageable pageable) {
        return mailRepository.findImportantByReceiver(receiverEmail, pageable);
    }

//    메일 상태 변경
    @Override
    public boolean updateMailStatus(String id, Integer mailStatus) {
        Optional<Mail> optionalMail = mailRepository.findById(id);
//        존재하면
        if (optionalMail.isPresent()) {

            MailDTO mailDTO = optionalMail.get().toDTO();
            mailDTO.setMailStatus(mailStatus);

            Mail mail = mailDTO.toEntity();

            mailRepository.save(mail);
            return true;
        } else {
            return false;
        }
    }

//    휴지통 메일 목록
    @Override
    public Page<Mail> getTrashEmailListTrashByReceiverEmail(String receiverEmail, Pageable pageable) {
        return mailRepository.findTrashByReceiver(receiverEmail, pageable);
    }

//    메일 상세보기
    @Override
    public MailDTO getEmailById(String id) {
        Mail mail = mailRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("메일을 찾을 수 없습니다. "));

        MailDTO mailDTO = mail.toDTO();

        //  만약 isRead가 0이면 1로 변경한 후 document에 저장
        if(mailDTO.getMailIsRead() == 0){
            mailDTO.setMailIsRead(1);
            mail = mailDTO.toEntity();
            mailRepository.save(mail);
        }
        return mailDTO;
    }

//     메일 삭제하기
    @Override
    public void deleteMailById(List<String> deleteMailList) {
        for(String mailId : deleteMailList){
            mailRepository.deleteById(mailId);
        }
    }

//    메일 복구하기
    @Override
    public void restoreMailById(List<String> restoreMailList) {
        for(String mailId : restoreMailList){
            Mail mail = mailRepository.findById(mailId).
                    orElseThrow(() -> new EntityNotFoundException("복구 메일을 찾을 수 없습니다. " + mailId));
            MailDTO mailDTO = mail.toDTO();
            mailDTO.setMailStatus(0);
            mail = mailDTO.toEntity();
            mailRepository.save(mail);
        }
    }

//
    @Override
    public List<Mail> latestMails(String email) {
        List<Mail> latestMails = mailRepository.findLatestMails(email,PageRequest.of(0, 5));
        return latestMails;
    }

}
