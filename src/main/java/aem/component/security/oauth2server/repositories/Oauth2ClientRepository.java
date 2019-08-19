package aem.component.security.oauth2server.repositories;

import aem.component.security.oauth2server.model.Oauth2Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2Client, Long> {
    Optional<Oauth2Client> findByClientId(String clientId);
}
