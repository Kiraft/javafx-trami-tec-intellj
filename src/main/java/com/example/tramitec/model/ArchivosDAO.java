
package com.example.tramitec.model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import com.example.tramitec.util.ConexionDB;

public class ArchivosDAO {


    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    
    private int getId(String matricula) {



        int userId = -1;
        String sql = "SELECT id FROM alumnos WHERE numero_control=?";

        try (PreparedStatement pst = getConnection().prepareStatement(sql)) {

            pst.setString(1, matricula);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }

        return userId;
    }
    
    public void setArchivo(String matricula, String ruta_archivo, int tipo_archivo) {

        int id = getId(matricula);
        String sql = "INSERT INTO archivos (alumno_id, tipos_archivo_id, ruta_archivo, estado, aprovado) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = getConnection().prepareStatement(sql)){


            pst.setInt(1, id);
            pst.setInt(2, tipo_archivo);
            pst.setString(3, ruta_archivo);
            pst.setString(4, "subido");
            pst.setBoolean(5, false);

            pst.executeUpdate();
            // Hacer algo con rowsAffected si es necesario
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }
    
    }

    public void deleteArchivo(String matricula, int tiposArchivoId) {

        int id = getId(matricula);
        String sql = "DELETE FROM archivos WHERE alumno_id = ? AND tipos_archivo_id = ?";

        try (PreparedStatement pst = getConnection().prepareStatement(sql)){

            pst.setInt(1, id);
            pst.setInt(2, tiposArchivoId);

            pst.executeUpdate();

    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }
    
    }

    public String getEstado(String matricula, int tipo_archivo_id) {

        int id = getId(matricula);
        String estado = "sin subir";
        String sql = "SELECT estado FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";
        try (PreparedStatement pst = getConnection().prepareStatement(sql)){


            pst.setInt(1, id);
            pst.setInt(2, tipo_archivo_id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                estado = rs.getString("estado");
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }

        return estado;
    }

    public boolean getStatusAprovado(String matricula, int tipo_archivo_id) {

        int id = getId(matricula);
        boolean status = false;
        String sql = "SELECT aprovado FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";

        try (PreparedStatement pst = getConnection().prepareStatement(sql)){


            pst.setInt(1, id);
            pst.setInt(2, tipo_archivo_id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                status = rs.getBoolean("aprovado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }

        return status;
    }

    public String getRutaArchivo(String matricula, int tipo_archivo_id) {

        int id = getId(matricula);
        String ruta = null;
        String sql = "SELECT ruta_archivo FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";

        try (PreparedStatement pst = getConnection().prepareStatement(sql)){

            pst.setInt(1, id);
            pst.setInt(2, tipo_archivo_id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ruta = rs.getString("ruta_archivo");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        }

        return ruta;
    }

}
