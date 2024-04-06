package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //1. establecer la variable donde se va a encontrar el estado de la conexión
    static Connection objConnection = null;

    //2. Crear un método para abrir y establecer la conexión entre java y la base de datos

    public static Connection openConnection(){
        //DriverManager

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://bdx4gvxcvu7mocpyve0r-mysql.services.clever-cloud.com:3306/bdx4gvxcvu7mocpyve0r";
            String user ="uetfnplv1o7irm3s";
            String password ="slhswErmPRVWMXbOCRSm";

            //Establecer la conexión
            objConnection=(Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexión a la base de datos establecida correctamente");
            //ERROR POR NO TENER EL DRIVER INSTALADO
        }catch (ClassNotFoundException e) {
            System.out.println("ERROR >> DRIVER NO INSTALADO");
            //ERROR POR NO PODER ESTABLECER LA CONEXIÓN
        }catch (SQLException e){
            System.out.println("ERROR >> NO SE PUEDE ESTABLECER CONEXIÓN CON LA BASE DE DATOS");
        }
        return objConnection;
    }
    //3. crear un método para cerrar la conexión entre java y la base de datos

    public static void closeConnection(){
        try {
            //condicional para saber si la conexión esta establecida o no
            //si la conexión esta establecida desconectar
            if (objConnection != null) objConnection.close();
            System.out.println("Desconexión de la base de datos realizada exitosamente");
            //si no hay conexión o hay un error mostrar mensaje de error
        }catch (SQLException e){
            System.out.println("ERROR" + e.getMessage());
        }
    }
}
