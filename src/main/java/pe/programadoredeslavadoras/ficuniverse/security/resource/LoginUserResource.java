package pe.programadoredeslavadoras.ficuniverse.security.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserResource {
    private String email;
    private String password;
}
