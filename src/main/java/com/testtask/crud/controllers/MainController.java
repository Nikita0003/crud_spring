package com.testtask.crud.controllers;

import com.testtask.crud.models.Product;
import com.testtask.crud.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Список товаров");
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);

        return "product-edit";
    }

    @PostMapping("/{id}/edit")
    public String productUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String quantity, @RequestParam String price, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(name);
        product.setQuantity(Integer.parseInt(quantity));
        product.setPrice(Integer.parseInt(price));
        productRepository.save(product);

        return "redirect:/";
    }

    @PostMapping("/{id}/remove")
    public String productDelete(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);

        return "redirect:/";
    }

}