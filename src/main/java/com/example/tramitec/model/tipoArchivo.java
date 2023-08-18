package com.example.tramitec.model;

public class tipoArchivo {
    private Long id;
    private String nombreArchivo;

    public tipoArchivo() {
    }


    public tipoArchivo(Long id, String nombreArchivo) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }




}
