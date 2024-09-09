package org.itstep.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    @Autowired
    EmployerRepository employerRepository;

    public List<Employer> findAll(){
        return employerRepository.findAll();
    }

    public List<Employer> findByOrderBy(String property){
        return employerRepository.findAll(Sort.by(Sort.Direction.ASC, property));
    }
} 