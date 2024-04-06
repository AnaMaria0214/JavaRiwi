package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql = "INSERT INTO patients (date_Appointment,time_Appointment,reason,id_Patient,id_Physician) values (?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setDate(1, objAppointment.getDate_Appointment());
            objPrepare.setDate(2, objAppointment.getTime_Appointment());
            objPrepare.setString(3, objAppointment.getReason());
            objPrepare.setInt(4, objAppointment.getId_Patient());
            objPrepare.setInt(5, objAppointment.getId_Physician());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAppointment.setId_Patient(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Specialty successfully added");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error adding the Specialty");
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public List<Object> findAll() {
        return null;
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
