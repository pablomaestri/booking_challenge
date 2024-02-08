package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.models.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleFactory {

    public Role create(String username, String authority, Long userId) {

        Role role = new Role();
        role.setId(userId);
        role.setUsername(username);
        role.setAuthority(authority);
        role.setUserId(userId);
        return role;
    }

    public Role create(RoleDTO roleDTO) {
        Role role = new Role();
        role.setUsername(roleDTO.getUsername());
        role.setAuthority(roleDTO.getAuthority());
        role.setUserId(roleDTO.getUserId());
        return role;
    }

    public List<Role> createList(List<RoleDTO> roleDTOs) {
        List<Role> roles = new ArrayList<>();
        roleDTOs.forEach (
            authorityDTO -> roles.add(create(authorityDTO))
        );
        return roles;

    }
}
