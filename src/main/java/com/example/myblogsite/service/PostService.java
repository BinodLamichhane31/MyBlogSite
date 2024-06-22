package com.example.myblogsite.service;

import com.example.myblogsite.entity.Post;
import com.example.myblogsite.pojo.PostPojo;

import java.util.List;

public interface PostService {
    PostPojo createPost(PostPojo postPojo,Long userId,Long categoryId);
    PostPojo updatePost(PostPojo postPojo,Long postId);
    void deletePost(Long postId);
    PostPojo getPostById(Long postId);
    List<PostPojo> getAllPosts();
    List<PostPojo> getPostsByCategory(Long categoryId);
    List<PostPojo> getPostsByUser(Long userId);
    List<PostPojo> searchPosts(String keyword);

}
