package kr.co.groupworks.dto.cis.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    // 1, 부서 pk
    private int departmentId;
    // 2, 부서 명
    private String departmentName;
    // 3, 부서 공용 연락처
    private String departmentContactNumber;
    // 4, 부서 지역
    private String departmentLocation;
}
