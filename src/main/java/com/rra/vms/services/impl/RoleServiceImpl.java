package com.rra.vms.services.impl;


import com.rra.vms.entities.Role;
import com.rra.vms.entities.User;
import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.mappers.RoleMapper;
import com.rra.vms.repository.RoleRepository;
import com.rra.vms.request.CreateRoleRequest;
import com.rra.vms.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(UUID roleID) {
        return roleRepository.findById(roleID).orElseThrow(
                ()-> new ResourceNotFoundException("Role not found")
        );
    }

    @Override
    public Role createRole(CreateRoleRequest createRoleRequest) {
        Role role= roleMapper.toRole(createRoleRequest);
       return roleRepository.save(role);
    }
}
