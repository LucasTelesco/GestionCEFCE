package herramientas;

import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

public class VistaBecario extends Vista {

    private ArrayList<Object> dni_mates = null; // para mates y termos prestados!!
    private ArrayList<Object> productos = null;

    private TextAutoCompleter ac;
    private TextAutoCompleter acProd;

    static JTextField[][] datventa;
    static JTextField numeroventa;
    static JTextField totalVenta;
    private actualizaPrecio actPrecio;

    // para termos
    private JTextField dni;
    private JTextField dniDevolver;
    private JTextField num_mate;
    private JTextField num_termo;
    private JButton prestar;
    private JButton listar_prestados;
    private JButton devolver;
    private JButton ordeCompra;
    private JButton consultarCaja;

    private JButton realizarVenta;
    private JButton cambiarPass;


    private int i =0;//indice para mostrar mjs
    public static String nuevalinea = System.getProperty("line.separator"); //para separacion de string

    public VistaBecario(conector cn, String usuario) {
        super(cn, usuario);
        dni_mates = new ArrayList<Object>();
        dni_mates=cn.litarmates();
        productos = new ArrayList<Object>(cn.listarProductos());

        cerrar();//metodo para cerrar

        JLabel lblTexto = new JLabel("----Venta----");
        lblTexto.setBounds(400, 15, 102, 50);
        add(lblTexto);
        JLabel lblTexto2 = new JLabel("Cant");
        lblTexto2.setBounds(590, 15, 102, 50);
        add(lblTexto2);
        JLabel lblTexto3 = new JLabel("----Termos/Mate----");
        lblTexto3.setBounds(80, 130, 300, 50);
        add(lblTexto3);
        JLabel lblTexto4 = new JLabel("----Devolución----");
        lblTexto4.setBounds(80, 280, 250, 50);
        add(lblTexto4);

        realizarVenta = new JButton("VENTA");
        int y=0;

        datventa = new JTextField[10][2];
        for(int f=0;f<10;f++){
            datventa[f][0] = new JTextField();
            datventa[f][1] = new JTextField();
            datventa[f][0].setBounds(330, 50 + y, 250, 20);
            acProd = new TextAutoCompleter(datventa[f][0]);
            acProd.addItems(productos);

            add(datventa[f][0]);
            datventa[f][1] = new JTextField();
            datventa[f][1].setBounds(600, 50 + y,20,20);
            datventa[f][1].setText("1");
            add(datventa[f][1]);
            y=y+30;

        }
        dni = new JTextField("0");
        dniDevolver = new JTextField("");
        num_mate = new JTextField("0");
        num_termo = new JTextField("0");
        numeroventa = new JTextField("0");
        totalVenta = new JTextField("$ 0");

        numeroventa.setBounds(400, 350, 100, 20); //para numero auxiliar de ventas.
        totalVenta.setBounds(550, 390, 125, 20);
        dni.setBounds(80, 170, 150, 20);
        num_termo.setBounds(80, 190,20,20);
        num_mate.setBounds(80, 210,20,20);

        prestar = new JButton("Prestar");
        prestar.setBounds(90,240,110,20);
        add(prestar);

        cambiarPass = new JButton("Cambiar Pass");
        cambiarPass.setBounds(25,475,130,20);
        add(cambiarPass);

        ordeCompra = new JButton("Lista-Compra");
        ordeCompra.setBounds(25,440,140,30);
        add(ordeCompra);

        consultarCaja = new JButton("Consultar-Caja");
        consultarCaja.setBounds(525,440,140,30);
        add(consultarCaja);

        listar_prestados = new JButton("T/M Prestado");
        listar_prestados.setBounds(70,266,150,20);
        add(listar_prestados);

        //devolucion
        dniDevolver.setBounds(80, 320, 150, 20);
        devolver = new JButton("Devolvel");
        devolver.setBounds(90,350,110,20);
        add(devolver);
        add(numeroventa);

        // botones especiales
        realizarVenta.setBounds(330, 390, 150, 20);

        // texto adicional
        JLabel dni1 = new JLabel("DNI");
        dni1.setBounds(25, 170, 60, 20);
        add(dni1);
        JLabel dni2 = new JLabel("DNI");
        dni2.setBounds(25, 320, 60, 20);
        add(dni2);
        JLabel ter = new JLabel("Termo");
        ter.setBounds(25, 190, 60, 20);
        add(ter);
        JLabel mat = new JLabel("Mate");
        mat.setBounds(25, 210, 60, 20);
        add(mat);
        JLabel tot = new JLabel("- TOTAL -");
        tot.setBounds(580, 375, 200, 20);
        add(tot);
        // texto adicional
        //imagenes
        Icon icm = new ImageIcon(getClass().getResource("icn.png"));
        JLabel imagGallito = new JLabel(icm);
        imagGallito.setBounds(0,0,150,150);
        add(imagGallito);
        //imagenes
        add(dni);
        add(num_mate);
        add(num_termo);
        add(dniDevolver);
        add(realizarVenta);
        add(totalVenta);

        // autocompletado
        ac = new TextAutoCompleter(dniDevolver);
        ac.addItems(dni_mates);

        actPrecio = new actualizaPrecio(productos);
        actPrecio.start();

        //accion de botones
        prestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni_prestar = dni.getText();
                String termo_prestar = num_termo.getText();
                String mate_prestar = num_mate.getText();

                dni_mates.clear();
                dni_mates.add(dni_prestar);
                ac.addItems(dni_mates);
                 cn.prestar_mate(dni_prestar,termo_prestar,mate_prestar);


                JOptionPane.showMessageDialog(null, "DNI: "+dni_prestar+" termo: "+termo_prestar+" mate: "+mate_prestar," Okaaa ",JOptionPane.INFORMATION_MESSAGE);
                dni.setText("");
                num_mate.setText("");
                num_termo.setText("");
            }
        });
        devolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mjs = new String("No hay prestamos hechos a este DNI!!");
                String dni_devolver = dniDevolver.getText();
                Elem_mate m = cn.prestamo(dni_devolver);
                if (m!=null){
                    mjs = "termo :"+m.getTermo()+"mate: "+m.getMate();
                    cn.devolver_mate(dni_devolver);
                    dni_mates.remove(dni_devolver);
                }
                JOptionPane.showMessageDialog(null, mjs," Devolución ",JOptionPane.INFORMATION_MESSAGE);
                dniDevolver.setText("");
            }
        });
        listar_prestados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String mjs = new String();
              ArrayList<Object> dat = cn.litarmates();

              VistaTermos mateTermo = new VistaTermos(cn,usuario,dat);
              mateTermo.setVisible(true);
            }
        });
        realizarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0; // contador
                int ultimocargado = 0;
                double total = 0;

                for (int f=0;f<10;f++){
                    String nombre = datventa[f][0].getText();
                    for(Object o :productos){
                        Producto pr = (Producto) o;
                        int i = Integer.parseInt(datventa[f][1].getText());

                        if (pr.getNombre().equals(nombre)){
                            if (count==0){
                                count++;
                                cn.cargarVenta();
                                ultimocargado = cn.ultimo_codigo_cargado();
                                cn.cargarDatoVenta(pr.getNombre(),ultimocargado,i);
                            }
                            else {
                                cn.cargarDatoVenta(pr.getNombre(),ultimocargado,i);
                            }
                            if (pr.getCant_promo()!=i) {
                                //total += pr.getPrecio() * i;
                                cn.descuentaCantidad(pr, i);
                                pr.setCantdis(i); // actualiza mi lista de cantidad
                            }
                            else{
                                //total += pr.getPrecio_promo();
                                cn.descuentaCantidad(pr, i);
                                pr.setCantdis(i);
                            }
                        }
                    }
                }

                total= Double.valueOf(totalVenta.getText());
                cn.sumarTotal(usuario,total);
                cn.modificaPrecio(total,ultimocargado);
                // Seteo los campos para proxima venta
                for(int i=0;i<10;i++){
                    datventa[i][0].setText("");
                    datventa[i][1].setText("1");
                }
                numeroventa.setText("0");
                datventa[0][0].requestFocusInWindow();
            }

        });
        consultarCaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mjs = " $ "+cn.verCaja(usuario);
                JOptionPane.showMessageDialog(null, mjs,"Total Caja : "+usuario,JOptionPane.INFORMATION_MESSAGE);
            }
        });
        ordeCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Producto> aux = new ArrayList<Producto>();
                for (Object o : productos){
                    aux.add( (Producto) o);
                }
                Collections.sort(aux);

                VistaPedido p = new VistaPedido(aux);
                p.setVisible(true);

            }
        });
        cambiarPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cambiarPass cm = new cambiarPass(cn,usuario);
            cm.setVisible(true);

            }
        });

    }

    public void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();
                }
            });
            this.setVisible(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void confirmarSalida(){
        int valor = JOptionPane.showConfirmDialog(this,"seguro qe desea salr??","cerrando sesion",JOptionPane.YES_NO_CANCEL_OPTION);
        if (valor == JOptionPane.YES_OPTION){
            cn.cargaCaja(usuario);

            System.exit(0);
        }
    }


}
