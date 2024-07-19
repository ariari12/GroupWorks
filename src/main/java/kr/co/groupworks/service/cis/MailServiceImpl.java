package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.repository.cis.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MailServiceImpl implements MailService{
    private final MailRepository mailRepository;
    private final int PAGE_SIZE = 10;

//    메일 저장
    @Override
    public void saveOne(MailDTO mailDTO) {
        Mail mail = toEntity(mailDTO);
        log.info(mail.getId() + "번 메일 저장 중");
        mailRepository.save(mail);
    }

//    수신 메일 목록 자겨오기
    @Override
    public Page<Mail> getEmailListByReceiverEmail(String receiverEmail, Pageable pageable) {
        return mailRepository.findAllByMailReceiver(receiverEmail, pageable);
    }
//    수신 메일 제목으로 찾기
    @Override
    public Page<Mail> getEmailListByReceiverEmailAndMailTitle(String receiverEmail, String mailTitle, Pageable pageable) {
        return mailRepository.findAllByMailReceiverAndMailTitle(receiverEmail, mailTitle, pageable);
    }
//    수신 메일 보낸사람으로 찾기
    @Override
    public Page<Mail> getEmailListByReceiverEmailAndMailSenderName(String receiverEmail, String mailSenderName, Pageable pageable) {
        return mailRepository.findAllByMailReceiverAndMailSenderName(receiverEmail, mailSenderName, pageable);
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

//    중요 메일 및 휴지통 상태 변경
    @Override
    public boolean updateMailStatus(String id, Integer mailStatus) {
        Optional<Mail> optionalMail = mailRepository.findById(id);
//        존재하면
        if (optionalMail.isPresent()) {

            MailDTO mailDTO = toDTO(optionalMail.get());
            mailDTO.setMailStatus(mailStatus);

            Mail mail = toEntity(mailDTO);

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
        Mail mail = mailRepository.findById(id).get();

        MailDTO mailDTO = toDTO(mail);

        //  만약 isRead가 0이면 1로 변경한 후 document에 저장
        if(mailDTO.getMailIsRead() == 0){
            mailDTO.setMailIsRead(1);
            mail = toEntity(mailDTO);
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

//    ========================================================================================================

//    dto to entity
    public Mail toEntity(MailDTO mailDTO) {
        return Mail.builder()
                .id(mailDTO.getId())
                .mailTitle(mailDTO.getMailTitle())
                .mailContent(mailDTO.getMailContent())
                .mailSender(mailDTO.getMailSender())
                .mailSenderName(mailDTO.getMailSenderName())
                .mailReceiver(mailDTO.getMailReceiver())
                .mailReceiverName(mailDTO.getMailReceiverName())
                .mailReferrer(mailDTO.getMailReferrer())
                .mailReferrerName(mailDTO.getMailReferrerName())
                .mailSendTime(mailDTO.getMailSendTime())
                .mailIsRead(mailDTO.getMailIsRead())
                .mailStatus(mailDTO.getMailStatus())
                .build();
    }

//    entity to dto
    public MailDTO toDTO(Mail mail) {
        return MailDTO.builder()
                .id(mail.getId())
                .mailTitle(mail.getMailTitle())
                .mailContent(mail.getMailContent())
                .mailSender(mail.getMailSender())
                .mailSenderName(mail.getMailSenderName())
                .mailReceiver(mail.getMailReceiver())
                .mailReceiverName(mail.getMailReceiverName())
                .mailReferrer(mail.getMailReferrer())
                .mailReferrerName(mail.getMailReferrerName())
                .mailSendTime(mail.getMailSendTime())
                .mailIsRead(mail.getMailIsRead())
                .mailStatus(mail.getMailStatus())
                .build();
    }
}
