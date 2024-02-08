package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.Role;
import com.psm.bookingchallenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserFactory {

    @Autowired
    private RoleFactory roleFactory;

    public User create(String username, String password, String name, String address, String phone, List<Role> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(roles);

        return user;
    }
    public User create(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getId()!=null) {
            user.setId(userDTO.getId());
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);
        if (userDTO.getRoles()!=null) {
            user.setRoles(roleFactory.createList(userDTO.getRoles()));
        }

        return user;
    }
}
