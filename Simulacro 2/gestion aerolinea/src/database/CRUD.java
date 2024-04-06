package database;

import java.security.PublicKey;
import java.util.List;

public interface CRUD {
    public Object create(Object object);
    public List<Object> read();
    public boolean update(Object object);
    public boolean delete(Object object);
}
