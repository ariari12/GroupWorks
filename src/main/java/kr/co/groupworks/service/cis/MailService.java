package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Mail;

import java.util.List;

public interface MailService {
    void saveOne(Mail mail);

    List<Mail> getEmailListByReceiverEmail(String receiverEmail);

    List<Mail> getEmailListBySenderEmail(String senderEmail);
}
