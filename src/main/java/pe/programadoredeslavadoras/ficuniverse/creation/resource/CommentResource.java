package pe.programadoredeslavadoras.ficuniverse.creation.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CommentResource {
  private String content;
  private Integer upVote;
  private Integer downVote;
}
