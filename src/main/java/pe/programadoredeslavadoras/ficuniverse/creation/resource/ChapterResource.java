package pe.programadoredeslavadoras.ficuniverse.creation.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterResource {
    private Integer id;
    private String title;
    private String content;
    private Integer chapterOrder;
}
