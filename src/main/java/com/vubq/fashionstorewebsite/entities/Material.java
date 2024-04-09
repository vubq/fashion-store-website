package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.dtos.MaterialDto;
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
@Table(name = "materials")
public class Material extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public static Material toEntity(MaterialDto materialDto) {
        return Material.builder()
                .id(materialDto.getId())
                .name(materialDto.getName())
                .description(materialDto.getDescription())
                .createdBy(materialDto.getCreatedBy() != null ? materialDto.getCreatedBy().getId() : null)
                .updatedBy(materialDto.getUpdatedBy() != null ? materialDto.getUpdatedBy().getId() : null)
                .createdAt(materialDto.getCreatedAt())
                .status(materialDto.getStatus())
                .updatedAt(materialDto.getUpdatedAt())
                .build();
    }
}
