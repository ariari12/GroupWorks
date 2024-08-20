package kr.co.groupworks.employee.entity;


import jakarta.persistence.*;
import kr.co.groupworks.common.BaseEntity;
import kr.co.groupworks.department.dto.DepartmentDTO;
import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@SuperBuilder
@ToString(exclude = "department")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "employee_pw", nullable = false)
    private String employeePW;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "rank_id")
    private Integer rankId;

    @Column(name = "rank_name")
    private String rankName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @Column(name = "is_active")
    private Integer isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    //    Entity to DTO
    public EmployeeDTO toEmployeeDTO() {
        return EmployeeDTO.builder()
                .employeeId(this.getEmployeeId())
                .employeePW(this.getEmployeePW())
                .employeeName(this.getEmployeeName())
                .rankId(this.getRankId())
                .rankName(this.getRankName())
                .department(DepartmentDTO.builder()
                        .departmentId(this.getDepartment().getDepartmentId())
                        .departmentName(this.getDepartment().getDepartmentName())
                        .contactNumber(this.getDepartment().getContactNumber())
                        .location(this.getDepartment().getLocation())
                        .build())
                .email(this.getEmail())
                .joinDate(this.getCreatedDate())
                .phoneNumber(this.getPhoneNumber())
                .address(this.getAddress())
                .gender(this.getGender())
                .salary(this.getSalary())
                .supervisorId(this.getSupervisorId())
                .isActive(this.getIsActive())
                .role(this.getRole())
                .build();
    }
}