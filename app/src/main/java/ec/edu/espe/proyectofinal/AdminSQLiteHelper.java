package ec.edu.espe.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adrián on 20/2/2018.
 */

public class AdminSQLiteHelper extends SQLiteOpenHelper {



    String sqlCreate1 = "CREATE TABLE tipo_ciudadano (" +
            "cod_tciudadano INTEGER PRIMARY KEY, " +
            "tipo_ciudadano TEXT)";

    String sqlCreate2 = "CREATE TABLE ciudadano (" +
            "codigo_ciudadano INTEGER PRIMARY KEY ," +
            "ci TEXT, " +
            "cod_tciudadano INTEGER, " +
            "nombres TEXT," +
            "apellidos TEXT, " +
            "nacionalidad TEXT," +
            "FOREIGN KEY(cod_tciudadano) REFERENCES tipo_ciudadano(cod_tciudadano) )";

    String sqlCreate3=  "CREATE TABLE tipo_competencia (" +
            "cod_tcompetencia INTEGER PRIMARY KEY , " +
            "tipo_competencia TEXT)";

    String sqlCreate4 = "CREATE TABLE competencia (" +
            "codigo INTEGER PRIMARY KEY, " +
            "cod_tcompetencia  INTEGER," +
            "titulo TEXT, " +
            "FOREIGN KEY(cod_tciudadano) REFERENCES tipo_competencia(cod_tcompetencia) )";

    String sqlCreate5 = "CREATE TABLE tipo_organizacion (" +
            "codigo_torganizacion INTEGER PRIMARY KEY ," +
            "tipo_organizacion TEXT)";

    String sqlCreate6 = "CREATE TABLE organizacion (" +
            "codigo_organizacion INTEGER PRIMARY KEY ," +
            "nombre TEXT," +
            "RUC  TEXT," +
            "cod_torganizacion INTEGER," +
            "FOREIGN KEY(cod_torganizacion) REFERENCES tipo_organizacion(cod_torganizacion))";

    String sqlCreate7 = "CREATE TABLE inscripcionCNE (" +
            "codigo_inscripcion INTEGER PRIMARY KEY ," +
            "competencia INTEGER," +
            "titulo INTEGER," +
            "FOREIGN KEY(competencia) REFERENCES competencia(cod_competencia))";

    String sqlCreate8 = "CREATE TABLE periodo (" +
            "codigo_periodo INTEGER PRIMARY KEY ," +
            "fechas TEXT)";

    public AdminSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate1);
        sqLiteDatabase.execSQL(sqlCreate2);
        sqLiteDatabase.execSQL(sqlCreate3);
        sqLiteDatabase.execSQL(sqlCreate4);
        sqLiteDatabase.execSQL(sqlCreate5);
        sqLiteDatabase.execSQL(sqlCreate6);
        sqLiteDatabase.execSQL(sqlCreate8);
        sqLiteDatabase.execSQL(sqlCreate7);

    }

    public Cursor cursor(){
        String[] columnas={"codigo_inscripcion","competencia","titulo"};

        Cursor c=this.getReadableDatabase().query("inscripcionCNE", columnas, null, null, null, null, null);
        return c;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Se elimina la versión anterior de la tabla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de laS tablaS
        sqLiteDatabase.execSQL(sqlCreate1);
        sqLiteDatabase.execSQL(sqlCreate2);
        sqLiteDatabase.execSQL(sqlCreate3);
        sqLiteDatabase.execSQL(sqlCreate4);
        sqLiteDatabase.execSQL(sqlCreate5);
        sqLiteDatabase.execSQL(sqlCreate6);
        sqLiteDatabase.execSQL(sqlCreate8);
        sqLiteDatabase.execSQL(sqlCreate7);
    }
}
