package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.repository.cis.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<Mail> getTrashEmailListTrashByReceiverEmail(String receiverEmail) {
        return mailRepository.findTrashByReceiver(receiverEmail);
    }

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

//    받은 메일함 제목으로 찾기
    @Override
    public List<Mail> getEmailListByReceiverEmailAndMailTitle(String receiverEmail, String mailTitle) {
        return mailRepository.findAllByMailReceiverAndMailTitle(receiverEmail,mailTitle);
    }
//    받은 메일함 보낸사람으로 찾기
    @Override
    public List<Mail> getEmailListByReceiverEmailAndMailSenderName(String receiverEmail, String mailSenderName) {
        return mailRepository.findAllByMailReceiverAndMailSenderName(receiverEmail,mailSenderName);
    }

    @Override
    public List<Mail> getEmailListBySenderEmailAndMailTitle(String senderEmail, String mailTitle) {
        return mailRepository.findAllByMailSenderAndMailTitle(senderEmail,mailTitle);
    }

    @Override
    public List<Mail> getEmailListBySenderEmailAndMailReceiverName(String senderEmail, String mailRecieverName) {
        return mailRepository.findAllByMailSenderAndMailReceiverName(senderEmail,mailRecieverName);
    }

    @Override
    public void deleteById(List<String> mailIdList) {
        for (String mailId : mailIdList) {
            mailRepository.deleteById(mailId);
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
