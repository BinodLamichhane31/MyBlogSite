package com.example.myblogsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPojo {
    private Long id;
    private String content;
    private String userName;
    private LocalDateTime date;
}
