package pe.edu.utp.Formulario;

import java.sql.*;


// Esta clase encapsula la lógica para establecer la conexión
// con la base de datos y resetear la secuencia.

public class Conect_BD {

    private static final String URL = "jdbc:sqlserver://DESKTOP-98DTJQD:1433;database=Tienda;user=sa;password=123456;TrustServerCertificate=true;";

    public static Connection con;

    public Conect_BD() {

        try {
            // Establecer la conexión con la base de datos
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(URL);
            System.out.println("Conectado a la base de datos");

            /* Ejecutar la consulta para resetear la secuencia
            String resetQuery = "DBCC CHECKIDENT(Producto, RESEED, 0)";
            Statement resetStatement = con.createStatement();
            resetStatement.executeUpdate(resetQuery);

            // Cerrar el statement después de usarlo
            resetStatement.close();*/

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores de conexión aquí
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return con;
    }

}
