/*
Class EntradaProduct
En esta clase se generara el panel que se creara cuando
el usuario de click a boton Entrada producto
 */
package GUI;

import Inventory.ProductManager;
import Product_and_Employee.Product;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class EntradaProduct extends JFrame {


    public EntradaProduct(JPanel mainPanel) {
       JPanel panel = new JPanel(new BorderLayout());

        panel.add(panelEscritura(), BorderLayout.NORTH);
        panel.add(panelProduct(), BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.CENTER);
    }
    /*
    ================= Parte donde escribe el usuario ====================
    @return panel donde ya se ha implementado todos lo que se ve en el panel
    @see textFields(), labels() que son arreglos para de JTextFailed y JLabel para
    que el usuario pueda escribir su producto
     */
    private JPanel panelEscritura(){
        JPanel panelEscritura = new JPanel(new GridBagLayout());
        panelEscritura.setBorder(null);
        panelEscritura.setBackground(Color.WHITE);

        JTextField[] text = textFields();
        int fila = 0;//Hace que se pongan en fila
        for(JTextField txt : text){

            txt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            txt.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));

            txt.setMaximumSize(new Dimension(100, 30));
            txt.setPreferredSize(new Dimension(150, 30));
            txt.setMaximumSize(new Dimension(500, 50));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = fila;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0;
            gbc.insets = new Insets(100, 25, 5, 50);  // márgenes = centrado
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            panelEscritura.add(txt, gbc);
            fila++;
        }
        JLabel[] labels = labels();
        fila = 0;
        for(JLabel label: labels){
            label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            label.setBorder(null);

            label.setMaximumSize(new Dimension(100, 30));
            label.setPreferredSize(new Dimension(150, 30));
            label.setMaximumSize(new Dimension(500, 50));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = fila;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0;
            gbc.insets = new Insets(70, 25, 5, 50);  // márgenes = centrado
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            panelEscritura.add(label, gbc);
            fila++;
        }
        return panelEscritura;
    }

    // @return JTextField[] retorna un arreglo de JTextField para que el usuario escriba
    private JTextField[] textFields(){
        JTextField codigo = new JTextField();
        JTextField entrada = new JTextField();
        JTextField lugar = new JTextField();

        codigo.putClientProperty("JTextField.placeholderText", " Codigo producto");
        entrada.putClientProperty("JTextField.placeholderText", " Entrada");
        lugar.putClientProperty("JTextField.placeholderText", " Lugar a donde va");

        return new JTextField[]{
                codigo,
                entrada,
                lugar
        };
    }

    // @return JLabel[] para poder poner arriba de cada JTextField y el usuario se pueda guiar
    private JLabel[] labels(){
        JLabel codigo = new JLabel("Codigo del producto");
        JLabel entrada = new JLabel("Entrada del producto");
        JLabel lugar = new JLabel("Lugar a donde va el producto");
        return new JLabel[]{
                codigo,
                entrada,
                lugar
        };
    }

    /*
    ==================== Parte donde se ve los Productos ====================
    @ return JPanel para con el panel interno done se vera los productos
    @ param gbc aqui se usa para que cuando se agrande la pantalla se estire
    y se siga viendo bien el programa
     */
    private JPanel panelProduct(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JPanel panelProduct = new JPanel();
        panelProduct.setBackground(new Color(222, 216, 231));
        panelProduct.setBorder(BorderFactory.createLineBorder(new Color(45, 45, 242)));

        // permite que crezca:
        panelProduct.setMinimumSize(new Dimension(200, 200));
        panelProduct.setPreferredSize(new Dimension(300, 300));
        panelProduct.setMaximumSize(new Dimension(800, 800)); // límite

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(100, 100, 50, 100);  // márgenes = centrado
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        panel.add(panelProduct, gbc);
        return panel;
    }
}
