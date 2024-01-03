package pe.edu.utp.Formulario.Registrar;

import com.microsoft.sqlserver.jdbc.StringUtils;
import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.Pedidos.FormPedidos;
import pe.edu.utp.Formulario.PrincipalAdmin.FormPrincipalAdmin;
import pe.edu.utp.Formulario.Ventas.FormVenta;
import pe.edu.utp.Formulario.cargadoresBD;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormRegistro extends JFrame {

    // Conexion a base de datos
    public static Connection con;
    public static Conect_BD conexion;

    // los text field para ingresar datos
    private JTextField textCodigo;
    private JTextField textNombre;
    private JTextField textPrecio;
    private JTextField textStock;
    private JTextField textProveedor;
    private JTextField textCategoria;
    private int initialX, initialY;
    public static JTable dataTableV;
    //private static String stockSeleccionado; // Variable para almacenar el stock seleccionado


    //public static String getStockSeleccionado() {return stockSeleccionado;}

    public FormRegistro() {

        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Registro de productos");//nombre
        setSize(950, 600);//dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));//icono de programa

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);//creando el JPanel
        formPanel.setBackground(new Color(27, 27, 27));//Agregando un color de fondo

        // -----------------MODIFICAMOS LOS COMPONENTES---------------------
        // Boton atras
        JLabel atras = new JLabel();
        atras.setBackground(new Color(27, 27, 27));
        ImageIcon imagenAtras = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Flecha_atras.png");
        atras.setIcon(imagenAtras);
        atras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        atras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormPrincipalAdmin mainWindow = new FormPrincipalAdmin();
                mainWindow.setVisible(true);
            }
        });
        // Borde movible de la ventana
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(30, 30)); // Las dimensiones del JPanel
        header.setBackground(new Color(27, 27, 27));
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialX = e.getXOnScreen() - getX();
                initialY = e.getYOnScreen() - getY();
            }
        });

        header.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - initialX;
                int newY = e.getYOnScreen() - initialY;

                setLocation(newX, newY);
            }
        });
        // JLabel imagen ICONO
        JLabel imagenIcono = new JLabel();
        ImageIcon imagenIcon = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_pequeño.png");
        imagenIcono.setIcon(imagenIcon);
        // JLabel venta
        JLabel venta = new JLabel(" Venta");
        venta.setForeground(new Color(255, 255, 255));
        venta.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        ImageIcon imagenVenta = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Pago.png");
        venta.setIcon(imagenVenta);
        venta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        venta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormVenta mainWindow = new FormVenta();
                mainWindow.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                venta.setForeground(new Color(128, 128, 128));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                venta.setForeground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });
        // JLabel pedidos
        JLabel pedidos = new JLabel(" Pedidos");
        pedidos.setForeground(new Color(255, 255, 255));
        pedidos.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        ImageIcon imagenPedido = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\bolsaCompra.png");
        pedidos.setIcon(imagenPedido);
        pedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormPedidos mainWindow = new FormPedidos();
                mainWindow.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pedidos.setForeground(new Color(128, 128, 128));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pedidos.setForeground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        //Verificamos el numero de filas de la tabla pedidos
        int numeroPed = cargadoresBD.numeroFilasTablaPedidos();
        String numPed;

        // Dependiendo de si haya se coloca numero y si no es vacio agregamos a la variable numPed
        if (numeroPed != 0) {
            numPed = String.valueOf(numeroPed);
        } else {
            numPed = String.valueOf("");
        }

        JLabel numPedidos = new JLabel(numPed);
        numPedidos.setForeground(new Color(255, 255, 255));
        numPedidos.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        numPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // JText TITULO
        JLabel lblTitulo1 = new JLabel("Tienda de componentes");
        lblTitulo1.setForeground(new Color(255, 255, 255));
        lblTitulo1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo2 = new JLabel("'TechGear'");
        lblTitulo2.setForeground(new Color(255, 255, 255));
        lblTitulo2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo3 = new JLabel("Agregar producto");
        lblTitulo3.setForeground(new Color(255, 255, 255));
        lblTitulo3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        JLabel lblTitulo4 = new JLabel("Vender Producto");
        lblTitulo4.setForeground(new Color(255, 255, 255));
        lblTitulo4.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // JLabel INGRESE TEXTO
        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setForeground(new Color(255, 255, 255));
        lblCodigo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(new Color(255, 255, 255));
        lblPrecio.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setForeground(new Color(255, 255, 255));
        lblStock.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setForeground(new Color(255, 255, 255));
        lblProveedor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setForeground(new Color(255, 255, 255));
        lblCategoria.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // CONDICIONES DE BORDEADO
        Border lineBorder = new LineBorder(new Color(255, 255, 255), 1); // Cambia el color y el grosor del borde
        Border topTransparentBorder = BorderFactory.createEmptyBorder(-1, -1, 5, -1); // Ajusta el espacio superior, Borde personalizado para hacer transparente la parte superior
        Border compoundBorder = BorderFactory.createCompoundBorder(topTransparentBorder, lineBorder);// Combina los bordes para crear el efecto deseado
        // JTextLabel texto
        textCodigo = new JTextField();
        textCodigo.setOpaque(false);
        textCodigo.setBorder(compoundBorder);
        textCodigo.setEditable(false);
        textCodigo.setFont(new Font("Arial", Font.PLAIN, 13));
        textCodigo.setForeground(new Color(204, 204, 204));

        textNombre = new JTextField();
        textNombre.setOpaque(false);
        textNombre.setBorder(compoundBorder);
        textNombre.setFont(new Font("Arial", Font.PLAIN, 13));
        textNombre.setForeground(new Color(204, 204, 204));

        textPrecio = new JTextField();
        textPrecio.setOpaque(false);
        textPrecio.setBorder(compoundBorder);
        textPrecio.setFont(new Font("Arial", Font.PLAIN, 13));
        textPrecio.setForeground(new Color(204, 204, 204));

        textStock = new JTextField();
        textStock.setOpaque(false);
        textStock.setBorder(compoundBorder);
        textStock.setFont(new Font("Arial", Font.PLAIN, 13));
        textStock.setForeground(new Color(204, 204, 204));

        textProveedor = new JTextField();
        textProveedor.setOpaque(false);
        textProveedor.setBorder(compoundBorder);
        textProveedor.setFont(new Font("Arial", Font.PLAIN, 13));
        textProveedor.setForeground(new Color(204, 204, 204));

        textCategoria = new JTextField();
        textCategoria.setOpaque(false);
        textCategoria.setBorder(compoundBorder);
        textCategoria.setFont(new Font("Arial", Font.PLAIN, 13));
        textCategoria.setForeground(new Color(204, 204, 204));
        //JTable agregamos tablas
        // Crear una tabla con datos
        dataTableV = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(dataTableV);
        // BTN Agregar, actualizar, eliminar
        //Borde blanco
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204,204,204), 1, false); // El tercer parámetro es para redondear

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBorder(white);
        agregarButton.setBackground(new Color(255, 255, 255));
        agregarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        agregarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        agregarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                agregarButton.setBorder(gray);
                agregarButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                agregarButton.setBorder(white);
                agregarButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBorder(white);
        actualizarButton.setBackground(new Color(255, 255, 255));
        actualizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        actualizarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        actualizarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                actualizarButton.setBorder(gray);
                actualizarButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                actualizarButton.setBorder(white);
                actualizarButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBorder(white);
        eliminarButton.setBackground(new Color(255, 255, 255));
        eliminarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        eliminarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eliminarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                eliminarButton.setBorder(gray);
                eliminarButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                eliminarButton.setBorder(white);
                eliminarButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        JButton deseleccionarButton = new JButton("Quitar selección");
        deseleccionarButton.setBorder(white);
        deseleccionarButton.setBackground(new Color(255, 255, 255));
        deseleccionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        deseleccionarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deseleccionarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deseleccionarButton.setBorder(gray);
                deseleccionarButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deseleccionarButton.setBorder(white);
                deseleccionarButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        atras.setBounds(15, 15, 35, 35);
        imagenIcono.setBounds(595, 7, 70, 70);
        venta.setBounds(460, 505, 100, 25);
        numPedidos.setBounds(690, 500, 100, 25);
        pedidos.setBounds(710, 505, 100, 25);
        lblTitulo1.setBounds(70, 30, 250, 30);
        lblTitulo2.setBounds(125, 60, 150, 30);
        lblTitulo3.setBounds(110, 100, 180, 30);
        lblCodigo.setBounds(760, 20, 100, 17);
        textCodigo.setBounds(820, 20, 70, 30);
        lblNombre.setBounds(40, 160, 100, 17);
        textNombre.setBounds(140, 159, 150, 30);
        lblPrecio.setBounds(40, 210, 100, 17);
        textPrecio.setBounds(140, 209, 150, 30);
        lblStock.setBounds(40, 260, 100, 17);
        textStock.setBounds(140, 259, 150, 30);
        lblProveedor.setBounds(40, 310, 100, 17);
        textProveedor.setBounds(140, 309, 150, 30);
        lblCategoria.setBounds(40, 360, 100, 17);
        textCategoria.setBounds(140, 359, 150, 30);
        tableScrollPane.setBounds(370, 120, 520, 390);
        agregarButton.setBounds(118, 420, 95, 25);
        actualizarButton.setBounds(40, 470, 95, 25);
        eliminarButton.setBounds(195, 470, 95, 25);
        deseleccionarButton.setBounds(107, 520, 120, 25);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(venta);
        formPanel.add(numPedidos);
        formPanel.add(pedidos);
        formPanel.add(lblTitulo1);
        formPanel.add(lblTitulo2);
        formPanel.add(lblTitulo3);
        formPanel.add(lblTitulo4);
        formPanel.add(lblCodigo);
        formPanel.add(textCodigo);
        formPanel.add(lblNombre);
        formPanel.add(textNombre);
        formPanel.add(lblPrecio);
        formPanel.add(textPrecio);
        formPanel.add(lblStock);
        formPanel.add(textStock);
        formPanel.add(lblProveedor);
        formPanel.add(textProveedor);
        formPanel.add(lblCategoria);
        formPanel.add(textCategoria);
        formPanel.add(agregarButton);
        formPanel.add(actualizarButton);
        formPanel.add(eliminarButton);
        formPanel.add(deseleccionarButton);

        add(atras, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.CENTER);

        dataTableV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int filaSeleccionada = dataTableV.getSelectedRow();
                if (filaSeleccionada != -1) {

                    // Obtener valores de la fila seleccionada
                    String codigo = dataTableV.getValueAt(filaSeleccionada, 0).toString();
                    String proveedor = dataTableV.getValueAt(filaSeleccionada, 1).toString();
                    String nombre = dataTableV.getValueAt(filaSeleccionada, 2).toString();
                    String categoria = dataTableV.getValueAt(filaSeleccionada, 3).toString();
                    String precio = dataTableV.getValueAt(filaSeleccionada, 4).toString();
                    String stock = dataTableV.getValueAt(filaSeleccionada, 5).toString();

                    // Establecer los valores en los campos de texto
                    textCodigo.setText(codigo);
                    textNombre.setText(nombre);
                    textPrecio.setText(precio);
                    textStock.setText(stock);
                    textProveedor.setText(proveedor);
                    textCategoria.setText(categoria);
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = dataTableV.getSelectedRow();

                // Verificar si se ha seleccionado una fila en la tabla
                if (filaSeleccionada != -1) {
                    JOptionPane.showMessageDialog(FormRegistro.this, "La selección de filas es exclusivamente para actualizar y eliminar datos", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si se ha seleccionado una fila
                }

                // Obtener los valores de las casillas de texto
                String nombre = textNombre.getText();
                String precioStr = textPrecio.getText();
                String stockStr = textStock.getText();
                String proveedorStr = textProveedor.getText();
                String categoriaStr = textCategoria.getText();

                Producto.setNombreProduc(nombre);
                Producto.setProveedor(proveedorStr);
                Producto.setCategoria(categoriaStr);

                if (!(StringUtils.isNumeric(precioStr) || StringUtils.isNumeric(stockStr))) {
                    JOptionPane.showMessageDialog(FormRegistro.this, "El precio y el stock del producto deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                if (Producto.getNombreProduc().isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                    JOptionPane.showMessageDialog(FormRegistro.this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                if (StringUtils.isNumeric(Producto.getNombreProduc()) || StringUtils.isNumeric(Producto.getProveedor()) || StringUtils.isNumeric(Producto.getCategoria())) {
                    JOptionPane.showMessageDialog(FormRegistro.this, "El campo debe ser una cadena de texto", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                try {

                    // Convertir las cadenas a números
                    double precio = Double.parseDouble(precioStr);
                    int stock = Integer.parseInt(stockStr);

                    Producto.setPrecio(precio);
                    Producto.setStock(stock);

                    // Llamamos al metodo llamado IsAgregar del validadorRegistro
                    Producto.isAgregarProducto(Producto.getProveedor(), Producto.getNombreProduc(), Producto.getCategoria(), Producto.getPrecio(), Producto.getStock());
                    cargadoresBD.cargarDatosDesdeBaseDeDatosRegistro();

                    // Limpiar las casillas de texto
                    textNombre.setText("");
                    textPrecio.setText("");
                    textStock.setText("");
                    textProveedor.setText("");
                    textCategoria.setText("");

                } catch (SQLException ex) {

                    // Mostrar un mensaje de error si hay un problema con la base de datos
                    JOptionPane.showMessageDialog(FormRegistro.this, "Existe un fallo en la base de datos", "Error SQL", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); //IMPRIME DE MANERA EXPLICITA EL TIPO DE ERROR EN CONSOLA

                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = dataTableV.getSelectedRow();
                if (filaSeleccionada != -1) {

                    DefaultTableModel model = (DefaultTableModel) dataTableV.getModel();
                    // Suponiendo que la columna 0 contiene el codigo
                    int codigoAEliminar = (int) model.getValueAt(filaSeleccionada, 0);

                    Producto.setCodigo(codigoAEliminar);

                    // Llama a un método para eliminar la fila en la base de datos
                    Producto.eliminarFilaEnBaseDeDatos(Producto.getCodigo());
                    // Elimina la fila de la tabla en la interfaz de usuario
                    model.removeRow(filaSeleccionada);

                } else {

                    // Mostrar un mensaje de error si hay no se selecciona una fila
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");

                }

                // Limpiar las casillas de texto
                textCodigo.setText("");
                textNombre.setText("");
                textPrecio.setText("");
                textStock.setText("");
                textProveedor.setText("");
                textCategoria.setText("");
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = dataTableV.getSelectedRow();

                if (filaSeleccionada != -1) {
                    String codigoStr = textCodigo.getText();
                    String nombre = textNombre.getText();
                    String precioStr = textPrecio.getText();
                    String stockStr = textStock.getText();
                    String proveedorStr = textProveedor.getText();
                    String categoriaStr = textCategoria.getText();

                    Producto.setNombreProduc(nombre);
                    Producto.setProveedor(proveedorStr);
                    Producto.setCategoria(categoriaStr);

                    // Obtener valores de los campos de texto
                    int codigo = Integer.parseInt(codigoStr);
                    double precio = Double.parseDouble(precioStr);
                    int stock = Integer.parseInt(stockStr);

                    Producto.setCodigo(codigo);
                    Producto.setPrecio(precio);
                    Producto.setStock(stock);

                    // Actualizar el producto en la base de datos
                    if (Producto.actualizarDatosEnBaseDeDatos(Producto.getCodigo(), Producto.getProveedor(),
                            Producto.getCategoria(), Producto.getNombreProduc(), Producto.getPrecio(), Producto.getStock())) {

                        dataTableV.getRowCount();
                        // Actualizar la fila en la tabla si la actualización en la base de datos fue exitosa
                        dataTableV.setValueAt(Producto.getCodigo(), filaSeleccionada, 0);
                        dataTableV.setValueAt(Producto.getProveedor(), filaSeleccionada, 1);
                        dataTableV.setValueAt(Producto.getNombreProduc(), filaSeleccionada, 2);
                        dataTableV.setValueAt(Producto.getCategoria(), filaSeleccionada, 3);
                        dataTableV.setValueAt(Producto.getPrecio(), filaSeleccionada, 4);
                        dataTableV.setValueAt(Producto.getStock(), filaSeleccionada, 5);

                        // Limpiar los campos de texto
                        textCodigo.setText("");
                        textNombre.setText("");
                        textPrecio.setText("");
                        textStock.setText("");
                        textProveedor.setText("");
                        textCategoria.setText("");
                    } else {

                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Selecciona una fila para actualizar");

                }
            }

        });

        deseleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Deseleccionar la fila en la tabla
                dataTableV.clearSelection();

                // Borrar los campos de texto
                textCodigo.setText("");
                textNombre.setText("");
                textPrecio.setText("");
                textStock.setText("");
                textProveedor.setText("");
                textCategoria.setText("");
            }
        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosRegistro();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormRegistro mainWindow = new FormRegistro();
                mainWindow.setVisible(true);
            }
        });
    }

}