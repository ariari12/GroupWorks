package kr.co.groupworks.common.mapper;

import kr.co.groupworks.comment.dto.CommentDTO;
import kr.co.groupworks.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "board.boardId", target = "boardId")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "parentComment.commentId", target = "parentCommentId")
    CommentDTO toDTO(Comment comment);

    @Mapping(source = "boardId", target = "board.boardId")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "parentCommentId", target = "parentComment.commentId")
    Comment toEntity(CommentDTO commentDTO);
}
