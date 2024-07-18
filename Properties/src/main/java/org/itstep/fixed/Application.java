package org.itstep.fixed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@PropertySource(value = "classpath:app_app_us_US.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
    @RestController
    static class helloController{
        @Value("${app.hello}")
        private String hello;
        @Value("${app.name}")
        private String name;
        @RequestMapping("/")
        public String index() {
            return String.format("%s, %s!%n", hello, name);
        }
    }
}
