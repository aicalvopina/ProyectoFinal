package ec.edu.espe.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCiudadano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCiudadano = (Button)findViewById(R.id.button7);
        btnCiudadano.setOnClickListener(this);
    }

    public void ciudadano(View view) {
        Intent i = new Intent(MainActivity.this, Ciudadano.class);
        startActivity(i);
    }
    public void tciudadano(View view) {
        Intent  i = new Intent(this, TCiudadano.class);
        startActivity(i);
    }
    public void competencia(View view)
    {
        Intent i = new Intent(this,Competencia.class);
        startActivity(i);
    }
    public void tcompetencia(View view)
    {
        Intent i = new Intent(this,TCompetencia.class);
        startActivity(i);
    }
    public void inscripcion(View view)
    {
        Intent i = new Intent(this,InscripcionCNE.class);
        startActivity(i);
    }
    public void organizacion(View view)
    {
        Intent i = new Intent(this,Organizacion.class);
        startActivity(i);
    }

    public void torganizacion(View view)
    {
        Intent i = new Intent(this,TOrganizacion.class);
        startActivity(i);
    }

    public void periodo(View view)
    {
        Intent i = new Intent(this,Periodo.class);
        startActivity(i);
    }
    public void explorador(View view) {
        Intent i = new Intent(this, Explorador.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        ciudadano(v);
    }
}
