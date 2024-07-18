package kr.co.groupworks.dto.ljm;

import lombok.Getter;

@Getter
public enum WorkFlowType {
    WORK_APPROVAL("업무 결재"),
    FINANCE_APPROVAL("재무 결재"),
    BUDGET_APPROVAL("예산 결재"),
    PURCHASE_APPROVAL("구매 결재"),
    REPORT_APPROVAL("보고 결재"),
    SPECIAL_APPROVAL("특별 결재")
    ;
    private final String value;

    private WorkFlowType(String value) {
        this.value = value;
    }

    public static String getMatch(int value) {
        return WorkFlowType.values()[value].getValue();
    }

}
