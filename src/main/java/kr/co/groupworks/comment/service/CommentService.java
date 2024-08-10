package kr.co.groupworks.comment.service;

import kr.co.groupworks.comment.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getComments(Long id);
    Long countAllComments(Long no);
    void addComment(CommentDTO commentDTO, Long employeeId);
    void updateComment(Long id, CommentDTO dto);
    void deleteComment(Long no);
}
