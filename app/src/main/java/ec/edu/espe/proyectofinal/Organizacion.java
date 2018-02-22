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

public class Organizacion extends AppCompatActivity {
    private EditText et1,et2,et3,txtCodOrg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizacion);

        txtCodOrg = (EditText)findViewById(R.id.txtCodOrg);
        et1=(EditText)findViewById(R.id.txtNombreOrg);
        et2=(EditText)findViewById(R.id.txtRUC);
        et3=(EditText)findViewById(R.id.txtCodTipoOrg);
    }


    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString();
        String RUC = et2.getText().toString();
        String cod_torganizacion = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("RUC", RUC);
        registro.put("cod_torganizacion", cod_torganizacion);
        bd.insert("organizacion", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "organizacion.txt", Activity.MODE_APPEND));
            archivo.append("nombre:"+nombre);
            archivo.write("\n");
            archivo.append("RUC:"+RUC);
            archivo.write("\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast.makeText(this, "Se cargaron los datos del organizacion",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String RUC = et2.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,cod_torganizacion from organizacion where RUC=" + RUC, null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et3.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe un organizacion con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String RUC = et2.getText().toString();
        int cant = bd.delete("organizacion", "RUC=" + RUC, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró la organizacion con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una organizacion con dicho código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString();
        String RUC = et2.getText().toString();
        String cod_torganizacion = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("RUC", RUC);
        registro.put("cod_torganizacion", cod_torganizacion);
        int cant = bd.update("organizacion", registro, "RUC=" + RUC, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe una organizacion con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
    public void regresar(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
