package herramientas;

import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaTermos extends JDialog implements ActionListener {


    private JPanel panelMain = new JPanel();
    private JTextField[] datMate;


    public VistaTermos(conector cn, String usuario, ArrayList<Object> list) {
        setTitle("Termos/Mates prestados :"+list.size());
        setBounds(400, 200, 350, 410);
        datMate = new JTextField[list.size()];
        int y = 0;
        for (int i = 0;i<list.size();i++){

            datMate[i] = new JTextField();
            datMate[i].setBounds(25, 25 + y, 250, 20);
            Elem_mate m = (Elem_mate) list.get(i);
            datMate[i].setText("DNI: "+m.getDni()+" Mate: "+m.getMate()+" Termo: "+m.getTermo());
            add(datMate[i]);

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
