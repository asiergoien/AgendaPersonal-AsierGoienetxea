package com.example.agendapersonal_asiergoienetxea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class consultarTareasAgenda extends AppCompatActivity  implements AcercaDeInterface {
    public static boolean   ModifiTarea= false;
    private Button btnTodas;
    private Button btnHechas;
    private Button btnPendientes;
    public int numBoton=0;
    public static  ListView LisTviewTareas;
    public static ArrayList<String> Codtareas;
    public static ArrayList<String> usuarioTarea;
    public static ArrayList<String> nomTarea;
    public static ArrayList<String>descTarea;
    public static ArrayList<String>fechaTarea;
    public static ArrayList<String>prioTarea;
    public static ArrayList<String>CosteTarea;
    public static ArrayList<String>RealizadaTarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_tareas);
        btnTodas = findViewById(R.id.btnTodas);
        btnHechas = findViewById(R.id.btnHechas);
        btnPendientes = findViewById(R.id.btnPendientes);
        LisTviewTareas = findViewById(R.id.LisTviewTareas);
        LisTviewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Aqui lo que hacemos es coger info de donde ha echo click el usuario
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VerTarea();
                VerTareaActivity.nombreStrVer=nomTarea.get(position);
                VerTareaActivity.descStrVer=descTarea.get(position);
                VerTareaActivity.FechaStrVer=fechaTarea.get(position);
                VerTareaActivity.prioStrVer=prioTarea.get(position);
                VerTareaActivity.CosteStrVer=CosteTarea.get(position);
            }
        });
        registerForContextMenu(LisTviewTareas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CargarTareas();
    }

    public void CargarTareas (){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = null;
        if (numBoton==1){
            LisTviewTareas.setAdapter(null);
            fila = bd.rawQuery("select * from TareasAgenda where usuario='" + MainActivity.usuarioGuar + "'", null);

        }else if(numBoton==2){
            LisTviewTareas.setAdapter(null);
            fila = bd.rawQuery("select * from TareasAgenda where ( usuario='"+MainActivity.usuarioGuar+"'and realizada='false')", null);
        }else if(numBoton==3){
            LisTviewTareas.setAdapter(null);
            fila = bd.rawQuery("select * from TareasAgenda where ( usuario='"+MainActivity.usuarioGuar+"'and realizada='true')", null);
        }else{
            LisTviewTareas.setAdapter(null);
            fila = bd.rawQuery("select * from TareasAgenda where usuario='" + MainActivity.usuarioGuar + "'", null);
        }
        if (fila.getCount()>0) {
            Codtareas = new ArrayList<>();
            usuarioTarea= new ArrayList<>();
            nomTarea =new ArrayList<>();
            descTarea =new ArrayList<>();
            fechaTarea =new ArrayList<>();
            prioTarea =new ArrayList<>();
            CosteTarea =new ArrayList<>();
            RealizadaTarea =new ArrayList<>();
            fila.moveToFirst();
            do {
                Codtareas.add(fila.getString(0));
                usuarioTarea.add(fila.getString(1));
                nomTarea.add(fila.getString(2));
                descTarea.add(fila.getString(3));
                fechaTarea.add(fila.getString(4));
                CosteTarea.add(fila.getString(5));
                prioTarea.add(fila.getString(6));
                RealizadaTarea.add(fila.getString(7));
            } while (fila.moveToNext());
            bd.close();
            admin.close();
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomTarea);
            LisTviewTareas.setAdapter(stringArrayAdapter);
        }

        //--------Hago un return de un array para guardarlo y acceder en toda la clase
    }
    public void BotonTodas(View view){
        numBoton=1;
        btnTodas.setBackgroundColor(getResources().getColor(R.color.selected));
        btnHechas.setBackgroundColor(getResources().getColor(R.color.selected2));
        btnPendientes.setBackgroundColor(getResources().getColor(R.color.selected3));
        CargarTareas();
    }
    public void BotonPendientes(View view){
        numBoton=2;
        btnTodas.setBackgroundColor(getResources().getColor(R.color.selected1));
        btnHechas.setBackgroundColor(getResources().getColor(R.color.selected2));
        btnPendientes.setBackgroundColor(getResources().getColor(R.color.selected));
        CargarTareas();
    }
    public void BotonRealizadas(View view){
        numBoton=3;
        btnTodas.setBackgroundColor(getResources().getColor(R.color.selected1));
        btnHechas.setBackgroundColor(getResources().getColor(R.color.selected));
        btnPendientes.setBackgroundColor(getResources().getColor(R.color.selected3));
        CargarTareas();
    }
    public void VerTarea(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent verPantalla = new Intent(this,VerTareaActivity.class);
        startActivity(verPantalla);

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Con esto recogemos en que sitio ha mantenido pulsado el usuario
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicionTarea = info.position;
        int codTarea = 0;

        for (int i = 0; i < Codtareas.size(); i++) {
            if (posicionTarea == i) {
                codTarea = Integer.parseInt(Codtareas.get(posicionTarea));
            }
        }
            switch (item.getItemId()) {
                case R.id.Menu_modifTarea:
                    ModifiTarea = true;
                    CrearTareaPantalla();
                    PantallaCrearActivity.nombreStr = nomTarea.get(posicionTarea);
                    PantallaCrearActivity.descStr = descTarea.get(posicionTarea);
                    PantallaCrearActivity.FechaStr = fechaTarea.get(posicionTarea);
                    PantallaCrearActivity.CosteStr = CosteTarea.get(posicionTarea);
                    PantallaCrearActivity.realizadaStr = RealizadaTarea.get(posicionTarea);
                    PantallaCrearActivity.prioStr= prioTarea.get(posicionTarea);
                    break;
                case R.id.Menu_eliminarTarea:
                    EliminarTarea(codTarea);
                    break;
            }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(R.string.QueDeseHacer);
        MenuInflater oInflater = getMenuInflater();
        oInflater.inflate(R.menu.menu_pulsacion, menu);
    }

    @Override
    public void onRespuesta(String psRespuesta) {
        Toast.makeText( this, psRespuesta, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_agenda, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.CrearTarea) {
            MenuPrincipalActivity.CrearTarea=true;
            CrearTareaPantalla();
        }else if (id==R.id.CambiarContra) {
            CambiarContraPantalla();
        }
        else if (id==R.id.AcercaDe) {
            mostrarDialogo();
        }

        return super.onOptionsItemSelected(item);
    }

    //-----------Metodos para cargar las pantallas
    public void mostrarDialogo() {
        DialagoAcercaDe oDialagoAcercaDe;
        oDialagoAcercaDe = new DialagoAcercaDe();
        oDialagoAcercaDe.show(getSupportFragmentManager(), "Mi Diálogo");
    }

    public void CrearTareaPantalla (){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);

    }
    public void CambiarContraPantalla (){
        Intent CambiarContra = new Intent(this,Pantalla_registrar.class);
        startActivity(CambiarContra);
        MainActivity.mostrarContraAnti=true;

    }
    public void EliminarTarea(int codTarea){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            int cant = bd.delete("TareasAgenda", "cod='"+codTarea+"' and usuario ='"+MainActivity.usuarioGuar+"'", null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, R.string.SeBorro, Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, R.string.Noborrar, Toast.LENGTH_SHORT).show();
                finish();
            }
        }

}