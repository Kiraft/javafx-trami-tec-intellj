
package com.example.tramitec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AlumnoDAO {

    private Connection getConnection() throws SQLException {
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
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }

        return state;
    }



    public String getNombre(String matricula) {

        String sql = "SELECT nombres from alumnos WHERE numero_control = ?";
        String nombre = null;

        try (PreparedStatement pst = getConnection().prepareStatement(sql);) {

            pst.setString(1, matricula);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombres");
                }
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
                    + ex.getMessage());
        }

        return nombre;
    }
//
    public String getCarrera(String matricula){

        String carrera = null;
        String sql = "SELECT carrera from alumnos WHERE numero_control = ?";

    	try(PreparedStatement pst = getConnection().prepareStatement(sql)){

            pst.setString(1, matricula);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                carrera = rs.getString("carrera");
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
                                              + ex.getMessage());
        }

        return carrera;
    }

    public String getCorreo(String matricula){

        String sql = "SELECT correo from alumnos WHERE numero_control = ?";
        String correo = null;

    	try (PreparedStatement pst = getConnection().prepareStatement(sql)){

            pst.setString(1, matricula);

            ResultSet rs = pst.executeQuery();


            if (rs.next()) {
                correo = rs.getString("correo");
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
                                              + ex.getMessage());
        }

        return correo;
    }
}
