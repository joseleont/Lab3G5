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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edicionTime;
    private TextView  temporizador;
    private TextView  tiempoDescanso;
    private Button mBotonEditar;
    private ImageView imagen;
    private CountDownTimer mCountDownTimer;// Clase de contador en reverso
    private boolean mtime;
    private static final long tiempo_empieza = 1500000; // Iniciador
    private static final long descanso = 300000;// Iniciador
    private long tiempito=descanso;
    private long mTiempoRestante=tiempo_empieza;
    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//====================================================
        edicionTime = findViewById(R.id.trabajoMinutos);// se llama el EditText para la edici[on del contador
        tiempoDescanso= findViewById(R.id.tiempoDescanso);// se llama el EditText para el contador de tiempo de descanso

        mBotonEditar = findViewById(R.id.button);
        //REGISTRO DEL MENU EDITAR-RESETEAR
        registerForContextMenu(findViewById(R.id.tiempo));

        //frases
         temporizador = findViewById(R.id.tiempo);
        String temp2 = temporizador.getText().toString();
        int temp = Integer.parseInt(temp2);
        contadorTiempoFrases(temp);


    }
    public void descansoTime(){
        int minutos = (int) ((descanso / 1000) % 3600) / 60;// Converte a minutos
        int segundos = (int) (descanso / 1000) % 60;//Convierte a segundos
        String forma = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);// se da el formato a MM:ss
        tiempoDescanso.setText(forma);// se set en el EditText
    }

    public void contadorTiempoFrases(final int temp){

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        Thread.sleep(25000);

                        String frase1 = "Siempre parece imposible hasta que se hace (Nelson Mandela)";
                        String frase2 = "La motivación es lo que te pone en marcha, el hábito es lo que hace que sigas (Jim Ryun)";
                        String frase3 = "Estudia el pasado si quieres intuir el futuro (Confucio)";
                        if (temp<1) {
                            int numero = (int) (Math.random() * 3 + 1);
                            String frasealeAtoria = "frase" + String.valueOf(numero);
                            TextView frase = findViewById(R.id.frasesApoyo);
                            frase.setText(frasealeAtoria);
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
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
// Se usa para pausar o iniciar el temporizador
    public void StartPause(View view){
        if(mtime){
            Pausa();
        } else{
            tiempoStart();
        }
    }
    // Logica para que empiece el temporizador
    public void tiempoStart(){

      mCountDownTimer=new CountDownTimer(mTiempoRestante, 1000) { // Toma como parametros el timpempo de inicio y la duracion de cambio (que es 1segundo)
          @Override
          public void onTick(long millisUntilFinished) {
              mTiempoRestante=millisUntilFinished;
              contadorTexto();// el valor se le manda para expresarlo en el formato MM:ss
          }
          @Override
          public void onFinish() {// Cuando finalice se muestra los botones condicionados
                mtime=false;// el tiempo no esta avanzando
                Botones();
          }
      }.start();
      mtime=true;
      contadorTexto();
    }

    private void contadorTexto() {// Para el temporizador principal

        int minutos = (int) ((mTiempoRestante / 1000) % 3600) / 60;// Converte a minutos
        int segundos = (int) (mTiempoRestante / 1000) % 60;// Converte a segundos
        String formatoNuevo = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);// se da el formato
        temporizador.setText(formatoNuevo);// Se setea en el EditText
    }

    private void Pausa(){
        mCountDownTimer.cancel();// Al contador se le detiene
        mtime=false;// El tiempo no esta avanzndo
        contadorTexto();
    }
    private void Botones(){
if (mtime){
     imagen=findViewById(R.id.playPausa);
    imagen.setImageResource(R.drawable.ic_action_pausa);// la imagen se le cambia al pausa
}else{
    if(mTiempoRestante<1000){// el boton de play es visible cuando termina el temporizador
        imagen.setVisibility(View.VISIBLE);
    }else {
        imagen.setVisibility(View.INVISIBLE);
    }
}
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