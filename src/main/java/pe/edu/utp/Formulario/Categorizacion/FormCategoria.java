package pe.edu.utp.Formulario.Categorizacion;

import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.PrincipalAdmin.FormPrincipalAdmin;
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

public class FormCategoria extends JFrame {

    // Conexion a la base de datos
    public static Connection con;
    private Conect_BD conexion;

    //Componenetes locales
    public static JTextField textCategoria;
    private int initialX, initialY;
    public static JTable dataTableC;
    public FormCategoria() {

        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Categorización");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cuando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
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

        JLabel lblTitulo3 = new JLabel("Categorización");
        lblTitulo3.setForeground(new Color(255, 255, 255));
        lblTitulo3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        //JComboBox para las categorías
        String[] categorias = Categoria.obtenerCategoriasDesdeBaseDeDatos();
        String[] categoriasConSeleccionar = new String[categorias.length + 1];
        // Se agrega "Seleccionar" como el primer elemento del nuevo arreglo
        categoriasConSeleccionar[0] = "Seleccionar";
        // Colocamos en el nuevo arreglo
        System.arraycopy(categorias, 0, categoriasConSeleccionar, 1, categorias.length);
        JComboBox<String> comboBoxCategorias = new JComboBox<>(categoriasConSeleccionar);
        comboBoxCategorias.setBounds(230, 149, 180, 30);

        //JTable agregamos tablas
        // Crear una tabla con datos de ejemplo
        String[] columnNamesC = {"Proovedor", "Categoria", "Producto", "Precio"};
        Object[][] dataC = {};

        DefaultTableModel modelC = new DefaultTableModel(dataC, columnNamesC){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };
        dataTableC = new JTable(modelC);
        JScrollPane tableScrollPane = new JScrollPane(dataTableC);
        //Borde
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204, 204, 204), 1, false); // El tercer parámetro es para redondear
        //BTN Reiniciar
        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.setBorder(white);
        reiniciarButton.setBackground(new Color(255, 255, 255));
        reiniciarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        reiniciarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reiniciarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reiniciarButton.setBorder(gray);
                reiniciarButton.setBackground(new Color(204, 204, 204));// cambia el color de primer plano al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reiniciarButton.setBorder(white);
                reiniciarButton.setBackground(new Color(255, 255, 255));// Restaura el color de primer plano al salir
            }
        });

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        atras.setBounds(15, 15, 35, 35);
        imagenIcono.setBounds(590, 45, 70, 70);
        lblTitulo1.setBounds(245, 15, 250, 30);
        lblTitulo2.setBounds(300, 45, 150, 30);
        lblTitulo3.setBounds(295, 85, 180, 30);
        reiniciarButton.setBounds(550, 150, 80, 25);
        tableScrollPane.setBounds(50, 230, 600, 330);
        comboBoxCategorias.setBounds(260, 149, 180, 30);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(lblTitulo1);
        formPanel.add(lblTitulo2);
        formPanel.add(lblTitulo3);
        formPanel.add(reiniciarButton);
        formPanel.add(comboBoxCategorias);

        add(atras, BorderLayout.NORTH);
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER); // Tabla de categorización
        add(formPanel, BorderLayout.CENTER);

        // Agrega un escuchador de eventos al JComboBox
        comboBoxCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategoria = comboBoxCategorias.getSelectedItem().toString();

                if (!selectedCategoria.isEmpty()) {
                    // Filtrar la tabla según la categoría seleccionada
                    Categoria.setCategoria(selectedCategoria);
                    Categoria.datosCategorias(Categoria.getCategoria());
                }
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comboBoxCategorias.setSelectedItem("Seleccionar");
                //Metodo para reiniciar la tabla
                cargadoresBD.cargarDatosDesdeBaseDeDatosCategorias();

            }

        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosCategorias();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormCategoria mainWindow = new FormCategoria();
                mainWindow.setVisible(true);
            }
        });
    }


}
