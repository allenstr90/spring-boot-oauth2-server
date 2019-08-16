package aem.component.security.oauth2server.model;

import aem.component.security.oauth2server.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "oauth2_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = Constants.USERNAME_PATTERN)
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private boolean active = false;

    private String activationCode;

    private String resetCode;

    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "oauth2_user_permission", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SELECT)
    private Set<Permission> permissions;

    public void addPermission(Permission permission) {
        if (this.permissions == null)
            this.permissions = new HashSet<>();
        this.permissions.add(permission);
    }
}
