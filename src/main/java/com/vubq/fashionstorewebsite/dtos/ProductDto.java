package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductDto extends BaseDto {

    private String id;
    private String name;
    private String description;
    private Double price;
    private CategoryDto category;
    private BrandDto brand;
    private CreatedByDto createdBy;
    private List<SizeDto> sizes;
    private List<ColorDto> colors;
    private List<MaterialDto> materials;

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(CategoryDto.toDto(product.getCategory()))
                .brand(BrandDto.toDto(product.getBrand()))
                .createdBy(CreatedByDto.builder().id(product.getCreatedBy()).build())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .status(product.getStatus())
                .build();
    }
}
