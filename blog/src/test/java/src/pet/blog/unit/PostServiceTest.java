package src.pet.blog.unit;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.pet.blog.controller.dto.PostDtoResponse;
import src.pet.blog.exception.exceptions.BadRequestException;
import src.pet.blog.exception.exceptions.NotFoundException;
import src.pet.blog.model.Post;
import src.pet.blog.repository.PostRepository;
import src.pet.blog.service.impl.PostServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    String validPostId = "d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14";

    @Test
    public void getConcretePost_ValidId() {
        UUID postId = UUID.fromString(validPostId);
        Post post = Post.builder()
                .id(postId)
                .title("Первая запись в блоге")
                .content("Содержимое первой записи...")
                .createdAt(LocalDateTime.of(2025,1,15,10,30))
                .updatedAt(LocalDateTime.of(2025,10,15,10,30))
                .build();

        when(postRepository.findById(postId))
                .thenReturn(Optional.of(post));

        PostDtoResponse result = postService.getConcretePost(postId);

        assertNotNull(result);
        assertEquals(postId,result.postId());
        assertEquals("Первая запись в блоге", result.title());
        assertEquals("Содержимое первой записи...", result.content());

        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void getConcretePost_InvalidId(){

        UUID postId = UUID.fromString(validPostId);

        when(postRepository.findById(postId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,() ->{
            postService.getConcretePost(postId);
        });
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void getConcretePost_NullId(){

        assertThrows(BadRequestException.class, () -> {
            postService.getConcretePost(null);
        });

        verify(postRepository, never()).findById(any());
    }
}
