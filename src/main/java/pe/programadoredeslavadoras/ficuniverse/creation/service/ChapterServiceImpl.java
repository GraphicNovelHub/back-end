package pe.programadoredeslavadoras.ficuniverse.creation.service;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.persistence.ChapterRepository;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.service.ChapterService;
import pe.programadoredeslavadoras.ficuniverse.store.domain.persistence.FanficRepository;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.ResourceValidationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final FanficRepository fanficRepository;
    private final Validator validator;

    @Transactional
    @Override
    public Chapter createChapter(Integer fanficId, Chapter chapter) {
        if(fanficRepository.existsById(fanficId)) {
            Set<ConstraintViolation<Chapter>> violation = validator.validate(chapter);
            if (violation.isEmpty()) {
                chapter.setFanfic(fanficRepository.findById(fanficId).orElseThrow());
                return chapterRepository.save(chapter);
            }
            throw new ResourceValidationException("Chapter",violation);
        }
        throw new ResourceValidationException("fanfic","Fanfic not found");
    }

    @Transactional
    @Override
    public Chapter update(Chapter chapter) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Chapter> fetchAll() {
        return chapterRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Chapter fetchById(Integer id) {
        if(chapterRepository.existsById(id)){
            return chapterRepository.findById(id).orElseThrow();
        }
        throw new FetchIdNotFoundException("Chapter", id);
    }

    @Transactional(readOnly = true)
    @Override
    public Chapter fetchByTitle(String title) {
        Optional<Chapter> optionalChapter = chapterRepository.sqlChapterByTitle(title);
        if(optionalChapter.isPresent()){
            return optionalChapter.get();
        }
        throw new FetchNotFoundException("Chapter"," title", title);

    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        if (chapterRepository.existsById(id)){
            chapterRepository.deleteById(id);
            if(chapterRepository.existsById(id)){
                return false;
            }
            return true;
        }
        throw new FetchIdNotFoundException("Chapter", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Chapter> fetchChaptersByFanficId(Integer fanficId) {
        //return chapterRepository.findChaptersByFanficId(fanficId);
        return chapterRepository.sqlChaptersByFanficId(fanficId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Chapter> fetchByChapterOrderBetween(Integer orderInit, Integer orderEnd) {
        return chapterRepository.sqlChaptersBetweenChapterOrder(orderInit,orderEnd);
        //return chapterRepository.findByChapterOrderBetween(orderInit,orderEnd);
    }
}
