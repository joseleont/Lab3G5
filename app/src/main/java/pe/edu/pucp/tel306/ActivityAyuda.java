package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityAyuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        setTitle("Ayuda");

        String[] lista = {"Sobre Pomodoro","Cómo usar Pomodoro","Plataformas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,lista);
        Spinner spinner = findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = findViewById(R.id.textoAyuda);

                if (i == 0) {
                    textView.setText(R.string.spinner1);
                }
                else if (i == 1) {
                    textView.setText(R.string.spinner2);
                }
                else if (i == 2) {
                    textView.setText(R.string.spinner3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                TextView textView = findViewById(R.id.textoAyuda);
                textView.setText("Elija una opcion");
            }
        });

    }
}