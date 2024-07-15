package kr.co.groupworks.dto.ljm.vo;

import kr.co.groupworks.entity.ljm.AttachmentFileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentFileVO {
    // 1, 결재 첨부파일 pk
    private int id;
    // 2, 결재 요청 fk
    private int workFlowId;
    // 3, 첨부 파일 경로
    // 파일저장 경로 예시) 공통경로/부서/이름/UUID/파일이름
    private String savePath;
    // 4, 첨부 파일 명
    private String fileName;

    public AttachmentFileVO(AttachmentFileEntity attachmentFileEntity) {
        this
            .setId(id)
            .setWorkFlowId(workFlowId)
            .setSavePath(savePath)
            .setFileName(fileName)
        ;
    }
}
