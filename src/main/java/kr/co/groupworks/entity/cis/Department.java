package kr.co.groupworks.entity.cis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
@Table(name = "department")
public class Department {
    // 1, 부서 pk
    @Id
    @Column(name = "department_id", nullable = false, unique = true)
    private long departmentId;
    // 2, 부서 명
    @Column(name = "department_name", nullable = false)
    private String departmentName;
    // 3, 부서 공용 연락처
    @Column(name = "contact_number",nullable = false)
    private String contactNumber;
    // 4, 부서 지역
    @Column(name = "location", nullable = false)
    private String location;
}