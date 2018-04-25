package herramientas;


public class Elem_mate{
    private String dni;
    private String mate;
    private String termo;

    public Elem_mate(String dni,String termo, String mate){
        this.dni=dni;
        this.termo=termo;
        this.mate=mate;
    }

    public String getDni() {
        return dni;
    }

    public String getMate() {
        return mate;
    }

    public String getTermo() {
        return termo;
    }

    public String toString(){return dni;}
}
