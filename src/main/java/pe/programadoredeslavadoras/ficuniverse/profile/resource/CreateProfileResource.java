package pe.programadoredeslavadoras.ficuniverse.profile.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileResource {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String userName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String email;

    private String imageUrl;

}
