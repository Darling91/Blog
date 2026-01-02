package src.pet.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.pet.blog.controller.dto.RegisterDtoFromAuthorService;
import src.pet.blog.model.User;
import src.pet.blog.repository.UserRepository;
import src.pet.blog.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void processRegister(RegisterDtoFromAuthorService registerDtoFromAuthorService) {
        userRepository.save(
                User.builder()
                        .email(registerDtoFromAuthorService.email())
                        .password(registerDtoFromAuthorService.password())
                        .username(registerDtoFromAuthorService.username())
                        .build()
        );
    }
}
