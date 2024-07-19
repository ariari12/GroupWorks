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

    List<Mail> getTrashEmailListTrashByReceiverEmail(String receiverEmail);

    MailDTO getEmailById(String id);

    List<Mail> getEmailListByReceiverEmailAndMailTitle(String receiverEmail, String mailTitle);

    List<Mail> getEmailListByReceiverEmailAndMailSenderName(String receiverEmail, String mailSenderName);

    List<Mail> getEmailListBySenderEmailAndMailTitle(String senderEmail, String mailTitle);

    List<Mail> getEmailListBySenderEmailAndMailReceiverName(String senderEmail, String mailRecieverName);

    void deleteMailById(List<String> deleteMailList);
}
