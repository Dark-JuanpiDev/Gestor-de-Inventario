package pe.edu.utp.Formulario.Categorizacion;

import pe.edu.utp.General;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categoria extends General {

    private static String categoria;

    public Categoria(int codigo, String nombre, String categoria) {
        super(codigo, nombre);
        this.categoria = categoria;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static void setCategoria(String categoria) {
        Categoria.categoria = categoria;
    }

    public static void datosCategorias(String categoria) {

        String[] columnNames = {"Proovedor", "Categoria", "Producto", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        //Establecer el modelo en la tabla
        FormCategoria.dataTableC.setModel(model);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT Proveedor, Categoria, Nombre, Precio FROM Producto WHERE Categoria = ?";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormCategoria.con.prepareStatement(sql);
            statement.setString(1, categoria);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String proveedor = resultSet.getString("Proveedor");
                String categoriastr = resultSet.getString("Categoria");
                String producto = resultSet.getString("Nombre");
                double precio = resultSet.getDouble("Precio");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{proveedor, categoriastr, producto, precio});

            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String[] obtenerCategoriasDesdeBaseDeDatos() {

        ArrayList<String> categorias = new ArrayList<>(); // Usaremos un ArrayList para almacenar las categorías

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT DISTINCT Categoria FROM Producto";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormCategoria.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener el valor de la categoría actual y agregarlo al ArrayList
                String categoria = resultSet.getString("Categoria");
                categorias.add(categoria);
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] categoriaArray = categorias.toArray(new String[0]);
        return categoriaArray;
    }

}
