package model;

import database.CRUD;
import database.ConfigDB;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationModel implements CRUD {
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
            objPrepare.setDate(1,objReservation.getReservartion_Date());
            objPrepare.setString(2,objReservation.getSeat());
            objPrepare.setInt(3,objReservation.getId_Passenger());
            objPrepare.setInt(4,objReservation.getId_Flight());
            //6.Ejecutamos el query
            objPrepare.execute();
            //7.Obtenemos los resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objReservation.setId_Reservations(objResult.getInt(1));
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
    public List<Object> findAll() {
        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListReservations = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM reservations order BY  reservations.id_Reservations ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Reservation objReservation = new Reservation();

                objReservation.setId_Reservations(objResult.getInt("id_Reservations"));
                objReservation.setReservation_Date(objResult.getDate("reservation_Date"));
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
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Reservation objReservation = (Reservation) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql = "UPDATE reservations SET reservation_Date = ?, seat = ?, id_Passenger = ?, id_Flight = ? WHERE reservations.id_Reservations = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setDate(1,objReservation.getReservartion_Date());
            objPrepare.setString(2, objReservation.getSeat());
            objPrepare.setInt(3,objReservation.getId_Passenger());
            objPrepare.setInt(4,objReservation.getId_Flight());
            objPrepare.setInt(5,objReservation.getId_Reservations());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                idUpdate =true;
                JOptionPane.showMessageDialog(null,"Reservation information successfully updated ");
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
        Reservation objAppointment = (Reservation) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM appointments WHERE id_Reservations = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objAppointment.getId_Reservations());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR when deleting the reservation");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
