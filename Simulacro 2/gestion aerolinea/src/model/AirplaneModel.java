package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {
    public Object create(Object object){
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Airplane objAirplane = (Airplane) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO airplanes (model,capacity) values (?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare =  objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setInt(2,objAirplane.getCapacity());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objAirplane.setId_Airplane(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Airplane successfully added");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"There was an error adding the airplane");
        }
        //9.Cerrar la conexion a la BD
        ConfigDB.closeConnection();
        return objAirplane;
        }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListAirplanes = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM airplanes order BY airplanes.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Airplane objAirPlane = new Airplane();

                objAirPlane.setId_Airplane(objResult.getInt("id_Airplane"));
                objAirPlane.setModel(objResult.getString("model"));
                objAirPlane.setCapacity(objResult.getInt("capacity"));

                ListAirplanes.add(objAirPlane);
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null, "ERROR");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListAirplanes;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Airplane objAirplane = (Airplane) object;
        //3.Crear una variable bandera para saber el estado de la actualizacion
        boolean idUpdate = false;

        try{
            //4.Crear el sql
            String sql = "UPDATE airplanes  SET model = ?, capacity =? WHERE airplanes.id_airplanes = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare =  objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setInt(2,objAirplane.getCapacity());
            objPrepare.setInt(3,objAirplane.getId_Airplane());

            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0) {
                idUpdate = true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR updating information");
            System.out.println("ERROR " + e.getMessage());
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
        Airplane objAirplane = (Airplane) object;
        //3.Crear una variable bandera para saber el estado de la eliminacion
        boolean isDeleted = false;

        try{
            //4.Crear el sql
            String sql = "DELETE FROM airplanes WHERE id_airplanes=?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6.Asignar los valores ?
            objPrepare.setInt(1,objAirplane.getId_Airplane());

            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                isDeleted=true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR when deleting the Airplane ");
            System.out.println("ERROR " + e.getMessage());
        }
        return isDeleted;
    }
}
