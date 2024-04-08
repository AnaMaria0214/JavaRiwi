package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Physician;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Appointment objAppointment = (Appointment) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO appointments (date_Appointment,time_Appointment,reason,id_Patient,id_Physician) values (?,?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setDate(1, objAppointment.getDate_Appointment());
            objPrepare.setTime(2, objAppointment.getTime_Appointment());
            objPrepare.setString(3, objAppointment.getReason());
            objPrepare.setInt(4, objAppointment.getId_Patient());
            objPrepare.setInt(5, objAppointment.getId_Physician());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAppointment.setId_Appointment(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Appointment successfully added");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error adding the appointment");
            System.out.println("ERROR " + e.getMessage());
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListAppointments = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM appointments order BY appointments.id_Appointments ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Appointment objAppointment = new Appointment();

                objAppointment.setId_Appointment(objResult.getInt("id_Appointments"));
                objAppointment.setDate_Appointment(objResult.getDate("date_Appointment"));
                objAppointment.setTime_Appointment(objResult.getTime("time_Appointment"));
                objAppointment.setReason(objResult.getString("reason"));
                objAppointment.setId_Patient(objResult.getInt("Id_Patient"));
                objAppointment.setId_Physician(objResult.getInt("Id_Physician"));

                ListAppointments.add(objAppointment);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListAppointments;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Appointment objAppointment = (Appointment) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql = "UPDATE appointments SET date_Appointment = ?,time_Appointment = ?,reason = ?, id_Patient = ?, id_Physician = ? WHERE appointments.id_appointments = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setDate(1,objAppointment.getDate_Appointment());
            objPrepare.setTime(2, objAppointment.getTime_Appointment());
            objPrepare.setString(3,objAppointment.getReason());
            objPrepare.setInt(4,objAppointment.getId_Patient());
            objPrepare.setInt(5,objAppointment.getId_Physician());
            objPrepare.setInt(6,objAppointment.getId_Appointment());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                idUpdate =true;
                JOptionPane.showMessageDialog(null,"Appointment information successfully updated ");
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
        Appointment objAppointment = (Appointment) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM appointments WHERE id_Appointments = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objAppointment.getId_Appointment());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR when deleting the Appointment");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
