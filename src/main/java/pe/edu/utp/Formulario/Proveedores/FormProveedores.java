package pe.edu.utp.Formulario.Proveedores;

import pe.edu.utp.Formulario.Boleta;
import pe.edu.utp.Formulario.PrincipalAdmin.FormPrincipalAdmin;
import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.cargadoresBD;
import pe.edu.utp.Formulario.txtAndpdf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class FormProveedores extends JFrame {

    public static Connection con;
    private Conect_BD conexion;

    //Componenetes locales
    private JTextField textProveedor;
    private JTextField textCategoria;
    private JTextField textProductos;
    private JTextField textStock;
    private int initialX, initialY;
    public static JTable dataTableP;
    public static JTable dataTableN;
    public static JTable dataTablePR;
    public FormProveedores() {

        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Gestion de Proveedores");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(new Color(27, 27, 27));

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
        // JText TITULO
        JLabel lblTitulo1 = new JLabel("Tienda de componentes");
        lblTitulo1.setForeground(new Color(255, 255, 255));
        lblTitulo1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo2 = new JLabel("'TechGear'");
        lblTitulo2.setForeground(new Color(255, 255, 255));
        lblTitulo2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JLabel lblTitulo3 = new JLabel("Gestor de proveedores");
        lblTitulo3.setForeground(new Color(255, 255, 255));
        lblTitulo3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        // JLabel INGRESE TEXTO
        JLabel lblCategoria = new JLabel("Total de Categorias:");
        lblCategoria.setForeground(new Color(255, 255, 255));
        lblCategoria.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblProductos = new JLabel("Total de Productos:");
        lblProductos.setForeground(new Color(255, 255, 255));
        lblProductos.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        JLabel lblStock = new JLabel("Total de Stock:");
        lblStock.setForeground(new Color(255, 255, 255));
        lblStock.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // CONDICIONES DE BORDEADO
        Border lineBorder = new LineBorder(new Color(255, 255, 255), 1); // Cambia el color y el grosor del borde
        Border topTransparentBorder = BorderFactory.createEmptyBorder(-1, -1, 5, -1); // Ajusta el espacio superior, Borde personalizado para hacer transparente la parte superior
        Border compoundBorder = BorderFactory.createCompoundBorder(topTransparentBorder, lineBorder);// Combina los bordes para crear el efecto deseado
        // JTextLabel texto
        textCategoria = new JTextField();
        textCategoria.setOpaque(false);
        textCategoria.setEditable(false);
        textCategoria.setBorder(compoundBorder);
        textCategoria.setFont(new Font("Arial", Font.PLAIN, 13));
        textCategoria.setForeground(new Color(204, 204, 204));

        textProductos = new JTextField();
        textProductos.setOpaque(false);
        textProductos.setEditable(false);
        textProductos.setBorder(compoundBorder);
        textProductos.setFont(new Font("Arial", Font.PLAIN, 13));
        textProductos.setForeground(new Color(204, 204, 204));

        textStock = new JTextField();
        textStock.setOpaque(false);
        textStock.setEditable(false);
        textStock.setBorder(compoundBorder);
        textStock.setFont(new Font("Arial", Font.PLAIN, 13));
        textStock.setForeground(new Color(204, 204, 204));

        textProveedor = new JTextField();
        textProveedor.setOpaque(false);
        textProveedor.setVisible(false);
        textProveedor.setBorder(compoundBorder);
        textProveedor.setFont(new Font("Arial", Font.PLAIN, 13));
        textProveedor.setForeground(new Color(204, 204, 204));
        //JTable agregamos tablas
        // Crear una tabla con datos
        dataTableP = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(dataTableP);

        // Crear una tabla con filtro de provedores
        String[] columnNamesN = {"Nombre de Proveedores"};
        Object[][] dataN = {

        };

        DefaultTableModel modelN = new DefaultTableModel(dataN, columnNamesN);
        dataTableN = new JTable(modelN);
        JScrollPane tableScrollPaneN = new JScrollPane(dataTableN);

        // Crear una tabla con datos para vender
        String[] columnNamesPR = {"Productos", "Categoria", "Stock"};
        Object[][] dataPR = {

        };

        DefaultTableModel modelPR = new DefaultTableModel(dataPR, columnNamesPR){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        dataTablePR = new JTable(modelPR);
        JScrollPane tableScrollPanePR = new JScrollPane(dataTablePR);
        tableScrollPanePR.setVisible(false);

        //Borde
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204, 204, 204), 1, false); // El tercer parámetro es para redondear
        // BTN Filtrar
        JButton filtrarButton = new JButton("Filtrar");
        filtrarButton.setBorder(white);
        filtrarButton.setBackground(new Color(255, 255, 255));
        filtrarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        filtrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filtrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                filtrarButton.setBorder(gray);
                filtrarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filtrarButton.setBorder(white);
                filtrarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        JButton datosButton = new JButton("Descargar información");
        datosButton.setBorder(white);
        datosButton.setVisible(false);
        datosButton.setBackground(new Color(255, 255, 255));
        datosButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        datosButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        datosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                datosButton.setBorder(gray);
                datosButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                datosButton.setBorder(white);
                datosButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        JButton infoButton = new JButton("Información");
        infoButton.setBorder(white);
        infoButton.setBackground(new Color(255, 255, 255));
        infoButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        infoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                infoButton.setBorder(gray);
                infoButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoButton.setBorder(white);
                infoButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setBorder(white);
        limpiarButton.setBackground(new Color(255, 255, 255));
        limpiarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        limpiarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        limpiarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                limpiarButton.setBorder(gray);
                limpiarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                limpiarButton.setBorder(white);
                limpiarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        atras.setBounds(15, 15, 35, 35);
        imagenIcono.setBounds(590, 45, 70, 70);
        lblTitulo1.setBounds(245, 15, 250, 30);
        lblTitulo2.setBounds(300, 45, 150, 30);
        lblTitulo3.setBounds(260, 85, 180, 30);
        lblCategoria.setBounds(220, 410, 180, 17);
        textCategoria.setBounds(370, 409, 60, 30);
        lblProductos.setBounds(220, 470, 180, 17);
        textProductos.setBounds(370, 469, 60, 30);
        lblStock.setBounds(460, 410, 180, 17);
        textStock.setBounds(580, 409, 60, 30);
        textProveedor.setBounds(480, 530, 140, 30);
        filtrarButton.setBounds(250, 520, 60, 25);
        infoButton.setBounds(520, 470, 100, 25);
        limpiarButton.setBounds(530, 520, 80, 25);
        datosButton.setBounds(250, 520, 140, 25);
        tableScrollPane.setBounds(50, 160, 600, 250);
        tableScrollPaneN.setBounds(50, 420, 150, 155);
        tableScrollPanePR.setBounds(700, 160, 550, 250);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(lblTitulo1);
        formPanel.add(lblTitulo2);
        formPanel.add(lblTitulo3);
        formPanel.add(lblCategoria);
        formPanel.add(textCategoria);
        formPanel.add(lblProductos);
        formPanel.add(textProductos);
        formPanel.add(lblStock);
        formPanel.add(textStock);
        formPanel.add(textProveedor);
        formPanel.add(limpiarButton);
        formPanel.add(filtrarButton);
        formPanel.add(infoButton);
        formPanel.add(datosButton);


        add(atras, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER);
        add(tableScrollPaneN, BorderLayout.CENTER);
        add(tableScrollPanePR, BorderLayout.CENTER);
        add(formPanel, BorderLayout.CENTER);

        dataTableN.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int filaSeleccionada = dataTableN.getSelectedRow();
                if (filaSeleccionada != -1) {

                    // Obtener valores de la fila seleccionada
                    String nombre = dataTableN.getValueAt(filaSeleccionada, 0).toString();

                    // Establecer los valores en los campos de texto
                    textProveedor.setText(nombre);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = dataTableN.getSelectedRow();

                if (filaSeleccionada != -1) {

                    // Deseleccionar la fila en la tabla
                    dataTableN.clearSelection();

                    // Borrar los campos de texto
                    textCategoria.setText("");
                    textProductos.setText("");
                    textStock.setText("");
                    textProveedor.setText("");

                } else {

                    JOptionPane.showMessageDialog(FormProveedores.this, "No hay datos para limpiar");

                }

            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Proveedor.filtrarDatosProveedores();
                filtrarButton.setVisible(false);
                datosButton.setVisible(true);

            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = dataTableN.getSelectedRow();

                if (filaSeleccionada != -1) {

                    String proveedor = textProveedor.getText();

                    textCategoria.setText(String.valueOf(Proveedor.cantidadCatProveedores(proveedor)));
                    textProductos.setText(String.valueOf(Proveedor.cantidadProProveedores(proveedor)));
                    textStock.setText(String.valueOf(Proveedor.cantidadStoProveedores(proveedor)));
                    Proveedor.infoDatosProveedores(proveedor);

                } else {

                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");

                }

            }
        });

        datosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel modelPR = (DefaultTableModel) dataTablePR.getModel();

                String productostr = textProductos.getText();
                String categoriastr = textCategoria.getText();
                String stockstr = textStock.getText();
                String proveedor = textProveedor.getText();

                Proveedor.setNombre_proveedor(proveedor);

                int producto = Integer.parseInt(productostr);
                int categoria = Integer.parseInt(categoriastr);
                int stock = Integer.parseInt(stockstr);

                try {
                    if (!(productostr.isEmpty() || categoriastr.isEmpty() || stockstr.isEmpty())) {
                        Boleta.infoProveedores(Proveedor.getNombre_proveedor(), categoria, producto, stock);
                        txtAndpdf.GenerarPDFinfoProveedor(modelPR, producto, categoria, stock);
                    } else {
                        JOptionPane.showMessageDialog(null, "Un campo esta vacio");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosProveedores();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormProveedores mainWindow = new FormProveedores();
                mainWindow.setVisible(true);
            }
        });
    }

}
