package org.itstep.controller;

import org.itstep.model.User;
import org.itstep.repository.UserRepository;
import org.itstep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> users(){
        return userService.findAll();
    }
    @GetMapping ("users/page1")
        public Page<User> page1(){
            Pageable pageable = PageRequest.of(0,10);
            return userService.findAll(pageable);
        }
    @GetMapping (value="users/page", params = {"page", "size"})
    public Page<User> pageUsers (@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        return userService.findAll(pageable);
        //http://localhost:8080/users?page=1&size=10
    }

    @GetMapping (value="users/offset", params = {"offset", "limit"})
    public Page<User> pageUsers2 (@RequestParam("offset") int offset, @RequestParam("limit") int limit){
        Pageable pageable = PageRequest.of(offset/limit,limit);
        return userService.findAll(pageable);

        //http://localhost:8080/users/offset?offset=110&limit=10
    }

    @GetMapping (value = "/users/sort", params = {"name","order"})
    public List<User> sortUser (@RequestParam("name") String name, @RequestParam ("order") String order){
        Sort.Direction direction = Sort.Direction.ASC; // по возростанию
        if (order.equals("desc"))
            direction = Sort.Direction.DESC;
        return userService.findByOrderByName(Sort.by(direction,name));
   //http://localhost:8080/users/sort?name=name&order=desc
    }
    @GetMapping (value="users/pagesort", params = {"page", "size","name","order"})
    public Page<User> pageSortUsers (@RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @RequestParam("name") String name,
                                     @RequestParam ("order") String order){

        return userService.getAllComplains(page,size,name,order);

        //http://localhost:8080/users/pagesort?page=1&size=11&name=surname&order=desc
    }

        //page-size - рамерность элементов на страницы, offset-limit - семщение и колличество элементов на страницу


}
