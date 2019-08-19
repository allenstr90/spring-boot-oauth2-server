package aem.component.security.oauth2server.controller;

import aem.component.security.oauth2server.config.SystemConfig;
import aem.component.security.oauth2server.dto.PaginatedData;
import aem.component.security.oauth2server.dto.UserDTO;
import aem.component.security.oauth2server.exception.FieldExistException;
import aem.component.security.oauth2server.services.UserService;
import aem.component.security.oauth2server.utils.Constants;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(
        path = SystemConfig.BASE_PATH + "/users",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@PreAuthorize("hasRole(\"" + SystemConfig.ADMIN + "\")")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PaginatedData<UserDTO>> getUsers(@SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
            @SortDefault(sort = "username", direction = Sort.Direction.ASC)}) Pageable pageable) {
        return ResponseEntity.ok(new PaginatedData<>(userService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException, FieldExistException {
        UserDTO user = userService.save(userDTO);
        return ResponseEntity.created(new URI(SystemConfig.BASE_PATH + "/users/" + userDTO.getUsername())).body(user);
    }

    @GetMapping(path = "{username:" + Constants.USERNAME_PATTERN + "}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        return ResponseEntity.of(userService.getUser(username));
    }
}
