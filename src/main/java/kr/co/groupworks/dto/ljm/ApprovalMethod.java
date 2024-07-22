package kr.co.groupworks.dto.ljm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ApprovalMethod {
    PRE_APPROVAL("선결"),
    POST_APPROVAL("후결"),
    JOINT_APPROVAL("대결"),
    DELEGATED_APPROVAL("전결"),
    REJECTION("반려")
    ;

    private final String method;

    ApprovalMethod(String method) {
        this.method = method;
    }

    public static String getMatch(int value) {
        return ApprovalMethod.values()[value].getMethod();
    }

    public List<String> getMethodList() {
        List<String> list = new ArrayList<String>();
        for (ApprovalMethod method : ApprovalMethod.values()) {
            list.add(method.getMethod());
        }
        return list;
    }
}