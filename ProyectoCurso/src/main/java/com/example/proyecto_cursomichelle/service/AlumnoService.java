package com.example.proyecto_cursomichelle.service;

import com.example.proyecto_cursomichelle.model.Alumno;
import com.example.proyecto_cursomichelle.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    private AlumnoRepository repo;

    public AlumnoService(AlumnoRepository repo){ this.repo = repo; }

    public List<Alumno> getAll() { return repo.findAll(); }

    public Optional<Alumno> findById(Long id) { return repo.findById(id); }

    public Alumno save(Alumno alumno) { return repo.save(alumno); }

    public Optional<Alumno> update(Long id, Alumno alumno){
        Optional<Alumno> optional = repo.findById(id);
        if (optional.isEmpty()){
            return optional.empty();
        }

        Alumno alumnos = optional.get();
        alumnos.setNombre(alumno.getNombre());
        alumnos.setApellido(alumno.getApellido());
        alumnos.setEdad(alumno.getEdad());
        alumnos.setCorreo(alumno.getCorreo());
        alumnos.setGradoGrupo(alumno.getGradoGrupo());

        return Optional.of(repo.save(alumnos));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }


}
