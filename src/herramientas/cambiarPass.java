package herramientas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class cambiarPass extends JDialog implements ActionListener {

    private JPanel panelMain = new JPanel();
    private JPasswordField passActual;
    private JPasswordField passnueva;
    private JPasswordField passnueva2;
    private JButton boton1;

    public cambiarPass(conector cn,String usuario){
        setTitle("Cambiar Clave");
        setBounds(400, 200, 350, 200);

        passActual = new JPasswordField();
        passnueva = new JPasswordField();
        passnueva2 = new JPasswordField();
        boton1 = new JButton("OK");
        passActual.setBounds(35, 40, 250, 20);
        passnueva.setBounds(35, 80, 250, 20);

        passnueva2.setBounds(35, 120, 250, 20);

        boton1.setBounds(50,155,70,30);

        add(passActual);
        add(passnueva);
        add(passnueva2);
        add(boton1);

        JLabel lblTexto = new JLabel("Clave Actual");
        lblTexto.setBounds(40, 20, 102, 20);
        add(lblTexto);
        JLabel lblTexto2 = new JLabel("Clave Nueva");
        lblTexto2.setBounds(40, 60, 102, 20);
        add(lblTexto2);
        JLabel lblTexto3 = new JLabel("Repetir Clave Nueva");
        lblTexto3.setBounds(40, 100, 300, 20);
        add(lblTexto3);

        panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panelMain, BorderLayout.CENTER);
        panelMain.setLayout(null);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actual = passActual.getText();
                String nueva = passnueva.getText();
                String nueva2 = passnueva2.getText();
                String mjs = new String();

                if (nueva.equals(nueva2) && cn.verificarUsuario(usuario,actual)){
                   cn.cambiarPass(usuario,nueva);
                    mjs = "Clave cambiada!!";
                }
                else{mjs = "Algun dato incorrecto!";}
                JOptionPane.showMessageDialog(null, mjs,"Info ",JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });

    }


    public void actionPerformed(ActionEvent e) {

    }
}
