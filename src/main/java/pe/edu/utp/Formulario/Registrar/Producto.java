package pe.edu.utp.Formulario.Registrar;

import pe.edu.utp.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Producto extends General {

    private static String nombreProduc;
    private static String proveedor;
    private static String categoria;
    private static int stock;
    private static double precio;

    public Producto(int codigo, String proveedor, String nombre, String categoria, String nombreProduc, double precio, int stock) {
        super(codigo, nombre);
        this.proveedor = proveedor;
        this.nombreProduc = nombreProduc;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    public static String getNombreProduc() {
        return nombreProduc;
    }

    public static void setNombreProduc(String nombreProduc) {
        Producto.nombreProduc = nombreProduc;
    }

    public static String getProveedor() {
        return proveedor;
    }

    public static void setProveedor(String proveedor) {
        Producto.proveedor = proveedor;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static void setCategoria(String categoria) {
        Producto.categoria = categoria;
    }

    public static int getStock() {
        return stock;
    }

    public static void setStock(int Stock) {
        stock = Stock;
    }

    public static double getPrecio() {
        return precio;
    }

    public static void setPrecio(double Precio) {
        precio = Precio;
    }

        public static void isAgregarProducto(String proveedor, String nombre, String categoria, double precio, int stock) throws SQLException {
        Connection con = FormRegistro.con; // Obtener la conexión de la clase principal

        // Crear la consulta para gregar con el código único
        String codigo = GenerarCodigo.generarCodigoUnico(con);

        try {

            String sql = "INSERT INTO Producto (Codigo, Proveedor, Nombre, Categoria, Precio, Stock) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setString(2, proveedor);
            statement.setString(3, nombre);
            statement.setString(4, categoria);
            statement.setDouble(5, precio);
            statement.setInt(6, stock);

            // Ejecutar la consulta
            statement.executeUpdate();

            // Cerrar el statement
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex; // Re-lanzar la excepción para manejarla en la clase que llame a este método
        }

    }

    public static void eliminarFilaEnBaseDeDatos(int codigo) {
        try {
            Connection con = FormRegistro.con; // Obtener la conexión de la clase principal

            // Consulta SQL para eliminar la fila con el codigo proporcionado
            String sql = "DELETE FROM Producto WHERE codigo = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.executeUpdate();

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException ex) {
            // Manejo de errores de SQL
            ex.printStackTrace();
        }
    }

    public static boolean actualizarDatosEnBaseDeDatos(int codigo, String proveedor, String categoria, String nombre, double precio, int stock) {
        try {

            Connection con = FormRegistro.con; // Obtener la conexión de la clase principal

            String sql = "UPDATE Producto SET Proveedor = ?, Nombre = ?, Categoria = ?, Precio = ?, Stock = ? WHERE Codigo = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, proveedor);
            statement.setString(2, nombre);
            statement.setString(3, categoria);
            statement.setDouble(4, precio);
            statement.setInt(5, stock);
            statement.setInt(6, codigo);

            int filasActualizadas = statement.executeUpdate();

            // Verificar si se actualizó al menos una fila
            if (filasActualizadas > 0) {
                return true; // La actualización fue exitosa
            } else {
                return false; // No se actualizó ninguna fila (registro no encontrado)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error en la actualización
        }
    }
}
