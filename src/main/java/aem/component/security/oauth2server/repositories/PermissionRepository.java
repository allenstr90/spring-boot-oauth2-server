package aem.component.security.oauth2server.repositories;

import aem.component.security.oauth2server.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
