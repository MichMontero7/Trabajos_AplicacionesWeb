package com.example.proyecto_cursomichelle.service;

import com.example.proyecto_cursomichelle.model.Inscripcion;
import com.example.proyecto_cursomichelle.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {
    private InscripcionRepository repo;

    public InscripcionService(InscripcionRepository repo){ this.repo = repo; }

    public List<Inscripcion> getAll() { return repo.findAll(); }

    public Optional<Inscripcion> findById(Long id){
        return repo.findById(id);
    }

    public Inscripcion save(Inscripcion inscripcion) { return repo.save(inscripcion); }

    public Optional<Inscripcion> update(Long id, Inscripcion inscripcion){
        Optional<Inscripcion> optional = repo.findById(id);
        if (optional.isEmpty()){
            return optional.empty();
        }

        Inscripcion nuevainscripcion = optional.get();
        nuevainscripcion.setNomAlumno(inscripcion.getNomAlumno());
        nuevainscripcion.setApellido(inscripcion.getApellido());
        nuevainscripcion.setNomCurso(inscripcion.getNomCurso());
        nuevainscripcion.setHorario(inscripcion.getHorario());

        return Optional.of(repo.save(nuevainscripcion));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }

        repo.deleteById(id);
        return true;
    }

}
