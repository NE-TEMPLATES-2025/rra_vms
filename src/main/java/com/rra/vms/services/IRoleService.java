package com.rra.vms.services;

import com.rra.vms.entities.Role;
import com.rra.vms.request.CreateRoleRequest;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    public List<Role> getRoles();
    public Role getRoleById(UUID roleID);
    public Role createRole(CreateRoleRequest createRoleRequest);
}
