package model;

import database.CRUD;
import database.ConfigDB;
import entity.Flight;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Flight objFlight = (Flight) object;
        try {
            //3. Crear el sql
            String sql = "INSERT INTO flights (destination,departure_Date,departure_Time,id_Airplane) VALUES (?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de las ??
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setDate(2, objFlight.getDeparture_Date());
            objPrepare.setTime(3, objFlight.getDeparture_Time());
            objPrepare.setInt(4, objFlight.getId_Airplane());
            //6.Ejecutar el query
            objPrepare.execute();
            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objFlight.setId_Flights(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Flight successfully added");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"There was an error adding the flight");;
            System.out.println("ERROR " + e.getMessage());
        }
        //9.Cerrar la conexión a la BD
        ConfigDB.closeConnection();
        return objFlight;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListFlights = new ArrayList<>();
        try{
            //3.Crear el sql
            String sql = "SELECT * FROM flights order BY flights.id_flights ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                Flight objFlight = new Flight();

                objFlight.setId_Flights(objResult.getInt("id_Flights"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDeparture_Date(objResult.getDate("departure_Date"));
                objFlight.setDeparture_Time(objResult.getTime("departure_Time"));
                objFlight.setId_Airplane(objResult.getInt("id_Airplane"));

                ListFlights.add(objFlight);
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListFlights;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Flight objFlight = (Flight) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try {
            //4.Crear el sql
            String sql = "UPDATE physicians SET destination = ?,departure_Date = ?,departure_Time = ?, id_Airplane = ? WHERE flights.id_Flights = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Asignar los valores a las llaves
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setDate(2, objFlight.getDeparture_Date());
            objPrepare.setTime(3, objFlight.getDeparture_Time());
            objPrepare.setInt(4, objFlight.getId_Airplane());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0) {
                idUpdate = true;
                JOptionPane.showMessageDialog(null, "Flight information successfully updated ");
            }
        } catch (SQLException e) {
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
        Flight objFlight = (Flight) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM flights WHERE id_Flights = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objFlight.getId_Flights());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR when deleting the flight ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
