package src.pet.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.pet.blog.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
