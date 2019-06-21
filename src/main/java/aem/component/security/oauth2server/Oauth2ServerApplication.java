package aem.component.security.oauth2server;

import aem.component.security.oauth2server.model.Oauth2Client;
import aem.component.security.oauth2server.model.Permission;
import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.repositories.Oauth2ClientRepository;
import aem.component.security.oauth2server.repositories.PermissionRepository;
import aem.component.security.oauth2server.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PermissionRepository permissionRepository,
                                               PasswordEncoder passwordEncoder, Oauth2ClientRepository oauth2ClientRepository) {
        return args -> {
            Permission permission = new Permission();
            permission.setName("admin".toUpperCase());
            permissionRepository.save(permission);

            User user = new User();
            user.setUsername("allens");
            user.setPassword(passwordEncoder.encode("allens"));
            user.setPermissions(Stream.of(permission).collect(Collectors.toSet()));
            userRepository.save(user);

            Oauth2Client oauth2Client = new Oauth2Client();
            oauth2Client.setClient_id("oauth2");
            oauth2Client.setClient_secret(passwordEncoder.encode("oauth2secret"));
            oauth2Client.setScope("read,write");
            oauth2Client.setAccess_token_validity(3600);
            oauth2Client.setAutoapprove("true");
            oauth2Client.setAuthorized_grant_types("password,authorization_code,refresh_token,client_credentials");
            oauth2Client.setAuthorities("USER,ADMIN");
            oauth2Client.setRefresh_token_validity(2592000);
            oauth2Client.setResource_ids("oauth2Id");
            oauth2ClientRepository.save(oauth2Client);
        };
    }*/

}
