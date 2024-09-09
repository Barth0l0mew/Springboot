package org.itstep.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value="/customers", produces="application/json")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }

    //Pathvariable
    //http://localhost:8080/customers/state
    @GetMapping (value = "/customers/{sort}", produces = "application/json")
    public List<Customer> getCustomerSort (@PathVariable String sort) {
        return customerService.findByOrderBy(sort);
    }
    //RequestPeram
    //http://localhost:8080/customers/?sort=company
    @GetMapping (value = "/customers/", produces = "application/json")
    public List<Customer> getCustomerSortRequestParam (@RequestParam String sort) {
        return customerService.findByOrderBy(sort);
    }
} 