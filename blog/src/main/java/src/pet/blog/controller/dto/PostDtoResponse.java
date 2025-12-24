package src.pet.blog.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PostDtoResponse(
        UUID postId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
