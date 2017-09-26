package frsf.isi.grupojf.lab02;
import frsf.isi.grupojf.lab02.modelo.Pedido;
import frsf.isi.grupojf.lab02.modelo.Tarjeta;
import frsf.isi.grupojf.lab02.modelo.Utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/* Glosario:
    - Elementos de vista comienzan con : list (listas) , tv(Text View), sp (spinner),
    -

*/

public class MainActivity extends AppCompatActivity implements  View.OnClickListener  , RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    private static final String TAG = "REGISTRO";


    private final Integer CODIGO_ESTATUS = 200;
    private ArrayAdapter<CharSequence> adapter; //adaptador spinner
    private Spinner spinnerHora;
    private TextView textoPedido;
    private ToggleButton toggleReserva;
    private RadioGroup radioGroup;

    private Boolean confirmado=false;

    //declaraciones lista
    private Utils utils;
    private ListView miLista;
    private Utils.ElementoMenu[] elementos;
    private Utils.ElementoMenu itemSeleccionado=null;
    private ArrayAdapter<Utils.ElementoMenu> miAdaptador;//adaptador lista

    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toggleButton
        toggleReserva = (ToggleButton) findViewById(R.id.toggleButton);
        textoPedido = (TextView) findViewById(R.id.textoPedido);


        //seteo de listener a group de radiobuttons
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);


        //carga spinner, con horas
        spinnerHora = (Spinner) findViewById(R.id.spinnerHora);
        adapter= ArrayAdapter.createFromResource(this, R.array.horas,android.R.layout.simple_spinner_item);
        spinnerHora.setAdapter(adapter);


        //eventos botones
        findViewById(R.id.botonAgregar).setOnClickListener(this);
        findViewById(R.id.botonConfirmar).setOnClickListener(this);
        findViewById(R.id.botonReiniciar).setOnClickListener(this);


        //Iniciar Utils y generar las listas
        utils = new Utils();
        utils.iniciarListas();
        miLista = (ListView) findViewById(R.id.listaItems);
        miLista.setOnItemClickListener(this);
        miLista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pedido = new Pedido();
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int id){
        switch (id){
            case -1:
                break;
            case R.id.radio1:

                break;
            case R.id.radio2:

                break;
            case R.id.radio3:

                break;
        }
    }


    public void onRadioButtonClicked(View v){
        final int id = v.getId();
        switch (id){
            case -1:
                break;
            case  R.id.radioPlato:
                //cargar lista platos
                elementos=utils.getListaPlatos();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, elementos);
                break;
            case R.id.radioBebida:
                //cargar lista Bebidas
                elementos=utils.getListaBebidas();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, elementos);
                break;
            case R.id.radioPostre:
                //cargar lista
                elementos=utils.getListaPostre();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, elementos);
                break;
        }
        miLista.setAdapter(miAdaptador);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemSeleccionado = (Utils.ElementoMenu) miLista.getItemAtPosition(position);
    }

     @Override
     public void onClick(View boton) {
         switch(boton.getId()) {
             case R.id.botonAgregar:
                 if (itemSeleccionado != null && !confirmado) {
                     Log.v(TAG, itemSeleccionado.toString());
                     textoPedido.append(itemSeleccionado.toString() + '\n');
                     switch (itemSeleccionado.getTipo()){
                         case PRINCIPAL:
                             pedido.addPlato(itemSeleccionado);
                             break;
                         case POSTRE:
                             pedido.addPostre(itemSeleccionado);
                         break;
                         case BEBIDA:
                             pedido.addBebida(itemSeleccionado);
                         break;

                     }

                     itemSeleccionado = null;
                 } else if (itemSeleccionado == null) {
                     Toast.makeText(getApplicationContext(), R.string.agregarVacio,
                             Toast.LENGTH_SHORT).show();
                 }
                 else if(confirmado) {
                     Toast.makeText(getApplicationContext(), R.string.yaConfirmado,
                             Toast.LENGTH_SHORT).show();
                 }
                 break;
             case R.id.botonReiniciar:
                 Utils.ElementoMenu[] vacio = new Utils.ElementoMenu[0];
                 textoPedido.setText("");
                 itemSeleccionado = null;
                 radioGroup.clearCheck();
                 confirmado = false;
                 miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, vacio);
                 miLista.setAdapter(miAdaptador);
                 break;
             case R.id.botonConfirmar:
                 if(!confirmado) {
                     Double total = 0.0;

                     for (Utils.ElementoMenu unElemento:pedido.getPlato()
                             ) {
                         if(unElemento != null) total += unElemento.getPrecio();

                     }

                     for (Utils.ElementoMenu unElemento:pedido.getBebida()
                             ) {
                         if(unElemento != null) total += unElemento.getPrecio();
                     }

                     for (Utils.ElementoMenu unElemento:pedido.getPostre()
                             ) {
                         if(unElemento!= null) total += unElemento.getPrecio();
                     }


                     pedido.setCosto(total);
                     pedido.setEsDelivery(toggleReserva.isChecked());
                     pedido.setHoraEntrega(spinnerHora.getSelectedItem().toString());

                     Intent intent = new Intent(MainActivity.this, pago_pedido_activity.class);
                     intent.putExtra("pedido", pedido);
                     startActivityForResult(intent, CODIGO_ESTATUS);
                 } else {
                     Toast.makeText(getApplicationContext(), R.string.yaConfirmado,
                             Toast.LENGTH_SHORT).show();
                 }
                 break;
         }
     }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("pedido", pedido);
        outState.putBoolean("confirmado", confirmado);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pedido = (Pedido) savedInstanceState.getSerializable("pedido");
        confirmado = savedInstanceState.getBoolean("confirmado");
        Utils.ElementoMenu[] listaPlatos = pedido.getPlato();
        Utils.ElementoMenu[] listaPostres= pedido.getPostre();
        Utils.ElementoMenu[] listaBebidas= pedido.getBebida();
        StringBuilder texto = new StringBuilder("");
        if(listaPlatos != null){

            for (Utils.ElementoMenu unElemento : listaPlatos
                    ) {
                if (unElemento != null) texto.append(unElemento.toString()).append("\n");

            }
        }
        if(listaPostres!=null) {
            for (Utils.ElementoMenu unElemento : listaPostres
                    ) {
                if (unElemento != null) texto.append(unElemento.toString()).append("\n");
            }
        }
        if(listaBebidas != null) {
            for (Utils.ElementoMenu unElemento : listaBebidas
                    ) {
                if (unElemento != null) texto.append(unElemento.toString()).append("\n");
            }
        }
        textoPedido.setText(texto.toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODIGO_ESTATUS) {
            if(resultCode == RESULT_OK) {
                confirmado = true;
                Tarjeta tarjeta = (Tarjeta) data.getSerializableExtra("tarjeta");
                pedido = (Pedido) data.getSerializableExtra("pedido");

                double monto = pedido.getCosto();
//                DecimalFormat df = new DecimalFormat("##.##");
//                txtDetallesPedido.setText(txtDetallesPedido.getText().toString() + "\nTotal: $ " + df.format(monto));

                Toast.makeText(getApplicationContext(), getString(R.string.mensajeConfirmado, monto), Toast.LENGTH_SHORT).show();
            } else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), R.string.mensajeCancelado, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
