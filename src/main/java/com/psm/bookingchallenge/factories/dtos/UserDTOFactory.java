package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOFactory {

    @Autowired
    private RoleDTOFactory roleDTOFactory;



    public UserDTO create(Long id, String username, String password, String name, String address, String phone,
                          Boolean active, List<RoleDTO> rolesDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setName(name);
        userDTO.setAddress(address);
        userDTO.setPhone(phone);
        userDTO.setActive(active);
        userDTO.setRoles(rolesDTO);

        return userDTO;
    }


    public UserDTO create(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setActive(user.isActive());
        if (user.getRoles()!=null) {
            userDTO.setRoles(roleDTOFactory.createList(user.getRoles()));
        }

        return userDTO;
    }

    public List<UserDTO> createList(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(
            user -> usersDTO.add(create(user))
        );
        return usersDTO;
    }
}
