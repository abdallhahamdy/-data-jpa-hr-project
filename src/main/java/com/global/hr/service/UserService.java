package com.global.hr.service;

import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo UserRepo;

    public User findById (Long id) {

        return UserRepo.findById(id).orElseThrow();
    }

    public User insert (User emp) {

        return UserRepo.save(emp);
    }

    public User update (User emp) {

        User current = UserRepo.findById(emp.getId()).get();

        current.setUserName(emp.getUserName());
        current.setPassword(emp.getPassword());

        return UserRepo.save(emp);
    }

    public List<User> findAll() {
        return UserRepo.findAll();
    }
}
