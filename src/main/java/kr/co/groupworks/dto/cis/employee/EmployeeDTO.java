package kr.co.groupworks.dto.cis.employee;

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
    //  사원번호 pk
    private int employeeId;
    //  사원명
    private String employeeName;
    //  직급 fk
    private int rankId;
    //  직급 명
    private String rankName;
    //  부서 번호 fk
    private int departmentId;
    //  부서 명
    private String departmentName;
    //  이메일
    private String email;
    //  연락처
    private String phoneNumber;
    //  거주지
    private String address;
    //  성별
    private String gender;
    //  입사일
    private String joinDate;
    //  이름
    private String name;
    //  급여
    private int salary;
    //  선임 사원번호
    private int supervisorId;
}

