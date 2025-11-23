package GUI;

import javax.swing.*;
import java.awt.*;

public class SaldoProduct {
    public SaldoProduct(JPanel mainPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(panelEscritura(), BorderLayout.NORTH);
        panel.add(panelProduct(), BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.CENTER);
    }

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

    private JTextField[] textFields(){
        JTextField codigo = new JTextField();
        //JTextField entrada = new JTextField();
        JTextField lugar = new JTextField();

        codigo.putClientProperty("JTextField.placeholderText", " Codigo producto");
        //entrada.putClientProperty("JTextField.placeholderText", " Saldo");
        lugar.putClientProperty("JTextField.placeholderText", " Lugar");

        return new JTextField[]{
                codigo,
                //entrada,
                lugar
        };
    }

    private JLabel[] labels(){
        JLabel codigo = new JLabel("Codigo del producto");
        //JLabel entrada = new JLabel("Saldo del producto");
        JLabel lugar = new JLabel("Lugar a saber el saldo");
        return new JLabel[]{
                codigo,
                //entrada,
                lugar
        };
    }
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

        //escrituraU();

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
