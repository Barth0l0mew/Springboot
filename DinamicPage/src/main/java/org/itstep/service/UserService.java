package org.itstep.service;

import org.itstep.model.Users;
import org.itstep.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    public List<Users> findAll (){
        return usersRepository.findAll();
    }
    public Users findById (Long id){
        return usersRepository.findById(id).orElse(null);

    }
    public void save (Users user){
        usersRepository.save(user);
    }
}
