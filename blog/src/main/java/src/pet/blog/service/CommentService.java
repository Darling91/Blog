package src.pet.blog.service;

import src.pet.blog.controller.dto.CommentDtoResponse;
import src.pet.blog.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentDtoResponse> getAllCommentsForPosts(UUID postId);
}
