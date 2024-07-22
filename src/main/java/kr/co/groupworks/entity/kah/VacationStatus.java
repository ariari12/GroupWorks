package kr.co.groupworks.entity.kah;

import lombok.Getter;

@Getter
public enum VacationStatus {
    PENDING("\uD83D\uDD0D 검토 중"),
    APPROVED("✅ 승인"),
    REJECTED("❌ 반려");

    private final String description;

    VacationStatus(String description) {
        this.description = description;
    }

}
