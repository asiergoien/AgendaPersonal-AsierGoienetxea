package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences shprefe;
    private EditText usu1;
    private EditText contra1;
    public  static boolean mostrarContraAnti=false;
    public static  String contraGuar;
    public static  String usuarioGuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usu1= (EditText)findViewById(R.id.usuario1);
        contra1= (EditText)findViewById(R.id.contraseña1);
        usu1.setText("asier");
        contra1.setText("1");
    }
    public void comprobarLog(View v ){
        //Guardo el valor de edittext en una varible de tipo string
        String strUsuario= usu1.getText().toString();
        String strContra1= contra1.getText().toString();
        //Aqui vamos a utilizar sharedPreferences para mirar si el usurio que se esta intentando logear esta registrado o no
        shprefe= getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        contraGuar=shprefe.getString(strUsuario,"");

        if (usu1.getText().toString().isEmpty() || contra1.getText().toString().isEmpty()){
            Toast.makeText(this,R.string.NocamposBlancos,Toast.LENGTH_LONG).show();

        }else{
            //En este if miraremos si existe ese usuario
            if (shprefe.contains(strUsuario) ){
                usuarioGuar= strUsuario;
                if (contraGuar.equals(strContra1)){
                    Toast.makeText(this,R.string.ExitoSesion,Toast.LENGTH_LONG).show();
                    MenuPantalla();
                }else{
                    Toast.makeText(this,R.string.Contramal,Toast.LENGTH_LONG).show();

                }
            }else {
                Toast.makeText(this,R.string.Contramal,Toast.LENGTH_LONG).show();

            }
        }
    }
    public void ErrePantalla(View v ){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent registrar = new Intent(this,Pantalla_registrar.class);
        startActivity(registrar);
        mostrarContraAnti=false;
    }

    public void MenuPantalla(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent menuPrincipal = new Intent(this,MenuPrincipalActivity.class);
        startActivity(menuPrincipal);
    }


}