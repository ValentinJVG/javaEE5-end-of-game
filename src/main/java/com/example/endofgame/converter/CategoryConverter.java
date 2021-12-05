package com.example.endofgame.converter;

import com.example.endofgame.dto.CategorySummary;
import com.example.endofgame.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements MappingOperations<Category, CategorySummary> {
    @Override
    public CategorySummary fromEntityToDto(Category entity) {
        return new CategorySummary(entity.getId(), entity.getName(), entity.getCreationTimeStamp(),
                entity.getUpdateTimeStamp());
    }

    @Override
    public Category fromDtoToEntity(CategorySummary dto) {
        return Category.builder()
                .id(dto.id())
                .name(dto.name())
                .creationTimeStamp(dto.created())
                .updateTimeStamp(dto.updated())
                .build();
    }
}