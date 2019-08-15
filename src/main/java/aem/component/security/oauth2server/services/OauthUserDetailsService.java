package aem.component.security.oauth2server.services;

import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class OauthUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public OauthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername
                .map(user ->
                        new org.springframework.security.core.userdetails.User(
                                user.getUsername(), user.getPassword(),
                                user.getPermissions().stream()
                                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                                        .collect(Collectors.toSet())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
