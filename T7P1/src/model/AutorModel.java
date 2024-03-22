package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {

    @Override
    public Object crear(Object object) {
        //1.Abrir la conexion establecida
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Autor objAutor = (Autor) object;

        try {
            //3.Crear el sql
            String sql = "INSERT INTO authors(name,nationality) Values (?,?)";
            //4.Preparar el statement
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ?
            objPrepare.setString(1,objAutor.getName());
            objPrepare.setString(2,objAutor.getNacionality());
            //6. Ejecutamos el Query
            objPrepare.execute();

            //7. Obtener resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAutor.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Autor creado exitosamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al al crear el autor"+e.getMessage());

        }
        //9. Cerrramos la conexion
        ConfigDB.closeConnection();
        return objAutor;
    }

    @Override
    public boolean actualizar(Object object) {
        //1.Abrir la conexion establecida
        Connection objconnection = ConfigDB.openConnection();
        //2. Castear el objeto
        Autor objAutor = (Autor) object;
        //3. variable bandera para saber si se actualizo
        boolean idUpdated=  false;

        try{
            //4.Crear el sql
            String sql = "INSERT INTO authors(id,name,nationality) Values (?,?,?)";
            //5. Preparar el statement
            PreparedStatement objPrepare =(PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.dar valor a los parametros de query
            objPrepare.setInt(1,objAutor.getId());
            objPrepare.setString(2,objAutor.getName());
            objPrepare.setString(3,objAutor.getNacionality());


            //7. Ejecutamos el Query
            int rowAffected  = objPrepare.executeUpdate();
            if (rowAffected >0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"Autor actualizado exitosamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. Cerramos la conexion
        ConfigDB.closeConnection();
        return idUpdated;
    }

    @Override
    public boolean eliminar(Object object) {
            //1. Castear el objeto
            Libro objAutor = (Libro) object;

            //2. Variable booleana para esteblecer el estado de la eliminación
            boolean isDeleted = false;

            //3.Abrir la conexion establecida
            Connection objConnection = ConfigDB.openConnection();

            try {
                //4.  Crear el sql
                String sql = "DELETE FROM authors WHERE  id = ?;";

                //5.Preparar el statement
                PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                //6.Asignar los valores ?
                objPrepare.setInt(1, objAutor.getId());

                int totalAffectedRows = objPrepare.executeUpdate();


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            return false;
        }



    @Override
    public List<Object> buscarTodos() {
        //1.Abrir la conexion establecida
        Connection objConnection = ConfigDB.openConnection();
        //2. Inicializar la lista donde se guiardan los registros que devuelve la BD
        List<Object> listAutores = new ArrayList<>();

        try {
            //3.Crear el sql
            String sql ="SELECT * FROM AuthorsORDER BY authors.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement =  (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutamos el Query
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();

            //6.Obtener resultados
            while (objResult.next()){
                Autor objAutor = new Autor();

                objAutor.setId(objResult.getInt("id"));
                objAutor.setName(objResult.getString("name"));
                objAutor.setNacionality(objResult.getString("nacionality"));


                listAutores.add(objAutor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR en los datos");
        }

        //7.Cerramos la conexion

        ConfigDB.closeConnection();

        return listAutores;
    }

    @Override
    public Object buscarPorId(int id) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. Castear el objeto
        Autor objAutor  = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM authors WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setName(objResult.getString("name"));
                objAutor.setNacionality(objResult.getString("nationality"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objAutor;


    }

    public ArrayList<Autor> BuscarPorNombre(String BuscarPorNombre){
        //1. //1.Abrir la conexion establecida
        Connection objConnection = ConfigDB.openConnection();
        //2. Inicializar la lista donde se guiardan los registros que devuelve la BD
        ArrayList<Autor> AutorList = new ArrayList<>();
        Autor objAutor = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM authors WHERE authors.name LIKE '%" + BuscarPorNombre + "%';";

            //3.prepare (convierte a codigo el sql)
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //4. query
            ResultSet objResult = objPrepare.executeQuery();



            //6. mientras alla un coder lo guarde en un array para ver las coincidencias

            while (objResult.next()){
                objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setName(objResult.getString("name"));
                objAutor.setNacionality(objResult.getString("nationality"));

                AutorList.add(objAutor);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Pruebalog" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return AutorList;
    }


}
