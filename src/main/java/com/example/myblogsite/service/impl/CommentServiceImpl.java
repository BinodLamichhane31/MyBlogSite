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

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", 0));
        UserPojo userPojo = this.modelMapper.map(user, UserPojo.class);
        comment.setUserName(userPojo.getName());
        comment.setEmail(userPojo.getEmail());
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentPojo.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = userDetails.getUsername();
//        if (!comment.getUserName().equals(email)) {
//            throw new RuntimeException("You can delete only your comments");
//        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentPojo updateComment(Long commentId, CommentPojo commentPojo) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = userDetails.getUsername();
//        if (!comment.getUserName().equals(email)) {
//            throw new RuntimeException("You can edit only your comments");
//        }
        comment.setContent(commentPojo.getContent());
        comment.setDate(new Date());
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment, CommentPojo.class);
    }
}
