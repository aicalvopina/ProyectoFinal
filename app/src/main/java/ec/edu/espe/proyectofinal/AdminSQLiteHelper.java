package ec.edu.espe.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adrián on 20/2/2018.
 */

public class AdminSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate1 = "CREATE TABLE tipo_ciudadano (" +
            "cod_tciudadano NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo_ciudadano TEXT)";

    String sqlCreate2 = "CREATE TABLE ciudadano (" +
            "codigo_ciudadano NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ci TEXT, " +
            "cod_tciudadano INTEGER, " +
            "nombres TEXT," +
            "apellidos TEXT, " +
            "nacionalidad TEXT," +
            "FOREIGN KEY(cod_tciudadano) REFERENCES tipo_ciudadano(cod_tciudadano) )";

    String sqlCreate3=  "CREATE TABLE tipo_competencia (" +
            "cod_tcompetencia NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo_competencia TEXT)";

    String sqlCreate4 = "CREATE TABLE competencia (" +
            "codigo NOT NULL INTEGER PRIMARY KEY, " +
            "cod_tcompetencia  INTEGER," +
            "tipo_competencia INTEGER, " +
            "titulo TEXT, " +
            "FOREIGN KEY(cod_tciudadano) REFERENCES tipo_competencia(cod_tcompetencia) )";

    String sqlCreate5 = "CREATE TABLE tipo_organizacion (" +
            "codigo_torganizacion NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tipo_organizacion TEXT)";

    String sqlCreate6 = "CREATE TABLE organizacion (" +
            "codigo_organizacion NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT," +
            "RUC  TEXT," +
            "cod_torganizacion INTEGER," +
            "FOREIGN KEY(cod_torganizacion) REFERENCES tipo_organizacion(cod_torganizacion))";

    String sqlCreate7 = "CREATE TABLE inscripcionCNE (" +
            "codigo NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT," +
            "organizacion INTEGER," +
            "representante INTEGER," +
            "veedor INTEGER," +
            "periodo INTEGER," +
            "fecha TEXT," +
            "FOREIGN KEY(organizacion) REFERENCES organizacion(cod_organizacion)," +
            "FOREIGN KEY(representante) REFERENCES ciudadano(ciudadano)," +
            "FOREIGN KEY(veedor) REFERENCES ciudadano(ciudadano)," +
            "FOREIGN KEY(periodo) REFERENCES periodo(codigo_periodo))";

    String sqlCreate8 = "CREATE TABLE periodo (" +
            "codigo_periodo NOT NULL INTEGER PRIMARY KEY AUTOINCREMENT," +
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