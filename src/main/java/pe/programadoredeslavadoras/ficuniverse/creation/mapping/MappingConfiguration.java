package pe.programadoredeslavadoras.ficuniverse.creation.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.programadoredeslavadoras.ficuniverse.comments.mapping.CommentMapper;

@Configuration("creationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ChapterMapper chapterMapper() {
        return new ChapterMapper();
    }

    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper();
    }
}
