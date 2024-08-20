package kr.co.groupworks.mail.repository;

import kr.co.groupworks.mail.entity.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MailRepository extends MongoRepository<Mail, String> {

//    mail_status = 0 : Default | 1 : 중요 | 2 : 휴지통

    // 받은 메일함에 사용하는 JPA
    @Query(value = "{ $or: [{mail_receiver: ?0}, {mail_referrer: ?0}], 'mail_status': { $ne: 2 } }", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailReceiverOrMailReferrer(String receiverEmail, Pageable pageable);

    // 받은 메일함에서 제목으로 검색하는 JPA
    @Query(value = "{ $or: [{mail_receiver: ?0}, {mail_referrer: ?0}], 'mail_title': { $regex: ?1, $options: 'i' }, 'mail_status': { $ne: 2 } }", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailReceiverOrMailReferrerAndMailTitle(String receiverEmail, String mailTitle, Pageable pageable);

    // 받은 메일함에서 보낸 사람으로 검색하는 JPA
    @Query(value = "{ $or: [{mail_receiver: ?0}, {mail_referrer: ?0}], 'mail_sendername': { $regex: ?1, $options: 'i' }, 'mail_status': { $ne: 2 } }", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailReceiverOrMailReferrerAndMailSenderName(String receiverEmail, String mailSenderName, Pageable pageable);
//  ==================================================================================================

//    보낸 메일함에 사용하는 JPA
    @Query(value = "{mail_sender: ?0,'mail_status':{ $ne: 2 } }", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailSender(String senderEmail, Pageable pageable);

//    보낸 메일함에서 제목으로 검색하는 JPA
    @Query(value = "{mail_sender: ?0,'mail_title': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailSenderAndMailTitle(String senderEmail, String mailTitle, Pageable pageable);

//    보낸 메일함에서 받은 사람으로 검색하는 JPA
//    $regex는 2번째 파라미터를 정확하게 일치하는 것 뿐 아니라 포함하는지를 비교함, $options 'i'는 대소문자 구분 안하겠다는 것
    @Query(value = "{mail_sender: ?0,'mail_receivername': {$regex: ?1, $options: 'i'},'mail_status': {$ne: 2}}", sort = "{'mail_send_time' : -1}")
    Page<Mail> findAllByMailSenderAndMailReceiverName(String senderEmail, String mailRecieverName, Pageable pageable);

//  ==================================================================================================

//    중요 메일함에 사용하는 JPA
    @Query(value = "{  '$or': [ {'mail_receiver': ?0}, {'mail_sender': ?0} ], 'mail_status': 1 }", sort = "{'mail_send_time' : -1}")
    Page<Mail> findImportantByReceiver(String receiverEmail,  Pageable pageable);

//    휴지통 메일함에 사용하는 JPA
    @Query(value = "{'mail_receiver' : ?0, 'mail_status' : 2}", sort = "{'mail_send_time' : -1}")
    Page<Mail> findTrashByReceiver(String receiverEmail,  Pageable pageable);

//    보낸 혹은 받은 메일을 최신순으로 정렬하는 JPA
    @Query(value = "{ $or: [ {'mail_receiver': ?0}, {'mail_sender': ?0} ], 'mail_status': { $ne: 2 } }", sort = "{'mail_send_time' : -1}")
    List<Mail> findLatestMails(String email, Pageable pageable);


}
