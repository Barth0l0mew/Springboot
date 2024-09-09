package org.itstep.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByOrderBy(String property){
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, property));
    }
} 