package yiwo.uploadimages;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JhanKalos on 20/04/2018.
 */

public class ConexionBD {
    Connection connection = null;
    String Driver = "net.sourceforge.jtds.jdbc.Driver";
    String IPServer = "192.168.1.111:1433/SQLSERVER2008R2";
    String databaseName = "Bd_Consultoria_2015";
    String user = "sa";
    String password = "Solu123456";
    String ConectarBD = "jdbc:jtds:sqlserver://" + IPServer + ";databaseName=" + databaseName + ";user=" + user + ";password=" + password;

    public Boolean OpenConection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(ConectarBD);
            return true;
        } catch (ClassNotFoundException e) {
            Log.d("ConexionBD", "ClassNotFoundException: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            Log.d("ConexionBD", "SQLException: " + e.getMessage());
            return false;
        }
    }

    public void CloseConection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<List<String>> getTabla(String[] Columnas, String[] Tablas, String[] Seleccion, String[] SeleccionArgs, String[] Agrupar, String[] Ordenar) {
        String sql = "select ";
        try {
            if (OpenConection()) {
                //region Select * from
                if (Columnas != null) {
                    for (int i = 0; i < Columnas.length; i++) {
                        sql += Columnas[i];
                        if (i < Columnas.length - 1)
                            sql += " , ";
                    }
                } else
                    sql += " * ";
                //endregion

                //region Select * from Table
                if (Tablas != null) {
                    sql += " from ";
                    for (int i = 0; i < Tablas.length; i++) {
                        sql += Tablas[i];
                        if (i < Tablas.length - 1)
                            sql += " , ";
                    }
                } else
                    return null;
                //endregion

                //region Select * from Table where Seleccion = SeleccionArgs
                if (Seleccion != null) {
                    sql += " where ";
                    for (int i = 0; i < Seleccion.length; i++) {
                        sql += Seleccion[i] + "  '" + SeleccionArgs[i] + "'";
                        if (i < Seleccion.length - 1)
                            sql += " and ";
                    }
                }
                //endregion


                //region Select * from Table where Seleccion = SeleccionArgs group by
                if (Agrupar != null) {
                    sql += " group by ";
                    for (int i = 0; i < Agrupar.length; i++) {
                        sql += Agrupar[i];
                        if (i < Agrupar.length - 1)
                            sql += " , ";
                    }
                }
                //endregion

                //region Select * from Table where Seleccion = SeleccionArgs group by Columna order by
                if (Ordenar != null) {
                    sql += " order by ";
                    for (int i = 0; i < Ordenar.length; i++) {
                        sql += Ordenar[i];
                        sql += Ordenar[i];
                        if (i < Ordenar.length - 1)
                            sql += " , ";
                    }
                }
                //endregion


                Statement statement = connection.createStatement();
                ResultSet c = statement.executeQuery(sql);
                ArrayList<List<String>> myArray = new ArrayList<>();

                /*
                while (c.next()) {
                        myArray.add(Arrays.asList(
                                c.getString(1),
                                c.getString(2),
                                c.getString(3),
                                c.getString(4)));
                    }
                Log.d("ConexionBD","getTabla: "+myArray);
                */

                while (c.next()) {
                    List<String> myList = new ArrayList<>();
                    for (int i = 0; i < Columnas.length; i++) {
                        myList.add(c.getString(i + 1));
                    }
                    myArray.add(myList);
                    //Log.d("ConexionBD","getTabla: "+myArray);
                }

                Log.d("SQL", sql);
                c.close();
                CloseConection();
                return myArray;
            } else {
                Log.d("SQL", sql);
                return null;
            }
        } catch (Exception e) {
            Log.d("ConexionBD", sql);
            Log.d("ConexionBD", "getTabla: " + e.getMessage());
            return null;
        }
    }

    public ResultSet getTablaHistorial(String[] Columnas, String[] Tablas, String[] Seleccion, String[] SeleccionArgs, String[] Agrupar, String[] Ordenar) {
        String sql = "select ";
        try {
            if (OpenConection()) {
                //region Select * from
                if (Columnas != null) {
                    for (int i = 0; i < Columnas.length; i++) {
                        sql += Columnas[i];
                        if (i < Columnas.length - 1)
                            sql += " , ";
                    }
                } else
                    sql += " * ";
                //endregion

                //region Select * from Table
                if (Tablas != null) {
                    sql += " from ";
                    for (int i = 0; i < Tablas.length; i++) {
                        sql += Tablas[i];
                        if (i < Tablas.length - 1)
                            sql += " , ";
                    }
                } else
                    return null;
                //endregion

                //region Select * from Table where Seleccion = SeleccionArgs
                if (Seleccion != null) {
                    sql += " where ";
                    for (int i = 0; i < Seleccion.length; i++) {
                        sql += Seleccion[i] + "  '" + SeleccionArgs[i] + "'";
                        if (i < Seleccion.length - 1)
                            sql += " and ";
                    }
                }
                //endregion


                //region Select * from Table where Seleccion = SeleccionArgs group by
                if (Agrupar != null) {
                    sql += " group by ";
                    for (int i = 0; i < Agrupar.length; i++) {
                        sql += Agrupar[i];
                        if (i < Agrupar.length - 1)
                            sql += " , ";
                    }
                }
                //endregion

                //region Select * from Table where Seleccion = SeleccionArgs group by Columna order by
                if (Ordenar != null) {
                    sql += " order by ";
                    for (int i = 0; i < Ordenar.length; i++) {
                        sql += Ordenar[i];
                        sql += Ordenar[i];
                        if (i < Ordenar.length - 1)
                            sql += " , ";
                    }
                }
                //endregion


                Statement statement = connection.createStatement();
                ResultSet c = statement.executeQuery(sql);
                return c;
            } else {
                Log.d("SQL", sql);
                return null;
            }
        } catch (Exception e) {
            Log.d("SQL", sql);
            Log.d("ConexionBD", e.getMessage());
            return null;
        }
    }

    public String NuevoCodigo(String LetraCodigo, String tableName) {
        Integer Orden = 0;
        try {
            if (OpenConection()) {
                ResultSet resultSet;
                String command = "select top (1) ID from " + tableName + " order by ID desc";
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(command);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Orden = resultSet.getInt("ID");
                    }
                }
            }
        } catch (Exception e) {
            Log.d("NuevoCodigo", e.getMessage());
        }
        CloseConection();
        return LetraCodigo + String.format("%05d", Orden + 1);
    }

    public String NuevoRecibo(String Tipo_Recibo, String tableName) {
        Integer Orden = 0;
        String LetraCodigo = Tipo_Recibo.substring(0, 1);
        try {
            if (OpenConection()) {
                ResultSet resultSet;
                String command = "select count (*)  as Cantidad from " + tableName + " where Tipo_Recibo='" + Tipo_Recibo + "'";
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(command);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Orden = resultSet.getInt("Cantidad");
                    }
                }
            }
        } catch (Exception e) {
            Log.d("NuevoCodigo", e.getMessage());
        }
        CloseConection();
        return LetraCodigo + String.format("%05d", Orden + 1);
    }

    public Boolean EjecutarProcedure(String Procedure, String[] values) {
        try {
            if (OpenConection()) {
                String sql = "EXEC " + Procedure;
                if (values.length > 0) {
                    for (int i = 0; i < values.length; i++) {
                        sql += " '" + values[i] + "'";
                        if (i + 1 < values.length)
                            sql += ",";
                    }
                }
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                CloseConection();
                return true;
            }
        } catch (Exception e) {
            Log.d("EjecutarProcedure", e.getMessage());
        }
        return false;
    }


    public static Connection getConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        //192.168.1.111:1433/SQLSERVER2008R2
        //String ip = "192.168.1.111\\SQLSERVER2008R2:1433";
        String ip = "192.168.1.111:1433/SQLSERVER2008R2";
        //String ip = "148.102.21.175:1433/SQLSERVER2008R2";
        String classs = "net.sourceforge.jtds.jdbc.Driver";
        String db = "Bd_Consultoria_2015";
        String un = "sa";
        String password = "Solu123456";
        try {
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERROR SQLException", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR ClassNotFound", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR Exception", e.getMessage());
        }
        return connection;
    }
}