package ec.edu.espe.proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ciudadano extends AppCompatActivity {
    private EditText et1,et2,et3,et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidadano);
        et1=(EditText)findViewById(R.id.etCodigo);
        et2=(EditText)findViewById(R.id.etTCompetencia);
        et3=(EditText)findViewById(R.id.etTitulo);
        et4=(EditText)findViewById(R.id.etTitulo);
        et5=(EditText)findViewById(R.id.etTitulo);
    }


    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci = et1.getText().toString();
        String cod_tciudadano = et2.getText().toString();
        String nombres = et3.getText().toString();
        String apellidos = et4.getText().toString();
        String nacionalidad = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("ci", ci);
        registro.put("cod_tcompetencia", cod_tciudadano);
        registro.put("nombres", nombres);
        registro.put("apellidos", apellidos);
        registro.put("nacionalidad", nacionalidad);
        bd.insert("ciudadano", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        Toast.makeText(this, "Se cargaron los datos del ciudadano",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select ci,cod_tciudadano,nombres,apellidos,nacionalidad from ciudadano where ci=" + ci, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No existe un ciudadano con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci= et1.getText().toString();
        int cant = bd.delete("ciudadano", "ci=" + ci, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró ciudadano con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe ciudadano con dicho código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci = et1.getText().toString();
        String cod_tciudadano = et2.getText().toString();
        String nombres = et3.getText().toString();
        String apellidos = et4.getText().toString();
        String nacionalidad = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("ci", ci);
        registro.put("cod_tcompetencia", cod_tciudadano);
        registro.put("nombres", nombres);
        registro.put("apellidos", apellidos);
        registro.put("nacionalidad", nacionalidad);
        int cant = bd.update("ciudadano", registro, "ci=" + ci, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe ciudadano con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
    public void regresar(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
