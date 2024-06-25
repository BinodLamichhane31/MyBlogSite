package com.example.myblogsite.service;

import com.example.myblogsite.pojo.CommentPojo;

public interface CommentService {
    CommentPojo addComment(CommentPojo commentPojo,Long postId);
    void deleteComment(Long commentId);
}
