package com.boot.project.services;

import com.boot.project.entities.Role;
import com.boot.project.iservice.IRole;
import com.boot.project.repositories.RoleRepository;
import com.boot.project.utils.EnumRole;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService implements IRole {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @param roleRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role createRole(Map<String, Object> roleRequest) {
        final String roleName = (String) roleRequest.get("roleName");
        final String description = (String) roleRequest.get("description");

        try {
            EnumSet<EnumRole> enumRoles = EnumSet.allOf(EnumRole.class);
            Optional.ofNullable(enumRoles
                    .stream()
                    .anyMatch(x -> x.equals(EnumRole.valueOf(roleName))))
                    .orElseThrow();
        } catch (Exception exception) {
            throw new RuntimeException("Cannot find any role name in data source");
        }

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setDescription(description);
        role.setRoleName(EnumRole.valueOf(roleName));

        return roleRepository.save(role);
    }

    /**
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
