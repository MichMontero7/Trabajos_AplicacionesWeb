package com.example.proyecto_cursomichelle.controller;

import com.example.proyecto_cursomichelle.model.Alumno;
import com.example.proyecto_cursomichelle.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private AlumnoService service;

    public AlumnoController(AlumnoService service){ this.service = service; }

    @GetMapping()
    public ResponseEntity<List<Alumno>> findAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable Long id){
        Optional<Alumno> alumno = service.findById(id);
        if (alumno.isPresent()){
            return ResponseEntity.ok(alumno.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Alumno> save(@RequestBody Alumno alumno){
        service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> putMethod(@PathVariable Long id, @RequestBody Alumno alumno){
        Optional<Alumno> optional = service.update(id, alumno);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }

        return ResponseEntity.ok(optional.get());
    }
}
