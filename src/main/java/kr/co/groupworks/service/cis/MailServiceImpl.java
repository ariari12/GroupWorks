package kr.co.groupworks.service.cis;

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
    public void saveOne(Mail mail) {
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
}
