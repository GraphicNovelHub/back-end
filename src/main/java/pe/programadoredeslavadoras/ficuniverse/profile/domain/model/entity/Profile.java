package pe.programadoredeslavadoras.ficuniverse.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.programadoredeslavadoras.ficuniverse.fanfic.domain.model.entitie.Fanfic;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "image_url", nullable = true, length = 200)
    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fanfic> fanfics;
}
