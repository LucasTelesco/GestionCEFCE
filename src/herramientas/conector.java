package herramientas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class conector {
        private PreparedStatement psInsertar;
        private Statement estatuto;
        private Connection conexion;

        public conector(){
            try {
                Class.forName("com.mysql.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                System.out.print(e.getMessage());
            }
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "paralelo");    //para borrar
                estatuto = conexion.createStatement();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

        public void eliminarProducto(String nombre) {

            try {
                String eliminar = "DELETE FROM Producto WHERE nombre = '" + nombre + "' ORDER BY nombre LIMIT 10";
                estatuto.execute(eliminar); // con este metodo ingreso lo qe sea para qe se ejecute
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void cargarVenta(float monto){
            try {
                String ejecutar = "insert into venta (precio,dia) values("+monto+",now());";
                estatuto.execute(ejecutar); // con este metodo ingreso lo qe sea para qe se ejecute
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void cargarVenta(){
           try {
                String ejecutar = "insert into venta (precio,dia) values(0,now());";
                estatuto.execute(ejecutar); // con este metodo ingreso lo qe sea para qe se ejecute
            } catch (SQLException e) {
             System.out.println(e.getMessage());
           }
        }

        public void modificaPrecio(double precio, int codigo){
            try {
                String ejecutar = "update venta set precio='"+precio+"' where codigo='"+codigo+"';";
                estatuto.execute(ejecutar);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        public void cargarDatoVenta(String nombre,int codigo,int cantidad){
            try {
                String ejecutar = " insert into datoventa values('"+nombre+"',"+codigo+","+cantidad+");";
                estatuto.execute(ejecutar); // con este metodo ingreso lo qe sea para qe se ejecute
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public Integer ultimo_codigo_cargado(){
            try {
                ResultSet rs = estatuto.executeQuery("select max(codigo) from venta;");
                if (rs.next()) {
                    int codigo = rs.getInt(1);
                    return codigo;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void insertarProducto(Producto p) { // throws SQLException{
            // Modificar para mas campos de producto
            try {

                psInsertar = conexion.prepareStatement("INSERT INTO Producto (nombre, precio, cantdis, minimo_alerta, cantpromo, preciopromo)" + "values(?,?,?,?,?,?)");
                psInsertar.setString(1, p.getNombre());
                psInsertar.setDouble(2, p.getPrecio());
                psInsertar.setInt(3, p.getCantdis());
                psInsertar.setInt(4, p.getMinimo_alerta());
                psInsertar.setInt(5,p.getCant_promo());
                psInsertar.setDouble(6,p.getPrecio_promo());

                psInsertar.executeUpdate();   /// se utiliza para las altas para borrar no!

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public String verCaja(String becario){
            try {
                ResultSet rs = estatuto.executeQuery("select * from Becarios where nombre = '"+becario+"';"); //mayuscula
                if (rs.next()) {
                    String caja = rs.getString(3);
                    return caja;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Object> listarBecarios() {

            ArrayList<Object> out = new ArrayList<Object>();

            try {
                ResultSet rs = estatuto.executeQuery("SELECT * FROM Becarios"); //mayuscula
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    out.add(nombre);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return out;
        }

    public Hashtable<String,Integer> grafico() {
        // cantidad de productos vendidos
        Hashtable<String,Integer> out = new Hashtable<String,Integer>();

        try {
            ResultSet rs = estatuto.executeQuery("select nombre,sum(cantidad) from datoventa group by nombre;");
            while (rs.next()) {
                String nombre = rs.getString(1);
                Integer cantidad = rs.getInt(2);
                out.put(nombre,cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public Hashtable<String,Integer> graficotres() {
        // horarios de venta
        Hashtable<String,Integer> out = new Hashtable<String,Integer>();

        try {
            ResultSet rs = estatuto.executeQuery("select hour(dia), sum(precio) from venta group by hour(dia);");
            while (rs.next()) {
                String hora = rs.getString(1);
                Integer total = rs.getInt(2);
                out.put(hora,total);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }


    public Hashtable<String,Integer> graficodos(String producto) {
        Hashtable<String,Integer> out = new Hashtable<String,Integer>();

        try {
            ResultSet rs = estatuto.executeQuery("select nombre,month(dia),sum(cantidad) from datoventa d inner join venta v on (d.codigo=v.codigo) where nombre='"+producto+"' group by month(dia) ;");
            while (rs.next()) {
                String nombre = rs.getString(2);
                Integer cantidad = rs.getInt(3);
                out.put(nombre,cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

        public ArrayList<Producto> listarProductos() {

            ArrayList<Producto> out = new ArrayList<Producto>();

            try {
                ResultSet rs = estatuto.executeQuery("SELECT * FROM Producto"); //mayuscula
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble(2);
                    int cantdis = rs.getInt(3);
                    int minimo = rs.getInt(4);
                    int cant_promo = rs.getInt(5);
                    double precio_promo = rs.getDouble(6);
                    Producto p = new Producto(nombre, precio, cantdis, minimo,cant_promo,precio_promo);
                    out.add(p);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return out;
        }
        public void Desconectar() throws SQLException {
            estatuto.close();
            conexion.close();
        }

        public void descuentaCantidad(Producto p,int i){

            try {
                String ejecutar = "UPDATE Producto SET cantdis=cantdis - "+i+" WHERE nombre='"+p.getNombre()+"'";
                estatuto.execute(ejecutar);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }

    public void cargaCaja(String becario){

        try {
            double total = Double.valueOf(verCaja(becario));
            if (total>0) {
                String ejecutar = "insert into caja values(now(),'" + becario + "'," + total + ");";
                estatuto.execute(ejecutar);
                setearTotal(becario);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

        public void sumarTotal(String nombre,double sumar){
            String aux = new String(String.valueOf(sumar));

            try {
                estatuto.execute("UPDATE Becarios SET total_vendido=total_vendido + "+aux+" WHERE nombre='"+nombre+"'");
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        public void cambiarPass(String nombre,String clave){
            try {
                estatuto.execute("UPDATE Becarios SET pass=SHA('"+clave+"') WHERE nombre='"+nombre+"'");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public ArrayList<String> getCaja(String fec_lim){
            ArrayList<String> out = new ArrayList<String>();
            try {

                ResultSet rs = estatuto.executeQuery("select * from caja where dia > '"+fec_lim+"' limit 15;"); //mayuscula
                while (rs.next()) {
                    String dia = rs.getString(1);
                    String bec = rs.getString(2);
                    String total = rs.getString(3);
                    out.add("Fecha : "+dia+" Becario : "+bec+" Total : "+total);
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
            return out;
        }

        public boolean verificarUsuario(String nombre,String clave){
            try {

                String passAux = null,passUsuario=null;

                estatuto.execute("insert into Becarios values('"+"usuarioxxxxx"+"',SHA('"+clave+"'),0);");
                ResultSet rs = estatuto.executeQuery("select * from Becarios where nombre = 'usuarioxxxxx';");

                while (rs.next()){
                 passAux = rs.getString("pass");}

                estatuto.execute("DELETE FROM Becarios WHERE nombre = 'usuarioxxxxx';");

                ResultSet rs2 = estatuto.executeQuery("select * from Becarios where nombre = '"+nombre+"';");

                while (rs2.next()){
                    passUsuario = rs2.getString("pass");}
                return (passAux.equals(passUsuario));

            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }

        public void setearTotal(String nombre){
            try {
                estatuto.execute("UPDATE Becarios SET total_vendido=0 WHERE nombre='"+nombre+"'");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public void prestar_mate(String dni_prestar,String termo_prestar,String mate_prestar){
            try {
                estatuto.execute("insert into mate values("+dni_prestar+","+termo_prestar+","+mate_prestar+");");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public void addBecario(String nombre, String pass){
            try {
                estatuto.execute("insert into Becarios values('"+nombre+"',SHA('"+pass+"'),0);");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

         public void devolver_mate(String dni_prestar){
                try {
                    estatuto.execute("DELETE FROM mate WHERE dni = "+dni_prestar+";");
                }catch (SQLException e){
                    e.printStackTrace();
                }
         }

        public Elem_mate prestamo(String dni){
            Elem_mate m = null;
            try {
                ResultSet rs = estatuto.executeQuery("select * from mate where dni = '"+dni+"';");

                if (rs.next()){
                    m = new Elem_mate(dni,rs.getString(2),rs.getString(3));
                }
               return m;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return m;
        }

        public ArrayList<Object> litarmates(){
            ArrayList<Object> out = new ArrayList<Object>();

            try {
                ResultSet rs = estatuto.executeQuery("SELECT * FROM mate"); //mayuscula
                while (rs.next()) {
                    Elem_mate guardar = new Elem_mate(rs.getString(1),rs.getString(2),rs.getString(3));
                    out.add(guardar);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return out;

        }

    }



