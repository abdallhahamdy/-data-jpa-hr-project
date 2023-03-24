package com.global.hr.service;

import com.global.hr.entity.Role;
import com.global.hr.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo RoleRepo;

    public Role findById (Long id) {

        return RoleRepo.findById(id).orElseThrow();
    }

    public Role insert (Role role) {

        return RoleRepo.save(role);
    }

    public Role update (Role role) {

        Role current = RoleRepo.findById(role.getId()).get();

        current.setName(role.getName());

        return RoleRepo.save(role);
    }

    public List<Role> findAll() {
        return RoleRepo.findAll();
    }
}
