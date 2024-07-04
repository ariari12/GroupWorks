package kr.co.groupworks.dto.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttachmentDTO {
    // 1, 결재 첨부파일 pk
    private int id;
    // 2, 결재 요청 fk
    private int workFlowId;
    // 3, 첨부 파일 경로
    private String savePath;
    // 4, 첨부 파일 명
    private String fileName;
}
