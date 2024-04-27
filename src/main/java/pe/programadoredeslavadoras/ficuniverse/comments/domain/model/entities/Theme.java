package pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "theme")
public class Theme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @NotBlank // Validation: Objeto
  @Size(min = 2, max = 50)  // Validation: Objeto
  @Column(name="category", length = 50, nullable = false)
  private String category;

  @OneToMany(mappedBy = "theme")
  private List<Comment> comments;
}