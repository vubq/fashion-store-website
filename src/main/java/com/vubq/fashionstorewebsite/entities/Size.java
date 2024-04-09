package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.dtos.SizeDto;
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
@Table(name = "sizes")
public class Size extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public static Size toEntity(SizeDto sizeDto) {
        return Size.builder()
                .id(sizeDto.getId())
                .name(sizeDto.getName())
                .description(sizeDto.getDescription())
                .createdBy(sizeDto.getCreatedBy() != null ? sizeDto.getCreatedBy().getId() : null)
                .updatedBy(sizeDto.getUpdatedBy() != null ? sizeDto.getUpdatedBy().getId() : null)
                .createdAt(sizeDto.getCreatedAt())
                .status(sizeDto.getStatus())
                .updatedAt(sizeDto.getUpdatedAt())
                .build();
    }
}
