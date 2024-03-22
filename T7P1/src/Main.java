import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AutorController objAutorController = new AutorController();
        LibroController objLibroController = new LibroController ();

        String opcion="";

        do{
                opcion = JOptionPane.showInputDialog("""
                        MENU
                        1.Listar
                        2.Crear
                        3.Actualizar
                        4.Eliminar
                        5.Salir
                        
                        Seleciona una opcion.
                        """);
            switch (opcion){
                case"1":
                    String opcion2="";

                    JOptionPane.showInputDialog("""
                            LISTAR
                            1 Listar libros
                            2 Listar autores
                            """);
                    switch (opcion2){
                        case"1":
                            break;
                        case"2":
                            break;
                    }
                break;
                case"2":
                    String opcion3="";

                    JOptionPane.showInputDialog("""
                            Crear
                            1 Crear libro
                            2 Crear autor
                            """);
                    switch (opcion3){
                        case"1":
                            break;
                        case"2":
                            break;
                    }
                break;
                case"3":
                    String opcion4="";

                    JOptionPane.showInputDialog("""
                            Actualizar
                            1 Actualizar libro
                            2 Actualizar autor
                            """);
                    switch (opcion4){
                        case"1":
                            break;
                        case"2":
                            break;
                    }
                    break;
                case"4":
                    String opcion5="";

                    JOptionPane.showInputDialog("""
                            Eliminar
                            1 Eliminar libro
                            2 Eliminar autor
                            """);
                    switch (opcion5){
                        case"1":
                            break;
                        case"2":
                            break;
                    }
                    break;


            }
        }while (opcion !="5");


        }
    }
}
