package kr.co.groupworks.entity.cis;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document(collection = "employee")
@Builder @ToString
public class Employee {
    private String employeeId;
    private String employeePW;
    private String employeeName;
    private Integer rankId;
    private String rankName;
    private String departmentName;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private LocalDateTime joinDate;
    private Integer salary;
    private Integer supervisorId;
}