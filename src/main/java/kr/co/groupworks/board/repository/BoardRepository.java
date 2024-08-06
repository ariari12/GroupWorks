package kr.co.groupworks.board.repository;

import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.department.entity.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.status = 'ACTIVE' " +
            "AND (:departmentId IS NULL OR b.department.id = :departmentId) " +
            "ORDER BY b.createDate DESC")
    List<Board> findByDepartmentIdOrNoDepartmentIdAndStatusActive(
            @Param("departmentId") Long departmentId, Sort sort);

    //
    Optional<Board> findByBoardIdAndDepartment(Long boardId, Department department);

}
