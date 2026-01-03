package src.pet.blog.service;

import src.pet.blog.controller.dto.LoginDto;
import src.pet.blog.controller.dto.RegisterDtoFromAuthorService;

import java.util.UUID;

public interface UserService {

    void processRegister(RegisterDtoFromAuthorService registerDtoFromAuthorService);
    UUID getId(String email);
}
