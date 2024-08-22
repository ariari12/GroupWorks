package kr.co.groupworks.board.repository;

import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryDsl {
    Optional<Board> findByBoardIdAndDepartment(Long boardId, Department department);

    @Modifying
    @Query("UPDATE Board b SET b.hits = b.hits + 1 WHERE b.boardId = :boardId")
    void updateViewCount(@Param("boardId") Long boardId);

}
