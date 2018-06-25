package yiwo.uploadimages;

/**
 * Created by JhanKalos on 20/04/2018.
 */

public class TablaProductos {
    public String Table_Name = "img_articulos";
    public String Table_Historial = "Historial_Productos";
    public String Field_0 = "ID";
    public String Field_1 = "cod_empresa";
    public String Field_2 = "cod_articulo";
    public String Field_3 = "img_articulo";
    public String Field_4 = "concepto1";

    public String[] getHeaders1() {
        String[] gridViewHeaders;
        gridViewHeaders = new String[]{
                Field_1,
                Field_2
                };
        return gridViewHeaders;
    }
    public String[] getHeaders2() {
        String[] gridViewHeaders;
        gridViewHeaders = new String[]{
                Field_0,
                Field_1,
                Field_2,
                Field_3,
                Field_4};
        return gridViewHeaders;
    }
}