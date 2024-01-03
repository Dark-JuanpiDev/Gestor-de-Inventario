package pe.edu.utp.Formulario.Proveedores;

import pe.edu.utp.General;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proveedor extends General {

    private static String nombre_proveedor;
    private static String categoria;
    private static int stock;

    public Proveedor(int codigo, String nombre, String nombre_proveedor, String categoria, int stock) {
        super(codigo, nombre);
        this.nombre_proveedor = nombre_proveedor;
        this.categoria = categoria;
        this.stock = stock;
    }

    public static String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public static void setNombre_proveedor(String nombre_proveedor) {
        Proveedor.nombre_proveedor = nombre_proveedor;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static void setCategoria(String categoria) {
        Proveedor.categoria = categoria;
    }

    public static int getStock() {
        return stock;
    }

    public static void setStock(int stock) {
        Proveedor.stock = stock;
    }

    public static void filtrarDatosProveedores() {

        String[] columnNames = {"Nombre de Proveedores"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        //Establecer el modelo en la tabla
        FormProveedores.dataTableN.setModel(model);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT DISTINCT Proveedor FROM Producto;";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String nProveedor = resultSet.getString("Proveedor");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{nProveedor});

            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException ex) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static int cantidadCatProveedores(String proveedor) {

        int numCategorias = 0;

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT COUNT(DISTINCT Categoria) as numCategorias FROM Producto WHERE Proveedor = ?";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            statement.setString(1, proveedor);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener el valor de la cantidad de categorias que tiene este proveedor
                numCategorias = resultSet.getInt("numCategorias");
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numCategorias;
    }

    public static int cantidadProProveedores(String proveedor) {

        int numProductos = 0;

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT COUNT(Nombre) as numProductos FROM Producto WHERE Proveedor = ?";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            statement.setString(1, proveedor);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener el valor de la cantidad de categorias que tiene este proveedor
                numProductos = resultSet.getInt("numProductos");
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numProductos;
    }

    public static int cantidadStoProveedores(String proveedor) {

        int numStock = 0;

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT SUM(Stock) as numStockTotal FROM Producto WHERE Proveedor = ?";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            statement.setString(1, proveedor);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener el valor de la cantidad de categorias que tiene este proveedor
                numStock = resultSet.getInt("numStockTotal");
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numStock;
    }

    public static void infoDatosProveedores(String proveedor) {

        String[] columnNames = {"Productos", "Categoria", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        //Establecer el modelo en la tabla
        FormProveedores.dataTablePR.setModel(model);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT Nombre, Categoria, Stock FROM Producto WHERE Proveedor = ?";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            statement.setString(1, proveedor);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String producto = resultSet.getString("Nombre");
                String categoria = resultSet.getString("Categoria");
                int stock = resultSet.getInt("Stock");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{producto, categoria, stock});

            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException ex) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

}
