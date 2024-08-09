package kr.co.groupworks.comment.repository;

import kr.co.groupworks.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentQueryDsl {

    List<Comment> findByBoard_BoardId(Long boardId);
    void deleteByBoardBoardId(Long boardId);
}
