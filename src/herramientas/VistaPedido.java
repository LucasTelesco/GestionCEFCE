package herramientas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaPedido extends JDialog implements ActionListener {
    private JPanel panelMain = new JPanel();
    private JTextField[] datPedido;

    public VistaPedido(ArrayList<Producto> list){
        setTitle("Para Pedir");
        setBounds(400, 200, 400, 50+list.size()*30); // ver
        datPedido = new JTextField[list.size()];
        int y = 0;

        for (int i = 0;i<list.size();i++){

            datPedido[i] = new JTextField();
            datPedido[i].setBounds(25, 25 + y, 300, 20);
            int pedir= (list.get(i).getMinimo_alerta()*3) -list.get(i).getCantdis();
            datPedido[i].setText(list.get(i).getNombre()+" Se recomienda pedir :"+pedir);
            add(datPedido[i]);

            y=y+30;
        }


        panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panelMain, BorderLayout.CENTER);
        panelMain.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
