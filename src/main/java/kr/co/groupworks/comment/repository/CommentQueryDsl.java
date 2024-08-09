package kr.co.groupworks.comment.repository;

import kr.co.groupworks.comment.entity.Comment;

import java.util.List;

public interface CommentQueryDsl {
    List<Comment> findCommentList(Long id);
}
