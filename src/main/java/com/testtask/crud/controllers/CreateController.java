package com.testtask.crud.controllers;

import com.testtask.crud.models.Product;
import com.testtask.crud.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/create")
    public String createMain(Model model) {
        model.addAttribute("title", "Добавление товара");
        return "create-page";
    }

    @PostMapping("/create")
    public String productAdd(@RequestParam String name, @RequestParam String quantity, @RequestParam String price, Model model) {
        Product product = new Product(name, Integer.parseInt(quantity), Integer.parseInt(price));
        productRepository.save(product);
        return "redirect:/";
    }
}
