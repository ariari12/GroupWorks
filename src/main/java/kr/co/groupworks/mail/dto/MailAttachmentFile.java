package kr.co.groupworks.mail.dto;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class MailAttachmentFile {
    private String fileName;
    private String filePath;
}
