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

public class TCiudadano extends AppCompatActivity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tciudadano);
        et1=(EditText)findViewById(R.id.etTipoCiudadano);
    }

    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("tipo_ciudadano", tipo);
        bd.insert("tipo_ciudadano", null, registro);
        bd.close();
        et1.setText("");
        Toast.makeText(this, "Se cargaron los datos del ciudadano",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo_ciudadano = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select tipo_ciudadano from tipo_ciudadano where tipo_ciudadano =" + tipo_ciudadano, null);
        if (fila.moveToFirst()) {

        } else
            Toast.makeText(this, "No existe un ciudadano con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo= et1.getText().toString();
        int cant = bd.delete("tipo_ciudadano", "tipo_ciudadano=" + tipo, null);
        bd.close();
        et1.setText("");
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
        String tipo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("tipo_ciudadano", tipo);
        int cant = bd.update("tipo_ciudadano", registro, "tipo_ciudadano=" + tipo, null);
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
