package pe.edu.utp.Formulario.PrincipalAdmin;

import pe.edu.utp.Formulario.Categorizacion.FormCategoria;
import pe.edu.utp.Formulario.iniciarSesion.FormLogin;
import pe.edu.utp.Formulario.Proveedores.FormProveedores;
import pe.edu.utp.Formulario.Registrar.FormRegistro;
import pe.edu.utp.Formulario.iniciarSesion.Login;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormPrincipalAdmin extends  JFrame {
    private int initialX, initialY;
    public FormPrincipalAdmin() {

        setTitle("Principal");//nombre
        setSize(400, 520);//dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));//icono de programa

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);//creando el JPanel
        formPanel.setBackground(new Color(35, 35, 35));//Agregando un color de fondo

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
        // JLabel MENU
        JLabel lblMenu = new JLabel("Menú:");
        lblMenu.setForeground(new Color(255, 255, 255));
        lblMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // JLabel Bienvenido
        JLabel lblUser = new JLabel("Bienvenido " + "Administrador" + " " + Login.getNombre());
        lblUser.setForeground(new Color(255, 255, 255));
        lblUser.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // BTN Registro, proveedoress
        // Boton blanco y plomo
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204,204,204), 1, false); // El tercer parámetro es para redondear

        JButton registroButton = new JButton("Registro de Productos");
        registroButton.setBorder(white);
        registroButton.setBackground(new Color(255, 255, 255));
        registroButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        registroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registroButton.setBorder(gray);
                registroButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registroButton.setBorder(white);
                registroButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        JButton proveedoresButton = new JButton("Proveedores");
        proveedoresButton.setBorder(white);
        proveedoresButton.setBackground(new Color(255, 255, 255));
        proveedoresButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        proveedoresButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        proveedoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                proveedoresButton.setBorder(gray);
                proveedoresButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                proveedoresButton.setBorder(white);
                proveedoresButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        JButton categorizacionButton = new JButton("Categorización");
        categorizacionButton.setBorder(white);
        categorizacionButton.setBackground(new Color(255, 255, 255));
        categorizacionButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        categorizacionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categorizacionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                categorizacionButton.setBorder(gray);
                categorizacionButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                categorizacionButton.setBorder(white);
                categorizacionButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        x.setBounds(15, 10, 17, 17);
        logout.setBounds(320, 10, 100, 23);
        imagenIcono.setBounds(175,7,70,70);
        imagen.setBounds(135, 390, 200, 100);
        lblTienda1.setBounds(115, 80, 200, 30);
        lblTienda2.setBounds(160, 110, 100, 30);
        lblMenu.setBounds(55, 150, 203, 17);
        lblUser.setBounds(90, 370, 230, 17);
        registroButton.setBounds(100, 180, 210, 30);
        proveedoresButton.setBounds(100, 230, 210, 30);
        categorizacionButton.setBounds(100, 280, 210, 30);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(imagen);
        formPanel.add(lblTienda1);
        formPanel.add(lblTienda2);
        formPanel.add(lblMenu);
        formPanel.add(lblUser);
        formPanel.add(registroButton);
        formPanel.add(proveedoresButton);
        formPanel.add(categorizacionButton);

        add(x, BorderLayout.NORTH);
        add(logout, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(formPanel, BorderLayout.CENTER);

        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormRegistro mainWindow = new FormRegistro();
                mainWindow.setVisible(true);

            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormProveedores mainWindow = new FormProveedores();
                mainWindow.setVisible(true);

            }
        });

        categorizacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // Cierra la ventana actual (FromPrincipal)
                FormCategoria mainWindow = new FormCategoria();
                mainWindow.setVisible(true);

            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormPrincipalAdmin mainWindow = new FormPrincipalAdmin();
                mainWindow.setVisible(true);
            }
        });
    }

}
