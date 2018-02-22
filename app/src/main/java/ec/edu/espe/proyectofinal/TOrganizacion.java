package ec.edu.espe.proyectofinal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class TOrganizacion extends AppCompatActivity {
    private EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torganisacion);
        et1 = (EditText) findViewById(R.id.etCodigo);
        et2 = (EditText) findViewById(R.id.etTipo);

    }

    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo = et2.getText().toString();
        String codigo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo_torganizacion", Integer.parseInt(codigo));
        registro.put("tipo_organizacion", tipo);
        bd.insert("tipo_organizacion", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "TipoOrganizacion.txt", Activity.MODE_APPEND));
            archivo.append("Tipo:"+tipo);
            archivo.write("\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast.makeText(this, "Se cargaron los datos",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo_torganizacion,tipo_organizacion from tipo_organizacion where codigo_torganizacion =" + Integer.parseInt(codigo), null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe ",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        int cant = bd.delete("tipo_organizacion", "cod_torganizacion=" + Integer.parseInt(codigo), null);
        bd.close();
        et1.setText("");
        et2.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo = et2.getText().toString();
        String codigo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo_torganizacion", Integer.parseInt(codigo));
        registro.put("tipo_organizacion", tipo);
        int cant = bd.update("tipo_organizacion", registro, "cod_torganizacion=" + Integer.parseInt(codigo), null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe ",
                    Toast.LENGTH_SHORT).show();
    }

    public void regresar(View view)
    {

        finish();
    }
}
