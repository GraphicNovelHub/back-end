package pe.programadoredeslavadoras.ficuniverse.creation.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Category;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.persistence.CategoryRepository;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.service.CategoryService;
import pe.programadoredeslavadoras.ficuniverse.shared.exception.ResourceNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String ENTITY = "Category";
    private  final CategoryRepository categoryRepository;
    private final Validator validator;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,categoryId));
    }

    @Override
    public Category create(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new ResourceValidationException(ENTITY, "A category with the same name already exists");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long categoryId, Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Optional<Category> genreWithName = categoryRepository.findByName(category.getName());
        if(genreWithName.isPresent() && !genreWithName.get().getId().equals(category.getId())) {
            throw new ResourceValidationException(ENTITY, "A category with the same name already exists");
        }
        return categoryRepository.findById(categoryId)
                .map(genreToUpdate -> categoryRepository.save(genreToUpdate
                        .withName(category.getName())))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,categoryId));
    }

    @Override
    public ResponseEntity<?> delete(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(genre -> {
                    categoryRepository.delete(genre);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,categoryId));
    }
}
