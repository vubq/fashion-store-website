package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Color;
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
public class ColorDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private CreatedByDto createdBy;
    private UpdatedByDto updatedBy;

    public static ColorDto toDto(Color color) {
        return ColorDto.builder()
                .id(color.getId())
                .name(color.getName())
                .description(color.getDescription())
                .createdAt(color.getCreatedAt())
                .createdBy(CreatedByDto.builder().id(color.getCreatedBy()).build())
                .updatedBy(UpdatedByDto.builder().id(color.getUpdatedBy()).build())
                .status(color.getStatus())
                .updatedAt(color.getUpdatedAt())
                .build();
    }
}