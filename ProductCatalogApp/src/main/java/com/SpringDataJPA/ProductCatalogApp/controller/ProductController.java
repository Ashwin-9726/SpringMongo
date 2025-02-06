package com.SpringDataJPA.ProductCatalogApp.controller;

import com.SpringDataJPA.ProductCatalogApp.entity.Product;
import com.SpringDataJPA.ProductCatalogApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Display main menu
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product("", 0, ""));
        return "add-product"; // Form to add product
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/display-products")
    public String displayProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "display-products"; // Display products in a table
    }

    @GetMapping("/category-products")
    public String displayProductsByCategory(@RequestParam("category") String category, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "display-products"; // Display products filtered by category
    }
}
