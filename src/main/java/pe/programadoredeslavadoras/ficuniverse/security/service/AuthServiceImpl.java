package pe.programadoredeslavadoras.ficuniverse.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.Roles;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.Token;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.TokenType;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;
import pe.programadoredeslavadoras.ficuniverse.security.domain.persistence.TokenRepository;
import pe.programadoredeslavadoras.ficuniverse.security.domain.persistence.UserRepository;
import pe.programadoredeslavadoras.ficuniverse.security.domain.service.AuthService;
import pe.programadoredeslavadoras.ficuniverse.security.domain.service.JwtService;
import pe.programadoredeslavadoras.ficuniverse.security.resource.AuthResponse;
import pe.programadoredeslavadoras.ficuniverse.security.resource.CreateUserResource;
import pe.programadoredeslavadoras.ficuniverse.security.resource.LoginUserResource;
import pe.programadoredeslavadoras.ficuniverse.shared.exception.ResourceValidationException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(CreateUserResource createUserResource) {
        var user = User.builder()
                .userName(createUserResource.getUserName())
                .email(createUserResource.getEmail())
                .password(passwordEncoder.encode(createUserResource.getPassword()))
                .role(Roles.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthResponse.builder()
                .id(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse login(LoginUserResource loginUserResource) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserResource.getEmail(),
                        loginUserResource.getPassword()
                )
        );
        var user =userRepository.findByEmail(loginUserResource.getEmail());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .id(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void validateRegisterRequest(CreateUserResource createUserResource) {
        if(createUserResource.getUserName() == null || createUserResource.getUserName().isEmpty()){
            throw new ResourceValidationException("UserName","El nombre de usuario debe ser obligatorio");
        }
        if(createUserResource.getUserName().length()>100){
            throw new ResourceValidationException("UserName", "El nombre de usuario no debe exceder los 50 caracteres");
        }
        if(createUserResource.getEmail() == null || createUserResource.getEmail().isEmpty()){
            throw new ResourceValidationException("Email","El correo de usuario  debe ser obligatorio");
        }
        if(createUserResource.getEmail().length()>100){
            throw new ResourceValidationException("Email", "El correo de usuario no debe exceder los 50 caracteres");
        }
        if(createUserResource.getPassword() == null || createUserResource.getPassword().isEmpty()){
            throw new ResourceValidationException("Password","La contraseña de usuario  debe ser obligatorio");
        }
        if(createUserResource.getPassword().length()>100){
            throw new ResourceValidationException("Password", "La contraseña de usuario no debe exceder los 100 caracteres");
        }
    }

    @Override
    public void existsUserByEmail(CreateUserResource createUserResource) {
        if(userRepository.existsByEmail(createUserResource.getEmail())){
            throw new ResourceValidationException("Email", "Ya existe un usuario con el email " + createUserResource.getEmail());
        }

    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String email;
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            return;
        }
        refreshToken = authHeader.substring(7);
        email = jwtService.extractUsername(refreshToken);
        if(email != null){
            var user = userRepository.findByEmail(email);
            if(jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authenticationResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);

            }
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
