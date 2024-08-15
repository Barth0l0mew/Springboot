package org.itstep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
@GetMapping(value = {"/","/index"})
    public String index (){
    return "index";
}
    @PostMapping(value = {"/","/index"})
    public RedirectView upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","Success uploaded "+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        File uploadedDirectory = new File("Spring-upload/src/main/resources/uploaded");
        System.out.println(uploadedDirectory);
        try {
            file.transferTo(new File(uploadedDirectory.getAbsolutePath()+"/"+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  new RedirectView("index");
    }
    @PostMapping (value = "multi")
    public RedirectView uploadMulti(@RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","Success uploaded ");
        for (int i=0;i<files.length;i++){
            System.out.println(i+"  "+files[i].getOriginalFilename());
        String fileName = files[i].getOriginalFilename();
        File uploadedDirectory = new File("Spring-upload/src/main/resources/uploaded");
        System.out.println(uploadedDirectory);
        try {
            files[i].transferTo(new File(uploadedDirectory.getAbsolutePath()+"/"+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        return  new RedirectView("index");
    }
}
