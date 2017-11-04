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
        productService.create(RootConfig.PRODUCT_01);
        productService.create(RootConfig.PRODUCT_02);
        productService.create(RootConfig.PRODUCT_03);
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String id, Model model) {
        model.addAttribute("product", productService.read(id));
        return "product";
    }

    @RequestMapping("/admin/product")
    public String getProductByIdForAdmin(@RequestParam("id") String id, Model model) {
        model.addAttribute("product", productService.read(id));
        return "adminProduct";
    }

    /**
     * Read all active products.
     */
    @RequestMapping("/products")
    public String readAll(Model model) {
        model.addAttribute("products", productService.readAll().stream().filter(Product::isActive).collect(toList()));
        return "products";
    }

    /**
     * Read all products for Admin.
     */
    @RequestMapping(value = "/admin/products")
    public String readAllForAdmin(Model model) {
        model.addAttribute("products", productService.readAll());
        return "adminProducts";
    }

    @RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
    public String getAddNew(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("availableIngredients", ingredientService.readAll());
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
        saveImageFile(newProduct, request);
        productService.create(newProduct);
        return "redirect:/admin/products/add";
    }

    @RequestMapping(value = "/admin/product/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam("id") String id, Model model) {
        model.addAttribute("updateProduct", productService.read(id));
        model.addAttribute("availableIngredients", ingredientService.readAll());
        return "updateProduct";
    }


    /**
     * Updates an existing product.
     *
     * @param request - Used to get the application path in the server.
     *                Spring will fill this request parameter with the actual HTTP request
     */
    @RequestMapping(value = "/admin/product/update", method = RequestMethod.POST)
    public String processUpdate(@ModelAttribute Product updatedProduct, HttpServletRequest request) {
        saveImageFile(updatedProduct, request);
        productService.update(updatedProduct);
        return "redirect:/admin/product?id=" + updatedProduct.getId();
    }

    @RequestMapping(value = "/admin/product/delete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id") String id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    /**
     * Saves image of a product in the server.
     *
     * @param request - Used to get the application path in the server.
     *                Spring will fill this request parameter with the actual HTTP request
     */
    private static void saveImageFile(Product product, HttpServletRequest request) {
        // Gets uploaded image
        MultipartFile image = product.getImage();
        // The root directory of the application in the server
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (image != null && !image.isEmpty()) {
            try {
                // Saves the file in the server so it can be available under the root directory of the application at runtime
                image.transferTo(new File(rootDirectory + "resources/images/" + product.getId() + ".jpg"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }
    }
}
