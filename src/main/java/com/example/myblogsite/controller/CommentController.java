package com.example.myblogsite.controller;

import com.example.myblogsite.entity.Comment;
import com.example.myblogsite.pojo.CommentPojo;
import com.example.myblogsite.service.CommentService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentPojo> addComment(@RequestBody CommentPojo commentPojo,
                                                  @PathVariable Long postId) {
        CommentPojo addedComment = this.commentService.addComment(commentPojo,postId);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentPojo> updateComment(@PathVariable Long commentId,
                                                     @RequestBody CommentPojo commentPojo) {
        CommentPojo updatedComment = commentService.updateComment(commentId, commentPojo);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}
