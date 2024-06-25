package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.Comment;
import com.example.myblogsite.entity.Post;
import com.example.myblogsite.exception.ResourceNotFoundException;
import com.example.myblogsite.pojo.CommentPojo;
import com.example.myblogsite.repository.CommentRepository;
import com.example.myblogsite.repository.PostRepository;
import com.example.myblogsite.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentPojo addComment(CommentPojo commentPojo, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentPojo, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentPojo.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepository.delete(comment);

    }
}
