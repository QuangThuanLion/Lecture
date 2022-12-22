package com.boot.project.repositories;

import com.boot.project.entities.Role;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
