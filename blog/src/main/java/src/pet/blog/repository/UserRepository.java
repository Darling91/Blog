package src.pet.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import src.pet.blog.controller.dto.LoginDto;
import src.pet.blog.model.User;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u.id from User u where u.email=:email")
    UUID getId(@Param("email")String email);

}
