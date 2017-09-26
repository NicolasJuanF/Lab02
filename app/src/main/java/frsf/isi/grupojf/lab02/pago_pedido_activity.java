package frsf.isi.grupojf.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import frsf.isi.grupojf.lab02.modelo.Pedido;
import frsf.isi.grupojf.lab02.modelo.Tarjeta;
import frsf.isi.grupojf.lab02.modelo.Utils;

public class pago_pedido_activity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    private EditText textoFecha;
    private EditText textoNombre;
    private EditText textoCodigoSeguridad;
    private EditText textoNroTarjeta;
    private EditText textoCorreo;

    private Button botonConfirmar;
    private Button botonCancelar;

    private Tarjeta tarjeta;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);

        textoCorreo = (EditText) findViewById(R.id.input_correo);
        textoFecha= (EditText) findViewById(R.id.input_fecha);
        textoNombre= (EditText) findViewById(R.id.input_nombre);
        textoCodigoSeguridad= (EditText) findViewById(R.id.input_codigo_seguridad);
        textoNroTarjeta = (EditText) findViewById(R.id.input_numero_tarjeta);
        botonCancelar = (Button) findViewById(R.id.botonCancelar);
        botonConfirmar = (Button) findViewById(R.id.botonConfirmarPago);
        intent = getIntent();
        pedido = (Pedido) intent.getSerializableExtra("pedido");
        tarjeta = new Tarjeta();

        botonCancelar.setOnClickListener(this);
        botonConfirmar.setOnClickListener(this);

    }

    @Override
    public void onClick(View boton) {
        switch(boton.getId()) {
            case R.id.botonConfirmarPago:
                if(validarCampos()){//deberia validar los campos
                    tarjeta.setNombre(textoNombre.getText().toString());
                    tarjeta.setCorreo(textoCorreo.getText().toString());
                    try{
                        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/yy"); // here set the pattern as you date in string was containing like date/month/year
                        Date date = simpleDate.parse(textoFecha.getText().toString());
                        tarjeta.setFechaVencimiento(date);
                    }catch(ParseException ex){
                        textoFecha.setError("Formato Inválido");
                    }

                    tarjeta.setSeguridad(Integer.parseInt(textoCodigoSeguridad.getText().toString()));
                    tarjeta.setNumero(Integer.parseInt( textoNroTarjeta.getText().toString()));
                    intent.putExtra("tarjeta",tarjeta);
                    intent.putExtra("pedido", pedido);//no se si esta bien devolverlo asi nomas
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.botonCancelar:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;


        }
    }

    public Boolean validarCampos(){
        Boolean bandera=true;
        if(TextUtils.isEmpty(textoCorreo.getText())){
            textoCorreo.setError("Este Campo es requerido");
            bandera = false;
        }
        if(TextUtils.isEmpty(textoNombre.getText())){
            textoNombre.setError("Este Campo es requerido");
            bandera = false;
        }
        if(TextUtils.isEmpty(textoFecha.getText())){
            textoFecha.setError("Este Campo es requerido");
            bandera = false;
        }
        if(TextUtils.isEmpty(textoCodigoSeguridad.getText())){
            textoCodigoSeguridad.setError("Este Campo es requerido");
            bandera = false;
        }
        if(TextUtils.isEmpty(textoNroTarjeta.getText())){
            textoNroTarjeta.setError("Este Campo es requerido");
            bandera = false;
        }

        return bandera;
    }




}
