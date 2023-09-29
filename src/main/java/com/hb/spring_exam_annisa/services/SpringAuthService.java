package com.hb.spring_exam_annisa.services;

import com.hb.spring_exam_annisa.models.Role;
import com.hb.spring_exam_annisa.models.User;
import com.hb.spring_exam_annisa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringAuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).get(0);

        if(user == null){
            throw  new UsernameNotFoundException("Invalid email and/or password3");
        }

        /*SimpleGrantedAuthority role = new SimpleGrantedAuthority("USER");
        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roleList.add(role);*/

        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();

        for(Role role: user.getRoles()){
            SimpleGrantedAuthority roleAuth = new SimpleGrantedAuthority(role.getRoleName());
            roleList.add(roleAuth);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roleList);
    }
}
