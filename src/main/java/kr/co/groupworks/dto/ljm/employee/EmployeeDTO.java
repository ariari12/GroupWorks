package kr.co.groupworks.dto.ljm.employee;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EmployeeDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String rank;
    private int departmentId;
    private String departmentName;
}
