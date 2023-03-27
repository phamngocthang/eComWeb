package com.webapp.clothes.controller.client;

import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.dto.ProductDTO;
import com.webapp.clothes.entity.Product;
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
    private ProductMapper mapper;
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

    @GetMapping(value = {"/detail/{id}"})
    public String shopdetail(Model model, @PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = mapper.convertToDTO(product);
        model.addAttribute("product", productDTO);
        return "client/shopdetail";
    }
}
