package pe.edu.utp.Formulario.iniciarSesion;

import pe.edu.utp.General;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends General {

    private static String apellidoPat;
    private static String apellidoMat;
    private static String codigoLogin;
    private static String password;

    public Login(int codigo, String nombre, String apellidoPat, String apellidoMat, String codigoLogin, String password) {
        super(codigo, nombre);
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.codigoLogin = codigoLogin;
        this.password = password;
    }

    public static String getApellidoPat() {
        return apellidoPat;
    }

    public static void setApellidoPat(String apellidoPat) {
        Login.apellidoPat = apellidoPat;
    }

    public static String getApellidoMat() {
        return apellidoMat;
    }

    public static void setApellidoMat(String apellidoMat) {
        Login.apellidoMat = apellidoMat;
    }

    public static String getCodigoLogin() {
        return codigoLogin;
    }

    public static void setCodigoLogin(String codigoLogin) {
        Login.codigoLogin = codigoLogin;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Login.password = password;
    }

    public static String obtenerTipoDeUsuario(String codigo, String password) {

        String tipoUsuario = "";
        String nombreUsuario = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";

        try {

            String sql = "SELECT tipo, nombre, apellidoPat, apellidoMat FROM Cuentas WHERE codigo = ? AND contraseña = ?";
            PreparedStatement statement = FormLogin.con.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setString(2, password);

            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery();

            // Verifica si se encontró un usuario
            if (resultSet.next()) {
                tipoUsuario = resultSet.getString("tipo");
                nombreUsuario = resultSet.getString("nombre");
                apellidoPaterno = resultSet.getString("apellidoPat");
                apellidoMaterno = resultSet.getString("apellidoMat");
            }

            setNombre(nombreUsuario);
            setApellidoPat(apellidoPaterno);
            setApellidoMat(apellidoMaterno);

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoUsuario;

    }

}


