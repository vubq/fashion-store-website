package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.ProductDetail;
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
public class ProductDetailDto extends BaseDto {

    private String id;
    private String code;
    private Double price;
    private Integer quantity;
    private ProductDto product;
    private SizeDto size;
    private ColorDto color;
    private MaterialDto material;
    private List<ImageDto> images;
    private String createdBy;
    private String updatedBy;

    public static ProductDetailDto toDto(ProductDetail productDetail) {
        return ProductDetailDto.builder()
                .id(productDetail.getId())
                .code(productDetail.getCode())
                .price(productDetail.getPrice())
                .quantity(productDetail.getQuantity())
                .product(ProductDto.toDto(productDetail.getProduct()))
                .size(SizeDto.toDto(productDetail.getSize()))
                .color(ColorDto.toDto(productDetail.getColor()))
                .material(MaterialDto.toDto(productDetail.getMaterial()))
                .createdAt(productDetail.getCreatedAt())
                .createdBy(productDetail.getCreatedBy())
                .updatedAt(productDetail.getUpdatedAt())
                .updatedBy(productDetail.getUpdatedBy())
                .build();
    }
}
