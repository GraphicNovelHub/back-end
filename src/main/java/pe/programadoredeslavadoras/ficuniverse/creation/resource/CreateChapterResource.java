package pe.programadoredeslavadoras.ficuniverse.creation.resource;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateChapterResource {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 70000)
    private String content;

    @Min(value = 1)
    @Max(value = 200)
    private Integer chapterOrder;
}
