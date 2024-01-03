package pe.edu.utp;

public abstract class General {

    private static int codigo;
    private static String nombre;

    public General(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int Codigo) {
        codigo = Codigo;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String Nombre) {
        nombre = Nombre;
    }
}
