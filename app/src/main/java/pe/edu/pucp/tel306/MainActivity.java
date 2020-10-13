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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

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
//====================================================
        edicionTime = findViewById(R.id.trabajoMinutos);
        temporizador = findViewById(R.id.tiempo);

        mBotonEditar = findViewById(R.id.button);
       // mBotonIniciarPausar = findViewById(R.id.playPausa);
        //mButtonReset = findViewById(R.id.button_reset);


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

        /*mBotonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editar=edicionTime.getText().toString();
                if(editar.length()==0){
                    Toast.makeText(MainActivity.this, "Tiene que agregar valor", Toast.LENGTH_SHORT).show();
                    return;
                }
                long miliTime = Long.parseLong(editar)*60000;
                if (miliTime == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
            setTime(miliTime);// puede causar ERROR
                edicionTime.setText("");
            }

        });*/


    }
    //BOTONES DEL MENU

    public void StartPause(View view){
        tiempoStart();

    }

    public void tiempoStart(){

      mFinTime=System.currentTimeMillis()+mTiempoRestante;
      mCountDownTimer=new CountDownTimer(mTiempoRestante, 1000) {
          @Override
          public void onTick(long millisUntilFinished) {
              mTiempoRestante=millisUntilFinished;
              contadorTexto();
          }

          @Override
          public void onFinish() {
                mtime=false;
                contadorTexto();
          }
      }.start();
      mtime=true;
      contadorTexto();
    }

    private void contadorTexto() {
        //int hours = (int) (mTiempoRestante / 1000) / 3600;
        int minutes = (int) ((mTiempoRestante / 1000) % 3600) / 60;
        int seconds = (int) (mTiempoRestante / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        temporizador.setText(timeLeftFormatted);

      //  String timeLeftFormatted;

    }


    //======================================================

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

    //INFO QUE VIENE DEL ACTIVITY EDIFIION TEMPORIZADOR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK){

            int ciclos = data.getIntExtra("ciclos",0);

            int trabajoMinutos = data.getIntExtra("trabajoMinutos",0);
            int trabajoSegundos = data.getIntExtra("trabajoSegundos",0);

            int descansoMinutos = data.getIntExtra("descansoMinutos",0);
            int descansoSegundos = data.getIntExtra("descansoSegundos",0);

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