package model;

import database.CRUD;
import database.ConfigDB;
import entity.Passenger;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationsModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Reservation objReservation = (Reservation) object;

        try{
            String sql = "INSERT INTO reservations (departure_Date,seat,id_Passenger,id_Flight) VALUE(?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ???
            objPrepare.setDate(1,objReservation.getDeparture_Date());
            objPrepare.setString(2,objReservation.getSeat());
            objPrepare.setInt(3,objReservation.getId_Passenger());
            objPrepare.setInt(4,objReservation.getId_Flight());
            //6.Ejecutamos el query
            objPrepare.execute();
            //7.Obtenemos los resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objReservation.setId(objResult.getInt(1));
            }

            //8.Cerramos el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Reservation successfully added");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"There was an error adding the reservation");
        }
        //9.Cerramos la conexióna la BD
        ConfigDB.closeConnection();
        return objReservation;
    }

    @Override
    public List<Object> read() {
        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListReservations = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM reservations order BY  reservations.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Reservation objReservation = new Reservation();

                objReservation.setId(objResult.getInt("id"));
                objReservation.setDeparture_Date(objResult.getDate("departure_Date"));
                objReservation.setSeat(objResult.getString("last_Name"));
                objReservation.setId_Passenger(objResult.getInt("id_Passenger"));
                objReservation.setId_Flight(objResult.getInt("id_Flight"));

                ListReservations.add(objReservation);
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null,"ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListReservations;
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
