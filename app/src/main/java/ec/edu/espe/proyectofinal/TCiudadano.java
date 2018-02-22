package ec.edu.espe.proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TCiudadano extends AppCompatActivity implements View.OnClickListener {
    private EditText txtCodigo;
    private EditText txtTipoCiudadano;
    AdminSQLiteHelper admin;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tciudadano);
        txtCodigo=(EditText)findViewById(R.id.txtCodTipoCiudadano1);
        txtTipoCiudadano = (EditText)findViewById(R.id.txtTipoCiudadano1);
        Button btnAlta = (Button)findViewById(R.id.button21);
        btnAlta.setOnClickListener(this);

    }

    public void alta(View v) {
        admin = new AdminSQLiteHelper(this,"Usuarios", null, 1);
        bd = admin.getWritableDatabase();
        String tipo = txtTipoCiudadano.getText().toString();
        int codigo = Integer.parseInt(txtCodigo.getText().toString());
        /*ContentValues registro = new ContentValues();
        registro.put("tipo_ciudadano", tipo);
       bd.insert("tipo_ciudadano", null, registro);*/
        bd.execSQL("INSERT INTO tipo_ciudadano (cod_tciudadano,tipo_ciudadano ) VALUES ("+ codigo +",'"+txtTipoCiudadano.getText().toString()+"')");
        bd.close();
        txtTipoCiudadano.setText("");
        txtCodigo.setText("");
        /*try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "TipoCiudadano.txt", Activity.MODE_APPEND));
            archivo.append("Tipo:"+tipo);
            archivo.write("\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }*/
        Toast.makeText(this, "Se cargaron los datos del ciudadano",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo_ciudadano = txtCodigo.getText().toString();
        Cursor fila = bd.rawQuery(
                "select cod_tciudadano,tipo_ciudadano from tipo_ciudadano where cod_tciudadano =" + Integer.parseInt(tipo_ciudadano), null);
        if (fila.moveToFirst()) {
            txtCodigo.setText(fila.getString(0));
            txtTipoCiudadano.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un ciudadano con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo= txtCodigo.getText().toString();
        int cant = bd.delete("tipo_ciudadano", "tipo_ciudadano=" + tipo, null);
        bd.close();
        txtCodigo.setText("");
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
        String tipo = txtCodigo.getText().toString();
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

    @Override
    public void onClick(View v) {
        alta(v);
    }
}
