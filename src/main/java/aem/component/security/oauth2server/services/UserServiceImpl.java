package aem.component.security.oauth2server.services;

import aem.component.security.oauth2server.config.SystemConfig;
import aem.component.security.oauth2server.dto.UserDTO;
import aem.component.security.oauth2server.model.User;
import aem.component.security.oauth2server.repositories.PermissionRepository;
import aem.component.security.oauth2server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword("");
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            user.addPermission(permissionRepository.findByName(SystemConfig.USER));
        }
        return new UserDTO(userRepository.save(user));
    }
}
