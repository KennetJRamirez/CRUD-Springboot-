package com.umg.primerappspringboot.model;

public class Persona {
    private Integer id; // Campo ID
    private String nombre;
    private String apellido;
    private String correo;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + apellido + "," + correo;
    }

    public static Persona fromString(String persona) {
        String[] datos = persona.split(",");
        return new Persona(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3]);
    }
}
