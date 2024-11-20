//Liberias y paquete
package pe.edu.utp.Formulario.iniciarSesion;

import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.PrincipalAdmin.FormPrincipalAdmin;
import pe.edu.utp.Formulario.PrincipalUser.FormPrincipalUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class FormLogin extends JFrame {

    // Conexion a base de datos
    public static Connection con;
    public static Conect_BD conexion;

    // JTextField de user y password
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    // Para el header, es decir, se utiliza para mover el recuadro
    private int xMouse, yMouse;

    public FormLogin() {

        // Crear una instancia de Conect_BD
        conexion = new Conect_BD();
        con = conexion.getConnection();

        // Configuramos el JFrame
        setTitle("Inicio de Sesión");//nombre
        setSize(800, 500);//dimension de interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));//icono de programa

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);//creando el JPanel
        formPanel.setBackground(new Color(27, 27, 27));//Agregando un color de fondo

        // -----------------MODIFICAMOS LOS COMPONENTES-----------------
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
        // Borde movible de la ventana
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(30, 30)); // Las dimensiones del JPanel
        header.setBackground(new Color(27, 27, 27));
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xMouse = e.getXOnScreen() - getX();
                yMouse = e.getYOnScreen() - getY();
            }
        });

        //  permite responder a eventos de arrastre del mouse, lo que significa que se ejecutará cuando el usuario
        //  arrastre el cursor del mouse mientras el botón del mouse esté presionado
        header.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - xMouse;
                int newY = e.getYOnScreen() - yMouse;

                setLocation(newX, newY);
            }
        });
        // JLabel imgen icon_computer
        JLabel imagenIcono = new JLabel();
        ImageIcon imagenIcon = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png");
        imagenIcono.setIcon(imagenIcon);
        // JLabel Imagen LOGO
        JLabel imagenLogo = new JLabel();
        ImageIcon imagenIconoLogo = new ImageIcon("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_utp.png");
        imagenLogo.setIcon(imagenIconoLogo);
        // JText INICIAR SESION, codigo, contraseña
        JLabel lblIniciarSesion = new JLabel("INICIAR SESIÓN");
        lblIniciarSesion.setForeground(new Color(255, 255, 255));
        lblIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 24));

        JLabel lblCodigo = new JLabel("CODIGO:");
        lblCodigo.setForeground(new Color(255, 255, 255));
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblPassword = new JLabel("CONTRASEÑA:");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        // CONDICIONES DE BORDEADO
        // Cambia el color y el grosor del borde
        Border lineBorder = new LineBorder(new Color(255, 255, 255), 1);
        // Ajusta el espacio superior, Borde personalizado para hacer transparente la parte superior
        Border topTransparentBorder = BorderFactory.createEmptyBorder(-1, -1, 4, -1);
        // Combina los bordes para crear el efecto deseado
        Border compoundBorder = BorderFactory.createCompoundBorder(topTransparentBorder, lineBorder);

        // JTextLabel codigo, contraseña
        usernameField = new JTextField();
        usernameField.setOpaque(false);
        usernameField.setBorder(compoundBorder);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 13));
        usernameField.setForeground(new Color(204, 204, 204));

        passwordField = new JPasswordField();
        passwordField.setOpaque(false);
        passwordField.setBorder(compoundBorder);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordField.setForeground(new Color(204, 204, 204));
        // BTN INGRESAR
        JButton loginButton = new JButton("INGRESAR");
        // El tercer parámetro es para redondear
        Border white = new LineBorder(new Color(255,255,255), 1, false);
        // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204,204,204), 1, false);
        loginButton.setBorder(white);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBorder(gray);
                loginButton.setBackground(new Color(204,204,204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBorder(white);
                loginButton.setBackground(new Color(255,255,255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        x.setBounds(15, 10, 17, 17);
        imagenIcono.setBounds(450, 80, 300, 300);
        imagenLogo.setBounds(130, 5, 200, 100);
        lblIniciarSesion.setBounds(100, 100, 200, 30);
        lblCodigo.setBounds(40, 180, 203, 17);
        usernameField.setBounds(40, 210, 310, 30);
        lblPassword.setBounds(40, 270, 130, 17);
        passwordField.setBounds(40, 300, 310, 30);
        loginButton.setBounds(140, 360, 120, 30);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(imagenLogo);
        formPanel.add(lblIniciarSesion);
        formPanel.add(lblCodigo);
        formPanel.add(usernameField);
        formPanel.add(lblPassword);
        formPanel.add(passwordField);
        formPanel.add(loginButton);

        add(x, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(formPanel, BorderLayout.CENTER);

        // Agregar acción al botón de inicio de sesión3
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = usernameField.getText();
                String password = passwordField.getText();

                String codemini = code.toLowerCase();
                String passwordmini = password.toLowerCase();

                Login.setCodigoLogin(codemini);
                Login.setPassword(passwordmini);

                if (code.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(FormLogin.this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        // Llama a obtenerTipoDeUsuario para verificar las credenciales
                        if ("ADMIN".equals(Login.obtenerTipoDeUsuario(Login.getCodigoLogin(), Login.getPassword()))) {
                            JOptionPane.showMessageDialog(FormLogin.this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            dispose();
                            FormPrincipalAdmin mainWindow = new FormPrincipalAdmin();
                            mainWindow.setVisible(true);
                        } else if ("USER".equals(Login.obtenerTipoDeUsuario(Login.getCodigoLogin(), Login.getPassword()))) {
                            JOptionPane.showMessageDialog(FormLogin.this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            dispose();
                            FormPrincipalUser mainWindow = new FormPrincipalUser();
                            mainWindow.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(FormLogin.this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                            usernameField.setText("");
                            passwordField.setText("");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormLogin loginForm = new FormLogin();
                loginForm.setVisible(true);
            }
        });
    }
}

