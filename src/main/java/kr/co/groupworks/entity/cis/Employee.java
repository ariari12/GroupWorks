package kr.co.groupworks.entity.cis;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "member")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Employee {
    @Id
    private Integer employeeId;

    @Field(type = FieldType.Text)
    private String employeeName;

    @Field(type = FieldType.Integer)
    private Integer rankId;

    @Field(type = FieldType.Text)
    private String rankName;

    @Field(type = FieldType.Integer)
    private Integer departmentId;

    @Field(type = FieldType.Text)
    private String departmentName;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Text)
    private String phoneNumber;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Text)
    private String gender;

    @Field(type = FieldType.Date)
    private LocalDateTime joinDate;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer salary;

    @Field(type = FieldType.Integer)
    private Integer supervisorId;
}