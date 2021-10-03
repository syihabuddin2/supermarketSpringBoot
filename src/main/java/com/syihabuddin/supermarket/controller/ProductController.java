package com.syihabuddin.supermarket.controller;

import java.util.List;

import javax.validation.Valid;

import com.syihabuddin.supermarket.model.Product;
import com.syihabuddin.supermarket.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Syihabuddin on 2021-09-19 22:04:59
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    // @GetMapping("/list")
    // public String findProductList(Model model) {
    // List<Product> productList = productService.getAll();
    // model.addAttribute("productList", productList);
    // return "admin/catalog/listproduct";
    // }

    @GetMapping("/list")
    public String loadListProduct(Model model) {
        List<Product> listProduct = productService.getAll();
        model.addAttribute("listProduct", listProduct);
        return "admin/catalog/listproduct";
    }

    // @GetMapping("/new")
    // public String addProductUI(Model model) {
    // model.addAttribute("addproductObject", new Product());
    // return "admin/catalog/addproduct";
    // }

    @GetMapping("/add")
    public String loadAddProduct(Product product, Model model) {
        model.addAttribute("product", product);
        return "admin/catalog/addproduct";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.editProduct(id);
        model.addAttribute("product", product);

        return "admin/catalog/addproduct";
    }

    // @PostMapping("/new")
    // public String addProduct(@Valid @ModelAttribute("product") Product
    // addproduct, BindingResult bindingResult,
    // Errors errors, Model model) {

    // // if (errors.hasErrors()) {
    // // model.addAttribute("addproductObject", addproduct);
    // // return "product/new";
    // // }
    // model.addAttribute("product", addproduct);
    // productService.addProduct(addproduct);
    // return "redirect:/product/list";
    // }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("product", product);
            return "admin/catalog/addproduct";
        }

        model.addAttribute("product", product);
        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        productService.deleteProduct(id);

        return "redirect:/product/list";
    }

}