package pe.programadoredeslavadoras.ficuniverse.creation.resource;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResource {

  @NotNull
  @NotBlank
  @Size(min = 1, max = 500)
  private String content;

  @Min(value = 1)
  @Max(value = 10001)
  private Integer upVote;

  @Min(value = 1)
  @Max(value = 10001)
  private Integer downVote;

}
