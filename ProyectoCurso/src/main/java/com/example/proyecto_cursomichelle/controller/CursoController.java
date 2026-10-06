package com.example.proyecto_cursomichelle.controller;

import com.example.proyecto_cursomichelle.model.Curso;
import com.example.proyecto_cursomichelle.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private CursoService service;

    public CursoController(CursoService service) { this.service = service; }

    @GetMapping()
    public ResponseEntity<List<Curso>> findAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id){
        Optional<Curso> curso = service.findById(id);
        if (curso.isPresent()){
            return ResponseEntity.ok(curso.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Curso> save(@RequestBody Curso curso){
        service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> putMethod(@PathVariable Long id, @RequestBody Curso curso){
        Optional<Curso> optional = service.update(id, curso);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }

        return ResponseEntity.ok(optional.get());
    }

}
