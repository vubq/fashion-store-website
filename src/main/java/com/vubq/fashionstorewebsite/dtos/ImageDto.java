package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.entities.Image;
import com.vubq.fashionstorewebsite.enums.EImageType;
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
public class ImageDto extends BaseDto {

    private String id;
    private String publicId;
    private String url;
    private EImageType type;
    private String privateId;
    private String createdBy;
    private String updatedBy;

    public static ImageDto toDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .publicId(image.getPublicId())
                .url(image.getUrl())
                .type(image.getType())
                .privateId(image.getPrivateId())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .createdBy(image.getCreatedBy())
                .updatedBy(image.getUpdatedBy())
                .status(image.getStatus())
                .build();
    }
}
