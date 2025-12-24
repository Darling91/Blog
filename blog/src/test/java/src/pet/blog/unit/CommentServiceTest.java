package src.pet.blog.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.pet.blog.controller.dto.CommentDtoResponse;
import src.pet.blog.exception.ExceptionMessages;
import src.pet.blog.exception.exceptions.BadRequestException;
import src.pet.blog.exception.exceptions.NotFoundException;
import src.pet.blog.model.Comment;
import src.pet.blog.repository.CommentRepository;
import src.pet.blog.service.impl.CommentServiceImpl;
import src.pet.blog.service.impl.PostMapperService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostMapperService postMapperService;

    @InjectMocks
    private CommentServiceImpl commentService;

    private UUID validPostId;
    private UUID invalidPostId;
    private Comment comment1;
    private Comment comment2;
    private CommentDtoResponse dto1;
    private CommentDtoResponse dto2;

    @BeforeEach
    void setUp() {
        validPostId = UUID.randomUUID();
        invalidPostId = UUID.randomUUID();

        comment1 = Comment.builder()
                .id(UUID.randomUUID())
                .text("First comment")
                .createdAt(LocalDateTime.now().minusHours(2))
                .build();

        comment2 = Comment.builder()
                .id(UUID.randomUUID())
                .text("Second comment")
                .createdAt(LocalDateTime.now().minusHours(1))
                .build();

        dto1 = CommentDtoResponse.builder()
                .text("First comment")
                .createdAt(comment1.getCreatedAt())
                .build();

        dto2 = CommentDtoResponse.builder()
                .text("Second comment")
                .createdAt(comment2.getCreatedAt())
                .build();
    }

    @Test
    void getAllCommentsForPosts_shouldReturnComments_whenPostIdIsValidAndCommentsExist() {

        List<Comment> comments = List.of(comment1, comment2);
        List<CommentDtoResponse> expectedDtos = List.of(dto1, dto2);

        when(commentRepository.getAllCommentsByPostId(validPostId)).thenReturn(comments);
        when(postMapperService.toCommentDto(comment1)).thenReturn(dto1);
        when(postMapperService.toCommentDto(comment2)).thenReturn(dto2);

        List<CommentDtoResponse> result = commentService.getAllCommentsForPosts(validPostId);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(dto1, dto2);

        verify(commentRepository, times(1)).getAllCommentsByPostId(validPostId);
        verify(postMapperService, times(2)).toCommentDto(any(Comment.class));
    }

    @Test
    void getAllCommentsForPosts_shouldThrowBadRequestException_whenPostIdIsNull() {

        assertThatThrownBy(() -> commentService.getAllCommentsForPosts(null))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(ExceptionMessages.BAD_REQUEST);

        verify(commentRepository, never()).getAllCommentsByPostId(any());
        verify(postMapperService, never()).toCommentDto(any());
    }

    @Test
    void getAllCommentsForPosts_shouldThrowNotFoundException_whenNoCommentsFound() {

        when(commentRepository.getAllCommentsByPostId(validPostId)).thenReturn(List.of());

        assertThatThrownBy(() -> commentService.getAllCommentsForPosts(validPostId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ExceptionMessages.NOT_FOUND);

        verify(commentRepository, times(1)).getAllCommentsByPostId(validPostId);
        verify(postMapperService, never()).toCommentDto(any());
    }

    @Test
    void getAllCommentsForPosts_shouldCallRepositoryWithCorrectParameter() {

        List<Comment> comments = List.of(comment1);
        when(commentRepository.getAllCommentsByPostId(validPostId)).thenReturn(comments);
        when(postMapperService.toCommentDto(any())).thenReturn(dto1);

        commentService.getAllCommentsForPosts(validPostId);

        verify(commentRepository, times(1)).getAllCommentsByPostId(validPostId);
        verify(commentRepository, never()).getAllCommentsByPostId(invalidPostId);
    }

    @Test
    void getAllCommentsForPosts_shouldMapAllCommentsToDto() {

        List<Comment> comments = List.of(comment1, comment2);
        when(commentRepository.getAllCommentsByPostId(validPostId)).thenReturn(comments);
        when(postMapperService.toCommentDto(comment1)).thenReturn(dto1);
        when(postMapperService.toCommentDto(comment2)).thenReturn(dto2);

        List<CommentDtoResponse> result = commentService.getAllCommentsForPosts(validPostId);

        verify(postMapperService, times(1)).toCommentDto(comment1);
        verify(postMapperService, times(1)).toCommentDto(comment2);
        assertThat(result).containsExactly(dto1, dto2);
    }
}

