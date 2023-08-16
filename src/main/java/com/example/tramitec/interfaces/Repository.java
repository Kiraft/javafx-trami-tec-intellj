package com.example.tramitec.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> listar();

    T porId(Long id);

    void guardar(T alumno);

    void eliminar(Long id);


}
