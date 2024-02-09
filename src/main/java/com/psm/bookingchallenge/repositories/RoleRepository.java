package com.psm.bookingchallenge.repositories;

import com.psm.bookingchallenge.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByUsername(String username);
    List<Role> findByUserId(Long userId);
}
