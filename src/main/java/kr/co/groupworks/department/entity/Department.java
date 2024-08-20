package kr.co.groupworks.department.entity;

import jakarta.persistence.*;
import kr.co.groupworks.department.dto.DepartmentDTO;
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
    private Long departmentId;
    // 2, 부서 명
    @Column(name = "department_name", nullable = false)
    private String departmentName;
    // 3, 부서 공용 연락처
    @Column(name = "contact_number",nullable = false)
    private String contactNumber;
    // 4, 부서 지역
    @Column(name = "location", nullable = false)
    private String location;

    //  Entity to Dto
    public DepartmentDTO toDTO() {
        return DepartmentDTO.builder()
                .departmentId(this.getDepartmentId())
                .departmentName(this.getDepartmentName())
                .contactNumber(this.getContactNumber())
                .location(this.getLocation())
                .build();
    }
}
