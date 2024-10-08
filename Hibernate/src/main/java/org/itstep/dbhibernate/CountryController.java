package org.itstep.dbhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    public CountryService countryService;
    @GetMapping("/countries/all")
    public List<Country> getCountries(){
        var countries = countryService.findAll();
        return countries;
    }
    @GetMapping ("/countries/a")
    public List<Country> getCountriesA(){
        var countries = countryService
                .findAll()
                .stream()
                .filter(country -> country.getName().charAt(0)=='A')
                .collect(Collectors.toList());
        return countries;
    }
    @GetMapping("/countries/page")
    public List<Country> getCountriesPage(@RequestParam(defaultValue = "0" ,name="page") int page,
                                          @RequestParam(defaultValue = "10",name = "size") int size){
        //http://localhost:8080/countries/page
        //http://localhost:8080/countries/page?page=1&size=20
        var countries = countryService
                .findAll(page, size);

        return countries;
    }

}
