package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuPrincipalActivity extends AppCompatActivity {
    public static boolean CrearTarea=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }
    public void  Crear_Tarea_Pantalla(View v){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);
        CrearTarea=true;
    }
    public void  Consultar_tarea_Pantalla(View v){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,consultarTareasAgenda.class);
        startActivity(CrearPantalla);





    }
}