package herramientas;

import javax.swing.*;

public class Vista extends JFrame{
    conector cn;
    String usuario;

    public Vista(conector cn,String usuario){
        this.cn = cn;
        this.usuario = usuario;

        setTitle("Ingreso al Sistema");
        setResizable(true);
        setSize(700,500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
