package com.example.tramitec.model.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.tramitec.model.Carrera;
import com.example.tramitec.model.interfaces.Repository;
import com.example.tramitec.util.ConexionDB;

public class CarreraRepositoryImplement implements Repository<Carrera> {

    private Connection getConnection() throws SQLException{
        return ConexionDB.getInstance();
    }

    @Override
    public List<Carrera> listar() {
        List<Carrera> carreras = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM carreras;")) {

            while (rs.next()) {
                Carrera c = new Carrera();
                c.setId(rs.getLong("id"));
                c.setCarrera(rs.getString("carrera"));
                carreras.add(c);
            }
            
        } catch (SQLException e) {
            
        }

        return carreras;
    }

    @Override
    public Carrera porId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'porId'");
    }

    @Override
    public void guardar(Carrera alumno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
