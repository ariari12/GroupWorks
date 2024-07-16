package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MailRepository extends MongoRepository<Mail, Integer> {
//    받은 메일함에 사용하는 JPA
    List<Mail> findAllByMailReceiver(String receiverEmail);

//    보낸 메일함에 사용하는 JPA
    List<Mail> findAllByMailSender(String senderEmail);
}
