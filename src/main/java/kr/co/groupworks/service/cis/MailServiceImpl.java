package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.repository.cis.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public void saveOne(MailDTO mailDTO) {
        Mail mail = toEntity(mailDTO);
        log.info(mail.getId() + "번 메일 저장 중");
        mailRepository.save(mail);
    }

    @Override
    public List<Mail> getEmailListByReceiverEmail(String receiverEmail) {
        return mailRepository.findAllByMailReceiver(receiverEmail);
    }

    @Override
    public List<Mail> getEmailListBySenderEmail(String senderEmail) {
        return mailRepository.findAllByMailSender(senderEmail);
    }

    @Override
    public List<Mail> getImportantEmailListByReceiverEmail(String receiverEmail) {
        return mailRepository.findImportantByReceiver(receiverEmail);
    }

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
