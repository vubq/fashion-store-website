package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Material;
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
public class MaterialDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private CreatedByDto createdBy;
    private UpdatedByDto updatedBy;

    public static MaterialDto toDto(Material material) {
        return MaterialDto.builder()
                .id(material.getId())
                .name(material.getName())
                .description(material.getDescription())
                .createdAt(material.getCreatedAt())
                .createdBy(CreatedByDto.builder().id(material.getCreatedBy()).build())
                .updatedBy(UpdatedByDto.builder().id(material.getUpdatedBy()).build())
                .status(material.getStatus())
                .updatedAt(material.getUpdatedAt())
                .build();
    }
}
