package src.pet.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import src.pet.blog.model.Comment;


import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("select c from Comment c where c.post.id =:postId")
    List<Comment> getAllCommentsByPostId(@Param("postId") UUID postId);
}
