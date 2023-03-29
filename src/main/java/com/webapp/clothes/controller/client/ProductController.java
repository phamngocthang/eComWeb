package com.webapp.clothes.controller.client;

import com.webapp.clothes.dto.ImageDTO;
import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.dto.ProductDTO;
import com.webapp.clothes.entity.Product;
import com.webapp.clothes.mapper.ImageMapper;
import com.webapp.clothes.mapper.ProductMapper;
import com.webapp.clothes.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Product> top4Product = productService.getTopProductLimit(4);

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

    @GetMapping(value = {"/shop"})
    public String shop(Model model, HttpServletRequest request) {

        // Số lượng sản phẩm theo color
        List<Integer> countProductByColor = productService.countProductByColor();
        // Số lượng sản phẩm theo size
        List<Integer> countProductByPrice = productService.countProductByPrice();
        // Filter theo Category, Price, Size, Sorting theo Lasted Product , Showing sử dụng session
        // Nếu chọn Filter theo Popular Product thì reset tất cả
        String color = request.getParameter("color");
        String price = request.getParameter("price");
        String typeSort = request.getParameter("typeSort");
        String showing = request.getParameter("showing");
        String pageCurrent = request.getParameter("pageCurrent");
        String category = request.getParameter("category");
        // Filter theo price, color, category và sắp xếp theo id, price
        List<Product> listFilterProduct = productService.filterProduct(category, price, color, typeSort);
        // Phân trang và hiện thị số lượng sản phẩm theo yeu cau
        List<Product> listPageable = productService.pageAble(showing, pageCurrent, listFilterProduct);
        // Lấy ra so trang
        Integer pageAmount = productService.getPageAmount(showing, listFilterProduct.size());

        System.out.println(color + " " + price + " " + typeSort + " " + showing);
        // Chuyen du lieu sang view
        model.addAttribute("countProductByColor", countProductByColor);
        model.addAttribute("countProductByPrice", countProductByPrice);
        model.addAttribute("listPageable", listPageable);
        model.addAttribute("pageAmount", pageAmount);
        model.addAttribute("color", color);
        model.addAttribute("price", price);
        model.addAttribute("typeSort", typeSort);
        model.addAttribute("showing", showing);
        model.addAttribute("category", category);
        model.addAttribute("pageCurrent", pageCurrent);
        return "client/shop";
    }

}
