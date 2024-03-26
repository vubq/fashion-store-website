package com.vubq.fashionstorewebsite.payloads.responses;

import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.payloads.PayloadBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class CategoryResponse extends PayloadBase {

    private String id;
    private String name;
    private String description;
    private String createdByName;

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .status(category.getStatus())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
