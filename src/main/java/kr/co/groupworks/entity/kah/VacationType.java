package kr.co.groupworks.entity.kah;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VacationType {
    @Id
    @GeneratedValue
    private Long vacationTypeId;
    //휴가 이름
    private String name;
    //최대휴가일
    private int maxLeave;
    //휴가에 대한 정보
    private String information;
}
