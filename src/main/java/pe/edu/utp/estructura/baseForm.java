package pe.edu.utp.estructura;

import javax.swing.*;

public class baseForm extends  JFrame {

    // ESTA ES UNA ESTRUCTURA PARA INTERFAZ, EN EL CASO DE QUE CREE OTRA INTERFAZ
    // SOLO COPIARIA ESTA ESTRUCTURA Y RELLENARIA
    /*
    private int initialX, initialY;
    public () {
        setTitle("");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));


        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(new Color(35, 35, 35));

        // Permitir redimensionar la ventana principal

        // -----------------MODIFICAMOS LOS COMPONENTES----------------------
        // JLabel para cerrar ventana
        JLabel x = new JLabel("X");
        x.setForeground(new Color(255, 255, 255));
        x.setFont(new Font("Arial", Font.BOLD, 14));

        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0); // Cierra la aplicación cuando se hace clic en "X"
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                x.setForeground(new Color(255, 51, 51));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                x.setForeground(new Color(255,255,255));// Restaura el color de primer plano al salir
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
        ImageIcon imagenIcon = new ImageIcon("C:\\Users\\Juanpi\\IdeaProjects\\PROGRAMACIÓN ORIENTADO A OBJETOS\\PROYECTO FINAL\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_pequeño.png");
        imagenIcono.setIcon(imagenIcon);
        // JLabel Imagen LOGO
        JLabel imagen = new JLabel();
        ImageIcon imagenLogo = new ImageIcon("C:\\Users\\Juanpi\\IdeaProjects\\PROGRAMACIÓN ORIENTADO A OBJETOS\\PROYECTO FINAL\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_utp.png");
        imagen.setIcon(imagenLogo);
        // JText TITULO 1
        JLabel  = new JLabel("");
        .setForeground(new Color(255, 255, 255));
        .setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // JText TITULO 2
        JLabel  = new JLabel("");
        .setForeground(new Color(255, 255, 255));
        .setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        // JLabel MENU
        JLabel  = new JLabel("");
        .setForeground(new Color(255, 255, 255));
        .setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        // BTN REGISTRO
        JButton  = new JButton("");
        .setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        // BTN ENCRYTION
        JButton  = new JButton("");
        .setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        x.setBounds(15, 10, 17, 17);
        imagenIcono.setBounds(160,7,70,70);
        imagen.setBounds(120, 470, 200, 100);
        .setBounds(100, 80, 200, 30);
        .setBounds(145, 110, 100, 30);
        .setBounds(40, 150, 203, 17);
        .setBounds(85, 180, 210, 30);
        .setBounds(  85, 230, 210, 30);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(imagen);
        formPanel.add();
        formPanel.add();
        formPanel.add();
        formPanel.add();
        formPanel.add();

        add(x, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(formPanel, BorderLayout.CENTER);

        .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // Cierra la ventana actual (FromPrincipal)
                 mainWindow = new ;
                mainWindow.setVisible(true);

            }
        });
    }

    // Agrega métodos y componentes adicionales según las necesidades de tu aplicación

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 mainWindow = new ;
                mainWindow.setVisible(true);
            }
        });
    }
*/
}