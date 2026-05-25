package com.example.cixoil.mapper;

import com.example.cixoil.dto.category.CategoryDTO;
import com.example.cixoil.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO (Category category);
}
