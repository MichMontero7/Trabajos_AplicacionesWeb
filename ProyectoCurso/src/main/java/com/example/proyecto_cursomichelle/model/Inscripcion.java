package com.example.proyecto_cursomichelle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomAlumno;
    private String apellido;
    private String nomCurso;
    private String horario;

    public Inscripcion(Long id, String nomAlumno, String apellido, String nomCurso, String horario){
        this.id = id;
        this.nomAlumno = nomAlumno;
        this.apellido = apellido;
        this.nomCurso = nomCurso;
        this.horario = horario;
    }

    public Inscripcion(){}

    public String getNomCurso() { return nomCurso; }

    public void setNomCurso(String nomCurso) { this.nomCurso = nomCurso; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNomAlumno() { return nomAlumno; }

    public void setNomAlumno(String nomAlumno) { this.nomAlumno = nomAlumno; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

}
