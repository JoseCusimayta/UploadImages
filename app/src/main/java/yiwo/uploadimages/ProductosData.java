package yiwo.uploadimages;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JhanKalos on 21/05/2018.
 */

public class ProductosData {
    ProductosBD entidad = new ProductosBD();

    public Boolean Insertar(String Codigo, String Categoria, String Imagen) {

            return entidad.Insertar(Codigo, Categoria,Imagen);

    }

    public Boolean Eliminar(String Codigo) {
        if (!Codigo.isEmpty()) {
            return entidad.Eliminar(Codigo);
        } else {
            return false;
        }
    }

    public ArrayList<List<String>> getTabla() {
        return entidad.getTabla(null, null, null, null);
    }
    public ArrayList<List<String>> getTabla2() {
        return entidad.getTabla();
    }


    public ArrayList<List<String>> getDato(String Codigo) {
        return entidad.getDato(Codigo);
    }
    public String GetNuevoCodigo(){
        return entidad.GetNuevoCodigo();
    }
}