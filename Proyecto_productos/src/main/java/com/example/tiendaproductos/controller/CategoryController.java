package com.example.tiendaproductos.controller;

import com.example.tiendaproductos.dto.CategoryRequest;
import com.example.tiendaproductos.dto.CategoryResponse;
import com.example.tiendaproductos.model.Category;
import com.example.tiendaproductos.repository.CategoryRepository;
import com.example.tiendaproductos.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    /*private final CategoryRepository repoCategory;
    public CategoryController(CategoryRepository repoCategory){
        this.repoCategory = repoCategory;
    }*/

    private final CategoryService service;

    public CategoryController(CategoryService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(){
        List<CategoryResponse> listaResponse = new ArrayList<>();
        for (Category c : service.getAll()){
            listaResponse.add(toResponse(c));
        }
        return ResponseEntity.ok(listaResponse);
        //return ResponseEntity.ok(repoCategory.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id){
        Optional<Category> category = service.findById(id);
        if (category.isPresent()){
            return ResponseEntity.ok( toResponse(category.get()) );
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest category){
        Category categoryd = service.save(toEntity(category));

        return  ResponseEntity.status(HttpStatus.CREATED).body( toResponse(categoryd) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest category){
        Category categoryd = service.update(id, toEntity(category));
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(categoryd));
    }

    private CategoryResponse toResponse(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    private Category toEntity(CategoryRequest request){
        Category category = new Category();
        category.setName(request.name());

        return category;
    }
}
