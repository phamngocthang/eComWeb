package com.webapp.clothes.mapper;

import com.webapp.clothes.dto.ProductDTO;
import com.webapp.clothes.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Product convertToEntity(ProductDTO productDTO) {
        Product entity = modelMapper.map(productDTO, Product.class);
        return entity;
    }

    public ProductDTO convertToDTO(Product product) {
        ProductDTO userDTO = modelMapper.map(product, ProductDTO.class);
        return userDTO;
    }
}
