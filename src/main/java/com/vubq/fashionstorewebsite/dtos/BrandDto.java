package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Brand;
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
public class BrandDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private CreatedByDto createdBy;
    private UpdatedByDto updatedBy;

    public static BrandDto toDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .createdAt(brand.getCreatedAt())
                .createdBy(CreatedByDto.builder().id(brand.getCreatedBy()).build())
                .updatedBy(UpdatedByDto.builder().id(brand.getUpdatedBy()).build())
                .status(brand.getStatus())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }
}