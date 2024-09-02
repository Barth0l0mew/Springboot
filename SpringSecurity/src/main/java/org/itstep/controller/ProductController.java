package org.itstep.controller;

import org.itstep.model.Product;
import org.itstep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping({"","/"})
    public String home(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
     return "index";
    }
    @RequestMapping("/login_custom")
    public String loginCustom(Model model) {

        return "login_custom";
    }
    @RequestMapping("/logout_custom")
    public String logoutCustom(Model model) {

        return "logout_custom";
    }
    @RequestMapping("/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/save", method =
            RequestMethod.POST
    )
    public String saveProduct(@ModelAttribute("product") Product product) {

        productService.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.getById(id);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping(value = "/403")
    public String denied(){
        return "403";
    }
} 