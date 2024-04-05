package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.enums.EImageType;
import jakarta.persistence.*;
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
@Table(name = "images")
public class Image extends BaseEntity{

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EImageType type;

    @Column(name = "private_id")
    private String privateId;
}
