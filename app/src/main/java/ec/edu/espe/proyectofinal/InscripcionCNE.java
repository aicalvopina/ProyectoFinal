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

public class InscripcionCNE extends AppCompatActivity {
    private EditText et1,et2,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incripcion_cne);

        et1=(EditText)findViewById(R.id.txtCodigoIncripcion);
        et2=(EditText)findViewById(R.id.txtCodCompetencia);
        et5=(EditText)findViewById(R.id.etTitulo);
    }

    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String competencia = et2.getText().toString();
        String titulo = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("competencia", competencia);
        registro.put("titulo", titulo);
        bd.insert("inscripcionCNE", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et5.setText("");
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "incripcion.txt", Activity.MODE_APPEND));
            archivo.append("titulo:"+titulo);
            archivo.write("\n");
            archivo.append("competencia:"+competencia);
            archivo.write("\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
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
        String competencia = et2.getText().toString();
        String titulo = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("competencia", competencia);
        registro.put("titulo", titulo);
        int cant = bd.update("inscripcionCNE", registro, "codigo_inscripcion=" + et1.getText().toString(), null);
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
