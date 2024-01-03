package pe.edu.utp.Formulario;
import pe.edu.utp.Formulario.Categorizacion.FormCategoria;
import pe.edu.utp.Formulario.Pedidos.FormPedidos;
import pe.edu.utp.Formulario.PrincipalUser.FormPrincipalUser;
import pe.edu.utp.Formulario.Proveedores.FormProveedores;
import pe.edu.utp.Formulario.Registrar.FormRegistro;
import pe.edu.utp.Formulario.Ventas.FormVenta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class cargadoresBD {

    //----- CARAGDORES DE BASE DE DATOS DE PRODUCTO Y PROVEEDOR

    public static void eliminarFilaStockVacioEnBaseDeDatos() {
        try {
            Connection con = FormRegistro.con; // Obtener la conexión de la clase principal

            // Consulta SQL para eliminar la fila con el codigo proporcionado
            String sql = "DELETE FROM Producto WHERE stock = 0";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.executeUpdate();

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException ex) {
            // Manejo de errores de SQL
            ex.printStackTrace();
        }
    }

    public static void cargarDatosDesdeBaseDeDatosProductos() {

        // Definición de los nombres de las columnas de la tabla
        String[] columnNames = {"Codigo", "Proveedor", "Nombre", "Precio", "Stock"};
        // Creación de un modelo de tabla personalizado (DefaultTableModel) con los nombres de las columnas y 0 filas iniciales
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };

        // Establece los nombres de las columnas en el modelo de la tabla
        model.setColumnIdentifiers(columnNames);
        // Asigna el modelo de tabla personalizado al objeto JTable llamado dataTableV
        FormVenta.dataTableV.setModel(model);
        // Configura el ancho mínimo, máximo y real de la columna "Stock" (cuarta columna) en 0 para ocultarla visualmente
        FormVenta.dataTableV.getColumnModel().getColumn(4).setMinWidth(0); // Oculta la columna "Stock"
        FormVenta.dataTableV.getColumnModel().getColumn(4).setMaxWidth(0);
        FormVenta.dataTableV.getColumnModel().getColumn(4).setWidth(0);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT Codigo, Proveedor, Nombre, Precio, Stock FROM Producto";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormVenta.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                int codigo = resultSet.getInt("Codigo");
                String proveedor = resultSet.getString("Proveedor");
                String nombre = resultSet.getString("Nombre");
                double precio = resultSet.getDouble("Precio");
                int stock = resultSet.getInt("Stock");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{codigo, proveedor, nombre, precio, stock});

                // Si el stock es igual a 0, elimina la fila recién agregada
                if (stock == 0) {
                    eliminarFilaStockVacioEnBaseDeDatos();
                    int rowCount = model.getRowCount();
                    model.removeRow(rowCount - 1);
                }
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static void cargarDatosDesdeBaseDeDatosRegistro() {

        String[] columnNames = {"Codigo", "Proveedor", "Producto", "Categoria", "Precio", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        //Establecer el modelo en la tabla
        FormRegistro.dataTableV.setModel(model);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT Codigo, Proveedor, Nombre, Categoria, Precio, Stock FROM Producto";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormRegistro.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                int codigo = resultSet.getInt("Codigo");
                String proveedor = resultSet.getString("Proveedor");
                String nombre = resultSet.getString("Nombre");
                String categoria = resultSet.getString("Categoria");
                double precio = resultSet.getDouble("Precio");
                int stock = resultSet.getInt("Stock");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{codigo, proveedor, nombre, categoria, precio, stock});

                // Si el stock es igual a 0, elimina la fila recién agregada
                if (stock == 0 || stock < 0) {
                    eliminarFilaStockVacioEnBaseDeDatos();
                    int rowCount = model.getRowCount();
                    model.removeRow(rowCount - 1);
                }
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); //IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static void cargarDatosDesdeBaseDeDatosUser() {

        String[] columnNames = {"Codigo", "Proveedor", "Nombre", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        //Establecer el modelo en la tabla
        FormPrincipalUser.dataTableU.setModel(model);

        try {

            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT Codigo, Proveedor, Nombre, Precio FROM Producto";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormPrincipalUser.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                int codigo = resultSet.getInt("Codigo");
                String proveedor = resultSet.getString("Proveedor");
                String nombre = resultSet.getString("Nombre");
                double precio = resultSet.getDouble("Precio");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{codigo, proveedor, nombre, precio});
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static void cargarDatosDesdeBaseDeDatosProveedores() {

        String[] columnNames = {"Nombre de Proovedor", "Categoria", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };

        // Establecer el modelo en la tabla
        FormProveedores.dataTableP.setModel(model);

        try {
            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT proveedor, categoria, Stock FROM Producto";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormProveedores.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String proveedor = resultSet.getString("proveedor");
                String categoria = resultSet.getString("categoria");
                int stock = resultSet.getInt("Stock");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{proveedor, categoria, stock});
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static void cargarDatosDesdeBaseDeDatosPedidos() {

        String[] columnNames = {"Codigo Cliente", "Cliente", "Apellidos", "Codigo", "Proveedor", "Nombre de Producto", "Cantidad", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };

        // Establecer el modelo en la tabla
        FormPedidos.dataTableP.setModel(model);

        try {
            // Consulta SQL para seleccionar datos de la base de datos
            String sql = "SELECT codigo_Client, nombre_Client, CAST(apellidoPat_Client + ' ' + apellidoMat_Client AS VARCHAR (50)) AS Apellidos, codigo, proveedor," +
                    " producto, cantidad, precio FROM Pedidos";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormPedidos.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String codigo = resultSet.getString("codigo_Client");
                String cliente = resultSet.getString("nombre_Client");
                String apellidos = resultSet.getString("Apellidos");
                int codigoProd = resultSet.getInt("codigo");
                String proveedor = resultSet.getString("proveedor");
                String nombre = resultSet.getString("producto");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{codigo, cliente, apellidos, codigoProd, proveedor, nombre, cantidad, precio});
            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static void cargarDatosDesdeBaseDeDatosCategorias() {

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
            String sql = "SELECT Proveedor, Categoria, Nombre, Precio FROM Producto;";
            // preparando consulta a SQL Server
            PreparedStatement statement = FormCategoria.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de las columnas en cada fila
                String proveedor = resultSet.getString("Proveedor");
                String categoria = resultSet.getString("Categoria");
                String producto = resultSet.getString("Nombre");
                double precio = resultSet.getDouble("Precio");

                // Agregar fila a la tabla con los valores ingresados
                model.addRow(new Object[]{proveedor, categoria, producto, precio});

            }

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException ex) {
            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA
        }
    }

    public static int numeroFilasTablaPedidos() {

        int rowCount = 0;

        try {

            // Consulta SQL para contar las filas en la tabla "Pedidos"
            String sql = "SELECT COUNT(*) AS contar_fila FROM Pedidos";
            // Preparando consulta a SQL Server
            PreparedStatement statement = FormRegistro.con.prepareStatement(sql);
            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt("contar_fila");
            }
            // Cerrar el statement después de usarlo
            statement.close();

        } catch (SQLException e) {

            // Mostrar un mensaje de error si hay un problema con la base de datos
            JOptionPane.showMessageDialog(null, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace(); IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA

        }

        return rowCount;
    }

}
