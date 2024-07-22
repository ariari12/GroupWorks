package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MailRepository extends MongoRepository<Mail, String> {

//    mail_status = 0 : Default | 1 : 중요 | 2 : 휴지통
    
//    받은 메일함에 사용하는 JPA
    @Query("{mail_receiver: ?0,'mail_status':{ $ne: 2 } }")
    Page<Mail> findAllByMailReceiver(String receiverEmail, Pageable pageable);

//    받은 메일함에서 제목으로 검색하는 JPA
    @Query("{mail_receiver: ?0,'mail_title': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2} }")
    Page<Mail> findAllByMailReceiverAndMailTitle(String receiverEmail, String mailTitle, Pageable pageable);

//    받은 메일함에서 보낸 사람으로 검색하는 JPA
    @Query("{mail_receiver: ?0,'mail_sendername': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2} }")
    Page<Mail> findAllByMailReceiverAndMailSenderName(String receiverEmail, String mailSenderName, Pageable pageable);

//  ==================================================================================================

//    보낸 메일함에 사용하는 JPA
    @Query("{mail_sender: ?0,'mail_status':{ $ne: 2 } }")
    Page<Mail> findAllByMailSender(String senderEmail, Pageable pageable);

//    보낸 메일함에서 제목으로 검색하는 JPA
    @Query("{mail_sender: ?0,'mail_title': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}")
    Page<Mail> findAllByMailSenderAndMailTitle(String senderEmail, String mailTitle, Pageable pageable);

//    보낸 메일함에서 받은 사람으로 검색하는 JPA
//    $regex는 2번째 파라미터를 정확하게 일치하는 것 뿐 아니라 포함하는지를 비교함, $options 'i'는 대소문자 구분 안하겠다는 것
    @Query("{mail_sender: ?0,'mail_receivername': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}")
    Page<Mail> findAllByMailSenderAndMailReceiverName(String senderEmail, String mailRecieverName, Pageable pageable);

//  ==================================================================================================

//    중요 메일함에 사용하는 JPA
    @Query("{ 'mail_receiver': ?0, 'mail_status': 1 }")
    Page<Mail> findImportantByReceiver(String receiverEmail,  Pageable pageable);

//    휴지통 메일함에 사용하는 JPA
    @Query("{'mail_receiver' : ?0, 'mail_status' : 2}")
    Page<Mail> findTrashByReceiver(String receiverEmail,  Pageable pageable);


}
