package org.itstep.dbhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller

public class CountryControllerFree {
    @Autowired
    public CountryService countryService;
    @GetMapping("/countries/all")
    public ModelAndView getCountries(){
        var countries = countryService.findAll();
        var params = new HashMap<String, Object>();
        params.put("countries",countries);
        return new ModelAndView("showCountries",params);
    }
    @GetMapping ("/countries/a")
    public ModelAndView getCountriesA(){
        var countries = countryService
                .findAll()
                .stream()
                .filter(country -> country.getName().charAt(0)=='A')
                .collect(Collectors.toList());
        var params = new HashMap<String, Object>();
        params.put("countries",countries);
        return new ModelAndView("showCountriesA",params);
    }
    @GetMapping("/countries/page")
    public ModelAndView getCountriesPage(@RequestParam(defaultValue = "0" ,name="page") int page,
                                          @RequestParam(defaultValue = "10",name = "size") int size){
        //http://localhost:8080/countries/page
        //http://localhost:8080/countries/page?page=1&size=20
        var countries = countryService
                .findAll(page, size);

        var params = new HashMap<String, Object>();
        params.put("countries",countries);
        return new ModelAndView("showCountriesPage",params);
    }

}
