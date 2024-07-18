package kr.co.groupworks.entity.kah;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vacation extends Calendar{
    // 클라이언트가 올린 파일이름
    private String fileName;
    // 실제 저장 파일 경로
    private String filePath;

    // 승인하는 사람
    private String approver;
    // 승인 상태
    @Enumerated(EnumType.STRING)
    private VacationStatus status;
    //휴가 종류
    @Enumerated(EnumType.STRING)
    private VacationType vacationType;

}
