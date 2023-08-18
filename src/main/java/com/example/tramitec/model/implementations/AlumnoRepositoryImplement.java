package com.example.tramitec.model.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.tramitec.model.Alumno;
import com.example.tramitec.model.Carrera;
import com.example.tramitec.model.interfaces.Repository;
import com.example.tramitec.util.ConexionDB;

public class AlumnoRepositoryImplement implements Repository<Alumno> {

    private Connection getConnection() throws SQLException{
        return ConexionDB.getInstance();
    }
    
    public int login(String matricula, String password) {
        int state = -1;

        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM alumnos WHERE numero_control=? AND password=?")){

            pst.setString(1, matricula);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    state = 1;
                } else {
                    state = 0;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return state;
    }

    public Alumno porMatricula(String matricula) {
        Alumno a = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM alumnos as a INNER JOIN carreras as c ON (a.carrera_id = c.id) WHERE a.numero_control = ?;")) {
            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    a = new Alumno();
                    a.setId(rs.getLong("id"));
                    a.setNombre(rs.getString("nombres"));
                    a.setNumeroControl(rs.getString("numero_control"));
                    a.setCorreo(rs.getString("correo"));
                    a.setPassword(rs.getString("password"));
                    Carrera c = new Carrera();
                    c.setId(rs.getLong("id"));
                    c.setCarrera(rs.getString("carrera"));
                    a.setCarrera(c);
                }
            }
        } catch (SQLException e) {
            
        }
        return a;
        
    }
    
        public Alumno porCorreo(String correo) {
            Alumno a = null;
    
            try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM alumnos as a INNER JOIN carreras as c ON (a.carrera_id = c.id) WHERE a.correo = ?;")) {
                stmt.setString(1, correo);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        a = new Alumno();
                        a.setId(rs.getLong("id"));
                        a.setNombre(rs.getString("nombres"));
                        a.setNumeroControl(rs.getString("numero_control"));
                        a.setCorreo(rs.getString("correo"));
                        a.setPassword(rs.getString("password"));
                        Carrera c = new Carrera();
                        c.setId(rs.getLong("id"));
                        c.setCarrera(rs.getString("carrera"));
                        a.setCarrera(c);
                    }
                }
            } catch (SQLException e) {
    
            }
            return a;
            
        }
    
    @Override
    public List<Alumno> listar() {

        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Alumno porId(Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'porId'");
    }


    @Override
    public void guardar(Alumno alumno) {
        String sql = "INSERT INTO alumnos(carrera_id, nombres, numero_control, correo, password) VALUES (?, ?, ?, ?, ?) ";

        // if (alumno.getId() != null && alumno.getId() > 0) {
        //     sql = "update";
        // } else {
        //     sql = "INSERT INTO alumnos(nombres, numero_control, correo, password, carrera) VALUES (?, ?, ?, ?, ?) ";
        // }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql) ) {
            stmt.setLong(1, alumno.getCarrera().getId());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getNumeroControl());
            stmt.setString(4, alumno.getCorreo());
            stmt.setString(5, alumno.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            
        }
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
