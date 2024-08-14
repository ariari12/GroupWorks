package kr.co.groupworks.mail.entity;


import jakarta.persistence.Id;
import kr.co.groupworks.mail.dto.MailAttachmentFile;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "mail")
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Mail {

    @Id
    private String id;
    @Field(name = "mail_title")
    private String mailTitle;
    @Field(name = "mail_content")
    private String mailContent;
    @Field(name = "mail_sender_id")
    private Long mailSenderId;
    @Field(name = "mail_sender")
    private String mailSender;
    @Field(name = "mail_sender_name")
    private String mailSenderName;
    @Field(name = "mail_receiver_id")
    private Long mailReceiverId;
    @Field(name = "mail_receiver")
    private String mailReceiver;
    @Field(name = "mail_receiver_name")
    private String mailReceiverName;
    @Field(name = "mail_referrer_id")
    private Long mailReferrerId;
    @Field(name = "mail_referrer")
    private String mailReferrer;
    @Field(name = "mail_referrer_name")
    private String mailReferrerName;
    @Field(name = "mail_send_time")
    private String mailSendTime;
    @Field(name = "mail_is_read")
    private Integer mailIsRead;
//    0은 일반, 1 중요, 2 휴지통
    @Field(name = "mail_status")
    private Integer mailStatus;

//  첨부파일
    @Field(name = "mail_attachment_fileList")
    private List<MailAttachmentFile> mailAttachmentFiles;


}
