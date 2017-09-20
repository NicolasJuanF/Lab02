package frsf.isi.grupojf.lab02;
import frsf.isi.grupojf.lab02.modelo.Utils;
import frsf.isi.grupojf.lab02.modelo.TipoBebida;

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

    private ArrayAdapter<CharSequence> adapter; //adaptador spinner
    private Spinner spinnerHora;
    private TextView textoPedido;
    private ToggleButton toggleReserva;
    private RadioGroup radioGroup;

    //declaraciones lista
    private Utils utils;
    private ListView miLista;
    private Utils.ElementoMenu[] elementos;
    private Utils.ElementoMenu itemSeleccionado=null;
    private ArrayAdapter<Utils.ElementoMenu> miAdaptador;//adaptador lista


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toggleButton
        toggleReserva = (ToggleButton) findViewById(R.id.toggleButton);


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
            case  R.id.radio1:
                //cargar lista platos
                elementos=utils.getListaPlatos();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, elementos);
                break;
            case R.id.radio2:
                //cargar lista Bebidas
                elementos=utils.getListaBebidas();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, elementos);
                break;
            case R.id.radio3:
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
                     textoPedido.append(itemSeleccionado.toString() + '\n');
                     precioTotal += itemSeleccionado.getPrecio();
                     radioGroup.clearCheck();
                     itemSeleccionado = null;
                 } else if (itemSeleccionado == null) {
                     Toast.makeText(getApplicationContext(), "Debes seleccionar algo",
                             Toast.LENGTH_SHORT).show();
                 }
                 else if(confirmado) {
                     Toast.makeText(getApplicationContext(), "Ya se ha confirmado el pedido",
                             Toast.LENGTH_SHORT).show();
                 }
                 break;
             case R.id.botonReiniciar:
                 textoPedido.setText("");
                 itemSeleccionado = null;
                 radioGroup.clearCheck();
                 precioTotal = 0;
                 confirmado = false;
                 break;
             case R.id.botonConfirmar:
                 if(!confirmado && precioTotal != 0) {
                     textoPedido.append(String.format("\nTotal: $%.2f", precioTotal));
                     confirmado = true;
                 }
                 else if(confirmado) {
                     Toast.makeText(getApplicationContext(), "El pedido ya fue confirmado",
                             Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(getApplicationContext(), "Debes pedir algo primero",
                             Toast.LENGTH_SHORT).show();
                 }
                 break;
         }
     }
}
