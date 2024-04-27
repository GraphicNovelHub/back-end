package pe.programadoredeslavadoras.ficuniverse.comments.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities.Comment;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.persistence.CommentRepository;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.service.CommentService;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;

import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.ResourceValidationException;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final Validator validator;


    @Transactional
    @Override
    public Comment save(Comment comment) {
        Set<ConstraintViolation<Comment>> violation = validator.validate(comment);
        if(violation.isEmpty()){
            return commentRepository.save(comment);
        }
        throw new ResourceValidationException("Comment",violation);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> fetchAll() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment fetchById(Integer id) {
        if(commentRepository.existsById(id)){
            return commentRepository.findById(id).orElseThrow();
        }
        throw new FetchIdNotFoundException("Comment", id);
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            if(commentRepository.existsById(id)){
                return false;
            }
            return true;
        }
        throw new FetchIdNotFoundException("Comment", id);
    }

    @Override
    public List<Comment> fetchCommentsByThemeId(Integer themeId) {
        return commentRepository.sqlCommentsByThemeId(themeId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> fetchCommentsByupVotes(Integer upVote, Integer downVote) {
        return commentRepository.sqlCommentsByupVotes(upVote,downVote);
    }

}
