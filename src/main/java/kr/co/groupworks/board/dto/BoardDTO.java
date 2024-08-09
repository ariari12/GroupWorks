package kr.co.groupworks.board.dto;

import kr.co.groupworks.board.entity.BoardStatus;
import kr.co.groupworks.board.entity.BoardType;
import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long boardId;
    private String title;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String ipAddress;
    private int hits;
    private BoardStatus status;
    private BoardType boardType;

    private Employee employee;
    private Department department;

    private String relativeCreateDate;
    private Long employeeId;
    private Long departmentId;
    private Long rankNum;
}
