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

public class InscripcionCNE extends AppCompatActivity {
    private EditText et1,et2,et3,et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incripcion_cne);

       /et1=(EditText)findViewById(R.id.etOrganizacion);
        et2=(EditText)findViewById(R.id.etRepresentante);
        et3=(EditText)findViewById(R.id.etVeedor);
        et4=(EditText)findViewById(R.id.etPeriodo);
        et5=(EditText)findViewById(R.id.etFecha);*/
    }

    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String organizacion = et1.getText().toString();
        String representante = et2.getText().toString();
        String veedor = et3.getText().toString();
        String periodo = et4.getText().toString();
        String fecha = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("organizacion", organizacion);
        registro.put("cod_tcompetencia", representante);
        registro.put("veedor", veedor);
        registro.put("periodo", periodo);
        registro.put("fecha", fecha);
        bd.insert("inscripcionCNE", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        Toast.makeText(this, "Se cargaron los datos del inscripcionCNE",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String veedor = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select organizacion,representante,veedor,periodo,fecha from inscripcionCNE where veedor=" + veedor, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No existe un inscripcionCNE con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String veedor= et1.getText().toString();
        int cant = bd.delete("inscripcionCNE", "veedor=" + veedor, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró inscripcionCNE con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe inscripcionCNE con dicho código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String organizacion = et1.getText().toString();
        String representante = et2.getText().toString();
        String veedor = et3.getText().toString();
        String periodo = et4.getText().toString();
        String fecha = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("organizacion", organizacion);
        registro.put("cod_tcompetencia", representante);
        registro.put("veedor", veedor);
        registro.put("periodo", periodo);
        registro.put("fecha", fecha);
        int cant = bd.update("inscripcionCNE", registro, "veedor=" + veedor, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe inscripcionCNE con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }

    public void regresar(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
