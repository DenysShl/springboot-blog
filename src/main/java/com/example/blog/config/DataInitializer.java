package com.example.blog.config;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private static final int QUANTITY_RANDOM_POSTS = 5;
    private final PostRepository repository;

    public DataInitializer(PostRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void injejctData() {
        Post postPicture = getPost("My post #1", "This post of my picture", "foto 35");
        Post postFilm = getPost("My post #2", "This post of my film", "film 12");
        Post postCar = getPost("My post #3", "This post of my car", "BMW");
        repository.saveAll(List.of(postPicture, postFilm, postCar));
        repository.saveAll(getRandomPosts(QUANTITY_RANDOM_POSTS));
        repository.saveAll(getRandomPostsByStream(QUANTITY_RANDOM_POSTS));
    }

    private List<Post> getRandomPosts(int quantity) {
        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            postList.add(
                    getPost(
                            "My post #" + i,
                            "ASD Ver " + Math.random(),
                            "Content => " + Math.random()
                    )
            );
        }
        return postList;
    }

    private List<Post> getRandomPostsByStream(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(x -> getPost(
                        "My post #" + x,
                        "ASD Ver " + Math.round(Math.random()),
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
}
