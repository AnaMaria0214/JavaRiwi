package model;

import database.CRUD;

import java.util.List;

public class LibroModelo implements CRUD {

    @Override
    public Object crear(Object object) {
        return null;
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
