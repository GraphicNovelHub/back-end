package pe.programadoredeslavadoras.ficuniverse.creation.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.ChapterResource;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CreateChapterResource;
import pe.programadoredeslavadoras.ficuniverse.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;

public class ChapterMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Chapter toEntity(CreateChapterResource resource) {
        return this.mapper.map(resource, Chapter.class);
    }

    public ChapterResource toResource(Chapter chapter) {
        return this.mapper.map(chapter, ChapterResource.class);
    }
}
