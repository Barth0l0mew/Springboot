package org.itstep.service;

import org.itstep.model.User;
import org.itstep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return (Page<User>) userRepository.findAll(pageable);
    }

    public List<User> findByOrderByName(Sort sort){
        //return userRepository.findByOrderByName();
        return userRepository.findAll(sort);
    }
    public Page<User> getAllComplains (int page, int size, String name, String order){
        Sort.Direction direction = Sort.Direction.ASC; // по возростанию
        if (order.equals("desc"))
            direction = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,name));
        return userRepository.findAll(pageable);
    }
}
