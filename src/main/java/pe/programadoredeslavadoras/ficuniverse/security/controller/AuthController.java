package pe.programadoredeslavadoras.ficuniverse.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.security.domain.service.AuthService;
import pe.programadoredeslavadoras.ficuniverse.security.resource.AuthResponse;
import pe.programadoredeslavadoras.ficuniverse.security.resource.CreateUserResource;
import pe.programadoredeslavadoras.ficuniverse.security.resource.LoginUserResource;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ficuniverse/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    //URL: http://localhost:8090/api/ficuniverse/v1/auth/register
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody CreateUserResource createUserResource){
        authService.existsUserByEmail(createUserResource);
        authService.validateRegisterRequest(createUserResource);
        AuthResponse authResponse = authService.register(createUserResource);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    //URL: http://localhost:8090/api/ficuniverse/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserResource loginUserResource){
        AuthResponse authResponse = authService.login(loginUserResource);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

}
