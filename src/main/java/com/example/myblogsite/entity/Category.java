package com.example.myblogsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "category_seq",allocationSize = 1)
    @Id
    private Long id;

    @Column(name="title",length = 50)
    private String categoryTitle;
    @Column(name = "description")
    private String categoryDescription;
}
