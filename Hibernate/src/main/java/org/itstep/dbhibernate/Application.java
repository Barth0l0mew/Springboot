package org.itstep.dbhibernate;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CountryService countryService){
        Country country1= new Country(1L,"Kukuha","KU");
        List<Country> countries = new ArrayList<>();
        countries.add(country1);
        countryService.saveAll(countries);
        return args -> {

        };
    }
}