package org.itstep.dbhibernate;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CountryService countryService) throws IOException {
//        Country country1= new Country(1L,"Kukuha","KU");
//        List<Country> countries = new ArrayList<>();
//        countries.add(country1);
//        countryService.saveAll(countries);
//        return args -> {
//
//        };
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();

        List<Country> countries = mapper.readValue(Paths.get("Hibernate/src/main/resources/static/countries.json").toFile(),
                new TypeReference<List<Country>>() {
                });
            //countries.stream().forEach(System.out::Println);
        countryService.saveAll(countries);
        System.out.println("Countries saved!");
        return args -> {

        };
    }
}