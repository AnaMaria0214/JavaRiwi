package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {

    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Specialty objSpecialty = (Specialty) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO specialties (name,description) values (?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setString(1, objSpecialty.getName());
            objPrepare.setString(2, objSpecialty.getDescription());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objSpecialty.setId(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Specialty successfully added");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error adding the Specialty");
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objSpecialty;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListSpecialties = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM specialties order BY specialties.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Specialty objSpecialty = new Specialty();

                objSpecialty.setId(objResult.getInt("id_Specialties"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));

                ListSpecialties.add(objSpecialty);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListSpecialties;
    }


    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Specialty objSpecialty = (Specialty) object;
        //3.Crear una variable bandera para saber el estado de la actualizacion
        boolean idUpdate = false;

        try {
            //4.Crear el sql
            String sql = "INSERT INTO specialties (id_Specialty,name,description) VALUE (?,?,?)";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //6.Dar valores a las llaves
            objPrepare.setInt(1, objSpecialty.getId_Specialty());
            objPrepare.setString(2, objSpecialty.getName());
            objPrepare.setString(3, objSpecialty.getDescription());
            //7.Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0) {
                idUpdate = true;
                JOptionPane.showMessageDialog(null, "Specialty information successfully updated");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        }
        //8.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return idUpdate;
    }

    @Override
    public boolean delete(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Specialty objSpecialty = (Specialty) object;
        //3.Crear una variable bandera para saber el estado de la eliminacion
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM specialties WHERE id=?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //6.Asignar los valores ?
            objPrepare.setInt(1, objSpecialty.getId_Specialty());

            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Specialty successfully removed");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        }
        return isDeleted;
    }
}


