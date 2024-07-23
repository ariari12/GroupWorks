package kr.co.groupworks.dto.cis.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data @Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    //  사원번호 pk
    private Long employeeId;
    //  비밀번호
    private String employeePW;
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
    private LocalDateTime joinDate;
    //  급여
    private Long salary;
    //  선임 사원번호
    private Long supervisorId;
}

