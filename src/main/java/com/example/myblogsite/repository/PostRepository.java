package com.example.myblogsite.repository;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.Post;
import com.example.myblogsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
