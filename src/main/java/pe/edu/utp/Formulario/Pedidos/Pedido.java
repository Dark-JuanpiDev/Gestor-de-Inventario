package pe.edu.utp.Formulario.Pedidos;

import pe.edu.utp.Formulario.Registrar.FormRegistro;
import pe.edu.utp.Formulario.Registrar.GenerarCodigo;
import pe.edu.utp.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pedido extends General {

    private static int codigoProducto;

    public Pedido(int codigo, String nombre, int codigoProducto) {
        super(codigo, nombre);
        this.codigoProducto = codigoProducto;
    }

    public static int getCodigoProducto() {
        return codigoProducto;
    }

    public static void setCodigoProducto(int codigoProducto) {
        Pedido.codigoProducto = codigoProducto;
    }

    public static void eliminarFilaEnBaseDeDatosPedido(int codigo) {
        try {
            Connection con = FormPedidos.con; // Obtener la conexión de la clase principal

            // Consulta SQL para eliminar la fila con el codigo proporcionado
            String sql = "DELETE FROM Pedidos WHERE codigo = ?";
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
}
