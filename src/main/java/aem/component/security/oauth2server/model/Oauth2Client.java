package aem.component.security.oauth2server.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
@Data
public class Oauth2Client {
    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "resource_ids")
    private String resourceIds;

    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private Integer access_token_validity;
    private Integer refresh_token_validity;
    private String additional_information;
    private String autoapprove;
}
