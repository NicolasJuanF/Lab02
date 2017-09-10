package frsf.isi.grupojf.lab02;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.RadioGroup;
        import android.widget.Spinner;
        import  frsf.isi.grupojf.lab02.Utils;
        // , RadioGroup.OnCheckedChangeListener

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
    ArrayAdapter<Utils.ElementoMenu> miAdaptador;//adaptador lista
    ArrayAdapter<CharSequence> adapter; //adaptador spinner
    private  ListView listaPlatos;
    private Utils.ElementoMenu[] platos;
    private Utils u;
    private Spinner spinnerHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("comienzo" , "comenzo la app");
//        u = new Utils();
//
//        platos=u.getListaPlatos();
//
        spinnerHora = (Spinner) findViewById(R.id.spinnerHora);
//
//        listaPlatos = (ListView) findViewById(R.id.listaItems);
//
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener(this);
//
        adapter= ArrayAdapter.createFromResource(this, R.array.horas,android.R.layout.simple_spinner_item);
        spinnerHora.setAdapter(adapter);

        findViewById(R.id.botonAgregar).setOnClickListener(this);
        findViewById(R.id.botonConfirmar).setOnClickListener(this);
        findViewById(R.id.botonReiniciar).setOnClickListener(this);


//
//        miAdaptador=new ArrayAdapter<Utils.ElementoMenu>(
//                this,
//                android.R.layout.simple_list_item_1,
//                platos);
//        listaPlatos.setAdapter(miAdaptador);
//

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
//
//    public void onCheckedChanged(RadioGroup arg0, int id){
//        switch (id){
//            case -1:
//                break;
//            case R.id.radio1:
//                Log.v("boton" , "boton 1 comida");
//                break;
//            case R.id.radio2:
//                Log.v("boton" , "boton 2 postre " );
//                break;
//            case R.id.radio3:
//                Log.v("boton" , "boton 3 bebida");
//                break;
//        }
//    }
}