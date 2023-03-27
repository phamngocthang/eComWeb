package com.webapp.clothes.controller.client;

import com.webapp.clothes.dto.ImageDTO;
import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.dto.ProductDTO;
import com.webapp.clothes.entity.Image;
import com.webapp.clothes.entity.Product;
import com.webapp.clothes.mapper.ImageMapper;
import com.webapp.clothes.mapper.ProductMapper;
import com.webapp.clothes.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductMapper mapperProduct;
    @Autowired
    private ImageMapper mapperImage;
    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Product> recentProducts = productService.getRecentProducts();
        List<ProductAmount> listCount = productService.countProductByCategoryId();
        List<Product> top4Product = productService.getTop4Product();

        model.addAttribute("topProduct", top4Product);
        model.addAttribute("recentProducts", recentProducts);
        model.addAttribute("listCount", listCount);
        return "client/home";
    }

    @GetMapping(value = {"/detail/{id}/{brand}"})
    public String shopdetail(Model model, @PathVariable("id") Integer id, @PathVariable("brand") String brand) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = mapperProduct.convertToDTO(product);
        ImageDTO imageDTO = mapperImage.convertToDTO(product.getImage());
        List<Product> listRelatedProduct = productService.getRelatedProduct(id, brand);
        model.addAttribute("listRelatedProduct", listRelatedProduct);
        model.addAttribute("product", productDTO);
        model.addAttribute("image", imageDTO);
        return "client/shopdetail";
    }
}
