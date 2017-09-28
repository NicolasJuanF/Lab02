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



public class MainActivity extends AppCompatActivity implements  View.OnClickListener  , RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    private static final String TAG = "REGISTRO";


    private final Integer CODIGO = 1;
    private ArrayAdapter<CharSequence> adapter; //adaptador spinner
    private Spinner spinnerHora;
    private TextView textoPedido;
    private ToggleButton toggleReserva;
    private RadioGroup radioGroup;

    private Boolean confirmado=false;

    //declaraciones lista
    private Utils utils;
    private ListView lv;
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
        lv = (ListView) findViewById(R.id.listaItems);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pedido = new Pedido();
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int id){
        switch (id){
            case -1:
                break;
            case R.id.radioPlato:

                break;
            case R.id.radioPostre:

                break;
            case R.id.radioBebida:

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
        lv.setAdapter(miAdaptador);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemSeleccionado = (Utils.ElementoMenu) lv.getItemAtPosition(position);
    }

     @Override
     public void onClick(View boton) {
         Utils.ElementoMenu[] vacio = new Utils.ElementoMenu[0]; // seteo un arreglo vacio por si agrega o reinicia
         switch(boton.getId()) {
             case R.id.botonAgregar:
                 if (itemSeleccionado != null && !confirmado) {
                     Log.v(TAG, itemSeleccionado.toString());

                     switch (itemSeleccionado.getTipo()){
                         case PRINCIPAL:
                             if(pedido.getPlato() == null){
                                 pedido.setPlato(itemSeleccionado);
                                 textoPedido.append(itemSeleccionado.toString() + '\n');
                             }else{
                                 Toast.makeText(getApplicationContext(), R.string.plato_ya_agregado,
                                         Toast.LENGTH_SHORT).show();
                             }

                             break;
                         case POSTRE:
                             if(pedido.getPostre() == null){
                                 pedido.setPostre(itemSeleccionado);
                                 textoPedido.append(itemSeleccionado.toString() + '\n');
                             }else{
                                 Toast.makeText(getApplicationContext(), R.string.postre_ya_agregado,
                                         Toast.LENGTH_SHORT).show();
                             }

                         break;
                         case BEBIDA:
                             if(pedido.getBebida() == null){
                                 pedido.setBebida(itemSeleccionado);
                                 textoPedido.append(itemSeleccionado.toString() + '\n');
                             }else{
                                 Toast.makeText(getApplicationContext(), R.string.bebida_ya_agregado,
                                         Toast.LENGTH_SHORT).show();
                             }

                         break;

                     }
                     radioGroup.clearCheck();
                     itemSeleccionado = null;
                     miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, vacio);
                     lv.setAdapter(miAdaptador);

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
                 textoPedido.setText("");
                 itemSeleccionado = null;
                 radioGroup.clearCheck();
                 confirmado = false;
                 pedido = new Pedido();//reseteo el pedido
                 miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, vacio);
                 lv.setAdapter(miAdaptador);
                 break;
             case R.id.botonConfirmar:
                 if(!confirmado && (pedido.getPlato() != null || pedido.getBebida()!=null || pedido.getPostre()!=null )) {
                     Double total = 0.0;
                     if(pedido.getPlato() != null) total += pedido.getPlato().getPrecio();
                     if(pedido.getBebida() != null) total += pedido.getBebida().getPrecio();
                     if(pedido.getPostre()!= null) total += pedido.getPostre().getPrecio();

                     pedido.setCosto(total);
                     pedido.setEsDelivery(toggleReserva.isChecked());
                     pedido.setHoraEntrega(spinnerHora.getSelectedItem().toString());

                     Intent intent = new Intent(getApplicationContext(), pago_pedido_activity.class);
                     Log.v(TAG, pedido.toString());
                     intent.putExtra("pedido", pedido);
                     startActivityForResult(intent, CODIGO);
                 } else if ( confirmado){
                     Toast.makeText(getApplicationContext(), R.string.yaConfirmado,
                             Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(getApplicationContext(), R.string.pedido_vacio,
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

        StringBuilder texto = new StringBuilder("");
        if (pedido.getPlato() != null) texto.append(pedido.getPlato().toString()).append("\n");
        if (pedido.getPostre() != null) texto.append(pedido.getPostre().toString()).append("\n");
        if (pedido.getBebida() != null) texto.append(pedido.getBebida().toString()).append("\n");

        textoPedido.setText(texto.toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODIGO) {
            if(resultCode == RESULT_OK) {
                confirmado = true;
                Tarjeta tarjeta = (Tarjeta) data.getSerializableExtra("tarjeta");
                pedido = (Pedido) data.getSerializableExtra("pedido");
                pedido.setNombreCliente(tarjeta.getNombre());
                pedido.setEmail(tarjeta.getCorreo());

                double monto = pedido.getCosto();

                Toast.makeText(getApplicationContext(), getString(R.string.mensajeConfirmado, monto), Toast.LENGTH_SHORT).show();
            } else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), R.string.mensajeCancelado, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
