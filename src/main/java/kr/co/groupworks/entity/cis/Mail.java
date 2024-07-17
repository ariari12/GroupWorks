package kr.co.groupworks.entity.cis;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mail")
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Mail {
    @Id
    private Integer mailId;

    private String mailTitle;
    private String mailContent;
    private String mailSender;
    private String mailSenderName;
    private String mailReceiver;
    private String mailReceiverName;
    private String mailReferrer;
    private String mailReferrerName;
    private String mailSubject;
    private String mailBody;
//    private List mailAttachment;


}
