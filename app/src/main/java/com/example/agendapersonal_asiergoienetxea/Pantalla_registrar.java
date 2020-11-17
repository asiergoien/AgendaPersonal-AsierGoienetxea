package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla_registrar extends AppCompatActivity {
    private   EditText usuarioErreEdit;
    private EditText ContraErre1Edit;
    private EditText ContraErre2Edit;
    private EditText ContraErre1Anti;
    private TextView ContraAnti;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar);

        //Crearmos varible para guardar los los editText
        usuarioErreEdit=(EditText)findViewById(R.id.UsuarioErre1);
        ContraErre1Edit=(EditText)findViewById(R.id.ContraErre1);
        ContraErre2Edit=(EditText)findViewById(R.id.ContraErre2);
        ContraErre1Anti=(EditText)findViewById(R.id.contraAnti1);
        ContraAnti=(TextView)findViewById(R.id.textView10);

        if (MainActivity.mostrarContraAnti==true){
            ContraErre1Anti.setVisibility(View.VISIBLE);
            ContraAnti.setVisibility(View.VISIBLE);
        }

    }
    public void Registrar(View v){

        //Aqui estamos guardando los datos de la pantalla registrar
        String strUsurio = usuarioErreEdit.getText().toString();
        String strContra1 = ContraErre1Edit.getText().toString();
        String strContra2 = ContraErre2Edit.getText().toString();
        String strContraAnti = ContraErre1Anti.getText().toString();

        //Ahora vamos a verificar si existe este usurio
        MainActivity.shprefe=getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        editor =MainActivity.shprefe.edit();

        //En este if miro si esta vacio o no
        if (!(strUsurio.isEmpty() || strContra1.isEmpty() || strContra2.isEmpty())) {
            //Aqui si las contraseñas son iguales
            if((strContra1.equals(strContra2))){
                //Aqui mirar si esta registrado o no
                if (MainActivity.shprefe.contains(strUsurio)){

                    if (MainActivity.mostrarContraAnti==true){
                        if (MainActivity.contraGuar.equals(strContraAnti)){
                            editor.putString(strUsurio,strContra1);
                            editor.commit();
                            Toast.makeText(this,R.string.ExitoCambiar,Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(this,R.string.VerfiContra,Toast.LENGTH_LONG).show();

                        }
                    }else {
                        Toast.makeText(this,R.string.UsuRegis,Toast.LENGTH_LONG).show();
                    }
                }else {
                    if (MainActivity.mostrarContraAnti==false){
                        Toast.makeText(this, R.string.ExitoUsu, Toast.LENGTH_LONG).show();
                        editor.putString(strUsurio,strContra1);
                        editor.commit();
                        finish();
                    }else {
                        Toast.makeText(this, R.string.UsuNoregis, Toast.LENGTH_LONG).show();
                    }
                }
            }else{
                Toast.makeText(this,R.string.ContraNoIgual,Toast.LENGTH_LONG).show();

            }
        }else  {
            Toast.makeText(this,R.string.NocamposBlancos,Toast.LENGTH_LONG).show();

        }
    }
    public  void PantallaInicial(View v){
        finish();
    }
}