public class Empleado extends Persona {
    private  int idEmpleado;
    private double salario;
    private String tipo;

    public Empleado(String nombre, int edad, int idEmpleado, double salario, String tipo) {
        super(nombre, edad);
        this.idEmpleado = idEmpleado;
        this.salario = salario;
        this.tipo = tipo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "salario=" + salario +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
