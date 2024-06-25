package com.example.myblogsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
    @SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_seq",allocationSize = 1)
    private Long id;
    private String content;

    @ManyToOne
    private Post post;

}
