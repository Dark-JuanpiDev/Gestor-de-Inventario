package pe.edu.utp.Formulario.PrincipalUser;

import pe.edu.utp.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Compra extends General {

    private static double precio;

    public Compra(int codigo, String nombre, double precio) {
        super(codigo, nombre);
        this.precio = precio;
    }

    public static double getPrecio() {
        return precio;
    }

    public static void setPrecio(double Precio) {
        precio = Precio;
    }

    public static void isAgregarPedido(String codigoClient, String nombreClient, String apellidoPat, String apellidoMat, int codigo,
                                       String proveedor, String nombre, int cantidad, double precio) throws SQLException {
        Connection con = FormPrincipalUser.con; // Obtener la conexión de la clase principal

        try {

            // Esta parte del codigo se utiliza para agregar los valores extraidos a la BD
            String sql = "INSERT INTO Pedidos (codigo_Client, nombre_Client, apellidoPat_Client, apellidoMat_Client, codigo, proveedor, producto, cantidad, precio) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, codigoClient);
            statement.setString(2, nombreClient);
            statement.setString(3, apellidoPat);
            statement.setString(4, apellidoMat);
            statement.setInt(5, codigo);
            statement.setString(6, proveedor);
            statement.setString(7, nombre);
            statement.setInt(8, cantidad);
            statement.setDouble(9, precio);

            // Ejecutar la consulta
            statement.executeUpdate();

            // Cerrar el statement
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex; // Re-lanzar la excepción para manejarla en la clase que llame a este método
        }

    }
}
