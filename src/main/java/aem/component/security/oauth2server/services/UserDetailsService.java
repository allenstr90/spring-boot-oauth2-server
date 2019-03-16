package aem.component.security.oauth2server.services;

import aem.component.security.oauth2server.model.Permission;
import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return getUserDetails(user);
    }

    private org.springframework.security.core.userdetails.User getUserDetails(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission permission : user.getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
