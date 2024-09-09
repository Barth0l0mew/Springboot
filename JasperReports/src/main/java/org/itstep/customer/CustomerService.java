package org.itstep.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public List<Customer> findByOrderBy(String property){
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, property));
    }
} 