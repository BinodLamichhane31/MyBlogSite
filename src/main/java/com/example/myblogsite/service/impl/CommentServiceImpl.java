package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.Comment;
import com.example.myblogsite.entity.Post;
import com.example.myblogsite.entity.User;
import com.example.myblogsite.exception.ResourceNotFoundException;
import com.example.myblogsite.pojo.CommentPojo;
import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.repository.CommentRepository;
import com.example.myblogsite.repository.PostRepository;
import com.example.myblogsite.repository.UserRepository;
import com.example.myblogsite.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentPojo addComment(CommentPojo commentPojo, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentPojo, Comment.class);
        comment.setPost(post);
        comment.setDate(new Date());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        // Retrieve the full user information using email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", 0));

        // Map User entity to UserPojo
        UserPojo userPojo = this.modelMapper.map(user, UserPojo.class);

        // Set the user's name in the comment
        comment.setUserName(userPojo.getName());
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentPojo.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepository.delete(comment);

    }
}
