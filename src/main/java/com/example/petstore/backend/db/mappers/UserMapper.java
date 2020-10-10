package com.example.petstore.backend.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.User;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.UserBE;
import com.example.petstore.backend.db.mappers.CategoryMapper;
import com.example.petstore.backend.db.mappers.TagMapper;

@Mapper
public interface UserMapper {

	//@Mapping(target = "pet", ignore = true)
	UserBE toBE(User s);
	
	User fromBE(UserBE s);
}
