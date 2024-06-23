package com.example.myblogsite.controller;

import com.example.myblogsite.entity.Post;
import com.example.myblogsite.pojo.PostPojo;
import com.example.myblogsite.service.PostService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostPojo> createPost(
            @RequestBody PostPojo postPojo,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {
        PostPojo createdPost = this.postService.createPost(postPojo, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostPojo>> getPostsByUser(@PathVariable Long userId) {
        List<PostPojo> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostPojo>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostPojo> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostPojo>> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "5",required = false) Integer pageSize) {
        List<PostPojo> posts = this.postService.getPostsInPage(pageNumber,pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostPojo> getPostById(@PathVariable Long postId) {
        PostPojo post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostPojo> updatePost(@PathVariable Long postId, @RequestBody PostPojo post) {
        PostPojo updatedPost = this.postService.updatePost(post, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully",true);
    }



}
