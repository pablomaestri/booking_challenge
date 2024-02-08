package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> getRolesByUserId(Long userId);
}
