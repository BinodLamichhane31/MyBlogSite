package com.example.myblogsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {


    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_gen")
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "post_seq",allocationSize = 1)
    @Id
    private Long postId;

    @Column(name="title",length = 100,nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @ManyToOne
    private User user;

}
