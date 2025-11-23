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
        setSize(1250, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        layout = new CardLayout();
        mainPanel = new JPanel(layout);
        mainPanel.putClientProperty("JPanel.style", "arc: 20; border: 4, #6478E1;");
        mainPanel.setBackground(new Color(255, 255, 255, 255));

        JFrame.setDefaultLookAndFeelDecorated(true);
        FlatLightLaf.setup();

        setJMenuBar(menuBar());
        add(panelSaid(), BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private JMenuBar menuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu[] menu = menus();
        menuBar.add(Box.createHorizontalGlue());
        for(JMenu m: menu){
            menuBar.add(m);
        }

        return menuBar;

    }
    private JMenu[] menus(){
        JMenu producto = new JMenu("Producto");
        JMenu guardar = new JMenu("Guardar");
        JMenu ajustes = new JMenu("Ajustes");

        JMenuItem addProduct = new JMenuItem("Agregar");
        JMenuItem removeProduct = new JMenuItem("Eliminar");
        JMenuItem editProduct = new JMenuItem("Editar");

        producto.add(addProduct);
        producto.add(removeProduct);
        producto.add(editProduct);

        return new JMenu[]{ producto,
                guardar,
                ajustes
        };
    }
    //Panel lateral del programa
    private JPanel panelSaid(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 800));
        panel.setBackground(new Color(17, 17, 236));
        panel.setBorder(BorderFactory.createMatteBorder(2,0,2,4,
                new Color(56, 56, 222, 76)));

        panel.setLayout(null);

        //Texto principal del panel lateral
        JLabel mainText = new JLabel("Inventario");
        mainText.setFont(new Font("Segoe UI", Font.BOLD, 40));
        mainText.setForeground(Color.WHITE);
        mainText.setHorizontalAlignment(SwingConstants.LEFT);
        mainText.setBounds(25,10,300,145);

        //Separador tipos de botones para el inventario en la pantalla
        JLabel inventory = new JLabel("<html><u>Inventario</u><html>");
        inventory.setFont(new Font("Segoe UI", Font.BOLD, 25));
        inventory.setForeground(Color.ORANGE);
        inventory.setHorizontalAlignment(SwingConstants.LEFT);
        inventory.setBounds(25,155,300,30);

        panel.add(mainText);
        panel.add(inventory);

        //Se ponen los botones en la pantalla
        JButton[] buttons = buttons();
        int y = 200;
        for(JButton btn: buttons){

            btn.addActionListener(e -> {
                String id = e.getActionCommand();
                switch (id){
                    case "EntradaProducts" -> {
                        mainPanel.removeAll();
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        new EntradaProduct(mainPanel);
                    }
                    case "SalidaProducts" -> {
                        mainPanel.removeAll();
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        new SalidaProduct(mainPanel);
                    }
                    case "SaldoProducts" -> {
                        mainPanel.removeAll();
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        new SaldoProduct(mainPanel);
                    }
                    case "VerInventario" -> System.out.println("Ver inventario");
                    case "Salir" -> System.exit(0);
                }
            });



            //Ajustes de los botones
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setForeground(Color.WHITE);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBounds(25,y,320,45);
            btn.setBorder(null);
            btn.setBackground(new Color(17, 17, 236));

            if(btn.getActionCommand().equals("Salir")){
                panel.setLayout(new BorderLayout());
                panel.add(btn, BorderLayout.SOUTH);
                break;
            }

            panel.add(btn);
            y+=50;
        }

        return panel;
    }

    //Botones del panel lateral
    private JButton[] buttons(){
        JButton btnEntradaProducts = new JButton("Entrada productos");
        JButton btnSalidaProducts = new JButton("Salida productos");
        JButton btnSaldoProducts = new JButton("Saldo productos");
        JButton btnVerInventario = new JButton("Ver inventario");
        //JButton btnAgregarProduct = new JButton("Agregar producto");
        //JButton btnEditarProduct = new JButton("Editar producto");
        //JButton btnEliminarProduct = new JButton("Eliminar producto");
        JButton btnSalir = new JButton("Salir");

        btnEntradaProducts.setActionCommand("EntradaProducts");
        btnSalidaProducts.setActionCommand("SalidaProducts");
        btnSaldoProducts.setActionCommand("SaldoProducts");
        btnVerInventario.setActionCommand("VerInventario");
        //btnAgregarProduct.setActionCommand("AgregarProduct");
        //btnEditarProduct.setActionCommand("EditarProduct");
        //btnEliminarProduct.setActionCommand("EliminarProduct");
        //btnSalir.setActionCommand("Salir");

        return new JButton[]{
                btnEntradaProducts,
                btnSalidaProducts,
                btnSaldoProducts,
                btnVerInventario,
                //btnAgregarProduct,
                //btnEditarProduct,
                //btnEliminarProduct,
                btnSalir
        };
    }
}
