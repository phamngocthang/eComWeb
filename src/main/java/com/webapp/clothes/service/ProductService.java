package com.webapp.clothes.service;

import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.dto.ProductDTO;
import com.webapp.clothes.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getRecentProducts();

    List<ProductAmount> countProductByCategoryId();

    List<Product> getTop4Product();

    Product getProductById(Integer id);

    List<Product> getRelatedProduct(Integer id, String brand);
}
