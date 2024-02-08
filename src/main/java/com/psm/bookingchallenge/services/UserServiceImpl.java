package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.factories.dtos.RoleDTOFactory;
import com.psm.bookingchallenge.factories.dtos.UserDTOFactory;
import com.psm.bookingchallenge.factories.models.RoleFactory;
import com.psm.bookingchallenge.factories.models.UserFactory;
import com.psm.bookingchallenge.models.Role;
import com.psm.bookingchallenge.models.User;
import com.psm.bookingchallenge.models.UserSecurity;
import com.psm.bookingchallenge.repositories.RoleRepository;
import com.psm.bookingchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private UserDTOFactory userDTOFactory;
    @Autowired
    private RoleDTOFactory roleDTOFactory;
    @Autowired
    private RoleFactory roleFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> userDTOFactory.create(value)).orElse(null);
    }

    @Override
    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> userDTOFactory.create(value)).orElse(null);
    }

    @Override
    public UserSecurity getUserSecurity(String username) {
        Optional<User> userResponse = userRepository.findByUsername(username);
        if (userResponse.isPresent()) {
            User user = userResponse.get();
            UserSecurity userSecurity = new UserSecurity();
            userSecurity.setId(user.getId().toString());
            userSecurity.setEmail(user.getUsername());
            userSecurity.setuPassword(user.getPassword());
            userSecurity.setuAuthorities(generateGrantedAuthorities(roleDTOFactory.createList(user.getRoles())));
            return userSecurity;

        }
        return null;
    }

    @Override
    public List<RoleDTO> getAuthoritiesByUsername(String username) {
        List<Role> rolesResult = roleRepository.findByUsername(username);
        if (!rolesResult.isEmpty()) {
            return roleDTOFactory.createList(rolesResult);
        }
        return Collections.emptyList();
    }

    @Override
    public List<GrantedAuthority> generateGrantedAuthorities(List<RoleDTO> roleDTOs) {
        List<GrantedAuthority> roles =  new ArrayList<GrantedAuthority>();
        roleDTOs.forEach(roleDTO -> roles.add(new SimpleGrantedAuthority(roleDTO.getAuthority())));
        return roles;
    }

    @Override
    public List<String> validateUserData(UserDTO userDTO) {
        List<String> validationResult = new ArrayList<>();
        Optional<User> userFound = userRepository.findByUsername(userDTO.getUsername());
        if (userDTO.getId() == null) {
            if (userFound.isPresent()) {
                validationResult.add("Username already used. Choose another.");
            }
        }
        else {
            User user = userFound.get();
            if (userFound.isPresent() && user.getId() != userDTO.getId()) {
                validationResult.add("Username already used. Choose another.");
            }
        }
        return validationResult;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO.getId()==null) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        else {
            Optional<User> user = userRepository.findById(userDTO.getId());
            if (user.isEmpty()) {
                return null;
            }
            if (userDTO.getPassword() == null) {
                userDTO.setPassword(user.get().getPassword());
            }
            if (!user.get().getPassword().equals(userDTO.getPassword())) {
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

        }
        User user = userFactory.create(userDTO);
        if (user.getId() != null) {
            if (user.getRoles()==null || user.getRoles().isEmpty()) {
                user.setRoles(roleRepository.findByUserId(userDTO.getId()));
            }
            for (Role role: user.getRoles()) {
                role.setUsername(user.getUsername());
            }
        }

        user = userRepository.save(user);

        return userDTOFactory.create(user);
    }

    @Override
    public void saveRoles(String username, List<String> roles) {
        UserDTO user = getUser(username);
        roles.forEach(roleName -> {
            Role role = roleFactory.create(username, roleName, user.getId());
            roleRepository.save(role);
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOp = userRepository.findByUsername(username);
        if (userOp.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User user = userOp.get();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), generateGrantedAuthorities(roleDTOFactory.createList(user.getRoles())));

        return userDetails;
    }
}
