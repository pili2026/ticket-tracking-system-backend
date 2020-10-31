package com.jeremy.convert;

import com.jeremy.entity.User;
import com.jeremy.entity.UserResponse;

public class UserConverter {

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setFullName(user.getFullName());
        response.setEnabled(user.isEnabled());
        response.setRoles(user.getRoles());
        response.setUserRole(user.getUserRole());

        return response;
    }

}
