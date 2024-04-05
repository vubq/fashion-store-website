package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.*;
import com.vubq.fashionstorewebsite.entities.ProductDetail;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.services.ProductDetailService;
import com.vubq.fashionstorewebsite.services.ProductService;
import com.vubq.fashionstorewebsite.services.SizeService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/product")
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody ProductFilterDto productFilterDto) {
        Page<ProductDetail> productDetailResults = this.productDetailService.getAll(request, productFilterDto);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList = productDetailResults.get().toList().stream().map(productDetail -> {
            ProductDto productDto = ProductDto.toDto(productDetail.getProduct());

            User user = this.userService.findById(productDetail.getProduct().getCreatedBy()).orElse(null);
            CreatedByDto createdByDto = CreatedByDto.builder()
                    .id(user != null ? user.getId() : null)
                    .name(user != null ? user.getFirstName() + " " + user.getLastName() : null)
                    .build();
            productDto.setCreatedBy(createdByDto);

            productDto.setSizes(this.productDetailService.findAllSizeByProductId(productDetail.getProduct().getId()).stream().map(SizeDto::toDto).collect(Collectors.toList()));
            productDto.setColors(this.productDetailService.findAllColorByProductId(productDetail.getProduct().getId()).stream().map(ColorDto::toDto).collect(Collectors.toList()));
            productDto.setMaterials(this.productDetailService.findAllMaterialByProductId(productDetail.getProduct().getId()).stream().map(MaterialDto::toDto).collect(Collectors.toList()));

            return productDto;
        }).toList();
        return DataTableResponse.build()
                .ok()
                .totalRows(productDetailResults.getTotalElements())
                .items(productDtoList);
    }
}
