
package com.example.tramitec.model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ArchivosDAO {

    private int getId(int matricula) {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int userId = -1;

        try {

            connection = ConexionDB.getInstance().getConnection();

            if (connection != null) {

                String sql = "SELECT id FROM alumnos WHERE numero_control=?";

                pst = connection.prepareStatement(sql);
                pst.setInt(1, matricula);

                rs = pst.executeQuery();

                if (rs.next()) {
                    userId = rs.getInt("id");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return userId;
    }
    
    public void setArchivo(int matricula, String ruta_archivo, int tipo_archivo) {

        Connection connection = null;
        PreparedStatement pst;
    
        int id = getId(matricula);
    
        try {
    
            connection = ConexionDB.getInstance().getConnection();
    
            if (connection != null) {
    
                String sql = "INSERT INTO archivos (alumno_id, tipos_archivo_id, ruta_archivo, estado, aprovado) " +
                             "VALUES (?, ?, ?, ?, ?)";
    
                pst = connection.prepareStatement(sql);
    
                pst.setInt(1, id);
                pst.setInt(2, tipo_archivo);
                pst.setString(3, ruta_archivo);
                pst.setString(4, "subido");
                pst.setBoolean(5, false);
    
                int rowsAffected = pst.executeUpdate();
                // Hacer algo con rowsAffected si es necesario
    
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {
    
            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    
        }
    
    }

    public void deleteArchivo(int matricula, int tiposArchivoId) {

        Connection connection = null;
        PreparedStatement pst;

        int id = getId(matricula);
    
        try {
    
            connection = ConexionDB.getInstance().getConnection();
    
            if (connection != null) {
    
                String sql = "DELETE FROM archivos WHERE alumno_id = ? AND tipos_archivo_id = ?";
    
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, tiposArchivoId);
    
                int rowsAffected = pst.executeUpdate();
                // Hacer algo con rowsAffected si es necesario
    
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
    
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {
    
            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    
        }
    
    }

    public String getEstado(int matricula, int tipo_archivo_id) {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int id = getId(matricula);

        String estado = "sin subir";

        try {

            connection = ConexionDB.getInstance().getConnection();

            if (connection != null) {

                String sql = "SELECT estado FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";

                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, tipo_archivo_id);

                rs = pst.executeQuery();

                if (rs.next()) {
                    estado = rs.getString("estado");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return estado;
    }

    public boolean getStatusAprovado(int matricula, int tipo_archivo_id) {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int id = getId(matricula);

        boolean status = false;

        try {

            connection = ConexionDB.getInstance().getConnection();

            if (connection != null) {

                String sql = "SELECT aprovado FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";

                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, tipo_archivo_id);

                rs = pst.executeQuery();

                if (rs.next()) {
                    status = rs.getBoolean("aprovado");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return status;
    }

    public String getRutaArchivo(int matricula, int tipo_archivo_id) {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int id = getId(matricula);

        String ruta = null;

        try {

            connection = ConexionDB.getInstance().getConnection();

            if (connection != null) {

                String sql = "SELECT ruta_archivo FROM archivos WHERE alumno_id = ? and tipos_archivo_id = ? ";

                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, tipo_archivo_id);

                rs = pst.executeQuery();

                if (rs.next()) {
                    ruta = rs.getString("ruta_archivo");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un error de ejecución, posibles errores:\n" + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConexionDB.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return ruta;
    }

}
