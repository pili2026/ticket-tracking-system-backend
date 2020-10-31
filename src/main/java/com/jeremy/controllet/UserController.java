package com.jeremy.controllet;


import com.jeremy.entity.User;
import com.jeremy.exception.InvalidValueException;
import com.jeremy.service.CustomLoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    CustomLoginUserDetailsService customLoginUserDetailsService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = customLoginUserDetailsService.findUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/add_user")
    public ResponseEntity<User> createUser(User user, BindingResult bindingResult) {
        User userExists = customLoginUserDetailsService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            throw new InvalidValueException("Invalid user info");
        } else {
            customLoginUserDetailsService.saveUser(user);
        }

        return ResponseEntity.ok(user);

    }

    @PutMapping(value = "/edit_user/{id}")
    public ResponseEntity<User> editUser(User user, @PathVariable("id") String id) {
        customLoginUserDetailsService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/delete_user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        customLoginUserDetailsService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
