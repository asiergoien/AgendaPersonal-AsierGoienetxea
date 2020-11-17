package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class VerTareaActivity extends AppCompatActivity {

    public static EditText NombreTarea;
    public static EditText DescTarea;
    public static EditText FechaTarea;
    public static EditText CosteTarea;
    public static TextView TextViewPrioridades;
    public static  String nombreStrVer;
    public static String descStrVer;
    public static String FechaStrVer;
    public static String CosteStrVer;
    public static String prioStrVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tarea);
        NombreTarea=(EditText)findViewById(R.id.NomTarea);
        DescTarea=(EditText)findViewById(R.id.DescTarea);
        FechaTarea=(EditText)findViewById(R.id.FechaTarea);
        CosteTarea=(EditText)findViewById(R.id.CosteTarea);
        TextViewPrioridades=(TextView)findViewById(R.id.TextViewPrioridades);
        NombreTarea.setText(nombreStrVer);
        DescTarea.setText(descStrVer);
        FechaTarea.setText(FechaStrVer);
        CosteTarea.setText(CosteStrVer);
        TextViewPrioridades.setText(prioStrVer);
        setEnable();
    }

    public  void setEnable(){
        NombreTarea.setEnabled(false);
        DescTarea.setEnabled(false);
        FechaTarea.setEnabled(false);
        CosteTarea.setEnabled(false);

    }
    public void Salir(View view){

        finish();
//        Intent registrar = new Intent(this,MenuPrincipalActivity.class);
//        startActivity(registrar);

    }
}