package kr.co.groupworks.mail.dto;

import kr.co.groupworks.mail.entity.Mail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String id;
    private String mailTitle;
    private String mailContent;
    private Long mailSenderId;
    private String mailSender;
    private String mailSenderName;
    private Long mailReceiverId;
    private String mailReceiver;
    private String mailReceiverName;
    private Long mailReferrerId;
    private String mailReferrer;
    private String mailReferrerName;
    private String mailSendTime;
    private Integer mailIsRead;
    private Integer mailStatus;
    private List<MailAttachmentFile> mailAttachmentFiles;

    //    dto to entity
    public Mail toEntity() {
        return Mail.builder()
                .id(this.getId())
                .mailTitle(this.getMailTitle())
                .mailContent(this.getMailContent())
                .mailSenderId(this.getMailSenderId())
                .mailSender(this.getMailSender())
                .mailSenderName(this.getMailSenderName())
                .mailReceiverId(this.getMailReceiverId())
                .mailReceiver(this.getMailReceiver())
                .mailReceiverName(this.getMailReceiverName())
                .mailReferrer(this.getMailReferrer())
                .mailReferrerName(this.getMailReferrerName())
                .mailSendTime(this.getMailSendTime())
                .mailIsRead(this.getMailIsRead())
                .mailStatus(this.getMailStatus())
                .mailAttachmentFiles(this.getMailAttachmentFiles())
                .build();
    }
}
