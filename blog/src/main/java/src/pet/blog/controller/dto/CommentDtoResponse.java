package src.pet.blog.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDtoResponse(
        String text,
        LocalDateTime createdAt
) {}
