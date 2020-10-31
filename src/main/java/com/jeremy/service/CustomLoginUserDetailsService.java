package com.jeremy.service;


import com.jeremy.entity.Role;
import com.jeremy.entity.User;
import com.jeremy.repository.RoleRepository;
import com.jeremy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomLoginUserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public List<User> findRDUsers() {
        return userRepository.findUserByUserRole("RD");
    }


    public void saveUser(User user) {
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setEnabled(true);
        user.setFullName(user.getFullName());
        Role userRole = roleRepository.findByRole(user.getUserRole());
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    public void updateUser(String id, User user) {
        User oldUser = findUserById(id);
        user.setId(oldUser.getId());
        userRepository.save(user);

    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
