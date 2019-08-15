package aem.component.security.oauth2server.dto;

import aem.component.security.oauth2server.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
