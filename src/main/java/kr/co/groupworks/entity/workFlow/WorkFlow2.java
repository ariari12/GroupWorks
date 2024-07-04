package kr.co.groupworks.entity.workFlow;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Table(name = "WorkFlow")
@Entity @Data @Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlow2 {
    @Id
    @Column(name = "docs_no")
    private Integer no;
    @Column(name = "document_no")
    private String dscsNo;

    @Column(name = "employee_num")
    private int employeeNum;
    private String draftsman;

    @Column(name = "team_no")
    private int temaNo;
    @Column(name = "team_name")
    private String teamName;

    private String title;
}
