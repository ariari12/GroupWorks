package kr.co.groupworks.dto.workFlow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorkFlowDTO {
    // 1, 결재 요청 pk
    private String id;
    // 2, 기안자 사원번호 fk
    private int employeeId;
    // 3, 기안자 이메일
    private String email;
    // 4, 기안자 직급
    private String employeeRank;
    // 5, 기안자 소속명
    private String department;
    // 6, 기안자 명
    private String employeeName;
    // 7, 결재 요청 코드
    private String code;
    // 8, 결재 요청 분류
    private String workFlowType;
    // 9, 결재 명
    private String title;
    // 10, 결재 내용
    private String description;
    // 11, 결재 요청 일
    private LocalDateTime draftDate;
    // 12, 결재 인원
    private int approverCount;
    // 13, 승인된 수
    private int approvalCount;
    // 14, 비용 청구
    private int cost;
}
