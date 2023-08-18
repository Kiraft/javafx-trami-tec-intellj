package com.example.tramitec.model;

public class Alumno {
    private Long id;
    private String nombre;
    private String numeroControl;
    private String correo;
    private String password;
    private Carrera carrera;


    public Alumno() {
    }


    public Alumno(Long id, String nombre, String numeroControl, String correo, String password, Carrera carrera) {
        this.id = id;
        this.nombre = nombre;
        this.numeroControl = numeroControl;
        this.correo = correo;
        this.password = password;
        this.carrera = carrera;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroControl() {
        return this.numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    




}
