package pe.programadoredeslavadoras.ficuniverse.comments.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities.Comment;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CommentResource;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CreateCommentResource;
import pe.programadoredeslavadoras.ficuniverse.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;

public class CommentMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public Comment toEntity(CreateCommentResource resource) {
    return this.mapper.map(resource, Comment.class);
  }

  public CommentResource toResource(Comment comment) {
    return this.mapper.map(comment, CommentResource.class);
  }
}
