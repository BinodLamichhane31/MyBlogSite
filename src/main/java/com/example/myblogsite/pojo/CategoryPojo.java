package com.example.myblogsite.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryPojo {

    private Long categoryId;
    @NotBlank
    @Size(min = 3,max = 12,message = "The minimum size of the title is 3 and maximum is 12")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10,message = "The minimum size of the description is 10")
    private String categoryDescription;

}
