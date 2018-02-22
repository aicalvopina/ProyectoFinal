package ec.edu.espe.proyectofinal;

import android.app.ListActivity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Explorador extends AppCompatActivity   {
    EditText txtMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorador);
        txtMulti = (EditText) findViewById(R.id.txtArchivos);


    }

    public void recuperar(View view){
        String[] archivos = fileList();

        if (existe(archivos, "ciudadanos.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("ciudadanos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo +""+linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txtMulti.setText(txtMulti.getText()+todo);
            } catch (IOException e) {
            }
        if (existe(archivos, "organizacion.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("organizacion.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo +""+linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txtMulti.setText(txtMulti.getText()+todo);
            } catch (IOException e) {
            }
        if (existe(archivos, "competencia.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("competencia.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo +""+linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txtMulti.setText(txtMulti.getText()+todo);
            } catch (IOException e) {
            }
        if (existe(archivos, "inscripcion.txt"))
        try {
            InputStreamReader archivo = new InputStreamReader(
                    openFileInput("inscripcion.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo +""+linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            txtMulti.setText(txtMulti.getText()+todo);
        } catch (IOException e) {
        }
    }
    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
}
