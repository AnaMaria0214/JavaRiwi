package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AutorModelo implements CRUD {

    @Override
    public Object crear(Object object) {
        //1.Abrir la conexion establecida
        Connection objConnection =
                ConfigDB.openConnection();
        //2.Castear el objeto
        Autor objAutor = (Autor) object;

        try {
            //3.Crear el sql
            String sql = "INSER INTO authors(id,name,nationality) Values (?,?,?)";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement)objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores ?
            objPrepare.setString(1,objAutor.);

       }catch ()
    }

    @Override
    public boolean actualizar(Object object) {
        return false;
    }

    @Override
    public boolean eliminar(Object object) {
        return false;
    }

    @Override
    public List<Object> buscarTodos() {
        return null;
    }

    @Override
    public Object buscarPorId(int id) {
        return null;
    }


}
