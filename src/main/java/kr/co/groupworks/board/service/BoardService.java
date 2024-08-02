package kr.co.groupworks.board.service;

import kr.co.groupworks.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getAllBoards(Long departmentId);
    Long register(BoardDTO dto);
    BoardDTO getDetail(Long no, Long departmentId);
}
