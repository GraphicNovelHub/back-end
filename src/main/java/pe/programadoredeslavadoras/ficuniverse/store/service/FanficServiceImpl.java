package pe.programadoredeslavadoras.ficuniverse.store.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;
import pe.programadoredeslavadoras.ficuniverse.store.domain.persistence.FanficRepository;
import pe.programadoredeslavadoras.ficuniverse.store.domain.service.FanficService;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.ResourceValidationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class FanficServiceImpl implements FanficService {
    private final FanficRepository fanficRepository;
    private final Validator validator;

    @Transactional
    @Override
    public Fanfic save(Fanfic fanfic) {
        Set<ConstraintViolation<Fanfic>> violation = validator.validate(fanfic);
        if (violation.isEmpty()) {
            return fanficRepository.save(fanfic);
        }
        throw new ResourceValidationException("Fanfic", violation);
    }

    @Transactional
    @Override
    public Fanfic update(Fanfic fanfic) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Fanfic> fetchAll() {
        return fanficRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Fanfic fetchById(Integer id) {
        return fanficRepository.findById(id)
                .orElseThrow(() -> new FetchIdNotFoundException("Fanfic", id));
    }

    @Transactional(readOnly = true)
    @Override
    public Fanfic findByTitle(String title) {
        Optional<Fanfic> optionalFanfic = fanficRepository.sqlFanficByTitle(title);
        if (optionalFanfic.isPresent()){
            return optionalFanfic.get();
        }
        throw new FetchNotFoundException("Fanfic", "title", title);
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        if (fanficRepository.existsById(id)) {
            fanficRepository.deleteById(id);
            return !fanficRepository.existsById(id);
        }
        throw new FetchIdNotFoundException("Fanfic", id);
    }


}
