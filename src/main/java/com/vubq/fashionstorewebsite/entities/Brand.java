package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.dtos.BrandDto;
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
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public static Brand toEntity(BrandDto brandDto) {
        return Brand.builder()
                .id(brandDto.getId())
                .name(brandDto.getName())
                .description(brandDto.getDescription())
                .createdBy(brandDto.getCreatedBy() != null ? brandDto.getCreatedBy().getId() : null)
                .updatedBy(brandDto.getUpdatedBy() != null ? brandDto.getUpdatedBy().getId() : null)
                .createdAt(brandDto.getCreatedAt())
                .status(brandDto.getStatus())
                .updatedAt(brandDto.getUpdatedAt())
                .build();
    }
}
