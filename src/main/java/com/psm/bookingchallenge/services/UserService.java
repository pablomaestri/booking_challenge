package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.UserSecurity;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserService {

    UserDTO getUser(String username);
    UserDTO getUser(Long id);
    UserSecurity getUserSecurity(String username);
    List<RoleDTO> getAuthoritiesByUsername(String username);
    List<GrantedAuthority> generateGrantedAuthorities(List<RoleDTO> roleDTOs);
    List<String> validateUserData(UserDTO userDTO);
    UserDTO saveUser(UserDTO userDTO);
    void saveRoles(String username, List<String> roles);

}
