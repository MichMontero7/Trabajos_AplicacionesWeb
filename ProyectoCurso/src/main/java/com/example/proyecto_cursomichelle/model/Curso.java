package com.example.proyecto_cursomichelle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String area;
    private int cupo;
    private String horario;

    public Curso(Long id, String nombre, String area, int cupo, String horario){
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.cupo = cupo;
        this.horario = horario;
    }

    public Curso(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    public int getCupo() { return cupo; }

    public void setCupo(int cupo) { this.cupo = cupo; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }
}
