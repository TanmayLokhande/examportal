package com.blog.Payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private int categoryId;
	
	@NotEmpty
	private String categoryTitle;
	private String categoryDescription;
}
