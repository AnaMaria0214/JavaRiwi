import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleados {

    private ArrayList<Empleado> listaDeEmpleados;

    private static int IdEmpleado = 0;

    public GestionEmpleados() {
        this.listaDeEmpleados = new ArrayList<>();
    }

    ;

    //metodos

    //Añadir empleado
    public void agregarEmpleado(Scanner objScan) {
        System.out.println("Ingrese el nombre completo de el nuevo empleado ");
        String nombre = objScan.next();
        System.out.println("Ingrese la edad de el nuevo empleado");
        int edad = objScan.nextInt();
        System.out.println("Ingrese el sueldo de el nuevo empleado");
        double salario = objScan.nextDouble();
        System.out.println("""
                Seleccione el tipo de empleado
                1.Empleado temporal
                2.Empleado permanente
                """);
        String tipo = objScan.next();

        if (tipo.equals("1")) {
            tipo = "Empleado temporal";
            this.listaDeEmpleados.add(new EmpleadoTemporal(nombre, edad,IdEmpleado, salario, tipo));
            System.out.println("Empleado agregado correctamente");
        } else if (tipo.equals("2")) {
            tipo = "Empleado permanente";
            this.listaDeEmpleados.add(new EmpleadoPermanente(nombre, edad,IdEmpleado, salario, tipo));
            System.out.println("Empleado agregado correctamente");
        } else {
            System.out.println("Ingrese un tipo de empleado valido");
        }
        IdEmpleado++;
    }

    //Eliminar empleado

    public boolean eliminarEmpleado(int Id) {
        return listaDeEmpleados.removeIf(empleado -> empleado.getIdEmpleado() == Id);
    }

    //Listar empleados
    public void listarEmpleados() {
        for (Empleado Empleado : this.listaDeEmpleados) {
            System.out.println("ID: " + Empleado.getIdEmpleado() + " Nombre: " + Empleado.getNombre() + " Edad: " + Empleado.getEdad() + " Salario: " + Empleado.getSalario() + " Tipo: " + Empleado.getTipo());
        }
    }
}



