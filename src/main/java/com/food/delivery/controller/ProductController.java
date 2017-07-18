package com.food.delivery.controller;

import com.food.delivery.config.RootConfig;
import com.food.delivery.model.Product;
import com.food.delivery.service.IngredientService;
import com.food.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Created by mz on 17/07/17.
 */
@Controller
public class ProductController {

    private final ProductService productService;
    private final IngredientService ingredientService;

    @Autowired
    public ProductController(ProductService productService, IngredientService ingredientService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
        productService.save(RootConfig.PRODUCT_01);
        productService.save(RootConfig.PRODUCT_02);
        productService.save(RootConfig.PRODUCT_03);
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "product";
    }

    /**
     * Read all active products.
     */
    @RequestMapping("/products")
    public String readAll(Model model) {
        model.addAttribute("products", productService.findAll().stream().filter(Product::isActive).collect(toList()));
        return "products";
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
    public String getAddNew(Model model) {
        Product p = new Product();
        model.addAttribute("newProduct", p);
//        model.addAttribute("availableIngredients", ingredientService.findAll());
        return "addProduct";
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public String processAddNew(@ModelAttribute Product newProduct) {
        productService.save(newProduct);
        return "redirect:/admin/products/add";
    }
}
