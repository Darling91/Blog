package src.pet.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.pet.blog.controller.dto.PostDtoResponse;
import src.pet.blog.exception.ExceptionMessages;
import src.pet.blog.exception.exceptions.BadRequestException;
import src.pet.blog.exception.exceptions.NotFoundException;
import src.pet.blog.model.Post;
import src.pet.blog.repository.PostRepository;
import src.pet.blog.service.PostService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapperService postMapperService;

    @Override
    public List<PostDtoResponse> getAllPosts() {
        List<Post> post = postRepository.findAll();
        if(post.isEmpty()){
            throw new NotFoundException(ExceptionMessages.NOT_FOUND);
        }
        return post.stream()
                .map(postMapperService::toDto)
                .toList();
    }


    @Override
    @Transactional
    public PostDtoResponse getConcretePost(UUID postId) {
        if (postId == null) {
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST);
        }

        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND));
        return new PostDtoResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUpdatedAt(),
                post.getCreatedAt()
        );
    }
}
