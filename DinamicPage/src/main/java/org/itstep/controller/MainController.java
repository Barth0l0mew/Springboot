package org.itstep.controller;

import org.itstep.model.Product;
import org.itstep.repository.ProductRepository;
import org.itstep.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProductRepository productRepository;
        @GetMapping({"","/","index"})
        public String showIndex (@RequestParam(value = "menuselect", defaultValue ="users") String menuselect, Model model){
            System.out.println("===="+menuselect);
            if (menuselect.equals("users")){
                model.addAttribute("menuselect",menuselect);
                model.addAttribute("users", usersRepository.findAll());
            } else if (menuselect.equals("product")){
                model.addAttribute("menuselect",menuselect);
                model.addAttribute("products",productRepository.findAll());
            }
        return "index";
    }
}
