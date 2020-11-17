package com.example.agendapersonal_asiergoienetxea;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class DialagoAcercaDe extends DialogFragment {
    AlertDialog.Builder oBuilder;
    AcercaDeInterface oRespuesta;
    boolean bElegido=false;

    //---- Creamos el Dialog con sus opciones.
    @NonNull
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        oBuilder = new AlertDialog.Builder(getActivity());
        //---- Poner título y mensaje al Dialog.
        oBuilder.setTitle(R.string.AcercaDe);
        oBuilder.setMessage(R.string.TextAcercaDe);

        oBuilder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                oRespuesta.onRespuesta(getResources().getString(R.string.Gracias));
            } // onClick()
        });
        oBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                oRespuesta.onRespuesta(getResources().getString(R.string.No_pasa_nada));
            } // onClick()
        });
        return oBuilder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        oRespuesta = (AcercaDeInterface) context;
    }


}
