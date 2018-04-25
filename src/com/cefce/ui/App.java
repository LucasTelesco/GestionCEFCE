package com.cefce.ui;
import com.mxrck.autocompleter.TextAutoCompleter;
import herramientas.Vista;
import herramientas.VistaBecario;
import herramientas.VistaRoot;
import herramientas.conector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JDialog implements ActionListener {

    private conector cn;
    private JPanel panelMain = new JPanel();
    public JTextField tfTexto;
    public JPasswordField tfTexto2;
    private TextAutoCompleter ac;

    private JButton boton1;

    public App() {
        cn=new conector();

        setTitle("Login");
        setBounds(400, 250, 525, 200);
        getContentPane().setLayout(new BorderLayout());
        panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panelMain, BorderLayout.CENTER);
        panelMain.setLayout(null);

        tfTexto = new JTextField();
        tfTexto2 = new JPasswordField();
        ac = new TextAutoCompleter(tfTexto);

        ac.addItems(cn.listarBecarios());

        tfTexto.setBounds(154, 38, 196, 20);
        tfTexto2.setBounds(154, 65, 196, 20);

        panelMain.add(tfTexto);
        panelMain.add(tfTexto2);

        boton1 = new JButton("Entrar");
        boton1.setBounds(200, 100, 100, 20);
        panelMain.add(boton1);
        boton1.addActionListener(this);


        JLabel lblTexto = new JLabel("Usuario:");
        lblTexto.setBounds(85, 38, 102, 18);
        panelMain.add(lblTexto);

        JLabel lblTextoAux = new JLabel("Clave:");
        lblTextoAux.setBounds(85, 65, 102, 18);
        panelMain.add(lblTextoAux);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String cad1 = tfTexto.getText(); //carga cad
            String cad2 = tfTexto2.getText();
             //setTitle(cad); //Setea el titulo
            tfTexto.setText("");

              if (cn.verificarUsuario(cad1,cad2)){
                  Vista vistaBecario = null;
                  setVisible(false);

               if (cad1.equals("Root")){
                   vistaBecario = new VistaRoot(cn,cad1);
                   vistaBecario.setExtendedState(JFrame.MAXIMIZED_BOTH);
                  }
               else{
                   vistaBecario = new VistaBecario(cn,cad1);
               }
                //vistaBecario.setExtendedState(JFrame.MAXIMIZED_BOTH);
                vistaBecario.setVisible(true);

              }
              else {
                  tfTexto.setText(""); //setea en vacio
                  tfTexto2.setText("");
                  JOptionPane.showMessageDialog(null, "Usuario o Contraceña no validos");
                  tfTexto.requestFocusInWindow();
                  return;
              }

        }
    }

    public static void main(String[] args) {
        try {
            App dialog = new App();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
