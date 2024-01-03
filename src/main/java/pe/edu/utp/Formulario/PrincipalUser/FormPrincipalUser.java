package pe.edu.utp.Formulario.PrincipalUser;

import com.microsoft.sqlserver.jdbc.StringUtils;
import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.Boleta;
import pe.edu.utp.Formulario.iniciarSesion.FormLogin;
import pe.edu.utp.Formulario.iniciarSesion.Login;
import pe.edu.utp.Formulario.cargadoresBD;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class FormPrincipalUser extends JFrame {

    // Conexion a base de datos
    public static Connection con;
    public static Conect_BD conexion;

    private final JTextField textCantidad;

    private int initialX, initialY;

    public static JTable dataTableU;
    public FormPrincipalUser() {

        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Principal Usuario");//nombre
        setSize(900, 700);//dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));//icono de programa

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);//creando el JPanel
        formPanel.setBackground(new Color(35, 35, 35));//Agregando un color de fondo

        // Permitir redimensionar la ventana principal

        // -----------------MODIFICAMOS LOS COMPONENTES----------------------
        // JLabel para cerrar ventana
        JLabel x = new JLabel("X");
        x.setForeground(new Color(255, 255, 255));
        x.setFont(new Font("Arial", Font.BOLD, 14));
        x.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0); // Cierra la aplicación cuando se hace clic en "X"
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                x.setForeground(new Color(128, 128, 128));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                x.setForeground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // JLabel Logout
        JLabel logout = new JLabel(" Logout");
        logout.setForeground(new Color(255, 255, 255));
        logout.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        ImageIcon imagenLogout = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\salida.png");
        logout.setIcon(imagenLogout);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormLogin mainWindow = new FormLogin();
                mainWindow.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setForeground(new Color(128, 128, 128));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setForeground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // Borde movible de la ventana
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(30, 30)); // Las dimensiones del JPanel
        header.setBackground(new Color(35, 35, 35));
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
        // JLabel Imagen LOGO
        JLabel imagen = new JLabel();
        ImageIcon imagenLogo = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_utp.png");
        imagen.setIcon(imagenLogo);
        // JText TITULO 1
        JLabel lblTienda1 = new JLabel("Tienda de componentes");
        lblTienda1.setForeground(new Color(255, 255, 255));
        lblTienda1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // JText TITULO 2
        JLabel lblTienda2 = new JLabel("'TechGear'");
        lblTienda2.setForeground(new Color(255, 255, 255));
        lblTienda2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // JLabel Bienvenido
        JLabel lblUser = new JLabel("Bienvenido Usuario" + " " + Login.getNombre());
        lblUser.setForeground(new Color(255, 255, 255));
        lblUser.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // JLabel cantidad
        JLabel lblCantidad = new JLabel("Cant. a vender:");
        lblCantidad.setForeground(new Color(255, 255, 255));
        lblCantidad.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // CONDICIONES DE BORDEADO
        Border lineBorder = new LineBorder(new Color(255, 255, 255), 1); // Cambia el color y el grosor del borde
        Border topTransparentBorder = BorderFactory.createEmptyBorder(-1, -1, 5, -1); // Ajusta el espacio superior, Borde personalizado para hacer transparente la parte superior
        Border compoundBorder = BorderFactory.createCompoundBorder(topTransparentBorder, lineBorder);// Combina los bordes para crear el efecto deseado
        // Jtext de cantidad
        textCantidad = new JTextField();
        textCantidad.setOpaque(false);
        textCantidad.setBorder(compoundBorder);
        textCantidad.setFont(new Font("Arial", Font.PLAIN, 13));
        textCantidad.setForeground(new Color(204, 204, 204));

        //JTable agregamos tablas
        dataTableU = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(dataTableU);

        // BTN comprar
        // Boton blanco y plomo
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204,204,204), 1, false); // El tercer parámetro es para redondear

        JButton comprarButton = new JButton("Comprar");
        comprarButton.setBorder(white);
        comprarButton.setBackground(new Color(255, 255, 255));
        comprarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        comprarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comprarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                comprarButton.setBorder(gray);
                comprarButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                comprarButton.setBorder(white);
                comprarButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        x.setBounds(15, 10, 17, 17);
        logout.setBounds(810, 10, 100, 23);
        imagenIcono.setBounds(400,7,70,70);
        imagen.setBounds(380, 570, 200, 100);
        lblTienda1.setBounds(335, 80, 200, 30);
        lblTienda2.setBounds(385, 110, 100, 30);
        lblUser.setBounds(70, 10, 230, 17);
        lblCantidad.setBounds(40, 500, 120, 17);
        textCantidad.setBounds(170, 499, 50, 30);
        comprarButton.setBounds(385, 500, 100, 30);
        tableScrollPane.setBounds(140, 200, 600, 280);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(imagen);
        formPanel.add(lblTienda1);
        formPanel.add(lblTienda2);
        formPanel.add(lblUser);
        formPanel.add(lblCantidad);
        formPanel.add(textCantidad);
        formPanel.add(comprarButton);

        add(x, BorderLayout.NORTH);
        add(logout, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.CENTER);

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = dataTableU.getSelectedRow();
                String cantidadstr = textCantidad.getText();

                if (cantidadstr.isEmpty()) {
                    JOptionPane.showMessageDialog(FormPrincipalUser.this, "Ingrese la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                if (!StringUtils.isNumeric(cantidadstr)) {
                    JOptionPane.showMessageDialog(FormPrincipalUser.this, "La cantidad debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si es que faltaran campos
                }

                if (filaSeleccionada != -1) {

                    // Obtiene el modelo de tabla
                    DefaultTableModel modelU = (DefaultTableModel) dataTableU.getModel();

                    String codigostr = modelU.getValueAt(filaSeleccionada, 0).toString();
                    String proveedor = modelU.getValueAt(filaSeleccionada, 1).toString();
                    String nombre = modelU.getValueAt(filaSeleccionada, 2).toString();
                    String precioStr = dataTableU.getValueAt(filaSeleccionada, 3).toString();

                    // Convertir las cadenas a números
                    int codigo = Integer.parseInt(codigostr);
                    int cantidad = Integer.parseInt(cantidadstr);
                    double precio = Double.parseDouble(precioStr);

                    Compra.setPrecio(precio);

                    //Metodo para general boleta y agregar el pedido a la BD
                    try {
                        Compra.isAgregarPedido(Login.getCodigoLogin(), Login.getNombre(), Login.getApellidoPat(), Login.getApellidoMat(), codigo, proveedor,
                                nombre,cantidad, precio);
                        Boleta.productCompra(cantidad, codigo, nombre, proveedor);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    // Lo coloca en blanco
                    textCantidad.setText("");

                    cargadoresBD.cargarDatosDesdeBaseDeDatosUser();


                } else {
                    // Muestra un mensaje si no se selecciona una fila
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar");
                }

                cargadoresBD.cargarDatosDesdeBaseDeDatosUser();

            }
        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosUser();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormPrincipalUser mainWindow = new FormPrincipalUser();
                mainWindow.setVisible(true);
            }
        });
    }

}
