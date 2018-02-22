package ec.edu.espe.proyectofinal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_lista extends AppCompatActivity {
    private ListView tv;
    private ArrayList<Integer> codigos;
    private Cursor c;
    AdminSQLiteHelper datos1 = new AdminSQLiteHelper(this,
            "Usuarios", null, 1);


    private ArrayList<String> datodos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        tv = (ListView) findViewById(R.id.lista);
        mostrarlista();
    }

    public void mostrarlista() {

        ArrayList<String> lista = new ArrayList<String>();
        codigos = new ArrayList<>();

        datos1 = new AdminSQLiteHelper(this,
                "Usuarios", null, 1);
        c = datos1.cursor();
        if (c.moveToFirst()) {

            do {

                lista.add(c.getString(1));
                codigos.add(c.getInt(0));

            } while (c.moveToNext());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        tv.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }
}