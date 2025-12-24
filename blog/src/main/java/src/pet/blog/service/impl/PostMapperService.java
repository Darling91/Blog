package src.pet.blog.service.impl;

import org.springframework.stereotype.Service;
import src.pet.blog.controller.dto.PostDtoResponse;
import src.pet.blog.model.Post;

@Service
public class PostMapperService {

    public PostDtoResponse toDto(Post post){
        return PostDtoResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
