package kr.co.groupworks.dto.cis.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String mailTitle;
    private String mailContent;
    private String mailSender;
    private String mailSenderName;
    private String mailReceiver;
    private String mailReceiverName;
    private String mailReferrer;
    private String mailReferrerName;
    private String mailSendTime;
    private Integer mailIsRead;
    private Integer mailStatus;
}
