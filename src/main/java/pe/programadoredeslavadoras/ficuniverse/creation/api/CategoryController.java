package pe.programadoredeslavadoras.ficuniverse.creation.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Category;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.service.CategoryService;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/ficuniverse/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.create(category);
    }

    @GetMapping
    public List<Category> fetchAll(){
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public Category fetchById(@PathVariable("id") Long id){
        return categoryService.getById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
