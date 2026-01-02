package src.pet.blog.service;

import src.pet.blog.controller.dto.RegisterDtoFromAuthorService;

public interface UserService {

    void processRegister(RegisterDtoFromAuthorService registerDtoFromAuthorService);
}
