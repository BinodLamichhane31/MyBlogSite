package com.example.myblogsite.pojo;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.User;
import lombok.*;

import java.util.Date;

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


}
