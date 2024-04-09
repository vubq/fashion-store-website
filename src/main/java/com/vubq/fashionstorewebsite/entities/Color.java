package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.dtos.ColorDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@SuperBuilder
@Entity
@Table(name = "colors")
public class Color extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public static Color toEntity(ColorDto colorDto) {
        return Color.builder()
                .id(colorDto.getId())
                .name(colorDto.getName())
                .description(colorDto.getDescription())
                .createdBy(colorDto.getCreatedBy() != null ? colorDto.getCreatedBy().getId() : null)
                .updatedBy(colorDto.getUpdatedBy() != null ? colorDto.getUpdatedBy().getId() : null)
                .createdAt(colorDto.getCreatedAt())
                .status(colorDto.getStatus())
                .updatedAt(colorDto.getUpdatedAt())
                .build();
    }
}
