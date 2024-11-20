package pe.edu.utp.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class SwingUTP {

    enum LayoutType { NoLayoutSelected, FreeDraw, GridBag, Border  };

    static ArrayList<Component> controles = new ArrayList<Component>();
    static HashMap<Component, Object> lista = new HashMap<>();
    static JFrame frame = new JFrame();
    static LayoutType tipo = LayoutType.NoLayoutSelected;
    static JPanel panelMain = new JPanel(new GridBagLayout());
    static GridBagConstraints d = new GridBagConstraints();

    public static void addControl(Component control, Object constrain){
        lista.put(control, constrain);
        tipo = LayoutType.Border;
    }

    public static void addControl(int row, int col, Component control){
        d.gridy=row;d.gridx=col;panelMain.add(control, d);
        tipo = LayoutType.GridBag;
    }

    public static void addControl(int x, int y, int width, int height, Component control){
        control.setBounds(x,y,width,height);
        frame.add(control);
        tipo = LayoutType.FreeDraw;
    }

    public static void addClickEvent(JButton control, Consumer action){
        control.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.accept(e);
            }
        });
    }

    public static void addKeyTypedEvent(JTextField control, Consumer action){
        control.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                action.accept(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void addKeyPressedEvent(JTextField control, Consumer action){
        control.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                action.accept(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void addReleasedEvent(JTextField control, Consumer action){
        control.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                action.accept(e);
            }
        });
    }

    public static void makeWindow(String title, int width, int height, boolean packed){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                frame.setTitle(title);
                frame.setSize(width, height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                switch (tipo) {
                    case FreeDraw -> {
                        frame.setLayout(null);
                    }
                    case Border -> {
                        frame.setLayout(new BorderLayout());
                        for (HashMap.Entry<Component, Object> item: lista.entrySet()) {
                            frame.add(item.getKey(), item.getValue());
                        }
                    }
                    case GridBag -> {
                        d.insets = new Insets(10, 0, 0, 0);
                        d.anchor = GridBagConstraints.LINE_START;
                        frame.add(panelMain);
                    }
                    default -> {
                        frame.setLayout(null);
                        //System.out.println("No olvide agregar controles al formulario");
                    }
                }

                if (packed) frame.pack();
                frame.setVisible(true);
            }
        });

    }


    public static void runWindow(String title, int width, int height){
        makeWindow(title, width, height, false);
    }

    public static void runWindow(String title, int width, int height, boolean packed){
        makeWindow(title, width, height, packed);
    }

}
