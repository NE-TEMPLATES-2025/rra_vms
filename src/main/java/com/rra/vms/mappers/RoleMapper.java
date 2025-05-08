package com.rra.vms.mappers;


import com.rra.vms.entities.Role;
import com.rra.vms.repository.RoleRepository;
import com.rra.vms.request.CreateRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private RoleRepository roleRepository;

    public Role toRole(CreateRoleRequest createRoleRequest) {
        return Role.
                builder()
                .name(createRoleRequest.name())
                .build();


    }

}
