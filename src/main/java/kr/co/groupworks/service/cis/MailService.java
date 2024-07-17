package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;

import java.util.List;

public interface MailService {
    void saveOne(MailDTO mail);

    List<Mail> getEmailListByReceiverEmail(String receiverEmail);

    List<Mail> getEmailListBySenderEmail(String senderEmail);

    List<Mail> getImportantEmailListByReceiverEmail(String receiverEmail);

    boolean updateMailStatus(String id, Integer mailStatus);
}
