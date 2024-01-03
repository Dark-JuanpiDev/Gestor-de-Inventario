package pe.edu.utp.Formulario.Pedidos;

import pe.edu.utp.Formulario.Conect_BD;
import pe.edu.utp.Formulario.Registrar.FormRegistro;
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

public class FormPedidos extends JFrame {

    // Conexion a base de datos
    public static Connection con;
    public static Conect_BD conexion;

    // Componentes
    private int initialX, initialY;

    public static JTable dataTableP;
    public FormPedidos () {
        conexion = new Conect_BD(); // Crear una instancia de Conect_BD
        con = conexion.getConnection();

        setTitle("Principal Usuario");//nombre
        setSize(800, 580);//dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//uando el usuario cierra la ventana principal, la aplicación se detendrá y finalizará.
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());//se utiliza comúnmente para organizar componentes en cinco áreas distintas de un contenedor
        setResizable(false); // No permite que se redimensione la ventana
        setUndecorated(true); // Quita la barra de estado para maximizar, minimizar y cerrar
        setIconImage(Toolkit.getDefaultToolkit().getImage("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_tienda.png"));//icono de programa

        // Añadimos algunas cosas al JPanel
        JPanel formPanel = new JPanel(null);//creando el JPanel
        formPanel.setBackground(new Color(27, 27, 27));//Agregando un color de fondo

        // Permitir redimensionar la ventana principal

        // -----------------MODIFICAMOS LOS COMPONENTES----------------------
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
        // JText TITULO 1
        JLabel pedidos = new JLabel("Pedidos");
        pedidos.setForeground(new Color(255, 255, 255));
        pedidos.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        //JTable agregamos tablas
        // Crear una tabla con datos de ejemplo
        String[] columnNamesP = {"Cliente", "Apellidos", "Codigo", "Proveedor", "Nombre de Producto", "Cantidad", "Precio"};
        Object[][] dataP = {

        };

        DefaultTableModel modelP = new DefaultTableModel(dataP, columnNamesP);
        dataTableP = new JTable(modelP);

        JScrollPane tableScrollPane = new JScrollPane(dataTableP);
        // BTN eliminar
        // Boton blanco y plomo
        Border white = new LineBorder(new Color(255, 255, 255), 1, false); // El tercer parámetro es para redondear
        Border gray = new LineBorder(new Color(204,204,204), 1, false); // El tercer parámetro es para redondear

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBorder(white);
        eliminarButton.setBackground(new Color(255, 255, 255));
        eliminarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
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

        // MODIFICAMOS LAS POSICIONES DE LOS COMPONENTES
        atras.setBounds(15, 15, 35, 35);
        imagenIcono.setBounds(380,7,70,70);
        pedidos.setBounds(380, 90, 200, 30);
        eliminarButton.setBounds(360, 470, 100, 30);
        tableScrollPane.setBounds(50, 180, 700, 280);

        // AGREGAMOS LOS COMPONENTES AL formPanel
        formPanel.add(imagenIcono);
        formPanel.add(pedidos);
        formPanel.add(eliminarButton);

        add(atras, BorderLayout.NORTH); // Para regresar a la interfaz registro
        add(header, BorderLayout.NORTH); // Agregamos el JPanel header para que sea movible
        add(tableScrollPane, BorderLayout.CENTER); // tabla
        add(formPanel, BorderLayout.CENTER);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Para seleccionar la fila
                int filaSeleccionada = dataTableP.getSelectedRow();
                if (filaSeleccionada != -1) {

                    DefaultTableModel model = (DefaultTableModel) dataTableP.getModel();
                    // La columna 2 contiene el codigo
                    int codigoAEliminar = (int) model.getValueAt(filaSeleccionada, 3);

                    // Metodo para eliminar la fila a traves del codigo
                    Pedido.setCodigoProducto(codigoAEliminar);

                    // Llama a un método para eliminar la fila en la base de datos
                    Pedido.eliminarFilaEnBaseDeDatosPedido(Pedido.getCodigoProducto());
                    // Elimina la fila de la tabla en la interfaz de usuario
                    model.removeRow(filaSeleccionada);

                } else {

                    // Mostrar un mensaje si no se selecciona una fila
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");

                }
            }
        });

        cargadoresBD.cargarDatosDesdeBaseDeDatosPedidos();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormPedidos mainWindow = new FormPedidos();
                mainWindow.setVisible(true);
            }
        });
    }

}
