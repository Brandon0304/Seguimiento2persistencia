package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    // Constructores, getters y setters

    public Paciente() {
    }

    public Paciente(int id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido(){return apellido;}

    public void setApellido(String apellido){this.apellido = apellido;}

    public int getEdad(){return edad;}

    public void setEdad(int edad){this.edad = edad;}

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido=" + apellido + '\'' +
                ", edad=" + edad
                '}';
    }
}