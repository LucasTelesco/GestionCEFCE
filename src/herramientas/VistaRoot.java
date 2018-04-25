package herramientas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class VistaRoot extends Vista {
    private JButton agregarProducto;
    private JButton agregarBecario;
    private JButton consultarCaja;
    private JButton masProductos;
    private JButton actualizar;
    private JButton grafico1;
    private JButton grafico2;
    private JButton grafico3;

    private JTextField nombre;
    private JTextField pass;
    private JTextField paraGrafico;

    private JTextField nomProducto;
    private JTextField precio;
    private JTextField canDis;
    private JTextField minimoAlert;
    private JTextField canPromo;
    private JTextField precioPromo;

    private JTextField[] productos;
    private JTextField[] cajas;

    ArrayList<Producto> prdc;
    ArrayList<String> List_caja;

    String ultima_fecha="0";
    int indice = 0;


    public VistaRoot(conector cn, String usuario) {
        super(cn, usuario);

        List_caja = cn.getCaja(ultima_fecha);
        if (List_caja.size()>0)
         ultima_fecha = List_caja.get(List_caja.size()-1);//agrego ultimo valor

        masProductos = new JButton("Más");
        masProductos.setBounds(15,365,110,20);
        add(masProductos);

        actualizar = new JButton("Actualizar");
        actualizar.setBounds(430,365,110,20);
        add(actualizar);

        consultarCaja = new JButton("Más");
        consultarCaja.setBounds(710,365,110,20);
        add(consultarCaja);

        nomProducto = new JTextField();
        nomProducto.setBounds(10, 465, 400, 20);
        add(nomProducto);
        JLabel  aux2 = new JLabel("Nomb. Producto");
        aux2.setBounds(415, 465, 180, 20);
        add(aux2);
        precio = new JTextField();
        precio.setBounds(10, 490, 400, 20);
        add(precio);
        JLabel  aux1 = new JLabel("Precio");
        aux1.setBounds(415, 490, 180, 20);
        add(aux1);
        canDis = new JTextField();
        canDis.setBounds(10, 515, 400, 20);
        add(canDis);
        JLabel  aux3 = new JLabel("Cant. Disponible");
        aux3.setBounds(415, 515, 180, 20);
        add(aux3);
        minimoAlert = new JTextField();
        minimoAlert.setBounds(10, 540, 400, 20);
        add(minimoAlert);
        JLabel  aux4 = new JLabel("Min Alerta");
        aux4.setBounds(415, 540, 180, 20);
        add(aux4);
        canPromo = new JTextField();
        canPromo.setBounds(10, 565, 400, 20);
        add(canPromo);
        JLabel  aux5 = new JLabel("Cant. Promo");
        aux5.setBounds(415, 565, 180, 20);
        add(aux5);
        precioPromo = new JTextField();
        precioPromo.setBounds(10, 590, 400, 20);
        add(precioPromo);
        JLabel  aux6 = new JLabel("Precio prom.");
        aux6.setBounds(415, 590, 180, 20);
        add(aux6);

        agregarProducto = new JButton("Add Prod");
        agregarProducto.setBounds(10, 620, 140, 20);
        add(agregarProducto);

        /**** Botones de Graficos  ****/
        grafico1 = new JButton("Total Ventas");
        grafico1.setBounds(10,650,140,30);
        add(grafico1);

        grafico2 = new JButton("Evolucion Pro");
        grafico2.setBounds(160,650,140,30);
        add(grafico2);

        grafico3 = new JButton("Horarios venta");
        grafico3.setBounds(310,650,140,30);
        add(grafico3);

        JLabel  aux7 = new JLabel("Nombre");
        aux7.setBounds(710, 445, 180, 20);
        add(aux7);
        nombre = new JTextField();
        nombre.setBounds(710, 465, 400, 20);
        add(nombre);
        JLabel  aux8 = new JLabel("Clave");
        aux8.setBounds(710, 492, 180, 20);
        add(aux8);
        pass = new JTextField();
        pass.setBounds(710,510,400,20);
        add(pass);

        paraGrafico = new JTextField();
        paraGrafico.setBounds(160,685,150,20);
        add(paraGrafico);

        agregarBecario = new JButton("Add Becario");
        agregarBecario.setBounds(710,540,140,20);
        add(agregarBecario);

        prdc = cn.listarProductos();

        int max = prdc.size(); // lista de productos

        productos = new JTextField[15];
        cajas = new JTextField[15];

        int sumar =0;
        for (int i =0; i<15; i++){
            String aux = new String();
            if (i<max) {
                aux = prdc.get(i).getNombre() + " Precio $: " + prdc.get(i).getPrecio() + " Cant Disp: " + prdc.get(i).getCantdis() + " Min Alerta: " + prdc.get(i).getMinimo_alerta() + " Cant Promo " + prdc.get(i).getCant_promo() + " Precio Promo: " + prdc.get(i).getPrecio_promo();
                indice++;
            }
            else {
                aux="";
            }
            productos[i] = new JTextField(aux);
            productos[i].setBounds(10, 50 + sumar, 570, 20);
            add(productos[i]);
            sumar += 20;

        }

        sumar=0;
        for (int i =0; i<15; i++){
           String aux = new String();
            if (i<List_caja.size()) {
                 aux = List_caja.get(i);
            } else{
                 aux = "";
            }
            cajas[i] = new JTextField(aux);
            cajas[i].setText(aux);

            cajas[i].setBounds(700,50+sumar,550,20);
            add(cajas[i]);
            sumar+=20;
        }

        JLabel  pro = new JLabel("---PRODUCTOS---");
        pro.setBounds(15, 10, 180, 20);
        add(pro);
        JLabel  caj = new JLabel("---CAJA---");
        caj.setBounds(715, 10, 180, 20);
        add(caj);
        JLabel  addP = new JLabel("-AGREGAR PRODUCTO-");
        addP.setBounds(15, 440, 180, 20);
        add(addP);
        JLabel  addB = new JLabel("-AGREGAR BECARIO-");
        addB.setBounds(715, 420, 180, 20);
        add(addB);


        consultarCaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List_caja = cn.getCaja(ultima_fecha.substring(8,27));

                System.out.print(ultima_fecha.substring(8,27));

                if (List_caja.size()<=1){
                    List_caja = cn.getCaja("0");
                }
                if (List_caja.size()>0)
                    ultima_fecha = List_caja.get(List_caja.size()-1);
                for (int i=0;i<List_caja.size();i++){
                    cajas[i].setText(List_caja.get(i));
                }

            }
        });

        grafico1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Fuente de Datos
                DefaultPieDataset data = new DefaultPieDataset(); // Para datos grafico
                Hashtable<String,Integer> aux=cn.grafico();

                Set<String> iterar = aux.keySet();
                for (String s : iterar){
                    data.setValue(s,aux.get(s));
                }
                // Creando el Grafico
                JFreeChart chart = ChartFactory.createPieChart(
                        "Demanda de Productos",
                        data,
                        true,
                        true,
                        false);
                // Mostrar Grafico
                ChartFrame frame = new ChartFrame("JFreeChart", chart);
                frame.pack();
                frame.setVisible(true);
            }
        });
        grafico3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Fuente de Datos
                DefaultPieDataset data = new DefaultPieDataset(); // Para datos grafico
                Hashtable<String,Integer> aux=cn.graficotres();

                Set<String> iterar = aux.keySet();
                for (String s : iterar){
                    data.setValue(s,aux.get(s));
                }
                // Creando el Grafico
                JFreeChart chart = ChartFactory.createPieChart(
                        "Ventas por hora",
                        data,
                        true,
                        true,
                        false);
                // Mostrar Grafico
                ChartFrame frame = new ChartFrame("JFreeChart", chart);
                frame.pack();
                frame.setVisible(true);
            }
        });

        grafico2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               String productoGrafico = paraGrafico.getText();
               System.out.println(productoGrafico);

                // Fuente de Datos
                DefaultPieDataset data = new DefaultPieDataset(); // Para datos grafico
                Hashtable<String,Integer> aux=cn.graficodos(productoGrafico);

                Set<String> iterar = aux.keySet();
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                for (int i =1;i<=12;i++){
                    String s;

                    switch ( i ){
                        case 1: s="Enero"; break;
                        case 2: s="Febrero"; break;
                        case 3: s="Marzo"; break;
                        case 4: s="Abril"; break;
                        case 5: s="Mayo"; break;
                        case 6: s="Junio"; break;
                        case 7: s="Julio"; break;
                        case 8: s="Agosto"; break;
                        case 9: s="Septiembre"; break;
                        case 10: s="Octubre"; break;
                        case 11: s="Noviembre"; break;
                        case 12: s="Diciembre"; break;
                        default: s="Mes no valido."; break;
                    }
                    String aux2 = new String(String.valueOf(i));
                    Integer valor;
                    valor = aux.get(aux2);
                    if (valor==null)
                        valor=0;

                    dataset.setValue(valor, productoGrafico,s);

                }

                JPanel panel = new JPanel();
                getContentPane().add(panel);
                // Fuente de Datos

                JFreeChart chart = ChartFactory.createBarChart3D
                        ("Evolución Venta del Producto","Producto", "Cantidad venta",
                                dataset, PlotOrientation.VERTICAL, true,true, false);
                chart.setBackgroundPaint(Color.cyan);
                chart.getTitle().setPaint(Color.black);
                CategoryPlot p = chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.red);

                // Mostrar Grafico
                ChartFrame frame = new ChartFrame("JFreeChart", chart);
                frame.pack();
                frame.setVisible(true);
            }
        });

        masProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indice>=prdc.size()){
                    indice = 0;
                }
                for (int i=0;i<15;i++){
                if (indice < prdc.size()){
                    productos[i].setText(prdc.get(i).getNombre() + " Precio $: " + prdc.get(i).getPrecio() + " Cant Disp: " + prdc.get(i).getCantdis() + " Min Alerta: " + prdc.get(i).getMinimo_alerta() + " Cant Promo " + prdc.get(i).getCant_promo() + " Precio Promo: " + prdc.get(i).getPrecio_promo());
                    indice++;
                } else{
                    productos[i].setText("");
                   }

                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prdc = cn.listarProductos();
            }
        });

        agregarBecario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nombre.getText();
                String pas = pass.getText();
                cn.addBecario(nom,pas);
                nombre.setText("");
                pass.setText("");
                JOptionPane.showMessageDialog(null, "Se agrego con éxito!"," Registro ",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        agregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom = nomProducto.getText();
                double prec = Double.valueOf(precio.getText());
                int canD = Integer.parseInt(canDis.getText());
                int miniAl = Integer.parseInt(minimoAlert.getText());
                int canP = Integer.parseInt(canPromo.getText());
                double precioP = Double.valueOf(precioPromo.getText());

                Producto p = new Producto(nom,prec,canD,miniAl,canP,precioP);
                cn.eliminarProducto(nom);
                cn.insertarProducto(p);
                JOptionPane.showMessageDialog(null, "Se agrego con éxito!"," Productos ",JOptionPane.INFORMATION_MESSAGE);

                nomProducto.setText("");
                precio.setText("");
                canDis.setText("");
                minimoAlert.setText("");
                canPromo.setText("");
                precioPromo.setText("");
            }
        });

    }

}
