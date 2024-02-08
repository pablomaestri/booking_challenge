package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.models.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDTOFactory {

    public RoleDTO create(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setUsername(role.getUsername());
        roleDTO.setAuthority(role.getAuthority());
        roleDTO.setUserId(role.getUserId());

        return roleDTO;
    }

    public List<RoleDTO> createList(List<Role> roles)

    {
        List<RoleDTO> roleDTOs = new ArrayList<>();
        roles.forEach(
            role -> roleDTOs.add(create(role))
        );
        return roleDTOs;
    }


}
