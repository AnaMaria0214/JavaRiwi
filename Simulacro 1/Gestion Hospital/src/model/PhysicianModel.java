package model;

import database.CRUD;
import database.ConfigDB;
import entity.Physician;
import entity.Specialty;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhysicianModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Physician objPhysician = (Physician) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO physicians (name,last_Name,Id_Specialty) values (?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setString(1, objPhysician.getName());
            objPrepare.setString(2, objPhysician.getLast_name());
            objPrepare.setInt(3, objPhysician.getId_Specialty());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objPhysician.setId_Physician(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Physician successfully added");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error adding the Physician"+ e.getMessage());
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objPhysician;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListPhysicians = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM physicians order BY physicians.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Physician objPhysician = new Physician();

                objPhysician.setId_Physician(objResult.getInt("id_Physician"));
                objPhysician.setName(objResult.getString("name"));
                objPhysician.setLast_name(objResult.getString("last_name"));
                objPhysician.setId_Specialty(objResult.getInt("id_Specialty"));

                ListPhysicians.add(objPhysician);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListPhysicians;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Physician objPhysician = (Physician) object;
        String sql="UPDATE physicians SET name=?,last_Name=?,Id_Specialty=? WHERE physicians.id_Physician=?;";
        try{

        }catch (SQLException e){
            ConfigDB.closeConnection();
            return objPhysician;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}

