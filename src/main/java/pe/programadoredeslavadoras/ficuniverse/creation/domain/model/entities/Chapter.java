package pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;

@Entity
@Getter
@Setter
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name= "title", length = 50, nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 70000)
    @Column(name= "content", length = 70000, nullable = false)
    private String content;

    @Min(value = 1)
    @Max(value = 200)
    @Column(name= "chapter_order")
    private Integer chapterOrder;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fanfic_id", referencedColumnName = "id")
    public Fanfic fanfic;
}
