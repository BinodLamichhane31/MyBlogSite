package com.example.myblogsite.pojo;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.Comment;
import com.example.myblogsite.entity.User;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPojo {
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    private UserPojo user;
    private CategoryPojo category;
    private Set<CommentPojo> comments = new HashSet<>();


}
