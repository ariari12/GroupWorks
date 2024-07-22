package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.mail.MailDTO;
import kr.co.groupworks.entity.cis.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MailService {
    void saveOne(MailDTO mail);

    Page<Mail> getEmailListByReceiverEmail(String receiverEmail, Pageable pageNumber);

    Page<Mail> getEmailListBySenderEmail(String senderEmail, Pageable pageable);

    Page<Mail> getImportantEmailListByReceiverEmail(String receiverEmail, Pageable pageable);

    boolean updateMailStatus(String id, Integer mailStatus);

    Page<Mail> getTrashEmailListTrashByReceiverEmail(String receiverEmail, Pageable pageable);

    MailDTO getEmailById(String id);

    Page<Mail> getEmailListByReceiverEmailAndMailTitle(String receiverEmail, String mailTitle, Pageable pageNumber);

    Page<Mail> getEmailListByReceiverEmailAndMailSenderName(String receiverEmail, String mailSenderName, Pageable pageNumber);

    Page<Mail> getEmailListBySenderEmailAndMailTitle(String senderEmail, String mailTitle, Pageable pageable);

    Page<Mail> getEmailListBySenderEmailAndMailReceiverName(String senderEmail, String mailRecieverName, Pageable pageable);

    void deleteMailById(List<String> deleteMailList);
}
