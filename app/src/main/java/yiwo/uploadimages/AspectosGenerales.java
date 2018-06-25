package yiwo.uploadimages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;


/**
 * Created by JhanKalos on 24/04/2018.
 */

public class AspectosGenerales {
    static ArrayList<List<String>> myArray;
    public static Boolean reiniciar=true;
    public static Boolean landscape=false;
    public static Boolean activar_navigation=true;

    public static String[] gridViewHeaders;
    public static ArrayList<List<String>> getTabla;

    public static Boolean RellenarGridView(Context context, TableView tableView, ArrayList<List<String>> myArray, String[] headers) {

        String[] gridViewHeaders = headers;
        tableView.setHeaderBackgroundColor(Color.parseColor("#ff96bb"));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context, gridViewHeaders));

        Log.d("AspectosGenerales ", "RellenarGridView: " );
        tableView.setColumnCount(gridViewHeaders.length);
        tableView.setBackgroundColor(Color.parseColor("#848484"));
        try {
            Log.d("AspectosGenerales ", "RellenarGridView: " );
            int length = myArray.size();
            int ancho = myArray.get(0).size();
            String[][] lista_productos = new String[length][ancho];
            for (int i = 0; i < length; i++) {
                Log.d("AspectosGenerales ", "RellenarGridView: " +i);
                for (int j = 0; j < ancho; j++) {
                    Log.d("AspectosGenerales ", "RellenarGridView: " +j);
                    lista_productos[i][j] = myArray.get(i).get(j);
                }
            }
            SimpleTableDataAdapter simpleTableDataAdapter = new SimpleTableDataAdapter(context, lista_productos);
            tableView.setDataAdapter(simpleTableDataAdapter);
            return true;
        } catch (Exception ignored) {
            Log.d("AspectosGenerales ", "RellenarGridView: " + ignored.getMessage());
            return false;
        }
    }

    public static void CabeceraGridView(Context context, TableView tableView, String[] headers) {

        String[] gridViewHeaders=headers;
        tableView.setHeaderBackgroundColor(Color.parseColor("#ff96bb"));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context, gridViewHeaders));
        tableView.setColumnCount(gridViewHeaders.length);
        tableView.setBackgroundColor(Color.parseColor("#848484"));
    }

    public static void RellenarSpinnerEstado(Context context, Spinner spinner) {
        try {
            List<String> values = new ArrayList();
            values.add("Estado");
            values.add("Activo");
            values.add("Anulado");

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, values){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0)
                    {
                        // Disable the second item from Spinner
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position==0) {
                        // Set the disable item text color
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

        } catch (Exception ignored) {

        }
    }

    public static void RellenarSpinnerTipoUsuario(Context context, Spinner spinner) {
        try {
            List<String> values = new ArrayList();
            values.add("Tipo de Usuario");
            values.add("Administrador");
            values.add("Vendedor");
            values.add("Despacho");
            values.add("Almacen");

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, values){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0)
                    {
                        // Disable the second item from Spinner
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position==0) {
                        // Set the disable item text color
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

        } catch (Exception ignored) {

        }
    }

    public  static void RellenarSpinnerTipoRecibo(Context context, Spinner spinner) {
        try {
            List<String> values = new ArrayList();
            values.add("Tipo de Recibo");
            values.add("Ticket");
            values.add("Boleta");
            values.add("Factura");

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, values){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0)
                    {
                        // Disable the second item from Spinner
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position==0) {
                        // Set the disable item text color
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

        } catch (Exception ignored) {

        }

    }

    public static void RellenarSpinnerCantidad(Context context, Spinner spinner) {
        try {
            List<String> values = new ArrayList();

            for (int i = 1; i < 100; i++) {
                values.add(String.valueOf(i));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, values);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

        } catch (Exception ignored) {

        }
    }

    public static void RellenarSpinerFruta(Context context, ArrayList<List<String>> arrayList, Spinner spinner){
        try {
            List<String> values = new ArrayList();
            arrayList.size();
            for (int i = 0; i < arrayList.size(); i++) {
                values.add(arrayList.get(i).get(1));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, values);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

        } catch (Exception ignored) {

        }
    }

    public static String get_Fecha_Hoy() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy ");
        return formatter.format(date);
    }

    public static ArrayList<List<String>> getMyArray() {
        return myArray;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Drawable resizeImage(Activity activity, int imageResource) {
        // R.drawable.icon
        // Get device dimensions
        Display display = activity.getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(activity.getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(activity.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    public static String Redondear2Decimales(Double d){
        return  String.format("%.2f", d);
    }
}
