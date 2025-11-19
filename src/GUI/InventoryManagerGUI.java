/*
class InventoryManagerGUI
Pantalla del inventario principal donde se maneja todo el programa
 */
package GUI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class InventoryManagerGUI extends JFrame{

    private JPanel mainPanel;
    private CardLayout layout;

    public InventoryManagerGUI() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch (UnsupportedLookAndFeelException e){
            System.out.println("Error: " + e.getMessage());
        }

        //Configuracion de la ventana
        setTitle("<html><b>Sanduches los bien Puestos</b>");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        mainPanel = new JPanel(new CardLayout());

        mainPanel.putClientProperty("JPanel.style", "arc: 20; border: 4, #6478E1;");
        mainPanel.setBackground(new Color(255, 255, 255, 255));

        add(panelSaid(), BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    //Panel lateral del programa
    private JPanel panelSaid(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 800));
        panel.setBackground(new Color(79, 79, 244));
        panel.setBorder(BorderFactory.createMatteBorder(2,0,2,4,
                new Color(56, 56, 222, 76)));

        panel.setLayout(null);

        //Texto principal del panel lateral
        JLabel mainText = new JLabel("<html><b>Inventario<br>Sanduches los <br>bien puestos<br><html>");
        mainText.setFont(new Font("Segoe UI", Font.BOLD, 40));
        mainText.setHorizontalAlignment(SwingConstants.LEFT);
        mainText.setBounds(25,10,300,145);

        //Separador tipos de botones para el inventario en la pantalla
        JLabel inventory = new JLabel("<html><u>Inventario</u><html>");
        inventory.setFont(new Font("Segoe UI", Font.BOLD, 25));
        inventory.setForeground(Color.RED);
        inventory.setHorizontalAlignment(SwingConstants.LEFT);
        inventory.setBounds(25,170,300,30);

        //Separador tipos de botones para el producto en la pantalla
        JLabel product = new JLabel("<html><u>Inventario</u><html>");
        product.setFont(new Font("Segoe UI", Font.BOLD, 25));
        product.setForeground(Color.RED);
        product.setHorizontalAlignment(SwingConstants.LEFT);

        //Se ponen los botones en la pantalla
        JButton[] buttons = buttons();
        int y = 200;
        for(JButton btn: buttons){

            btn.addActionListener(e -> {
                String id = e.getActionCommand();
                switch (id){
                    case "EntradaProducts" -> System.out.println("Entrada productos");
                    case "SalidaProducts" -> System.out.println("Salida productos");
                    case "SaldoProducts" -> System.out.println("Saldo Producto");
                    case "VerInventario" -> System.out.println("Ver inventario");
                    case "AgregarProduct" -> System.out.println("Agregar Producto");
                    case "EliminarProduct" -> System.out.println("Eliminar Producto");
                    case "Salir" -> System.exit(0);
                }
            });

            //Agrega las separacion para el producto en la pantalla
            if(btn.getActionCommand().equals("AgregarProduct")) {
                product.setBounds(25, y, 300, 30);
                y += 50;
                panel.add(product);
            }
            //Hace que salir este al final
            else if(btn.getActionCommand().equals("Salir")){
                y = panel.getPreferredSize().height - 90;

            }

            //Ajustes de los botones
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setHorizontalAlignment(SwingConstants.RIGHT);
            btn.setBounds(25,y,320,45);
            btn.setBorder(null);
            btn.setBackground(new Color(79, 79, 244));
            panel.add(btn);

            y+=50;
        }
        panel.add(mainText);
        panel.add(inventory);

        return panel;
    }

    //Botones del panel lateral
    private JButton[] buttons(){
        JButton btnEntradaProducts = new JButton("Entrada productos");
        JButton btnSalidaProducts = new JButton("Salida productos");
        JButton btnSaldoProducts = new JButton("Saldo productos");
        JButton btnVerInventario = new JButton("Ver inventario");
        JButton btnAgregarProduct = new JButton("Agregar producto");
        JButton btnEditarProduct = new JButton("Editar producto");
        JButton btnEliminarProduct = new JButton("Eliminar producto");
        JButton btnSalir = new JButton("Salir");

        btnEntradaProducts.setActionCommand("EntradaProducts");
        btnSalidaProducts.setActionCommand("SalidaProducts");
        btnSaldoProducts.setActionCommand("SaldoProducts");
        btnVerInventario.setActionCommand("VerInventario");
        btnAgregarProduct.setActionCommand("AgregarProduct");
        btnEditarProduct.setActionCommand("EditarProduct");
        btnEliminarProduct.setActionCommand("EliminarProduct");
        btnSalir.setActionCommand("Salir");

        return new JButton[]{
                btnEntradaProducts,
                btnSalidaProducts,
                btnSaldoProducts,
                btnVerInventario,
                btnAgregarProduct,
                btnEditarProduct,
                btnEliminarProduct,
                btnSalir
        };
    }
}
