package pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    @Column(name="content", length = 500, nullable = false)
    private String content;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    private Date publicationDate;

    @Min(value = 1)
    @Max(value = 10001)
    @Column(name="upvote", nullable = false)
    private Integer upVote;

    @Min(value = 1)
    @Max(value = 10001)
    @Column(name="downvote", nullable = false)
    private Integer downVote;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

}
