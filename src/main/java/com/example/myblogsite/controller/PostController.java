package com.example.myblogsite.controller;

import com.example.myblogsite.config.AppConstants;
import com.example.myblogsite.pojo.PostPojo;
import com.example.myblogsite.service.FileService;
import com.example.myblogsite.service.PostService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import com.example.myblogsite.shared.pojo.PostResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${projct.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostPojo> createPost(
            @RequestBody PostPojo postPojo,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {
        PostPojo createdPost = this.postService.createPost(postPojo, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Long userId,
                                                         @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                         @RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                       @RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        PostResponse postResponse = this.postService.getPostsByUser(userId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Long categoryId,
                                                             @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                           @RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                           @RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        PostResponse postResponse = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

//    @GetMapping("/posts")
//    public ResponseEntity<List<PostPojo>> getAllPosts() {
//        List<PostPojo> posts = this.postService.getAllPosts();
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        PostResponse postResponse = this.postService.getPostsInPage(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);

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
    public ApiResponse deletePost(@PathVariable Long postId) throws IOException {
        PostPojo post = this.postService.getPostById(postId);

        if (post != null && post.getImageName() != null) {
            this.fileService.deleteImage(path, post.getImageName());
        }
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully", true);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostPojo>> getPostByKeyword(@PathVariable String keyword) {
        List<PostPojo> result = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostPojo> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                         @PathVariable Long postId) throws IOException {
        PostPojo postPojo = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);
        postPojo.setImageName(fileName);
        PostPojo updatedPost = this.postService.updatePost(postPojo, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
    }

    @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }




}
