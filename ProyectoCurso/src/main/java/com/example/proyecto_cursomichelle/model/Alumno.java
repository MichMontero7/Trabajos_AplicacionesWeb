package com.example.proyecto_cursomichelle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private String gradoGrupo;

    public Alumno(Long id, String nombre, String apellido, int edad, String correo, String gradoGrupo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.gradoGrupo = gradoGrupo;
    }

    public Alumno(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }

    public String getGradoGrupo() { return gradoGrupo; }

    public void setGradoGrupo(String gradoGrupo) { this.gradoGrupo = gradoGrupo; }

}
