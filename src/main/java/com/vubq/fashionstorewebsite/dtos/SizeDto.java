package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Size;
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
public class SizeDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private CreatedByDto createdBy;
    private UpdatedByDto updatedBy;

    public static SizeDto toDto(Size size) {
        return SizeDto.builder()
                .id(size.getId())
                .name(size.getName())
                .description(size.getDescription())
                .createdAt(size.getCreatedAt())
                .createdBy(CreatedByDto.builder().id(size.getCreatedBy()).build())
                .updatedBy(UpdatedByDto.builder().id(size.getUpdatedBy()).build())
                .status(size.getStatus())
                .updatedAt(size.getUpdatedAt())
                .build();
    }
}

