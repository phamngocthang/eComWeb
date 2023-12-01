package com.webapp.clothes.service.Imp;

import com.webapp.clothes.dto.ProductAmount;
import com.webapp.clothes.entity.Product;
import com.webapp.clothes.mapper.ImageMapper;
import com.webapp.clothes.mapper.ProductMapper;
import com.webapp.clothes.repositories.ProductRepository;
import com.webapp.clothes.service.BillDetailService;
import com.webapp.clothes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    private ProductMapper mapperProduct;

    @Autowired
    private ImageMapper mapperImage;
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
            countProductByCategory.add(new ProductAmount(Integer.valueOf(obj[0].toString()), obj[1].toString(), Integer.valueOf(obj[2].toString()),
                    obj[3].toString()));
        });
        return countProductByCategory;
    }

    @Override
    public List<Product> getTopProductLimit(Integer limit) {
        List<Product> listTopProductLimit = productRepository.getTopProduct();
        if(listTopProductLimit.size() > limit && listTopProductLimit.size() > 0)
            return listTopProductLimit.stream().limit(limit).collect(Collectors.toList());
        else
            return listTopProductLimit;
    }


    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Integer> countProductByColor() {
        List<Integer> listCountProductByColor = productRepository.countProductByColor();

        return listCountProductByColor;
    }

    @Override
    public List<Product> filterPrice(String typePrice) {
        if(typePrice.equals("1")) {
            return productRepository.findByPriceBetween(0, 399999);
        } else if (typePrice.equals("2")) {
            return productRepository.findByPriceBetween(400000, 699999);
        }
        else {
            return productRepository.findByPriceGreaterThan(700000);
        }
    }

    @Override
    public List<Product> filterColor(String typeColor) {
        return productRepository.findByColor(typeColor);
    }

    @Override
    public List<Product> filterCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public static Object handleNull(Object input, Object defaultValue) {
        if (input == null || input.equals("")) {
            return defaultValue;
        } else {
            return input;
        }
    }

    @Override
    public Integer getPageAmount(String showing, Integer productAmount) {
        Integer showingNew = Integer.valueOf(handleNull(showing, "3").toString());
        Integer pageAmount = productAmount / showingNew;
        if(productAmount % showingNew != 0) {
            pageAmount++;
        }
        return pageAmount;
    }

    @Override
    public List<Product> filterProductByCategoryAndPriceAndColor(String categoryId, String typePrice, String typeColor, String typeSort) {
        List<Product> listFilterProduct = new ArrayList<>();

        // handleNull
        Integer categoryIdNew = Integer.valueOf(handleNull(categoryId, "-1").toString());
        typePrice = handleNull(typePrice, "").toString();
        typeColor = handleNull(typeColor, "").toString();
        typeSort = handleNull(typeSort, "").toString();

        if(typeSort.equals("")) {
            System.out.println("co");
            listFilterProduct = productRepository.findAll();
        }
        else if(typeSort.equals("CheapProduct")) {
            Sort sort = Sort.by(Sort.Direction.ASC, "price");
            listFilterProduct = productRepository.findAll(sort);
        }
        else { // Lasted Product
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            listFilterProduct = productRepository.findAll(sort);
        }

        // Filter theo categoryId
        if(categoryIdNew != -1) {
            List<Product> listFilterCategory = filterCategory(categoryIdNew);
            listFilterProduct = listFilterProduct.stream()
                    .filter(listFilterCategory::contains)
                    .collect(Collectors.toList());
        }


        // Filter theo color
        if(!typeColor.equals("")) {
            List<Product> listFilterColor = filterColor(typeColor);
            listFilterProduct = listFilterProduct.stream()
                    .filter(listFilterColor::contains)
                    .collect(Collectors.toList());
        }

        // Filter theo price
        if (!typePrice.equals("")) {
            List<Product> listFillterPrice = filterPrice(typePrice);
            listFilterProduct = listFilterProduct.stream()
                    .filter(listFillterPrice::contains)
                    .collect(Collectors.toList());
        }

        // Hiện thị các phần tử trong pageCurrent và hiển thị ra showing phần tử


        return listFilterProduct;
    }

    @Override
    public List<Integer> countProductByPrice() {
        List<Object[]> list = productRepository.countProductByPrice();
        // List về 2 cột, cột 1 chứa price-range (400k - 700k, 100k - 400k, Over 700k), cột 2 là số lượng sản phẩm tương ứng
        List<Integer> listCountProductByPrice = new ArrayList<>();
        list.forEach(obj -> {
            listCountProductByPrice.add(Integer.valueOf(obj[1].toString()));
        });
        return listCountProductByPrice;
    }
    @Override
    public List<Product> getRelatedProduct(Integer id, String brand) {
        // 6 Product
        // Get san pham co cung brand va duoc mua nhieu nhat. If khong du 6 product lay them san pham cung brand
        List<Object[]> listSumProductBought = billDetailService.getSumProductBought(brand);
        List<Product> listRelatedProduct = new ArrayList<>();
        listSumProductBought.forEach(obj -> {
            Product product = productRepository.findById(Integer.valueOf(obj[0].toString())).get();
            listRelatedProduct.add(product);
        });

        if (listRelatedProduct.size() < 6) {
            List<Product> listProductByBrand = productRepository.findByBrand(brand);
            for(Product p : listProductByBrand) {
                boolean exists = listRelatedProduct
                        .stream()
                        .anyMatch(product -> product.getProductName().equals(p.getProductName()));
                if (!exists)
                    listRelatedProduct.add(p);
                if (listRelatedProduct.size() == 6)
                    break;
            }
        }

        return listRelatedProduct;
    }

    @Override
    public List<Product> filterByShowingAndPageCurrent(String showing, String pageCurrent, List<Product> listProduct) {
        Integer pageCurrentNew = Integer.valueOf(handleNull(pageCurrent, "1").toString());
        Integer showingNew = Integer.valueOf(handleNull(showing, "3").toString());

        if(showingNew*pageCurrentNew <= listProduct.size())
            listProduct = listProduct
                    .subList((pageCurrentNew-1)*showingNew, (showingNew*pageCurrentNew));
        else
            listProduct = listProduct.subList((pageCurrentNew-1)*showingNew, listProduct.size());
        return listProduct;
    }

}
