package com.example.tramitec.model;

public class Carrera {

    private Long id;
    private String carrera;


    public Carrera() {
    }

    public Carrera(Long id, String carrera) {
        this.id = id;
        this.carrera = carrera;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }


    @Override
    public String toString() {
        return getCarrera();
    }

}
