package ec.edu.espe.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import grupo3.participacionciudadanaseleccion.R;

public class MainActivity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ciudadano(View view) {
        i = new Intent(this, Ciudadano.class);
        startActivity(i);
    }
    public void tciudadano(View view) {
         i = new Intent(this, TCiudadano.class);
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

}
