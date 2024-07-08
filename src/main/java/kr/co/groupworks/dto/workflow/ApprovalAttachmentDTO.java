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
    // service 에서 미리 UUID 지정, 컨트롤러를 통해 view js로 전달
    private int id;
    // 2, 결재 요청 fk
    private int workFlowId;
    // 3, 첨부 파일 경로
    // 파일저장 경로 예시) 공통경로/부서/이름/UUID/파일이름
    private String savePath;
    // 4, 첨부 파일 명
    private String fileName;
}
