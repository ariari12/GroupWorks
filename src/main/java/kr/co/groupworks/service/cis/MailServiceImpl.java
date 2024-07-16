package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.repository.cis.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MailServiceImpl implements MailService{
    private final MailRepository mailRepository;

    @Override
    public void saveOne(MailDTO mailDTO) {
        Mail mail = toEntity(mailDTO);
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

    public Mail toEntity(MailDTO mailDTO) {
        return Mail.builder()
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
}
