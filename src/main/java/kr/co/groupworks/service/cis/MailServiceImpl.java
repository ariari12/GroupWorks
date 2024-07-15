package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Mail;
import kr.co.groupworks.repository.cis.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final MailRepository mailRepository;

    @Override
    public void saveOne(Mail mail) {
        mailRepository.save(mail);
    }
}
