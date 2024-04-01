package entity;

import java.util.Date;

public class Paciente {
    private int id;
    private String nombres;
    private String apellidos;
    private Date fecha_nacimiento;
    private int documento_identidad;

    public Paciente() {
    }

    public Paciente(int id, String nombres, String apellidos, Date fecha_nacimiento, int documento_identidad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento_identidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(int documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", documento_identidad=" + documento_identidad +
                '}';
    }
}

