package com.example.petstore.backend.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.petstore.backend.api.model.Category;
import com.example.petstore.backend.db.CategoryBE;

@Mapper
public interface CategoryMapper {
	
	@Mapping(target = "pet", ignore = true)
	CategoryBE toBE(Category s);
}
