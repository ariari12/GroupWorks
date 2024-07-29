package kr.co.groupworks.dto.workflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.dto.workflow.vo.WorkflowListVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleStatusDTO {
    @Schema(name = "부서목록", defaultValue = "[부서01, 부서02, 부서03, ...]")
    private String response1;

    @Schema(name = "부서별 결재 발송/승인/반려 건 수", defaultValue = "{[부서별 발송 건 수 목록], [부서별 승인 건 수 목록], [부서별 반려 건 수 목록]}")
    private String response2;

    @Schema(name = "올해 연도 전체 결재 발송 건 구분", defaultValue = "[업무 건 수, 채무 건 수, 예산 건 수, 구매 건 수, 보고 건 수, 특별 건 수]")
    private String response3;

    @Schema(name = "부서 결재 완료 목록")
    private WorkflowListVO response4;

    @Schema(name = "월 별 결재 발송/승인/반려 건 수 - 요소 구분", defaultValue = "[1월, 2월, 3월, 4월, 5월, 6월, 7월, 8월, 9월, 10월, 11월, 12월]")
    private String response5Label;
    @Schema(name = "월 별 결재 발송/승인/반려 건 수", defaultValue = "{ \"발송\": [1월 건 수, ... , 12월 건 수], \"승인\": [1월 건 수, ... , 12월 건 수], \"반려\": [1월 건 수, ... , 12월 건 수] }")
    private String response5;

}
