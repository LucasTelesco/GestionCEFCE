package herramientas;

import java.util.ArrayList;

import static herramientas.VistaBecario.*;

public class actualizaPrecio extends Thread {

    private ArrayList<Object> productos;

    public actualizaPrecio(ArrayList<Object> productos){
        this.productos=productos;
    }

    public void run(){

        double total=0;

        if (comprobarFloat(numeroventa.getText()))
            total = Double.valueOf(numeroventa.getText());

        for (int f=0;f<10;f++){
            String nombre = datventa[f][0].getText();
            for(Object o :productos){
                Producto pr = (Producto) o;
                int i=0;
                if (comprobarFloat(datventa[f][1].getText()))
                i = Integer.parseInt(datventa[f][1].getText());

                if (pr.getNombre().equals(nombre)){
                    if (pr.getCant_promo()!=i) {
                        total += pr.getPrecio() * i;
                    }
                    else{
                        total += pr.getPrecio_promo();
                    }
                }
            }
        }
        totalVenta.setText(String.valueOf(total));

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.run();
    }

    public boolean comprobarFloat (String cadena)
    {
        try
        {
            float num = Float.parseFloat(cadena);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;

    }

    }


