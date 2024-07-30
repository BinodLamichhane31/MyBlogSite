package com.example.myblogsite;

import com.example.myblogsite.entity.Post;
import com.example.myblogsite.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    @Order(1)
    @Rollback(false)
    public void savePostTest() {
        Post post = new Post();
        post.setTitle("title");
        post.setContent("content");
        postRepository.save(post);
        Assertions.assertThat(post.getPostId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findPostByIdTest() {
        Post post = postRepository.findById(1L).get();
        Assertions.assertThat(post).isNotNull();
    }

    @Test
    @Order(3)
    public void findListOfPostsTest() {
        List<Post> posts = postRepository.findAll();
        Assertions.assertThat(posts).isNotNull();
    }

    @Test
    @Order(4)
    public void updatePostTest() {
        Post post = postRepository.findById(1L).get();
        post.setTitle("updated title");
        postRepository.save(post);
        Assertions.assertThat(post.getTitle()).isEqualTo("updated title");
    }

    @Test
    @Order(5)
    public void deletePostTest() {
        Post post = postRepository.findById(1L).get();
        postRepository.delete(post);
        Assertions.assertThat(postRepository.existsById(1L)).isFalse();
    }
}
