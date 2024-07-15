package kr.co.groupworks.dto.kah.select;

import java.util.ArrayList;
import java.util.List;

public class VacationMyHistoryDTO {
    //이름 잔여연차 잔여병가	기타 사용일
    //기간 종류 사용기간	증명자료 승인
    // 이름
    private String name;
    // 잔여 연차
    private String remainingAnnualLeave;
    // 기타 사용일
    private String otherLeaveDays;
    // 휴가 종류
    private String type;
    // 첨부파일 경로
    private String filePath;
    // 첨부파일이름
    private String fileName;
    // 승인 상태
    private String status;
}
