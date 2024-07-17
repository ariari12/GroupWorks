package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MailRepository extends MongoRepository<Mail, String> {

//    mail_status = 0 : Default | 1 : 중요 | 2 : 휴지통
    
//    받은 메일함에 사용하는 JPA
    @Query("{mail_receiver: ?0,'mail_status':{ $ne: 2 } }")
    List<Mail> findAllByMailReceiver(String receiverEmail);

//    받은 메일함에서 제목으로 검색하는 JPA
    @Query("{mail_receiver: ?0,'mail_title': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2} }")
    List<Mail> findAllByMailReceiverAndMailTitle(String receiverEmail, String mailTitle);

//    받은 메일함에서 보낸 사람으로 검색하는 JPA
    @Query("{mail_receiver: ?0,'mail_sendername': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2} }")
    List<Mail> findAllByMailReceiverAndMailSenderName(String receiverEmail, String mailSenderName);

//  ==================================================================================================

//    보낸 메일함에 사용하는 JPA
    @Query("{mail_sender: ?0,'mail_status':{ $ne: 2 } }")
    List<Mail> findAllByMailSender(String senderEmail);

//    보낸 메일함에서 제목으로 검색하는 JPA
    @Query("{mail_sender: ?0,'mail_title': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}")
    List<Mail> findAllByMailSenderAndMailTitle(String senderEmail, String mailTitle);

//    보낸 메일함에서 받은 사람으로 검색하는 JPA
//    $regex는 2번째 파라미터를 정확하게 일치하는 것 뿐 아니라 포함하는지를 비교함, $options 'i'는 대소문자 구분 안하겠다는 것
    @Query("{mail_sender: ?0,'mail_receivername': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}")
    List<Mail> findAllByMailSenderAndMailReceiverName(String senderEmail, String mailRecieverName);

//  ==================================================================================================

//    중요 메일함에 사용하는 JPA
    @Query("{ 'mail_receiver': ?0, 'mail_status': 1 }")
    List<Mail> findImportantByReceiver(String receiverEmail);

//    휴지통 메일함에 사용하는 JPA
    @Query("{'mail_receiver' : ?0, 'mail_status' : 2}")
    List<Mail> findTrashByReceiver(String receiverEmail);


}
