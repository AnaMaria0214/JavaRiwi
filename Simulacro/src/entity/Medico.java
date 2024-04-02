package entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int id_Medico;
    private Medico objMedico;

    public Medico() {
    }

    public Medico(int id, String nombre, String apellidos, int id_Medico) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_Medico = id_Medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_Medico() {
        return id_Medico;
    }

    public void setId_Medico(int id_Medico) {
        this.id_Medico = id_Medico;
    }

    public Medico getObjMedico() {
        return objMedico;
    }

    public void setObjMedico(Medico objMedico) {
        this.objMedico = objMedico;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id_Medico=" + id_Medico +
                ", objMedico=" + objMedico +
                '}';
    }
}
