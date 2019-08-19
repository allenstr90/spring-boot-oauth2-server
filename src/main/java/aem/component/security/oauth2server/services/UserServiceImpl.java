package aem.component.security.oauth2server.services;

import aem.component.security.oauth2server.config.SystemConfig;
import aem.component.security.oauth2server.dto.UserDTO;
import aem.component.security.oauth2server.exception.FieldExistException;
import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.repositories.PermissionRepository;
import aem.component.security.oauth2server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO save(UserDTO userDTO) throws FieldExistException {
        Optional<User> byUsername = userRepository.findByUsername(userDTO.getUsername());
        if (byUsername.isPresent())
            throw new FieldExistException("username", userDTO.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail.isPresent())
            throw new FieldExistException("email", userDTO.getEmail());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword("");
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            user.addPermission(permissionRepository.findByName(SystemConfig.USER));
        } else {
            userDTO.getRoles().stream()
                    .map(String::toUpperCase)
                    .map(permissionRepository::findByName)
                    .filter(Objects::nonNull)
                    .forEach(user::addPermission);
        }
        return new UserDTO(userRepository.save(user));
    }
}
