package pe.programadoredeslavadoras.ficuniverse.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;
import pe.programadoredeslavadoras.ficuniverse.security.domain.service.UserService;
import pe.programadoredeslavadoras.ficuniverse.security.mapping.UserMapper;
import pe.programadoredeslavadoras.ficuniverse.security.resource.UserResource;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ficuniverse/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers().stream()
                .map(this::convertToResource)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable(name = "userId") Integer userId){
        try{
            return new ResponseEntity<>(convertToResource(userService.getUserById(userId)),HttpStatus.OK);
        }catch (Exception e) {
            throw new FetchIdNotFoundException("user",userId);
        }

    }

private UserResource convertToResource(User user){
    return userMapper.toResource(user);
}



}
