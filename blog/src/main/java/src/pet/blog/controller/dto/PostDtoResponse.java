package src.pet.blog.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import src.pet.blog.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record PostDtoResponse(
        UUID postId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
