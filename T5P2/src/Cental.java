import java.util.Scanner;

public class Cental {
    public static void main(String[] args) {

        GestionEmpleados objGestionEmpleados = new GestionEmpleados();
        Scanner objScan = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("""
                    MENÚ
                    Seleccione una opcíón.
                    1.Agregar un empleado.
                    2.Eliminar un empleado.
                    3.Listar empleados.
                    4.Salida.
                    """);
                option = objScan.nextInt();

            switch (option) {
                case 1:
                    objGestionEmpleados.agregarEmpleado(objScan);
                    break;
                case 2:
                    objGestionEmpleados.listarEmpleados();
                    System.out.println("Ingrese el Id del empleado a eliminar ");
                    int id = objScan.nextInt();
                    if (objGestionEmpleados.eliminarEmpleado(id)) {
                        System.out.println("Empleado eliminado correctamente");
                    } else {
                        System.out.println("Ingrese Id valido");
                    }
                    break;
                case 3:
                    objGestionEmpleados.listarEmpleados();
                    break;
            }
        } while (option !=4);
    }
}