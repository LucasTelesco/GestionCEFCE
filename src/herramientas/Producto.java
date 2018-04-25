package herramientas;

public class Producto implements Comparable<Producto> {
    private String nombre;
    private double precio;
    private int cantdis;
    private int minimo_alerta;
    private int cant_promo;
    private double precio_promo;

    public Producto(String nombre, double precio, int cantdis, int minimo_alerta,int cant_promo, double precio_promo){
        this.nombre=nombre;
        this.precio=precio;
        this.cantdis=cantdis;
        this.minimo_alerta=minimo_alerta;
        this.cant_promo = cant_promo;
        this.precio_promo = precio_promo;
    }

    public Producto(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantdis() {
        return cantdis;
    }

    public void setCantdis(int cantdis) {
        this.cantdis-= cantdis;
    }

    public int getMinimo_alerta() {
        return minimo_alerta;
    }

    public void setMinimo_alerta(int minimo_alerta) {
        this.minimo_alerta = minimo_alerta;
    }

    public String toString(){
        return nombre;
    }

    public int getCant_promo() {
        return cant_promo;
    }

    public double getPrecio_promo() {
        return precio_promo;
    }

    @Override
    public int compareTo(Producto o) {
        int mi_margen = this.getCantdis() - this.getMinimo_alerta();
        int otro_margen = o.getCantdis() - o.getMinimo_alerta();
        return mi_margen-otro_margen;
    }
}

