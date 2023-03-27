package com.webapp.clothes.service.Imp;

import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.entity.Product;
import com.webapp.clothes.repositories.ProductRepository;
import com.webapp.clothes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getRecentProducts() {
        List<Product> listRecentProducts = productRepository.findTop4ByOrderByIdDesc();
        return listRecentProducts;
    }

    @Override
    public List<ProductAmount> countProductByCategoryId() {
        List<ProductAmount> countProductByCategory = new ArrayList<>();
        List<Object[]> getCount = productRepository.countProductByCategory();
        getCount.forEach(obj -> {
            countProductByCategory.add(new ProductAmount(obj[0].toString(), Integer.valueOf(obj[1].toString()),
                    obj[2].toString()));
        });
        return countProductByCategory;
    }

    @Override
    public List<Product> getTop4Product() {
        List<Product> listTop4Product = productRepository.getTopProduct();
        if(listTop4Product.size() > 4)
            return listTop4Product.stream().limit(4).collect(Collectors.toList());
        else
            return listTop4Product;
    }
    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Override
    public List<Product> getRelatedProduct(Integer id, String brand) {
        // 8 Product
        List<Product> listRelatedProduct = productRepository.getRelatedProduct(id, brand);
        if(listRelatedProduct.size() < 8) {

        }
        else {
            List<Product> listProductByBrand = productRepository.findByBrand(brand);
        }

        return null;
    }


}
