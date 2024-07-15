package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailRepository extends MongoRepository<Mail, Integer> {
}
