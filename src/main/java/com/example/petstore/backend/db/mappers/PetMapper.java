package com.example.petstore.backend.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.mappers.CategoryMapper;
import com.example.petstore.backend.db.mappers.TagMapper;

@Mapper(uses = {CategoryMapper.class, TagMapper.class})
public interface PetMapper {

	//@Mapping(target = "pet", ignore = true)
	PetBE toBE(Pet s);
	
	Pet fromBE(PetBE s);
}
