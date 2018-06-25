package yiwo.uploadimages;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;


/**
 * Created by JhanKalos on 21/05/2018.
 */

public class Seleccionar  implements TableDataClickListener<String[]> {
    TableView tableView;
    String Codigo;
    EditText et_codigo, et_Descripcion;
    @Override
    public void onDataClicked(int rowIndex, String[] clickedData) {
        Codigo = clickedData[1];
        et_codigo.setText(Codigo);
        et_Descripcion.setText(clickedData[0]);

    }
    public interface FinalizoCuadroDialogo {
        void ResultadoCuadroDialogo(String Codigo);
    }

    private FinalizoCuadroDialogo interfaz;
    public Seleccionar(final Context context, final FinalizoCuadroDialogo actividad) {

        interfaz = actividad;
        final Dialog dialogo = new Dialog(context);
        //dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.seleccionar);
        dialogo.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tableView = dialogo.findViewById(R.id.tableView);
        et_codigo = dialogo.findViewById(R.id.et_codigo);
        et_Descripcion = dialogo.findViewById(R.id.et_Descripcion);
        Button b_aceptar = dialogo.findViewById(R.id.b_aceptar);
        Button b_cancelar = dialogo.findViewById(R.id.b_cancelar);
        tableView.addDataClickListener(this);
        b_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaz.ResultadoCuadroDialogo(et_codigo.getText().toString());
                dialogo.dismiss();
            }
        });
        Log.d("asdd","dsd");
        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        AspectosGenerales.RellenarGridView(context, tableView, AspectosGenerales.getTabla, AspectosGenerales.gridViewHeaders);
        tableView.requestFocus();
        Log.d("tt","ttt");
        dialogo.show();
    }

}
