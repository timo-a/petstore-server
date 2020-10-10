package com.example.petstore.backend.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.petstore.backend.api.model.Tag;
import com.example.petstore.backend.db.TagBE;

@Mapper
public interface TagMapper {

	@Mapping(target = "pets", ignore = true)
	TagBE toBE(Tag s);
}
