package pe.programadoredeslavadoras.ficuniverse.security.domain.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.programadoredeslavadoras.ficuniverse.security.resource.AuthResponse;
import pe.programadoredeslavadoras.ficuniverse.security.resource.CreateUserResource;
import pe.programadoredeslavadoras.ficuniverse.security.resource.LoginUserResource;

import java.io.IOException;

public interface AuthService {
    public abstract AuthResponse register(CreateUserResource createUserResource);

    public abstract AuthResponse login(LoginUserResource loginUserResource);

    public void validateRegisterRequest(CreateUserResource createUserResource);
    public void existsUserByEmail(CreateUserResource createUserResource);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
