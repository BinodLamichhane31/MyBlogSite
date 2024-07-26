package com.example.myblogsite.repository;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.Post;
import com.example.myblogsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByUser(User user,Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);

    @Query(value = "SELECT * FROM posts WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%'))",nativeQuery = true)
    List<Post> findByTitleContaining(@Param("keyword") String keyword);
    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
