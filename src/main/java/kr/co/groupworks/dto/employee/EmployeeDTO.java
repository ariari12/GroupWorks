package kr.co.groupworks.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data @Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    // 1, 사원번호 pk
    private int employeeId;
    // 2, 사원명
    private String employeeName;
    // 3, 직급 fk
    private int rankId;
    // 4, 직급 명
    private String rankName;
    // 5, 부서 번호 fk
    private int departmentId;
    // 6, 부서 명
    private String departmentName;
    // 7, 이메일
    private String email;
    // 8, 비밀번호
    private String password;
    // 9, 연락처
    private String phone;
    // 10, 거주지
    private String address;
    // 11, 성별
    private String gender;
    // 12, 입사일
    private String joinDate;
}
