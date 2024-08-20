package kr.co.groupworks.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.board.entity.BoardStatus;
import kr.co.groupworks.board.entity.BoardType;
import kr.co.groupworks.board.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import static kr.co.groupworks.board.entity.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardQueryDsl {

    private final JPAQueryFactory queryFactory;

    // 키워드 검색
    public BooleanExpression getKeywordFilter(String keyword, String keywordType) {
        BooleanExpression keywordPredicate = null;
        if (!keyword.isEmpty()) {
            switch (keywordType) {
                case "title":
                    keywordPredicate = board.title.containsIgnoreCase(keyword);
                    break;
                case "content":
                    keywordPredicate = board.content.containsIgnoreCase(keyword);
                    break;
                case "writer":
                    keywordPredicate = board.employee.employeeName.stringValue().containsIgnoreCase(keyword);
                    break;
                case "rank":
                    keywordPredicate = board.employee.rankName.containsIgnoreCase(keyword);
                    break;
                default:
                    keywordPredicate = null;
            }
        }
        return keywordPredicate;
    }

    // 공지 게시판글
    @Override
    public List<Board> findTopBoardNotice(Long departmentId, int limit) {
        return queryFactory.select(board)
                .from(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(board.department.departmentId.eq(departmentId))
                        .and(board.subject.eq("announcement")))
                .orderBy(board.createDate.desc())
                .limit(limit)
                .fetch();
    }

    // 전체 공지글
    @Override
    public List<Board> findBoardNotice(Long departmentId) {

        BooleanExpression departmentPredicate = departmentId == null
                ? board.department.departmentId.isNull()
                : board.department.departmentId.eq(departmentId);

        return queryFactory.select(board)
                .from(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.eq("announcement")))
                .orderBy(board.createDate.desc())
                .fetch();
    }

    // 공지가 아닌 게시판글
    @Override
    public List<Board> findBoardList(Long departmentId) {

        BooleanExpression departmentPredicate = departmentId == null
                ? board.department.departmentId.isNull()
                : board.department.departmentId.eq(departmentId);

        return queryFactory.select(board)
                .from(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.ne("announcement")))
                .orderBy(board.createDate.desc())
                .fetch();
    }

    @Override
    public Page<Board> findBoardList(Long departmentId, Pageable pageable, String keyword, String keywordType) {

        BooleanExpression departmentPredicate = departmentId == null
                ? board.department.departmentId.isNull()
                : board.department.departmentId.eq(departmentId);

        QBoard board = QBoard.board;

        List<Board> boards = queryFactory
                .selectFrom(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.ne("announcement"))
                        .and(getKeywordFilter(keyword, keywordType)))
                .orderBy(board.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.ne("announcement"))
                        .and(getKeywordFilter(keyword, keywordType)))
                .fetchCount();
        return new PageImpl<>(boards, pageable, total);
    }

    @Override
    public Page<Board> findBoardNotice(Long departmentId, Pageable pageable, String keyword, String keywordType) {
        QBoard board = QBoard.board;

        BooleanExpression departmentPredicate = departmentId == null
                ? board.department.departmentId.isNull()
                : board.department.departmentId.eq(departmentId);

        List<Board> boards = queryFactory
                .selectFrom(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.eq("announcement"))
                        .and(getKeywordFilter(keyword, keywordType)))
                .orderBy(board.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(board)
                .where(board.status.eq(BoardStatus.ACTIVE)
                        .and(departmentPredicate)
                        .and(board.subject.eq("announcement"))
                        .and(getKeywordFilter(keyword, keywordType)))
                .fetchCount();
        return new PageImpl<>(boards, pageable, total);
    }


    /* 공지사항 최근 5개 가져오기 */
    @Override
    public List<Board> recentNotices() {
        QBoard b = QBoard.board;
        return queryFactory
                .select(b).from(b)
                .where(b.boardType.eq(BoardType.NOTICE))
                .orderBy(b.createDate.desc(), b.updateDate.asc())
                .limit(5).fetch();
    }

}
