package GUI;
import javax.swing.*;
import java.awt.*;

import Inventory.ProductManager;
import com.formdev.flatlaf.FlatLightLaf; // si usas FlatLaf

public class MainGUI extends JFrame {

    // 🔹 Atributos globales
    private JPanel mainPanel;
    private CardLayout layout;

    // 🔹 Constructor
    public MainGUI() {
        // 1️⃣ Look and Feel (tema visual)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2️⃣ Configuración básica de la ventana
        setTitle("Sistema de Inventario 📦");   // ← nombre de la ventana
        setSize(800, 600);                     // ← tamaño en píxeles
        setLocationRelativeTo(null);            // ← centrada en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ← cerrar programa al salir
        setLayout(new BorderLayout());          // ← distribuye contenido por regiones

        // 3️⃣ Crear el panel principal
        layout = new CardLayout();             // permite cambiar “pantallas”
        mainPanel = new JPanel(new GridBagLayout());
        // color de fondo base

        // Opcional: redondear bordes con FlatLaf
        mainPanel.putClientProperty("JPanel.style", "arc: 20; border: 4, #6478E1;");

        // Agregar las “pantallas” internas
        mainPanel.setBackground(new Color(149, 149, 246));;
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(156, 0, 255)));

        UIManager.put("Panel.arc", 100);

        //Cuadrado Central del de la ventana
        JPanel cuadrado = new JPanel();
        cuadrado.setPreferredSize(new Dimension(400, 400));
        cuadrado.setBorder(BorderFactory.createMatteBorder(0,0,5,5, new Color(248, 164, 164, 63)));
        cuadrado.putClientProperty("JPanel.style",
                "arc: 50; border: 10, #6478E1; background: #F0F3FF;");

        cuadrado.setLayout(null);
        //Etiqueta que inicia o indica el inicio
        JLabel etiquetaInicio = new JLabel("-------Inventario-------");
        etiquetaInicio.setFont(new Font("Segoe UI", Font.BOLD, 30));
        etiquetaInicio.setBounds(10, 20, 380, 65);
        etiquetaInicio.setBackground(new Color(225,225,225));
        etiquetaInicio.setHorizontalAlignment(SwingConstants.CENTER);

        //Label inicio de secion
        JLabel inicioSesion = new JLabel("Inicio de sesión");
        inicioSesion.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        inicioSesion.setBounds(50, 100, 300, 20);


        //Para que el usuario escriba su nombre de usuario
        JTextField usuario = new JTextField();
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        usuario.setBounds(50, 150, 300, 24);
        usuario.setBackground(null);
        usuario.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(0,0,0)));
        usuario.putClientProperty("JTextField.placeholderText", "Usuario");

        //Para que el usuario escriba la contraseña
        JTextField password = new JTextField();
        password.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        password.setBounds(50, 200, 300, 24);
        password.setBackground(null);
        password.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(0,0,0)));
        password.putClientProperty("JTextField.placeholderText", "Contraseña");

        //Boton de salida
        JButton btnInicioSesion = new JButton("Iniciar sesión");
        btnInicioSesion.setFont(new Font("Segoe UI", Font.PLAIN, 27));
        btnInicioSesion.setBounds(50, 250, 300, 50);
        btnInicioSesion.setBackground(new Color(215, 245, 215));
        btnInicioSesion.setBorder(null);

        //Boton de salida
        JButton buttonSalir = new JButton("Salir");
        buttonSalir.setFont(new Font("Segoe UI", Font.PLAIN, 27));
        buttonSalir.setBounds(50, 320, 300, 50);
        buttonSalir.setBackground(new Color(251, 207, 207));
        buttonSalir.setBorder(null);

        //Accionoes de los botones

        btnInicioSesion.addActionListener( e ->{
            if("Hola".equals(usuario.getText()) && "Chao".equals(password.getText())){
                dispose();
                new InventoryManagerGUI();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Esta mal el usuario o la contraseña",
                        "Error en de inicio",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        });
        buttonSalir.addActionListener(e -> System.exit(0));

        cuadrado.add(etiquetaInicio);
        cuadrado.add(inicioSesion);
        cuadrado.add(usuario);
        cuadrado.add(password);
        cuadrado.add(btnInicioSesion);
        cuadrado.add(buttonSalir);

        // Balidacion de los botones, etiquetas y textos en la pantalla
        mainPanel.add(cuadrado);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        addWindowStateListener(e -> {
            if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                setState(JFrame.NORMAL);
            }
        });

        // 6️⃣ Hacer visible la ventana
        setVisible(true);
    }

    // Método para crear paneles internos (pantallas)
    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(249, 200, 224));
        panel.setBorder(BorderFactory.createLineBorder(new Color(156, 0, 255)));

        JLabel label = new JLabel("Inicio");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    // 🔹 Método principal para ejecutar el programa
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        productManager.reloadProduct();
        SwingUtilities.invokeLater(MainGUI::new);
    }

}

