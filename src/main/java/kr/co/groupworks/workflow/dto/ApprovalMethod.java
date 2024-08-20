package kr.co.groupworks.workflow.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public enum ApprovalMethod {
    PRE_APPROVAL("선결"),
    POST_APPROVAL("후결"),
    DELEGATED_APPROVAL("대결"),
    FULL_APPROVAL("전결"),
    REJECTION("반려")
    ;

    private final String method;

    ApprovalMethod(String method) {
        this.method = method;
    }

    public static String getMatch(int value) {
        if(value == 0) return null;
        log.info("ApprovalMethod value: {}", value);
        log.info("ApprovalMethod getMatch value: {}", ApprovalMethod.values()[value -1].getMethod());
        return ApprovalMethod.values()[value -1].getMethod();
    }

    public List<String> getMethodList() {
        List<String> list = new ArrayList<>();
        for (ApprovalMethod method : ApprovalMethod.values()) {
            list.add(method.getMethod());
        }
        return list;
    }
}
