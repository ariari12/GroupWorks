package kr.co.groupworks.entity.ljm;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachment_file")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentFileEntity {
    @Id @Column(name = "af_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 1, 결재 첨부파일 pk
    // service 에서 미리 UUID 지정, 컨트롤러를 통해 view js로 전달
    private long id;
    // 2, 결재 요청 fk
    private long workFlowId;
    // 3, 첨부 파일 경로
    // 파일저장 경로 예시) 공통경로/부서/이름/UUID/파일이름
    private String savePath;
    // 4, 첨부 파일 명
    private String fileName;
}
