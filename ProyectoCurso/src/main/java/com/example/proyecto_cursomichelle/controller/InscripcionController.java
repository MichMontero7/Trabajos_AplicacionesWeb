package com.example.proyecto_cursomichelle.controller;

import com.example.proyecto_cursomichelle.model.Inscripcion;
import com.example.proyecto_cursomichelle.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    private InscripcionService service;

    public InscripcionController(InscripcionService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Inscripcion>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> getById(@PathVariable Long id){
        Optional<Inscripcion> inscripcion = service.findById(id);
        if (inscripcion.isPresent()){
            return ResponseEntity.ok(inscripcion.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Inscripcion> save(@RequestBody Inscripcion inscripcion){
        service.save(inscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(inscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inscripcion> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> putMethod(@PathVariable Long id, @RequestBody Inscripcion inscripcion){
        Optional<Inscripcion> optional = service.update(id, inscripcion);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }

        return ResponseEntity.ok(optional.get());
    }

}
