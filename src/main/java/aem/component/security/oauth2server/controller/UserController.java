package aem.component.security.oauth2server.controller;

import aem.component.security.oauth2server.config.SystemConfig;
import aem.component.security.oauth2server.dto.PaginatedData;
import aem.component.security.oauth2server.dto.UserDTO;
import aem.component.security.oauth2server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = SystemConfig.BASE_PATH + "/users",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<PaginatedData<UserDTO>> getUsers(@SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
            @SortDefault(sort = "username", direction = Sort.Direction.ASC)}) Pageable pageable) {
        return ResponseEntity.ok(new PaginatedData<>(userService.getAll(pageable)));
    }
}
