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
    @Field(name = "mail_sender")
    private String mailSender;
    @Field(name = "mail_sendername")
    private String mailSenderName;
    @Field(name = "mail_receiver")
    private String mailReceiver;
    @Field(name = "mail_receivername")
    private String mailReceiverName;
    @Field(name = "mail_referrer")
    private String mailReferrer;
    @Field(name = "mail_referrername")
    private String mailReferrerName;
    @Field(name = "mail_sendtime")
    private String mailSendTime;
    @Field(name = "mail_isread")
    private Integer mailIsRead;
//    0은 일반, 1 중요, 2 휴지통
    @Field(name = "mail_status")
    private Integer mailStatus;

//  첨부파일
    @Field(name = "mail_attachmentfileList")
    private List<MailAttachmentFile> mailAttachmentFiles;


}