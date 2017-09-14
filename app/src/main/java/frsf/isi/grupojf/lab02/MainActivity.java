package frsf.isi.grupojf.lab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


// , RadioGroup.OnCheckedChangeListener

/* Glosario:
    - Elementos de vista comienzan con : list (listas) , tv(Text View), sp (spinner),
    -

*/

public class MainActivity extends AppCompatActivity implements  View.OnClickListener  , RadioGroup.OnCheckedChangeListener{

    private ArrayAdapter<CharSequence> adapter; //adaptador spinner
    private Spinner spinnerHora;

    //declaraciones lista
    private Utils utils;
    private ListView miLista;
    private Utils.ElementoMenu[] elementos;
    private ArrayAdapter<Utils.ElementoMenu> miAdaptador;//adaptador lista





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seteo de listener a group de radiobuttons
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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




    }
    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id){
            case R.id.botonAgregar:
                Log.v("boton" , "boton agregar");
                break;
            case R.id.botonConfirmar:

                break;
            case R.id.botonReiniciar:

                break;
        }

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
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, elementos);
                miLista.setAdapter(miAdaptador);
                break;
            case R.id.radio2:
                //cargar lista Bebidas
                elementos=utils.getListaBebidas();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, elementos);
                miLista.setAdapter(miAdaptador);
                break;
            case R.id.radio3:
                //cargar lista
                elementos=utils.getListaPostre();
                miAdaptador=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, elementos);
                miLista.setAdapter(miAdaptador);
                break;
        }
    }
}