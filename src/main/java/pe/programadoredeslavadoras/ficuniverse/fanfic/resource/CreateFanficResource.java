package pe.programadoredeslavadoras.ficuniverse.fanfic.resource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateFanficResource {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String author;

    private String summary;

    @Past
    private Date publicationDate;

    @Size(min = 4, max = 20)
    private String language;

    @Size(min = 5, max = 30)
    private String status;

    private String thumbnail;
    private Long favorites;
    private Long views;

}
