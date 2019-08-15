package aem.component.security.oauth2server.services;

import aem.component.security.oauth2server.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> getAll(Pageable pageable);
}
