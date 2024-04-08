package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object create(Object object) {
            //1.Abrir la conexión con la BD
            Connection objConnection = ConfigDB.openConnection();
            //2.Castear el objeto
        Patient objPatient = (Patient) object;

            try {
                //3.crear el sql
                String sql = "INSERT INTO patients (name,last_Name,date_Birth,identity_Document) values (?,?,?,?);";
                //4.Preparar el statement
                PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                //5.Asignar los valores a las llaves
                objPrepare.setString(1, objPatient.getName());
                objPrepare.setString(2, objPatient.getLast_Name());
                objPrepare.setDate(3, objPatient.getDate_Birth());
                objPrepare.setString(4, objPatient.getIdentity_Document());

                //6.Ejecutar el query
                objPrepare.execute();

                //7.Obtener resultados
                ResultSet objResult = objPrepare.getGeneratedKeys();
                //Extraer el resultado de el ResultSet
                while (objResult.next()) {
                    objPatient.setId_Patient(objResult.getInt(1));
                }
                //8.Cerrar el statement
                objPrepare.close();
                JOptionPane.showMessageDialog(null, "Patient successfully added");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "There was an error adding the patient");
                System.out.println("ERROR "+ e.getMessage());
            }
            //9.Cerrar la conexión
            ConfigDB.closeConnection();
            return objPatient;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListPatients = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM patients order BY patients.id_Patients ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Patient objPatient = new Patient();

                objPatient.setId_Patient(objResult.getInt("id_Patients"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLast_Name(objResult.getString("last_Name"));
                objPatient.setDate_Birth(objResult.getDate("Date_Birth"));
                objPatient.setIdentity_Document(objResult.getString("identity_Document"));

                ListPatients.add(objPatient);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListPatients;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Patient objPatient = (Patient) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql = "UPDATE patients  name = ?,last_Name = ?,date_Birth = ?, identity_Document = ? WHERE patients.id_patients=?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setString(1,objPatient.getName());
            objPrepare.setString(2, objPatient.getLast_Name());
            objPrepare.setDate(3,objPatient.getDate_Birth());
            objPrepare.setString(4,objPatient.getIdentity_Document());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                idUpdate =true;
                JOptionPane.showMessageDialog(null,"Patient information successfully updated ");
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
        Patient objPatient = (Patient) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM patients WHERE id_Patients = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPatient.getId_Patient());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR when deleting the Patient ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
