package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.factories.dtos.RoleDTOFactory;
import com.psm.bookingchallenge.factories.models.RoleFactory;
import com.psm.bookingchallenge.models.Role;
import com.psm.bookingchallenge.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleFactory roleFactory;
    @Autowired
    private RoleDTOFactory roleDTOFactory;


    @Override
    public List<RoleDTO> getRolesByUserId(Long userId) {
        List<Role> roles = roleRepository.findByUserId(userId);
        return roleDTOFactory.createList(roles);
    }
}
