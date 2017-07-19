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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

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

        // temporary data for manual testing purpose
        productService.save(RootConfig.PRODUCT_01);
        productService.save(RootConfig.PRODUCT_02);
        productService.save(RootConfig.PRODUCT_03);
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "product";
    }

    @RequestMapping("/admin/product")
    public String getProductByIdForAdmin(@RequestParam("id") String id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "adminProduct";
    }

    /**
     * Read all active products.
     */
    @RequestMapping("/products")
    public String readAll(Model model) {
        model.addAttribute("products", productService.findAll().stream().filter(Product::isActive).collect(toList()));
        return "products";
    }

    /**
     * Read all products for Admin.
     */
    @RequestMapping(value = "/admin/products")
    public String readAllForAdmin(Model model) {
        model.addAttribute("products", productService.findAll());
        return "adminProducts";
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
    public String getAddNew(Model model) {
        Product p = new Product();
        model.addAttribute("newProduct", p);
        model.addAttribute("availableIngredients", ingredientService.findAll());
        return "addProduct";
    }

    /**
     * Creates a new product.
     *
     * @param request - Used to get the application path in the server.
     *                Spring will fill this request parameter with the actual HTTP request
     */
    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public String processAddNew(@ModelAttribute Product newProduct, HttpServletRequest request) {

        // Gets uploaded image
        MultipartFile image = newProduct.getImage();
        // The root directory of the application in the server
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (image != null && !image.isEmpty()) {
            try {
                // Saves the file in the server so it can be available under the root directory of the application at runtime
                image.transferTo(new File(rootDirectory + "resources/images/" + newProduct.getId() + ".jpg"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }

        productService.save(newProduct);
        return "redirect:/admin/products/add";
    }
}
