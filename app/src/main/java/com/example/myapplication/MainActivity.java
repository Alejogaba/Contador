package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
   public int contador;
   public boolean negativo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        contador=0;
        negativo=false;
        this.resultado();
        TextView.OnEditorActionListener miteclado;
        miteclado = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    reset(null);
                }
                return false;
            }
        };
        EditText set = findViewById(R.id.set);
        set.setOnEditorActionListener(miteclado);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public void incrementar(View vista){
    contador++;
    validar();
    }

    public void decrementar(View vista){
    contador--;
    validar();
    }

    public void negativos(View vista){
        CheckBox chk =(CheckBox)vista;
        if(chk.isChecked()){
            negativo=true;
        }else {
            negativo=false;
        }
        validar();
    }
    public void validar(){
        if(negativo==false && contador<0){contador=0;}
        resultado();
    }
    public void reset(View vista){
        EditText edt = findViewById(R.id.set);
        try {
            contador = Integer.parseInt(edt.getText().toString());
        }catch (Exception exc){
            contador=0;
        }
        validar();
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(edt.getWindowToken(),0);
    }
    public void resultado(){
        String valor = getString(R.string.contador, this.contador);
        TextView texto = findViewById(R.id.textView);
        texto.setText(valor);
    }

}
