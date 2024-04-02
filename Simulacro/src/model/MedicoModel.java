package model;

import database.ConfigDB;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexion.
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Medico objMedico = (Medico) object;

        try {
            //3.Crear el sql
            String sql = "INSERT INTO medicos (nombre,apellidoss) Values(?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ?
            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            //6.Ejecutamos el Query
            objPrepare.execute();

            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objMedico.setId(objResult.getInt(1));
            }
            //8.Cerramos la conexion
            objPrepare.close();
            JOptionPane.showInputDialog(null, "Medico creado correctamente");
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, "Error al crear el Medico" + e.getMessage());
        }
        //9.Cerramos la conexion
        ConfigDB.closeConnection();
        return objMedico;

    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2.Inicial la lista donde se guardaran los registros que devuelve la BD
        List<Object> ListaMedicos = new ArrayList<>();

        try {
            //3.Crear el sql
            String sql = "SELECT * FROM medicos ORDER BY medicos.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutamos el Query
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Obtener resultados
            while (objResult.next()) {
                Medico objMedico = new Medico();

                objMedico.setId(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));

                ListaMedicos.add(objMedico);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
        //7.Cerramos la conexion
        ConfigDB.closeConnection();

        return ListaMedicos;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexion.
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Medico objMedico = (Medico) object;
        //3.Variable bandera para saber si se actualizo
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql ="INSERT INTO medicos (id,id_especialidad,nombre,apellido,) Values(?,?,?,?)";
            //5.Preparar el statement
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Dar valor a los parametros del Query
            objPrepare.setInt(1,objMedico.getId());
            objPrepare.setInt(2,objMedico.getId_Especialidad();
            objPrepare.setString(3,objMedico.getNombre());
            objPrepare.setString(4,objMedico.getApellidos());
            //7.Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();
            if(rowAffected >0){
                idUpdate=true;
                JOptionPane.showMessageDialog(null,"informacion del Medico actualizada correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        //8.Cerramos la coneccion
        ConfigDB.closeConnection();
        return idUpdate;
    }

    @Override
    public boolean delete(Object object) {
        //1.Castear el objeto
        Medico objMedico = (Medico) object;

        //2. Variable booleana para esteblecer el estado de la eliminación
        boolean isDeleted = false;

        //3.Abrir la conexion establecida
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4.  Crear el sql
            String sql = "DELETE FROM medicos WHERE  id = ?;";

            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Asignar los valores ?
            objPrepare.setInt(1, objMedico.getId());

            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null,"medico eliminado correctamente");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return isDeleted;
    }
    public ArrayList<Medico>FindForName(String FindForName){
        //1. encender la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Medico> ListaMedicos = new ArrayList<Medico>();
        Medico objMedico = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM books WHERE especialidades.nombre LIKE '%" + FindForName + "%';";

            //3.Preparar el statement
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);


            //4.Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();
            //5.Obtener resultados
            while (objResult.next()){

                objMedico = new Medico();
                objPrepare.setId(objResult.getInt("id"));
                objPrepare.setId_Especialidad(objResult.getInt("id_Especialidad"));
                objPrepare.setNombre(objResult.getString("nombre"));
                objPrepare.setApellidos(objResult.getString("apellidos"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return ListaMedicos;
    }
}
}


