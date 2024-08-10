package kr.co.groupworks.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.comment.entity.Comment;
import kr.co.groupworks.comment.entity.QComment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findCommentList(Long id) {
        QComment comment = QComment.comment;
        return queryFactory.selectFrom(comment)
                .leftJoin(comment.parentComment)
                .fetchJoin()
                .where(comment.board.boardId.eq(id))
                .orderBy(
                        comment.createdDate.asc().nullsFirst()
                ).fetch();
    }

}
