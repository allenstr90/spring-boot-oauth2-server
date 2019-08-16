package aem.component.security.oauth2server.dto;

import aem.component.security.oauth2server.model.Permission;
import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.utils.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.USERNAME_PATTERN)
    private String username;

    private Set<String> roles;

    @Email
    private String email;

    private boolean active = false;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet());
        this.email = user.getEmail();
        this.active = user.isActive();
    }

    public UserDTO() {
        this.roles = new HashSet<>();
    }
}
