package pe.programadoredeslavadoras.ficuniverse.security.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResource {
    private String userName;
    private String email;
    private String password;
    private String role;
}
