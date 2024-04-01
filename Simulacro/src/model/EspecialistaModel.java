package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EspecialistaModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexion.
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Especialidad objEspecialidad = (Especialidad) object;

        try {
            //3.Crear el sql
            String sql = "INSERT INTO especialidad(nombre,descripcion) Values(?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ?
            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());
            //6.Ejecutamos el Query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objEspecialidad.setId(objResult.getInt(1));
            }
            //8.Cerramos la conexion
            objPrepare.close();
            JOptionPane.showInputDialog(null, "Especialidad creada correctamente");
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, "Error al crear la especialidad" + e.getMessage());
        }
        //9.Cerramos la conexion
        ConfigDB.closeConnection();
        return objEspecialidad;

    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2.Inicial la lista donde se guardaran los registros que devuelve la BD
        List<Object> ListaEspecialidades = new ArrayList<>();

        try{
            //3.Crear el sql
            String sql= "SELECT * FROM especialidades ORDER BY especialidades.id ASC;";
        }catch (){

        }
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
