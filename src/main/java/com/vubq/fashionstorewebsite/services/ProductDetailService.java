package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.dtos.ProductFilterDto;
import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.entities.ProductDetail;
import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailService {

    Page<ProductDetail> getAll(DataTableRequest dataTableRequest, ProductFilterDto productFilterDto);

    List<Size> findAllSizeByProductId(String productId);

    List<Color> findAllColorByProductId(String productId);

    List<Material> findAllMaterialByProductId(String productId);
}
