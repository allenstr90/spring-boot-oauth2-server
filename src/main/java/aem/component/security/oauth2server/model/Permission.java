package aem.component.security.oauth2server.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "oauth2_permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
