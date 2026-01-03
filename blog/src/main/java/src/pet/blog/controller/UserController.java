package src.pet.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import src.pet.blog.controller.dto.LoginDto;
import src.pet.blog.service.UserService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getId")
    public ResponseEntity<UUID> getId(@RequestBody String email){
        return ResponseEntity.ok(userService.getId(email));
    }
}
