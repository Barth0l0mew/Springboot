package org.itstep.dbhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    public CountryService countryService;
    @GetMapping("/countries/all")
    public List<Country> getCountries(){
        var countries = countryService.findAll();
        return countries;
    }
}
