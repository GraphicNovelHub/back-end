package pe.programadoredeslavadoras.ficuniverse.comments.domain.service;

import pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    Comment update(Comment comment);

    List<Comment> fetchAll();

    Comment fetchById(Integer id);

    boolean deleteById(Integer id);
    List<Comment> fetchCommentsByThemeId(Integer tagId);

    List<Comment> fetchCommentsByupVotes(Integer upVote, Integer downVote);
}
