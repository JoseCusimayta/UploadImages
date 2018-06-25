package yiwo.uploadimages;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by JhanKalos on 21/05/2018.
 */

public class ProductosBD {

    ConexionBD conexion = new ConexionBD();
    TablaProductos tabla = new TablaProductos();

    public Boolean Insertar(String Codigo, String Categoria, String Imagen) {
        Connection connection;
        try {
            String stsql;
            stsql = "insert into img_articulos (cod_empresa,cod_articulo, img_articulo,concepto1) values (?,?,?,?)";
            connection = ConexionBD.getConnection();
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, "01");
            query.setString(2, Codigo);
            query.setString(3, Imagen);
            query.setString(4, Categoria);
            query.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            Log.d("ProductosBD", "- Insertar: " + e.getMessage());
        }
        return false;
    }


    public static ArrayList<List<String>> getLista() {
        ArrayList<List<String>> Conceptos = new ArrayList<>();
        Connection connection = null;

        try {
            connection= ConexionBD.getConnection();

            String stsql = "select cod_empresa, cod_articulo from img_articulos";

            PreparedStatement query = connection.prepareStatement(stsql);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Conceptos.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListConceptos", e.getMessage());
        }
        return Conceptos;
    }




    public Boolean Eliminar(String Codigo) {
        try {
            conexion.EjecutarProcedure("ELiminarProductos", new String[]{Codigo});
            return true;
        } catch (Exception e) {
            Log.d("ProductosBD", "Eliminar: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<List<String>> getTabla(String[] Seleccion, String[] SeleccionArgs, String[] Agrupar, String[] Ordenar) {
        try {
            return conexion.getTabla(tabla.getHeaders1(), new String[]{tabla.Table_Name}, Seleccion, SeleccionArgs, Agrupar, Ordenar);
        } catch (Exception e) {
            Log.d("ProductosBD", "getTabla: " + e.getMessage());
            return null;
        }
    }
    public ArrayList<List<String>> getTabla() {
        try {
            return conexion.getTabla(tabla.getHeaders2(), new String[]{tabla.Table_Name}, null, null, null, null);
        } catch (Exception e) {
            Log.d("ProductosBD", "getTabla: " + e.getMessage());
            return null;
        }
    }


    public ArrayList<List<String>> getDato(String Codigo) {
        try {
            return conexion.getTabla(tabla.getHeaders2(), new String[]{tabla.Table_Name}, new String[]{ tabla.Field_2+"="},  new String[]{ Codigo}, null, null);
        } catch (Exception e) {
            Log.d("ProductosBD", "getTabla: " + e.getMessage());
            return null;
        }
    }
    public String GetNuevoCodigo(){
        return conexion.NuevoCodigo("CP",tabla.Table_Name);
    }
}
