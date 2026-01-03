package src.pet.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.pet.blog.controller.dto.LoginDto;
import src.pet.blog.controller.dto.RegisterDtoFromAuthorService;
import src.pet.blog.exception.ExceptionMessages;
import src.pet.blog.exception.exceptions.NotFoundException;
import src.pet.blog.model.User;
import src.pet.blog.repository.UserRepository;
import src.pet.blog.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public UUID getId(String email) {
        return userRepository.getId(email);
    }
    /*
    переделать получение id с optional + получать его через email + password
     */
}
