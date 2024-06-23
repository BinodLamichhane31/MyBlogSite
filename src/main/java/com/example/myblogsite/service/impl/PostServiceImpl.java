package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.Post;
import com.example.myblogsite.entity.User;
import com.example.myblogsite.exception.ResourceNotFoundException;
import com.example.myblogsite.pojo.PostPojo;
import com.example.myblogsite.repository.CategoryRepository;
import com.example.myblogsite.repository.PostRepository;
import com.example.myblogsite.repository.UserRepository;
import com.example.myblogsite.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostPojo createPost(PostPojo postPojo,Long userId,Long categoryId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

        Post post = this.modelMapper.map(postPojo, Post.class);
        post.setImageName("default.png");
        post.setCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepository.save(post);

        return this.modelMapper.map(newPost,PostPojo.class);
    }

    @Override
    public PostPojo updatePost(PostPojo postPojo, Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "Post id", postId));

        post.setTitle(postPojo.getTitle());
        post.setContent(postPojo.getContent());
        post.setImageName(postPojo.getImageName());
//        post.setCategory(this.categoryRepository.findById(postPojo.getCategory().getCategoryId()).get());
        return this.modelMapper.map(postRepository.save(post),PostPojo.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "Post id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostPojo getPostById(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "Post id", postId));
        return this.modelMapper.map(post,PostPojo.class);
    }

    @Override
    public List<PostPojo> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        return posts.stream().map(post -> this.modelMapper.map(post,PostPojo.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostPojo> getPostsByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category ", "category id ", categoryId));
        List<Post> posts= this.postRepository.findByCategory(category);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostPojo.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostPojo> getPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        List<Post> posts= this.postRepository.findByUser(user);

        return posts.stream().map((post) -> this.modelMapper.map(post, PostPojo.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostPojo> searchPosts(String keyword) {
        return List.of();
    }

    @Override
    public List<PostPojo> getPostsInPage(Integer pageNum, Integer size) {
        Pageable p = PageRequest.of(pageNum, size);
        Page<Post> postsPage = this.postRepository.findAll(p);
        List<Post> posts = postsPage.getContent();
        return posts.stream().map(post -> this.modelMapper.map(post,PostPojo.class)).collect(Collectors.toList());
    }
}
