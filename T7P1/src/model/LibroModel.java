package model;

import database.CRUD;
import database.ConfigDB;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroModel implements CRUD {

    @Override
    public Object crear(Object object) {
        //1.Abrir la conexion establecida
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Libro objLibro = (Libro) object;

        try {
            //3.Crear el sql
            String sql = "INSERT INTO books (id,title,year_publication,price,id_author) Values (?,?,?,?,?)";
            //4.Preparar el statement
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ?
            objPrepare.setInt(1,objLibro.getId());
            objPrepare.setString(2,objLibro.getTitle());
            objPrepare.setString(3,objLibro.getYear_publication());
            objPrepare.setDouble(4,objLibro.getPrice());
            objPrepare.setInt(5,objLibro.getId_author());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //7. Obtener resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objLibro.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"libro creado exitosamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al creal el libro"+e.getMessage());

        }
        //9. Cerrramos la conexion
        ConfigDB.closeConnection();

        return objLibro;
    }


    @Override
    public boolean actualizar(Object object) {
        //1.Abrir la conexion establecida
        Connection objconnection = ConfigDB.openConnection();
        //2. Castear el objeto
        Libro objLibro = (Libro) object;
        //3. variable bandera para saber si se actualizo
        boolean idUpdated=  false;

        try{
            //4.Crear el sql
            String sql ="UPDATE books SET title =?,year_publication=?,price=?,id_author=? WHERE id= ?;";

            //5.Preparar el statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6.dar valor a los parametros de query
            objPrepare.setString(1,objLibro.getTitle());
            objPrepare.setString(2,objLibro.getYear_publication());
            objPrepare.setDouble(3,objLibro.getPrice());
            objPrepare.setInt(4,objLibro.getId_author());
            objPrepare.setInt(5,objLibro.getId());

            //7.Ejecutamos el Query
            int rowAffected  = objPrepare.executeUpdate();
            if (rowAffected >0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"Libro actualizado exitosamente");
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
        Libro objLibro = (Libro) object;

        //2. Variable booleana para esteblecer el estado de la eliminación
        boolean isDeleted = false;

        //3.Abrir la conexion establecida
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4.  Crear el sql
            String sql = "DELETE FROM books WHERE  id = ?;";

            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Asignar los valores ?
            objPrepare.setInt(1, objLibro.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Libro eliminado exitosamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public List<Object> buscarTodos() {
        //1.Abrir la conexion establecida
        Connection objConnection = ConfigDB.openConnection();
        //2. Inicializar la lista donde se guiardan los registros que devuelve la BD
        List<Object> listLibros = new ArrayList<>();

        try {
            //3.Crear el sql
            String sql ="SELECT * FROM books ORDER BY books.id ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement =  (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutamos el Query
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();

            //6.Obtener resultados
            while (objResult.next()){
                Libro objLibro = new Libro();

                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitle(objResult.getString("title"));
                objLibro.setYear_publication(objResult.getString("year_publication"));
                objLibro.setPrice(objResult.getDouble("price"));
                objLibro.setId_author(objResult.getInt("id_author"));

                listLibros.add(objLibro);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR en los datos");
        }

        //7.Cerramos la conexion

        ConfigDB.closeConnection();

        return listLibros;
    }

    @Override
    public Object buscarPorId(int id) {
            //1.Abrir la conexion establecida
            Connection objConnection = ConfigDB.openConnection();
                Libro objLibro = null;

                try{
                    //2.Crear el sql
                    String sql = "SELECT * FROM books WHERE id = ?;";
                    //3.Preparar el statement
                    PreparedStatement objPrepare = objConnection.prepareStatement(sql);
                    //4.Asignar los valores ?
                    objPrepare.setInt(1,id);
                    //5.Ejecutamos el Query
                    ResultSet objResult = objPrepare.executeQuery();

                    while (objResult.next()){
                        objLibro = new Libro();
                        objLibro.setId(objResult.getInt("id"));
                        objLibro.setTitle(objResult.getString("title"));
                        objLibro.setYear_publication(objResult.getString("year_publication"));
                        objLibro.setPrice(objResult.getDouble("price"));
                        objLibro.setId_author(objResult.getInt("id_author"));
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                //7. Cerrar la conexion

                ConfigDB.closeConnection();

                return objLibro;
        }

    public ArrayList<libro> BuscarPorNombre(String buscarPorNombre){
        //1. encender la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<libro> BookList = new ArrayList<libro>();
        libro objlibro = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM books WHERE books.title LIKE '%" + buscarPorNombre + "%';";

            //3.prepare (convierte a codigo el sql)
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);


            //3. query
            ResultSet objResult = objPrepare.executeQuery();


            while (objResult.next()){
                objlibro = new libro();
                objlibro.SetId(objResult.getInt("id"));
                objlibro.setTitle(objResult.getString("title"));
                objlibro.setyear_publication(objResult.getInt("year_publication"));
                objlibro.setPrice(objResult.getDouble("price"));


               List.add(objlibro);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return BookList;
    }
    }

