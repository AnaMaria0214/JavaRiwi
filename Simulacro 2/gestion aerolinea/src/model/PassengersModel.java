package model;

import database.CRUD;
import database.ConfigDB;
import entity.Flight;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengersModel implements CRUD {
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
            objPrepare.setString(1,objPassenger.getFirst_Name());
            objPrepare.setString(2, objPassenger.getFirst_Name());
            objPrepare.setInt(3,objPassenger.getIdentification());

            //6.Ejecutar el query
            objPrepare.executeQuery();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objPassenger.setId(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Passenger successfully added");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"There was an error adding the passenger");
        }
        //9.Cerrar la conexión a la BD
        ConfigDB.closeConnection();
        return objPassenger;
    }

    @Override
    public List<Object> read() {
        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListPassengers = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM passengers order BY passengers.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Passenger objPassenger = new Passenger();

                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setFirst_Name(objResult.getString("first_Name"));
                objPassenger.setLast_Name(objResult.getString("last_Name"));
                objPassenger.setIdentification(objResult.getInt("identification"));

                ListPassengers.add(objPassenger);
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null,"ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListPassengers;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}
