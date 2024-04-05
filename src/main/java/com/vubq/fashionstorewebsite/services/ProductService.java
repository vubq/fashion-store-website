package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.dtos.ProductFilterDto;
import com.vubq.fashionstorewebsite.entities.Product;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<Product> getAll(DataTableRequest request, ProductFilterDto productFilterDto);
}
