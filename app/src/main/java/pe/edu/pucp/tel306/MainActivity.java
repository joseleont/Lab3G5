package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edicionTime;
    private TextView  temporizador;
    private Button mBotonEditar;
    private Button mBotonPausar;
    private Button mBotonreset;

    private CountDownTimer mCountDownTimer;

    private boolean mtime;

    private long mInicioMilis;
    private long mTiempoRestante;
    private long mFinTime;
    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REGISTRO DEL MENU EDITAR-RESETEAR
        registerForContextMenu(findViewById(R.id.tiempo));

        //frases
        TextView temp1 = findViewById(R.id.tiempo);
        String temp2 = temp1.getText().toString();
        int temp = Integer.parseInt(temp2);
        contadorTiempoFrases(temp);


    }

    public void contadorTiempoFrases(final int temp){

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (temp < 1){
                    try {
                        Thread.sleep(25000);

                        String frase1 = "Siempre parece imposible hasta que se hace (Nelson Mandela)";
                        String frase2 = "La motivación es lo que te pone en marcha, el hábito es lo que hace que sigas (Jim Ryun)";
                        String frase3 = "Estudia el pasado si quieres intuir el futuro (Confucio)";

                        int numero = (int)(Math.random()*3+1);
                        String frasealeAtoria = "frase"+String.valueOf(numero);
                        TextView frase = findViewById(R.id.frasesApoyo);
                        frase.setText(frasealeAtoria);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    //CREAR EL MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_edicion_resetear,menu);
    }
    //BOTONES DEL MENU

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.editar:

                //ABRIR ACTIVITY
                Intent intent = new Intent(MainActivity.this,EdicionTemporizador.class);
                int requestCode = 1;
                startActivityForResult(intent,requestCode);


                break;
            case R.id.resetear:

                break;
        }
        return super.onContextItemSelected(item);
    }

    //Frases de ayuda


    //INFO QUE VIENE DEL ACTIVITY EDIFIION TEMPORIZADOR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK){

           //String infoActivity = data.getStringExtra("nj");




        }

    }

    //MENU DE AYUDA
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ayuda,menu);
        return true;
    }

    public void clickHelp (MenuItem menu){
        Log.d("ayuda","se abre activity ayuda");
        Intent intent = new Intent(MainActivity.this, ActivityAyuda.class);
        startActivity(intent);
    }





}// FIN DEL MAIN