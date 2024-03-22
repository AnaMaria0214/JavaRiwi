package database;

import java.util.List;

public interface CRUD {

    //Autores
    public Object crear(Object object);

    public boolean actualizar(Object object);

    public boolean eliminar(Object object);

    public List<Object> buscarTodos();

    public Object buscarPorId (int id);

}
