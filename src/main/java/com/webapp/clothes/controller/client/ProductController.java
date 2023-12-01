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

    public static final String PRODUCT_MESSAGE = "Update Product Successfully";

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        // Lists the newest, recently added products
        List<Product> listRecentProduct = productService.getRecentProducts();

        // List products by category, convert Product list to ProductAmount List
        List<ProductAmount> listProductAmountByCategory = productService.countProductByCategoryId();

        // Take the four products with the best revenue.
        List<Product> listTop4Product = productService.getTopProductLimit(4);

        // Add data and forward to view
        model.addAttribute("topProduct", listTop4Product);
        model.addAttribute("recentProducts", listRecentProduct);
        model.addAttribute("listCount", listProductAmountByCategory);
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
        String test = PRODUCT_MESSAGE;
        return "client/shopdetail";
    }

    @GetMapping(value = {"/shop"})
    public String shop(Model model, HttpServletRequest request) {
        // Get product list by color, price.
        List<Integer> countProductByColor = productService.countProductByColor();
        List<Integer> countProductByPrice = productService.countProductByPrice();

        // Get data params from request
        String color = request.getParameter("color");
        String price = request.getParameter("price");
        String typeSort = request.getParameter("typeSort");
        String showing = request.getParameter("showing");
        String pageCurrent = request.getParameter("pageCurrent");
        String category = request.getParameter("category");

        // Filter Product by category, price, color and typeSort
        List<Product> listFilterProduct = productService.filterProductByCategoryAndPriceAndColor(category,
                                                            price, color, typeSort);

        // Filter Product by number of showing, currently page index.
        List<Product> listPageable = productService.filterByShowingAndPageCurrent(showing, pageCurrent, listFilterProduct);

        // Get the number of pages
        Integer pageAmount = productService.getPageAmount(showing, listFilterProduct.size());

        // Add data and forward to view
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
