package com.example.blog.config;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private static final int QUANTITY_RANDOM_POSTS = 5;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DataInitializer(PostRepository postRepository,
                           CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void injejctData() {
        Post postPicture = getPost("My post #1", "This post of my picture", "foto 35");
        Post postFilm = getPost("My post #2", "This post of my film", "film 12");
        Post postCar = getPost("My post #3", "This post of my car", "BMW");
        postRepository.saveAll(List.of(postPicture, postFilm, postCar));
        postRepository.saveAll(getRandomPostsByStream(QUANTITY_RANDOM_POSTS));
        commentRepository.save(getComment("Den", "denys@gmail.com",
                "solution number first by picture", postPicture));
        commentRepository.save(getComment("Den", "denys@gmail.com",
                "solution number first by picture", postPicture));
        commentRepository.save(getComment("Mak", "mak@gmail.com",
                "solution number first by car", postCar));
        commentRepository.save(getComment("Tan", "tan@gmail.com",
                "solution number first by film", postFilm));
    }

    private List<Post> getRandomPostsByStream(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(x -> getPost(
                        "My post #" + (x + 1),
                        "ASD Ver " + Math.round(Math.random() + 112),
                        "Content => " + Math.random()
                ))
                .toList();
    }

    private Post getPost(String title, String description, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setContent(content);
        return post;
    }

    private Comment getComment(String name, String email, String body, Post post) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);
        comment.setPost(post);
        return comment;
    }
}
