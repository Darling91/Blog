package src.pet.blog.controller.dto;

import lombok.NonNull;

public record RegisterDtoFromAuthorService(
        @NonNull
        String username,
        @NonNull
        String email,
        @NonNull
        String password
) {}
