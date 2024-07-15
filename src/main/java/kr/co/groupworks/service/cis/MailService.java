package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Mail;
import org.springframework.stereotype.Service;

public interface MailService {
    void saveOne(Mail mail);
}
