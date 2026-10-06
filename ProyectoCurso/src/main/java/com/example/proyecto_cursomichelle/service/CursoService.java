package com.example.proyecto_cursomichelle.service;

import com.example.proyecto_cursomichelle.model.Curso;
import com.example.proyecto_cursomichelle.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private CursoRepository repo;

    public CursoService(CursoRepository repo) { this.repo = repo; }

    public List<Curso> getAll() { return repo.findAll();}

    public Optional<Curso> findById(Long id){ return repo.findById(id);}

    public Curso save(Curso curso) { return repo.save(curso);}

    public Optional<Curso> update(Long id, Curso curso){
        Optional<Curso> optional = repo.findById(id);
        if (optional.isEmpty()){
            return optional.empty();
        }

        Curso cursos = optional.get();
        cursos.setNombre(curso.getNombre());
        cursos.setArea(curso.getArea());
        cursos.setCupo(curso.getCupo());
        cursos.setHorario(curso.getHorario());

        return Optional.of(repo.save(cursos));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }

        repo.deleteById(id);
        return true;
    }

}
