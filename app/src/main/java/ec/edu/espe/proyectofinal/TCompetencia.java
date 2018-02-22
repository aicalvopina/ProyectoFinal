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

public class TCompetencia extends AppCompatActivity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcompetencia);
        et1=(EditText)findViewById(R.id.etTipoCompetencia);
    }

    public void alta(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("tipo_competencia", tipo);
        bd.insert("tipo_competencia", null, registro);
        bd.close();
        et1.setText("");
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "TipoCompetencia.txt", Activity.MODE_APPEND));
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
        String tipo = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select tipo_competencia from tipo_competencia where tipo_competencia =" + tipo, null);
        if (fila.moveToFirst()) {

        } else
            Toast.makeText(this, "No existe ",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo= et1.getText().toString();
        int cant = bd.delete("tipo_competencia", "tipo_competencia=" + tipo, null);
        bd.close();
        et1.setText("");
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
        String tipo = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("tipo_competencia", tipo);
        int cant = bd.update("tipo_competencia", registro, "tipo_competencia=" + tipo, null);
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
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
