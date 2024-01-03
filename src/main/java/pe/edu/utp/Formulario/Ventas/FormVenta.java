package pe.edu.utp.Formulario.Ventas;

import com.microsoft.sqlserver.jdbc.StringUtils;
import pe.edu.utp.Formulario.Boleta;
import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.Registrar.FormRegistro;
import pe.edu.utp.Formulario.iniciarSesion.Login;
import pe.edu.utp.Formulario.txtAndpdf;
import pe.edu.utp.Formulario.cargadoresBD;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class FormVenta extends JFrame {

    // Conexion a base de datos
    public static Connection con;
    public static Conect_BD conexion;

    //Componenetes locales
    private JTextField textCodigo;
    private JTextField textNombre;
    private JTextField textPrecio;
    private JTextField textCantidad;
    private JTextField textStock;
    private JTextField textDesc;
    private JTextField textTotal;
    private JTextField textTotal2;
    private JTextField textProveedor;
    private int initialX, initialY;
    public static JTable dataTableV;
    public static JTable dataTableF;

    private double precioTotal = 0.0;

    public FormVenta() {

        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Registro de productos");//nombre
        setSize(800, 680);//dimensiones
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
                FormRegistro mainWindow = new FormRegistro();
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
        // JText TITULO
        JLabel lblTitulo1 = new JLabel("Tienda de componentes");
        lblTitulo1.setForeground(new Color(255, 255, 255));
        lblTitulo1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo2 = new JLabel("'TechGear'");
        lblTitulo2.setForeground(new Color(255, 255, 255));
        lblTitulo2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo3 = new JLabel("Vender producto");
        lblTitulo3.setForeground(new Color(255, 255, 255));
        lblTitulo3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        JLabel lblTitulo4 = new JLabel("Resumen de factura");
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

        JLabel lblCantidad = new JLabel("Cant. a vender:");
        lblCantidad.setForeground(new Color(255, 255, 255));
        lblCantidad.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblDesc = new JLabel("Descuento (%):");
        lblDesc.setForeground(new Color(255, 255, 255));
        lblDesc.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setForeground(new Color(255, 255, 255));
        lblTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        //Borde
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204, 204, 204), 1, false); // El tercer parámetro es para redondear
        // CONDICIONES DE BORDEADO
        Border lineBorder = new LineBorder(new Color(255, 255, 255), 1); // Cambia el color y el grosor del borde
        Border topTransparentBorder = BorderFactory.createEmptyBorder(-1, -1, 5, -1); // Ajusta el espacio superior, Borde personalizado para hacer transparente la parte superior
        Border compoundBorder = BorderFactory.createCompoundBorder(topTransparentBorder, lineBorder);// Combina los bordes para crear el efecto deseado
        // Check box y su label
        // Crear un ButtonGroup para agrupar los JCheckBox en uno solo
        ButtonGroup agruparCheckBox = new ButtonGroup();

        JLabel lblVip = new JLabel("Cliente VIP:");
        lblVip.setForeground(new Color(255, 255, 255));
        lblVip.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JCheckBox checkSi = new JCheckBox("Si");
        checkSi.setOpaque(false);
        checkSi.setBackground(new Color(255, 255, 255));
        checkSi.setForeground(new Color(255, 255, 255));

        JCheckBox checkNo = new JCheckBox("No");
        checkNo.setOpaque(false);
        checkNo.setSelected(true);
        checkNo.setBackground(new Color(255, 255, 255));
        checkNo.setForeground(new Color(255, 255, 255));

        // Agregar los JCheckBox al ButtonGroup
        agruparCheckBox.add(checkSi);
        agruparCheckBox.add(checkNo);

        // Asegurarse de que ninguno de los JCheckBox esté seleccionado inicialmente
        checkSi.setSelected(false);
        checkNo.setSelected(false);
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

        textCantidad = new JTextField();
        textCantidad.setOpaque(false);
        textCantidad.setBorder(compoundBorder);
        textCantidad.setFont(new Font("Arial", Font.PLAIN, 13));
        textCantidad.setForeground(new Color(204, 204, 204));

        textStock = new JTextField();
        textStock.setVisible(false);
        textStock.setOpaque(false);
        textStock.setBorder(compoundBorder);
        textStock.setFont(new Font("Arial", Font.PLAIN, 13));
        textStock.setForeground(new Color(204, 204, 204));

        textDesc = new JTextField();
        textDesc.setOpaque(false);
        textDesc.setEditable(false);
        textDesc.setBorder(compoundBorder);
        textDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        textDesc.setForeground(new Color(204, 204, 204));

        textTotal = new JTextField("S/. 0.0");
        textTotal.setOpaque(false);
        textTotal.setEditable(false);
        textTotal.setBorder(compoundBorder);
        textTotal.setFont(new Font("Arial", Font.PLAIN, 13));
        textTotal.setForeground(new Color(204, 204, 204));

        textTotal2 = new JTextField();
        textTotal2.setOpaque(false);
        textTotal2.setEditable(false);
        textTotal2.setVisible(false);
        textTotal2.setBorder(compoundBorder);
        textTotal2.setFont(new Font("Arial", Font.PLAIN, 13));
        textTotal2.setForeground(new Color(204, 204, 204));

        textProveedor = new JTextField();
        textProveedor.setOpaque(false);
        textProveedor.setEditable(false);
        textProveedor.setVisible(false);
        textProveedor.setBorder(compoundBorder);
        textProveedor.setFont(new Font("Arial", Font.PLAIN, 13));
        textProveedor.setForeground(new Color(204, 204, 204));
        //JTable agregamos tablas
        // Crear una tabla con datos de ejemplo
        dataTableV = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(dataTableV);

        // Crear una tabla con datos para vender
        String[] columnNamesF = {"Codigo", "Nombre", "Precio", "Cantidad", "Stock", "Proveedor"};
        Object[][] dataF = {

        };

        DefaultTableModel modelF = new DefaultTableModel(dataF, columnNamesF){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        dataTableF = new JTable(modelF);

        // Establece los nombres de las columnas en el modelo de la tabla
        modelF.setColumnIdentifiers(columnNamesF);
        // Configura el ancho mínimo, máximo y real de la columna "Stock" (cuarta columna) en 0 para ocultarla visualmente
        dataTableF.getColumnModel().getColumn(4).setMinWidth(0); // Oculta la columna "Stock"
        dataTableF.getColumnModel().getColumn(4).setMaxWidth(0);
        dataTableF.getColumnModel().getColumn(4).setWidth(0);

        dataTableF.getColumnModel().getColumn(5).setMinWidth(0); // Oculta la columna "proveedor"
        dataTableF.getColumnModel().getColumn(5).setMaxWidth(0);
        dataTableF.getColumnModel().getColumn(5).setWidth(0);

        JScrollPane tableScrollPaneF = new JScrollPane(dataTableF);

        // BTN Agregar, actualizar, eliminar
        JButton facturaButton = new JButton("Factura"); // Resumen de factura
        facturaButton.setBorder(white);
        facturaButton.setBackground(new Color(255, 255, 255));
        facturaButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        facturaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        facturaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                facturaButton.setBorder(gray);
                facturaButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                facturaButton.setBorder(white);
                facturaButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        JButton comprarButton = new JButton("Vender");
        comprarButton.setBorder(white);
        comprarButton.setBackground(new Color(255, 255, 255));
        comprarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        comprarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comprarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                comprarButton.setBorder(gray);
                comprarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                comprarButton.setBorder(white);
                comprarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
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
                eliminarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                eliminarButton.setBorder(white);
                eliminarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
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
                deseleccionarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deseleccionarButton.setBorder(white);
                deseleccionarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        JButton actualizarButton = new JButton("OK"); // Resumen de factura
        actualizarButton.setBorder(white);
        actualizarButton.setVisible(false);
        actualizarButton.setBackground(new Color(255, 255, 255));
        actualizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        actualizarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        actualizarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                actualizarButton.setBorder(gray);
                actualizarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                actualizarButton.setBorder(white);
                actualizarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        // Condiciones del checkbox y el boton actualizar
        // Agregar ActionListener a los JCheckBox para controlar su comportamiento
        checkSi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se selecciona "Si", deselecciona "No"
                checkNo.setSelected(false);
            }
        });

        checkNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se selecciona "No", deselecciona "Si"
                checkSi.setSelected(false);
            }
        });
        // Agregar un ItemListener al checkSi para habilitar textDesc cuando esté seleccionado
        checkSi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textDesc.setEditable(true);
                    Venta.setVip(true);
                } else {
                    textDesc.setEditable(false);
                    Venta.setVip(false);
                }
            }
        });

        // Agregar un ItemListener al checkNo para deshabilitar textDesc cuando esté seleccionado
        checkNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textDesc.setEditable(false);
                } else {
                    textDesc.setEditable(true);
                }
            }
        });

        // Agregar un DocumentListener al campo de texto textDesc
        textDesc.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarButton.setVisible(debeMostrarBoton());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarButton.setVisible(debeMostrarBoton());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarButton.setVisible(debeMostrarBoton());
            }

            private boolean debeMostrarBoton() {
                String descText = textDesc.getText();
                return checkSi.isSelected() && ((descText.length() == 1 || descText.length() == 2 ));
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        atras.setBounds(15, 15, 35, 35);
        imagenIcono.setBounds(520, 7, 70, 70);
        lblTitulo1.setBounds(70, 30, 250, 30);
        lblTitulo2.setBounds(125, 60, 150, 30);
        lblTitulo3.setBounds(110, 110, 180, 30);
        lblTitulo4.setBounds(110, 380, 180, 30);
        lblCodigo.setBounds(620, 20, 100, 17);
        textCodigo.setBounds(680, 20, 70, 30);
        lblNombre.setBounds(40, 160, 100, 17);
        textNombre.setBounds(170, 159, 150, 30);
        lblPrecio.setBounds(40, 210, 100, 17);
        textPrecio.setBounds(170, 209, 150, 30);
        lblCantidad.setBounds(40, 260, 120, 17);
        textCantidad.setBounds(170, 259, 150, 30);
        textStock.setBounds(140, 700, 150, 30);
        lblVip.setBounds(40, 430, 120, 17);
        checkSi.setBounds(170, 430, 50, 17);
        checkNo.setBounds(230, 430, 50, 17);
        lblDesc.setBounds(40, 480, 150, 30);
        textDesc.setBounds(170, 480, 80, 30);
        lblTotal.setBounds(40, 530, 150, 30);
        textTotal.setBounds(170, 530, 150, 30);
        textTotal2.setBounds(300, 600, 150, 30);
        textProveedor.setBounds(250, 600, 95, 25);
        tableScrollPane.setBounds(370, 120, 370, 260);
        tableScrollPaneF.setBounds(370, 450, 370, 150);
        facturaButton.setBounds(40, 320, 95, 25);
        deseleccionarButton.setBounds(198, 320, 120, 25);
        comprarButton.setBounds(40, 600, 95, 25);
        eliminarButton.setBounds(195, 600, 95, 25);
        actualizarButton.setBounds(280, 480, 40, 25);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
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
        formPanel.add(lblCantidad);
        formPanel.add(textCantidad);
        formPanel.add(textStock);
        formPanel.add(lblVip);
        formPanel.add(checkSi);
        formPanel.add(checkNo);
        formPanel.add(lblDesc);
        formPanel.add(textDesc);
        formPanel.add(lblTotal);
        formPanel.add(textTotal);
        formPanel.add(textTotal2);
        formPanel.add(textProveedor);
        formPanel.add(facturaButton);
        formPanel.add(comprarButton);
        formPanel.add(eliminarButton);
        formPanel.add(deseleccionarButton);
        formPanel.add(actualizarButton);

        add(atras, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER);
        add(tableScrollPaneF, BorderLayout.CENTER);
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
                    String precio = dataTableV.getValueAt(filaSeleccionada, 3).toString();
                    String stock = dataTableV.getValueAt(filaSeleccionada, 4).toString();

                    // Establecer los valores en los campos de texto
                    textCodigo.setText(codigo);
                    textNombre.setText(nombre);
                    textPrecio.setText(precio);
                    textStock.setText(stock);
                    textProveedor.setText(proveedor);
                }
            }
        });


        facturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener los valores de las casillas de texto
                String codigoStr = textCodigo.getText();
                String proveedor = textProveedor.getText();
                String nombre = textNombre.getText();
                String precioStr = textPrecio.getText();
                String stockStr = textStock.getText();
                String cantidadStr = textCantidad.getText();

                if (!StringUtils.isNumeric(cantidadStr)) {
                    JOptionPane.showMessageDialog(FormVenta.this, "La cantidad debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                if (cantidadStr.isBlank()) {
                    // No hay nada en el campo cantidad
                    JOptionPane.showMessageDialog(FormVenta.this, "Ingrese la cantidad de producto");
                } else {
                    // Convertir la cadenas a número
                    int cantidad = Integer.parseInt(cantidadStr);
                    int codigo = Integer.parseInt(codigoStr);
                    double precio = Double.parseDouble(precioStr);
                    int stock = Integer.parseInt(stockStr);

                    int filaSeleccionada = dataTableV.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String priceStr = dataTableV.getValueAt(filaSeleccionada, 3).toString();
                        double price = Double.parseDouble(priceStr);

                        double priceTot = price * cantidad;

                        // Sumar el precio al precio total
                        precioTotal += priceTot;

                        // Actualizar el campo de texto para mostrar el precio total
                        textTotal2.setText(String.valueOf(precioTotal));
                        textTotal.setText("S/. " + String.valueOf(precioTotal));

                        String totalStr = textTotal2.getText();
                        double total = Double.parseDouble(totalStr);
                        Venta.setAntesDesc(total);

                    }

                    try {

                        // Llamamos al método para actualizar la base de datos
                        Venta.eliminarStockEnBasedeDatos(codigo, stock, cantidad);

                        // Actualizar el stock del producto específico después de la venta
                        int stockActualizado = stock - cantidad;

                        // Insertar una nueva fila en la tabla dataTableF con el stock actualizado
                        DefaultTableModel modelF = (DefaultTableModel) dataTableF.getModel();
                        modelF.addRow(new Object[] { codigo, nombre, precio, cantidad, stockActualizado, proveedor });

                        // Limpiar los campos de texto
                        textCodigo.setText("");
                        textNombre.setText("");
                        textPrecio.setText("");
                        textCantidad.setText("");
                        textProveedor.setText("");
                        textStock.setText(String.valueOf(stockActualizado)); // Actualizar el campo de stock en la GUI

                        cargadoresBD.cargarDatosDesdeBaseDeDatosProductos();

                    } catch (NumberFormatException exception) {
                        // Manejar errores de formato de número
                        JOptionPane.showMessageDialog(FormVenta.this, "Error en el formato de número", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
                textProveedor.setText("");
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = dataTableF.getSelectedRow();

                if (filaSeleccionada != -1) {

                    // Obtiene el modelo de tabla
                    DefaultTableModel modelF = (DefaultTableModel) dataTableF.getModel();

                    // Obtener valores de la fila seleccionada
                    String codigoStr = dataTableF.getValueAt(filaSeleccionada, 0).toString();
                    String priceStr = dataTableF.getValueAt(filaSeleccionada, 2).toString();
                    String cantidadStr = dataTableF.getValueAt(filaSeleccionada, 3).toString();
                    String stockStr = dataTableF.getValueAt(filaSeleccionada, 4).toString();

                    // Convertir las cadenas a números
                    int codigo = Integer.parseInt(codigoStr);
                    double price = Double.parseDouble(priceStr);
                    int cantidad = Integer.parseInt(cantidadStr);
                    int stock = Integer.parseInt(stockStr);

                    double priceTot = price * cantidad;

                    // Sumar el precio al precio total
                    precioTotal -= priceTot;

                    String letrasnum = textTotal.getText();

                    if (letrasnum.length() <= 7) {
                        textTotal.setText("S/. 0.0");
                    } else {
                        textTotal.setText("S/. " + String.valueOf(precioTotal));
                    }

                    // Actualizar el campo de texto para mostrar el precio total
                    textTotal2.setText(String.valueOf(precioTotal));

                    // Llamamos al método para actualizar la base de datos
                    Venta.aumentarStockEnBasedeDatos(codigo, stock, cantidad);

                    // Elimina la fila del modelo de tabla
                    modelF.removeRow(filaSeleccionada);

                    cargadoresBD.cargarDatosDesdeBaseDeDatosProductos();
                } else {
                    // Muestra un mensaje de error si no se selecciona una fila
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar");
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String descStr = textDesc.getText();
                String totalStr = textTotal2.getText();

                if (!(StringUtils.isNumeric(descStr))) {
                    JOptionPane.showMessageDialog(FormVenta.this, "El descuento debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                double total = Double.parseDouble(totalStr);

                double desc, descuento, precioTot;

                if (descStr.length() == 1) {
                    String dsc = "0.0" + descStr;
                    desc = Double.parseDouble(dsc);
                } else {
                    String dsc = "0." + descStr;
                    desc = Double.parseDouble(dsc);
                }

                descuento = total * desc;
                precioTot = total - descuento;

                // Actualizar el campo de texto para mostrar el precio total
                textTotal2.setText(String.valueOf(precioTot));
                textTotal.setText("S/. " + textTotal2.getText());

            }

        });

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtiene el modelo de tabla
                DefaultTableModel modelF = (DefaultTableModel) dataTableF.getModel();
                int conTabla = modelF.getRowCount();
                String textTot = textTotal2.getText();
                String textDescuento = textDesc.getText();

                String nombreClient, apellidoPat, apellidoMat, dni;
                int selec, selec2;

                try {

                    // Verificar si hay al menos una fila en la tabla
                    if (conTabla > 0) {

                        double total = Double.parseDouble(textTot);
                        Venta.setPrecioTotal(total);

                        selec = JOptionPane.showConfirmDialog(null, "¿Boleta con nombre de cliente?", "Cliente", JOptionPane.YES_NO_OPTION);

                        if (selec == JOptionPane.YES_OPTION) {

                            selec2 = JOptionPane.showConfirmDialog(null, "¿Es cliente nuevo?", "Cliente", JOptionPane.YES_NO_OPTION);

                            if (selec2 == JOptionPane.YES_OPTION) {

                                nombreClient = JOptionPane.showInputDialog("Nombre del cliente: ");
                                apellidoPat = JOptionPane.showInputDialog("Apellido paterno: ");
                                apellidoMat = JOptionPane.showInputDialog("Apellido materno: ");
                                dni = JOptionPane.showInputDialog("DNI: ");

                                //Seteamos los datos del cliente a variables de la clase Venta
                                Venta.setNombreClient(nombreClient);
                                Venta.setApellidoPat(apellidoPat);
                                Venta.setApellidoMat(apellidoMat);
                                Venta.setDni(dni);

                                //Agregamos memtodos para generar boletas, pdf y agregamos el nuevo cliente
                                Venta.isAgregarCliente(Venta.getDni(), Venta.getNombreClient(), Venta.getApellidoPat(), Venta.getApellidoMat());
                                Boleta.GenerarBoletaconClientes(modelF, textDescuento);
                                txtAndpdf.GenerarPDFconClientes(modelF, textDescuento);

                            } else {

                                boolean clienteEncontrado = false; // Inicializamos la variable como falso

                                while (!clienteEncontrado) {
                                    dni = JOptionPane.showInputDialog("Ingrese DNI del cliente: ");
                                    clienteEncontrado = Venta.encontrarCliente(dni); // Verificar si el cliente existe

                                    if (clienteEncontrado) {
                                        // El cliente existe, puedes continuar con el método
                                        Venta.obtenerCliente(dni);
                                        Boleta.GenerarBoletaconClientes(modelF, textDescuento);
                                        txtAndpdf.GenerarPDFconClientes(modelF, textDescuento);
                                    } else {
                                        // El cliente no existe en la base de datos, mostrar un mensaje
                                        JOptionPane.showMessageDialog(null, "Este cliente no existe en la base de datos");
                                    }
                                }

                            }

                        } else {
                            //Agregamos memtodos para generar boletas y pdf
                            Boleta.GenerarBoletasinbClientes(modelF, textDescuento);
                            txtAndpdf.GenerarPDFsinClientes(modelF, textDescuento);
                        }

                        //Hacemos un txt de movimiemtos
                        Boleta.productVenta(modelF);
                        //Esos movimientos se registran tambien en la base de datos
                        Venta.isAgregarMovimiento(Login.getCodigoLogin(), Login.getNombre(), Login.getApellidoPat(), Login.getApellidoMat(),
                                modelF);

                        // Limpiar la tabla
                        modelF.setRowCount(0);

                        // Limpiar los campos de texto
                        textDesc.setText("");
                        textTotal2.setText("");
                        textTotal.setText("S/. 0.0");

                        // Reiniciar el precioTotal a cero
                        precioTotal = 0.0;

                        cargadoresBD.cargarDatosDesdeBaseDeDatosProductos();

                    } else {
                        // Muestra un mensaje de error si no hay datos en la tabla
                        JOptionPane.showMessageDialog(null, "Agregue productos a la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(FormVenta.this, "Error al generar la boleta.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }

        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosProductos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormVenta mainWindow = new FormVenta();
                mainWindow.setVisible(true);
            }
        });
    }
}
