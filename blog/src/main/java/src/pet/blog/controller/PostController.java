package src.pet.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.pet.blog.controller.dto.CommentDtoResponse;
import src.pet.blog.controller.dto.PostDtoResponse;
import src.pet.blog.service.CommentService;
import src.pet.blog.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    @Operation(
            summary = "Получить список постов",
            description = "Возвращает все посты что есть"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возврат постов",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пост не найден",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            )
    })
    public List<PostDtoResponse> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK).getBody();
    }


    @GetMapping("/{postId}")
    @Operation(
            summary = "Получить конкретный пост",
            description = "Возвращает пост по его идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пост найден",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные входные данные",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пост не найден",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            )
    })
    public ResponseEntity<PostDtoResponse> getConcretePost(@PathVariable UUID postId){
        return ResponseEntity.ok(postService.getConcretePost(postId));
    }

    @GetMapping("/comments/{postId}")
    @Operation(
            summary = "Получить коментарии к посту",
            description = "Возвращает коментарии по идентификатору поста"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "возвращает список коментариев",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные входные данные",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Нет коментариев",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostDtoResponse.class)
                    )
            )
    })
    public ResponseEntity<List<CommentDtoResponse>> getComments(@PathVariable UUID postId){
        return ResponseEntity.ok(commentService.getAllCommentsForPosts(postId));
    }

/*
    No autorith
    GET    /api/posts              - список постов с пагинацией и фильтрацией
    написал функицональность + ошибку/интеграционники не проходят
    --------------------------------------------------------------------------
    GET    /api/posts/{id}         - получить конкретный пост
    написал функциональность + хэндлер на ошибки + юнит тесты
    --------------------------------------------------------------------------
    GET    /api/posts/{id}/comments - комментарии к посту
    написал функциональность + ошибки + юнит тесты
    --------------------------------------------------------------------------
    [POST   /api/auth/login         - вход
    написал функицональность без тестов и fallback если что
    POST   /api/auth/register      - регистрация]
    вынес в отдельный сервис эти 2 эндпоинта
 */


/*
    Autorith
    POST   /api/posts              - создать пост
    PUT    /api/posts/{id}         - обновить пост (только автор или админ)
    DELETE /api/posts/{id}         - удалить пост
    POST   /api/posts/{id}/comments - добавить комментарий
    PUT    /api/comments/{id}      - обновить комментарий
    DELETE /api/comments/{id}      - удалить комментарий
*/

/*
    Admin requests
    GET    /api/admin/users        - список пользователей
    PUT    /api/admin/users/{id}/role - изменить роль пользователя
    DELETE /api/admin/posts/{id}   - удалить любой пост
    DELETE /api/admin/comments/{id} - удалить любой комментарий

*/
}
