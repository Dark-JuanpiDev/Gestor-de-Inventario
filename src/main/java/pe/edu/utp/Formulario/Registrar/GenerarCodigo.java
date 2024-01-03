package pe.edu.utp.Formulario.Registrar;

import java.util.Random;
import java.sql.*;

public class GenerarCodigo {

    public static String generarCodigoUnico(Connection con) throws SQLException {
        String codigoGenerado;
        do {
            codigoGenerado = generarCodigoAleatorio();
        } while (existeCodigoEnTabla(con, codigoGenerado));
        return codigoGenerado;
    }

    private static String generarCodigoAleatorio() {
        Random random = new Random();
        int numero = random.nextInt(10000);
        return String.format("%04d", numero);
    }

    public static boolean existeCodigoEnTabla(Connection con, String codigo) {
        try {
            // Consulta SQL para verificar si el código existe en la tabla
            String sql = "SELECT COUNT(*) FROM Producto WHERE Codigo = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount > 0; // el código existe en la tabla
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}