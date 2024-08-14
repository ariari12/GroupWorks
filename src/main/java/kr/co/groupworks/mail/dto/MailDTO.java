package kr.co.groupworks.mail.dto;

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
}
