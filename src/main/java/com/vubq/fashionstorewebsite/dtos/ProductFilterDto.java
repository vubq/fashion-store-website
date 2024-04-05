package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductFilterDto {

    List<String> categories;
    List<String> brands;
    List<String> colors;
    List<String> sizes;
    List<String> materials;
    List<EStatus> status;
    List<Double> priceApprox;
}
