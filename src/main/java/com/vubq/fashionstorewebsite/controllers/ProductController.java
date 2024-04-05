package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.*;
import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.entities.Product;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
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
        Page<Product> result = this.productService.getAll(request, productFilterDto);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList = result.get().toList().stream().map(product -> {
            ProductDto productDto = ProductDto.toDto(product);

            User user = this.userService.findById(product.getCreatedBy()).orElse(null);
            CreatedByDto createdByDto = CreatedByDto.builder()
                    .id(user != null ? user.getId() : null)
                    .name(user != null ? user.getFirstName() + " " + user.getLastName() : null)
                    .build();
            productDto.setCreatedBy(createdByDto);

            List<SizeDto> sizeDtoList = this.productDetailService.getListDistinctSizeByProductId(product.getId())
                    .stream().map(sizeId -> {
                        SizeDto sizeDto = SizeDto.toDto(this.sizeService.findById(sizeId).get());
                        return sizeDto;
                    }).toList();
            productDto.setSizes(sizeDtoList);

            return productDto;
        }).collect(Collectors.toList());
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(productDtoList);
    }
}
