package pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;
import pe.programadoredeslavadoras.ficuniverse.profile.domain.model.entity.Profile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fanfics")
public class Fanfic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "author", length = 50, nullable = false)
    private String author;


    @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
    private String summary;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Size(min = 4, max = 20)
    @Column(name = "language")
    private String language;

    @Column(name = "status", nullable = false)
    private String status;


    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "favorites")
    private Long favorites;

    @Column(name = "views")
    private Long views;

    @JsonIgnore
    @OneToMany(mappedBy = "fanfic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id", referencedColumnName = "id")
    public Profile profile;
}
