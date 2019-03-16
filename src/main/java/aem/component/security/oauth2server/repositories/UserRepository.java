package aem.component.security.oauth2server.repositories;

import aem.component.security.oauth2server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
