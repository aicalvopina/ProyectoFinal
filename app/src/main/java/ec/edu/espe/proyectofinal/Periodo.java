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

public class Periodo extends AppCompatActivity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo);
        et1=(EditText)findViewById(R.id.etFechas);
    }
    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String fecha = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("fechas", fecha);
        bd.insert("periodo", null, registro);
        bd.close();
        et1.setText("");
        Toast.makeText(this, "Se cargaron los datos",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String fecha = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select fechas from periodo where fechas =" + fecha, null);
        if (fila.moveToFirst()) {

        } else
            Toast.makeText(this, "No existe con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String fecha= et1.getText().toString();
        int cant = bd.delete("periodo", "fechas=" + fecha, null);
        bd.close();
        et1.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró  con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe  con dicho código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String fecha = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("fechas", fecha);
        int cant = bd.update("periodo", registro, "fechas=" + fecha, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
    public void regresar(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
