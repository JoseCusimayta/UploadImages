package yiwo.uploadimages;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Seleccionar.FinalizoCuadroDialogo {

    ProductosData datos = new ProductosData();
    TablaProductos tabla = new TablaProductos();
    String Codigo;
    EditText et_codigo, et_categoria;
    Button b_nuevo, b_modificar, b_eliminar, b_guardar, b_cancelar, b_historial, b_lista, b_buscar, b_camara;
    ImageView iv_imagen;
    String Modo = "Guardar";
    String ImagenDecode;

    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int cameraData = 0;
    ProgressBar progressBar;
    String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = findViewById(R.id.et_codigo);

        et_categoria = findViewById(R.id.et_categoria);
        iv_imagen = findViewById(R.id.iv_imagen);

        progressBar = findViewById(R.id.progressBar);

        b_nuevo = findViewById(R.id.b_nuevo);
        b_modificar = findViewById(R.id.b_modificar);
        b_eliminar = findViewById(R.id.b_eliminar);
        b_guardar = findViewById(R.id.b_guardar);
        b_cancelar = findViewById(R.id.b_cancelar);
        b_historial = findViewById(R.id.b_historial);
        b_lista = findViewById(R.id.b_lista);
        b_camara = findViewById(R.id.b_camara);
        b_buscar = findViewById(R.id.b_buscar);

        b_nuevo.setOnClickListener(this);
        b_modificar.setOnClickListener(this);
        b_eliminar.setOnClickListener(this);
        b_guardar.setOnClickListener(this);
        b_cancelar.setOnClickListener(this);
        b_historial.setOnClickListener(this);
        b_lista.setOnClickListener(this);
        b_camara.setOnClickListener(this);
        b_buscar.setOnClickListener(this);

        progressBar.setVisibility(View.INVISIBLE);

        Cancelar();
    }
    public void Nuevo() {
        /*
        b_lista.setEnabled(false);
        b_historial.setEnabled(false);

        b_nuevo.setEnabled(false);
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
        b_guardar.setEnabled(true);
        b_cancelar.setEnabled(true);
        b_buscar.setEnabled(true);
        b_camara.setEnabled(true);

        et_codigo.setEnabled(false);
        et_categoria.setEnabled(true);


        et_codigo.requestFocus();*/
        et_codigo.setText(datos.GetNuevoCodigo());
    }

    public void Cancelar() {
      /*  b_lista.setEnabled(true);
        b_historial.setEnabled(true);

        b_nuevo.setEnabled(true);
        b_modificar.setEnabled(true);
        b_eliminar.setEnabled(false);
        b_guardar.setEnabled(false);
        b_cancelar.setEnabled(false);
        b_buscar.setEnabled(false);
        b_camara.setEnabled(false);

        et_codigo.setEnabled(false);
        et_categoria.setEnabled(false);*/

    }

    public void Limpiar() {
      /*  try {
            Codigo = null;

            et_codigo.setText(null);
            et_categoria.setText(null);

            iv_imagen.setImageBitmap(null);
        } catch (Exception e) {

        }*/
    }

    @Override
    public void ResultadoCuadroDialogo(String Codigo) {
        this.Codigo = Codigo;
        et_codigo.setText(Codigo);

        DescargarInformacion descargarInformacion = new DescargarInformacion();
        descargarInformacion.execute("");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == this.RESULT_OK && null != data) {
            Bitmap originBimap = null;
            Uri selectedImage = data.getData();
            InputStream imageStream;
            try {
                imageStream = this.getContentResolver().openInputStream(selectedImage);
                originBimap = BitmapFactory.decodeStream(imageStream);

            } catch (FileNotFoundException e) {
                Log.d("onActivityResult", e.getMessage());
            }
            if (originBimap != null) {
                originBimap = AspectosGenerales.getResizedBitmap(originBimap, 300);
                iv_imagen.setImageBitmap(originBimap);
                try {
                    Bitmap image = ((BitmapDrawable) iv_imagen.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    // UploadImage uploadImage = new UploadImage();
                    // uploadImage.execute("");
                } catch (Exception e) {
                    Log.w("onActivityResult", e.getMessage());
                    Toast.makeText(this, "Imagen no encontrada", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.w("onActivityResult", "Error");
            }
        }


        if (requestCode == cameraData && resultCode == this.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            bmp = AspectosGenerales.getResizedBitmap(bmp, 300);
            iv_imagen.setImageBitmap(bmp);
            Bitmap image = ((BitmapDrawable) iv_imagen.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.b_nuevo):
                Modo = "Guardar";
                Limpiar();
                Nuevo();
                break;
            case (R.id.b_modificar):
                Modo = "Modificar";
                AspectosGenerales.gridViewHeaders = tabla.getHeaders1();
                AspectosGenerales.getTabla = datos.getTabla();
                new Seleccionar(this, this);
                break;
            case (R.id.b_eliminar):
                new AlertDialog.Builder(this).setTitle("Eliminar").setMessage("¿Está seguro de realizar la operación?")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (datos.Eliminar(et_codigo.getText().toString())) {
                                    Toast.makeText(MainActivity.this, "Operación realizada con éxito", Toast.LENGTH_SHORT).show();
                                    Cancelar();
                                } else {
                                    Toast.makeText(MainActivity.this, "Problemas al intentar realizar la operación", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();
                break;
            case (R.id.b_guardar):
                new AlertDialog.Builder(this).setTitle("Aviso").setMessage("¿Está seguro de realizar la operación?")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SubirInformacion subirInformacion = new SubirInformacion();
                                subirInformacion.execute("");
                            }
                        }).create().show();
                break;
            case (R.id.b_cancelar):
                Cancelar();
                Limpiar();
                break;
            case (R.id.b_historial):
                break;
            case (R.id.b_lista):
                AspectosGenerales.reiniciar = false;
                AspectosGenerales.landscape = true;
                AspectosGenerales.gridViewHeaders = tabla.getHeaders1();
                AspectosGenerales.getTabla = datos.getTabla();

                break;
            case (R.id.b_buscar):
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case (R.id.b_camara):
                checkCameraPermission();
                break;
        }

    }


    public class SubirInformacion extends AsyncTask<String, String, String> {
        String Mensaje;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Iniciando operación....", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                if (datos.Insertar(et_codigo.getText().toString(), et_categoria.getText().toString(), encodedImage)) {
                    Mensaje = "Operación realizada con éxito";
                } else {
                    Mensaje = "Problemas al intentar realizar la operación";
                }
            } catch (Exception e) {
                Mensaje = "Problemas al intentar realizar la operación";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.INVISIBLE);
            Cancelar();
            Toast.makeText(MainActivity.this, Mensaje, Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
    }

    public class DescargarInformacion extends AsyncTask<String, String, String> {
        String Mensaje;
        ArrayList<List<String>> myArray;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Iniciando operación....", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                myArray = datos.getDato(Codigo);
                ImagenDecode = myArray.get(0).get(3);
            } catch (Exception e) {
                Log.d("ResultadoCuadroDialogo", e.getMessage());
                Mensaje = "No se ha podido extraer la información";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                et_codigo.setText(myArray.get(0).get(2));
                et_categoria.setText(myArray.get(0).get(4));

                Nuevo();

                Modo = "Modificar";
                b_eliminar.setEnabled(true);
                et_codigo.setEnabled(false);

                byte[] decodeString = Base64.decode(ImagenDecode, Base64.DEFAULT);
                Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                iv_imagen.setImageBitmap(decodebitmap);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, Mensaje, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
            super.onPostExecute(s);
        }
    }

    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para la camara!.");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("Mensaje", "Tienes permiso para usar la camara.");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, cameraData);
        }
    }
}
