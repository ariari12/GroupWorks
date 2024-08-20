package kr.co.groupworks.comment.dto;

import kr.co.groupworks.employee.dto.EmployeeDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long commentId;
    private Long boardId;
    private String content;
    private LocalDateTime createdDate;
    private Long parentCommentId;
    private List<CommentDTO> childComments = new ArrayList<>();
    private EmployeeDTO employee;
}
