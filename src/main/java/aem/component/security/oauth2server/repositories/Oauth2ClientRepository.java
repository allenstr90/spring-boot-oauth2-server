package aem.component.security.oauth2server.repositories;

import aem.component.security.oauth2server.model.Oauth2Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2Client, Long> {
}
