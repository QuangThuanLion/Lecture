package com.mvc.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDTO{
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 0, max = 10)
    private String name;

    @Size(min = 0, max = 30)
    @NotEmpty
    @NotNull
    private String description;

    private String thumbnail;
}