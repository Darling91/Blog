package src.pet.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.pet.blog.controller.dto.CommentDtoResponse;
import src.pet.blog.exception.ExceptionMessages;
import src.pet.blog.exception.exceptions.BadRequestException;
import src.pet.blog.exception.exceptions.NotFoundException;
import src.pet.blog.model.Comment;
import src.pet.blog.repository.CommentRepository;
import src.pet.blog.service.CommentService;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostMapperService postMapperService;

    @Override
    public List<CommentDtoResponse> getAllCommentsForPosts(UUID postId) {
        if(postId == null){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST);
        }

        List<Comment> comments = commentRepository.getAllCommentsByPostId(postId);
        if(comments.isEmpty()){
            throw new NotFoundException(ExceptionMessages.NOT_FOUND);
        }

        return comments.stream()
                .map(postMapperService::toCommentDto)
                .toList();
    }
}
