package frsf.isi.grupojf.lab02.modelo;

import java.text.DecimalFormat;
import java.util.Random;

public class Utils implements  java.io.Serializable {
    DecimalFormat f = new DecimalFormat("##.00");

    private ElementoMenu[] listaBebidas;
    private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;

     public class ElementoMenu implements  java.io.Serializable {
        private Integer id;
        private String nombre;
        private Double precio;
        private TipoPlato tipo;

        public ElementoMenu() {
        }

        public ElementoMenu(Integer i, String n, Double p , TipoPlato t) {
            this.setId(i);
            this.setNombre(n);
            this.setPrecio(p);
            this.setTipo(t);
        }

        public ElementoMenu(Integer i, String n , TipoPlato t) {
            this(i,n,0.0,t);
            Random r = new Random();
            this.precio= (r.nextInt(3)+1)*((r.nextDouble()*100));
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getPrecio() {
            return precio;
        }

        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return this.nombre+ "( "+f.format(this.precio)+")";
        }

         public void setTipo(TipoPlato tipo) {
             this.tipo = tipo;
         }

         public TipoPlato getTipo() {
             return tipo;
         }
     }

    public void iniciarListas(){
        // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0]=new ElementoMenu(1,"Coca" , TipoPlato.BEBIDA);
        listaBebidas[1]=new ElementoMenu(4,"Jugo" , TipoPlato.BEBIDA);
        listaBebidas[2]=new ElementoMenu(6,"Agua" , TipoPlato.BEBIDA);
        listaBebidas[3]=new ElementoMenu(8,"Soda" , TipoPlato.BEBIDA);
        listaBebidas[4]=new ElementoMenu(9,"Fernet" , TipoPlato.BEBIDA);
        listaBebidas[5]=new ElementoMenu(10,"Vino" , TipoPlato.BEBIDA);
        listaBebidas[6]=new ElementoMenu(11,"Cerveza", TipoPlato.BEBIDA);
        // inicia lista de platos
        listaPlatos= new ElementoMenu[14];
        listaPlatos[0]=new ElementoMenu(1,"Ravioles" ,TipoPlato.PRINCIPAL);
        listaPlatos[1]=new ElementoMenu(2,"Gnocchi",TipoPlato.PRINCIPAL);
        listaPlatos[2]=new ElementoMenu(3,"Tallarines",TipoPlato.PRINCIPAL);
        listaPlatos[3]=new ElementoMenu(4,"Lomo",TipoPlato.PRINCIPAL);
        listaPlatos[4]=new ElementoMenu(5,"Entrecot",TipoPlato.PRINCIPAL);
        listaPlatos[5]=new ElementoMenu(6,"Pollo",TipoPlato.PRINCIPAL);
        listaPlatos[6]=new ElementoMenu(7,"Pechuga",TipoPlato.PRINCIPAL);
        listaPlatos[7]=new ElementoMenu(8,"Pizza",TipoPlato.PRINCIPAL);
        listaPlatos[8]=new ElementoMenu(9,"Empanadas",TipoPlato.PRINCIPAL);
        listaPlatos[9]=new ElementoMenu(10,"Milanesas",TipoPlato.PRINCIPAL);
        listaPlatos[10]=new ElementoMenu(11,"Picada 1",TipoPlato.PRINCIPAL);
        listaPlatos[11]=new ElementoMenu(12,"Picada 2",TipoPlato.PRINCIPAL);
        listaPlatos[12]=new ElementoMenu(13,"Hamburguesa",TipoPlato.PRINCIPAL);
        listaPlatos[13]=new ElementoMenu(14,"Calamares",TipoPlato.PRINCIPAL);
        // inicia lista de postres
        listaPostre= new ElementoMenu[15];
        listaPostre[0]=new ElementoMenu(1,"Helado",TipoPlato.POSTRE);
        listaPostre[1]=new ElementoMenu(2,"Ensalada de Frutas",TipoPlato.POSTRE);
        listaPostre[2]=new ElementoMenu(3,"Macedonia",TipoPlato.POSTRE);
        listaPostre[3]=new ElementoMenu(4,"Brownie",TipoPlato.POSTRE);
        listaPostre[4]=new ElementoMenu(5,"Cheescake",TipoPlato.POSTRE);
        listaPostre[5]=new ElementoMenu(6,"Tiramisu",TipoPlato.POSTRE);
        listaPostre[6]=new ElementoMenu(7,"Mousse",TipoPlato.POSTRE);
        listaPostre[7]=new ElementoMenu(8,"Fondue",TipoPlato.POSTRE);
        listaPostre[8]=new ElementoMenu(9,"Profiterol",TipoPlato.POSTRE);
        listaPostre[9]=new ElementoMenu(10,"Selva Negra",TipoPlato.POSTRE);
        listaPostre[10]=new ElementoMenu(11,"Lemon Pie",TipoPlato.POSTRE);
        listaPostre[11]=new ElementoMenu(12,"KitKat",TipoPlato.POSTRE);
        listaPostre[12]=new ElementoMenu(13,"IceCreamSandwich",TipoPlato.POSTRE);
        listaPostre[13]=new ElementoMenu(14,"Frozen Yougurth",TipoPlato.POSTRE);
        listaPostre[14]=new ElementoMenu(15,"Queso y Batata",TipoPlato.POSTRE);

    }

    public ElementoMenu[] getListaPostre(){
        return listaPostre;
    }

    public ElementoMenu[] getListaBebidas(){
        return listaBebidas;
    }

    public ElementoMenu[] getListaPlatos(){
        return listaPlatos;
    }

}