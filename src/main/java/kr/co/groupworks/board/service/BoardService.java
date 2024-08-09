package kr.co.groupworks.board.service;

import kr.co.groupworks.board.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getBoardList(Long departmentId);
    List<BoardDTO> getTopNotices(Long departmentId);
    List<BoardDTO> getNotices(Long departmentId);
    Long register(BoardDTO dto);
    BoardDTO getDetail(Long no, Long departmentId);
    String deleteBoard(Long no, Long employeeId);


    Page<BoardDTO> getBoardList(Long departmentId, Pageable pageable, String keyword, String keywordType);
    Page<BoardDTO> getNotices(Long departmentId, Pageable pageable, String keyword, String keywordType);

    void increaseHits(Long boardId);
    void editBoard(Long no, BoardDTO dto);
}
