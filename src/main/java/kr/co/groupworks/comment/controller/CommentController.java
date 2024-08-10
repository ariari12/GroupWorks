package kr.co.groupworks.comment.controller;

import kr.co.groupworks.comment.dto.CommentDTO;
import kr.co.groupworks.comment.entity.Comment;
import kr.co.groupworks.comment.repository.CommentRepository;
import kr.co.groupworks.comment.service.CommentService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 목록
    @GetMapping("/{no}")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long no) {
        List<CommentDTO> dto = commentService.getComments(no);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 댓글 작성
    @PostMapping("/{no}")
    public ResponseEntity<Void> addComment(@PathVariable Long no,
                                           @RequestBody CommentDTO commentDTO,
                                           @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        commentDTO.setBoardId(no);
        commentDTO.setCreatedDate(LocalDateTime.now());
        commentService.addComment(commentDTO, sessionEmployeeDTO.getEmployeeId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 댓글 업데이트
    @PutMapping("/{no}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long no, @RequestBody CommentDTO dto) {
        commentService.updateComment(no, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // 댓글 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long no) {
        commentService.deleteComment(no);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
