package com.example.myblogsite.service;

import com.example.myblogsite.entity.Post;
import com.example.myblogsite.pojo.PostPojo;
import com.example.myblogsite.shared.pojo.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostPojo createPost(PostPojo postPojo,Long userId,Long categoryId);
    PostPojo updatePost(PostPojo postPojo,Long postId);
    void deletePost(Long postId);
    PostPojo getPostById(Long postId);
    List<PostPojo> getAllPosts();
    PostResponse getPostsByCategory(Long categoryId,Integer pageNum, Integer pageSize,String sortBy,String sortDirection);
    PostResponse getPostsByUser(Long userId,Integer pageNum, Integer pageSize,String sortBy,String sortDirection);
    List<PostPojo> searchPosts(String keyword);
    PostResponse getPostsInPage(Integer pageNum, Integer pageSize, String sortBy, String sortDirection, String query);

}
