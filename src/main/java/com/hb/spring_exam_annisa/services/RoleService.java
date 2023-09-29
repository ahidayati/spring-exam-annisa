package com.hb.spring_exam_annisa.services;

import com.hb.spring_exam_annisa.models.Role;
import com.hb.spring_exam_annisa.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public void createRole(Role role){
        roleRepository.save(role);
    }
}
