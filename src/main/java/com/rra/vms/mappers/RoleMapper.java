package com.rra.vms.mappers;


import com.rra.vms.entities.Role;
import com.rra.vms.request.CreateRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Role toRole(CreateRoleRequest createRoleRequest) {
        return Role.
                builder()
                .name(createRoleRequest.name())
                .build();


    }

}
