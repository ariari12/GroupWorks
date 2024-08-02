package kr.co.groupworks.common.mapper;

import kr.co.groupworks.board.dto.BoardDTO;
import kr.co.groupworks.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
    BoardDTO toDTO(Board board);
    Board toEntity(BoardDTO boardDTO);
}
