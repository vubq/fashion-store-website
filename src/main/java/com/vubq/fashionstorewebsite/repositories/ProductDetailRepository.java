package com.vubq.fashionstorewebsite.repositories;

import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.entities.ProductDetail;
import com.vubq.fashionstorewebsite.entities.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    Page<ProductDetail> findAll(Specification<ProductDetail> spec, Pageable pageable);

    List<ProductDetail> findAll(Specification<ProductDetail> spec);

    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<ProductDetail> findAllByProductId(String productId);

    @Query(value = "SELECT DISTINCT pd.size FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<Size> findAllSizeByProductId(String productId);

    @Query(value = "SELECT DISTINCT pd.color FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<Color> findAllColorByProductId(String productId);

    @Query(value = "SELECT DISTINCT pd.material FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<Material> findAllMaterialByProductId(String productId);

    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.quantity > 0")
    List<ProductDetail> getAllProductDetailInOfStock();

    @Query(value = "SELECT DISTINCT pd.size.id FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.quantity > 0")
    List<String> findAllSizeIdByProductIdInOfStock(String productId);

    @Query(value = "SELECT DISTINCT pd.color.id FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.quantity > 0")
    List<String> findAllColorIdByProductIdInOfStock(String productId);

    @Query(value = "SELECT DISTINCT pd.material.id FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.quantity > 0")
    List<String> findAllMaterialIdByProductIdInOfStock(String productId);

    @Query(value = "SELECT SUM(pd.quantity) FROM ProductDetail pd WHERE pd.product.id = :productId")
    Integer getQuantityOfProductAvailable(String productId);

    @Query(value = "SELECT pd FROM ProductDetail pd " +
            "WHERE pd.product.id = :productId " +
            "AND pd.size.id = :sizeId " +
            "AND pd.color.id = :colorId " +
            "AND pd.material.id = :materialId")
    ProductDetail getProductDetailByAttributes(String productId, String sizeId, String colorId, String materialId);
}
