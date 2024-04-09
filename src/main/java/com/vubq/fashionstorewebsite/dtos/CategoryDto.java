package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategoryDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private CreatedByDto createdBy;
    private UpdatedByDto updatedBy;

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .createdBy(CreatedByDto.builder().id(category.getCreatedBy()).build())
                .updatedBy(UpdatedByDto.builder().id(category.getUpdatedBy()).build())
                .status(category.getStatus())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
