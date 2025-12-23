package src.pet.blog.service;

import src.pet.blog.controller.dto.PostDtoResponse;
import src.pet.blog.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostDtoResponse getConcretePost(UUID postId);
}
