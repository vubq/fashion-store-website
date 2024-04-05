package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.dtos.ProductFilterDto;
import com.vubq.fashionstorewebsite.entities.ProductDetail;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

public interface ProductDetailService {

    Page<ProductDetail> getAll(DataTableRequest dataTableRequest, ProductFilterDto productFilterDto);
}
