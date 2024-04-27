package pe.programadoredeslavadoras.ficuniverse.creation.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Page<Category> getAll(Pageable pageable);
    Category getById(Long categoryId);
    Category create(Category category);
    Category update(Long categoryId, Category category);
    ResponseEntity<?> delete(Long categoryId);
}
