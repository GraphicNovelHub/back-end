package pe.programadoredeslavadoras.ficuniverse.profile.resource;

import lombok.*;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;

import java.util.List;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResource {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private List<Fanfic> fanfics;

}
