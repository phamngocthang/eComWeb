package com.webapp.clothes.service;

import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.entity.Product;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    List<Product> getRecentProducts();

    List<ProductAmount> countProductByCategoryId();

    List<Product> getTopProductLimit(Integer limit);

    Product getProductById(Integer id);

    List<Product> findAll(Sort sort);

    List<Integer> countProductByColor();

    List<Product> filterPrice(String typePrice);

    List<Product> filterColor(String typeColor);

    Integer getPageAmount(String showing, Integer productAmount);

    List<Product> filterProductByCategoryAndPriceAndColor(String typePrice, String typeColor, String categoryId, String typeSort);

    List<Product> filterByShowingAndPageCurrent(String showing, String pageCurrent, List<Product> listProduct);
    List<Product> filterCategory(Integer categoryId);

    List<Integer> countProductByPrice();

    List<Product> getRelatedProduct(Integer id, String brand);
}
