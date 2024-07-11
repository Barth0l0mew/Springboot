package org.itstep.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//1 вариант есть @Controller и @RestController
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
    @RequestMapping ("/")
private String helloWorld(){
        return "<h1>hello springboot</h1>";
    }
    // в mapping не должно быть одинаковых путей
    @RequestMapping ("/{name}")
    private String helloName(@PathVariable("name") String name){
        return String.format("<h1>hello springboot %s </h1>",name);
    }
    //http://localhost:8080/query?name=ddd
    @RequestMapping ("/query")
    private String helloName2(@RequestParam("name") String name){
        return String.format("<h1>hello springboot %s </h1>",name);
    }
    /*
    //2 вариант
    @RestController
    static class Controller{

    }*/

}
