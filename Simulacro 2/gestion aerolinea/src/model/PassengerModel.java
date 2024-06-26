package model;

import database.CRUD;
import database.ConfigDB;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnetion = ConfigDB.openConnection();
        //2.Castear el objeto
        Passenger objPassenger = (Passenger) object;

        try{
            //3.Crear el sql
            String sql="INSERT INTO passengers (first_Name,last_Name,identification) VALUES (?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnetion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar valores a las ??
            objPrepare.setString(1,objPassenger.get_Name());
            objPrepare.setString(2, objPassenger.get_Name());
            objPrepare.setString(3,objPassenger.getIdentification());

            //6.Ejecutar el query
            objPrepare.executeQuery();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objPassenger.setId_Passenger(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Passenger successfully added");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"There was an error adding the passenger");
            System.out.println("ERROR "+ e.getMessage());
        }
        //9.Cerrar la conexión a la BD
        ConfigDB.closeConnection();
        return objPassenger;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListPassengers = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM passengers order BY passengers.id_Passengers ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Passenger objPassenger = new Passenger();

                objPassenger.setId_Passenger(objResult.getInt("id_Passenger"));
                objPassenger.setName(objResult.getString("first_Name"));
                objPassenger.setLast_Name(objResult.getString("last_Name"));
                objPassenger.setIdentification(objResult.getString("identification"));

                ListPassengers.add(objPassenger);
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListPassengers;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Passenger objPatient = (Passenger) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql = "UPDATE passengers SET name = ?,last_Name = ?, identification = ? WHERE passengers.id_passengers = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setString(1,objPatient.get_Name());
            objPrepare.setString(2, objPatient.getLast_Name());
            objPrepare.setString(4,objPatient.getIdentification());

            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                idUpdate =true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR updating information");
            System.out.println("ERROR "+ e.getMessage());
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
        Passenger objPatient = (Passenger) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM patients WHERE id_Passengers = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPatient.getId_Passenger());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR when deleting the passenger ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
