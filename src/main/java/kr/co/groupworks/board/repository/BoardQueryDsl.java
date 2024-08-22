package kr.co.groupworks.board.repository;

import kr.co.groupworks.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQueryDsl {
    List<Board> findTopBoardNotice(Long departmentId, int limit);
    List<Board> findBoardNotice(Long departmentId);
    List<Board> findBoardList(Long departmentId);

    Page<Board> findBoardList(Long departmentId, Pageable pageable, String keyword, String keywordType);
    Page<Board> findBoardNotice(Long departmentId, Pageable pageable, String keyword, String keywordType);

    /* 공지사항 최근 5개 가져오기 */
    List<Board> recentNotices();
}
