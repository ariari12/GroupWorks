package kr.co.groupworks.entity.cis;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class MailAttachmentFile {
    private String fileName;
    private String filePath;
}
