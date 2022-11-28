package com.example.blog.config;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private static final int QUANTITY_RANDOM_POSTS = 5;
    private static final String PREFIX = "ROLE_";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PostRepository postRepository,
                           CommentRepository commentRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.passwordEncoder = passwordEncoder;
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

        Role adminRole = getRole(PREFIX + "ADMIN");
        Role userRole = getRole(PREFIX + "USER");
        roleRepository.saveAll(List.of(adminRole,userRole));
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();

        User den = getUser(
                "7860@gmail.com",
                "densh",
                passwordEncoder.encode("147147147"),
                Collections.singleton(roleAdmin),
                "Den",
                "Shl");
        userRepository.save(den);
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

    private User getUser(String email, String userName, String password, Set<Role> roles,
                         String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoles(roles);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStatus(true);
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        return user;
    }

    private Role getRole(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
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
