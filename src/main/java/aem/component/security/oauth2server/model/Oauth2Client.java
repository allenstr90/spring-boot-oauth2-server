package aem.component.security.oauth2server.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "oauth_client_details")
@Data
public class Oauth2Client {
    @Id
    private String client_id;

    private String client_secret;
    private String resource_ids;
    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private Integer access_token_validity;
    private Integer refresh_token_validity;
    private String additional_information;
    private String autoapprove;
}
