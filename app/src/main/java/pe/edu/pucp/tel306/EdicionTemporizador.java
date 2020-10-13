package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EdicionTemporizador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_temporizador);

        setTitle("Edición del temporizador");
    }


//METODO DEL BOTON
    public void enviarTemporizadorEditado(View v){

        int validacionDeErrores=0;

        EditText numeroCiclos=findViewById(R.id.textCiclos); //NUMERO DE CICLOS DEL POMODOROS
        int numeroCiclosInt=0;

        //VALIDACION DE LOS NUMERO DE CICLOS
        if (numeroCiclos.getText().toString().isEmpty()){
            numeroCiclos.setError("Debe ingresar un numero de ciclos");
            validacionDeErrores=1;

        } else{

            try {
                numeroCiclosInt=Integer.parseInt(numeroCiclos.getText().toString());
                if (numeroCiclosInt<=0){
                    numeroCiclos.setError("El numero debe ser mayor de cero");
                    validacionDeErrores=1;
                }
            }catch (NumberFormatException ex){
                numeroCiclos.setError("El numero ingresado es incorrecto");
            }
        }
       ///////////////////////////////////////////////////////// VALIDACION DEL NUMEOR DE CICLOS

        //***************************************************************************************
        EditText trabajoMinutos=findViewById(R.id.trabajoMinutos); //Minutos del trabajo
        int trabajoMinutosInt=0;

        //VALIDACION DE LOS MINUTOS DEL TRABAJO
        if (trabajoMinutos.getText().toString().trim().equalsIgnoreCase("")){
            trabajoMinutos.setError("Debe ingresar los minutos");
            validacionDeErrores=1;


        } else{
            try {
                trabajoMinutosInt=Integer.parseInt(trabajoMinutos.getText().toString());
                if ((trabajoMinutosInt>=61)||(trabajoMinutosInt<0)){
                    trabajoMinutos.setError("El numero debe ser mayor a cero y menor a 61");
                    validacionDeErrores=1;
                    Log.d("msg","Se");
                }
            }catch (NumberFormatException ex){
                trabajoMinutos.setError("El numero ingresado es incorrecto");
            }

        }
        ///////////////////////////////////////////////////////// VALIDACION DEL MINUTOS DEL TRABAJO



        //***************************************************************************************
        EditText trabajoSegundos=findViewById(R.id.trabajoSegundos); //Segundos del trabajo
        int trabajoSegundosInt=0;
        //VALIDACION DE LOS Segundos DEL TRABAJO
        if (trabajoSegundos.getText().toString().trim().equalsIgnoreCase("")){
            trabajoSegundos.setError("Debe ingresar los segundos");
            validacionDeErrores=1;


        } else{
            try {
                trabajoSegundosInt=Integer.parseInt(trabajoSegundos.getText().toString());
                if ((trabajoSegundosInt>=61)||(trabajoSegundosInt<0)){

                    trabajoSegundos.setError("El numero debe ser mayor a cero y menor a 61");
                    validacionDeErrores=1;

                }
            }catch (NumberFormatException ex){
                trabajoSegundos.setError("El numero ingresado es incorrecto");
            }


            }

        ///////////////////////////////////////////////////////// VALIDACION DEL Segundo DEL TRABAJO

        //***************************************************************************************
        EditText descansoMinutos=findViewById(R.id.descansoMinutos); //Minutos del descanso
        int descansoMinutosInt=0;
        //VALIDACION DE LOS MINUTOS DEL descanso
        if (descansoMinutos.getText().toString().trim().equalsIgnoreCase("")){
            descansoMinutos.setError("Debe ingresar los minutos");
            validacionDeErrores=1;


        } else{

            try {
                descansoMinutosInt=Integer.parseInt(descansoMinutos.getText().toString());
                if ((descansoMinutosInt>=61)||(descansoMinutosInt<0)){
                    descansoMinutos.setError("El numero debe ser mayor a cero y menor a 61");
                    validacionDeErrores=1;

                }
            }catch (NumberFormatException ex){
                descansoMinutos.setError("El numero ingresado es incorrecto");
            }

        }
        ///////////////////////////////////////////////////////// VALIDACION DEL MINUTOS DEL descanso


        //***************************************************************************************
        EditText descansoSegundos=findViewById(R.id.descansoSegundos); //Segundos del descanso
        int descansoSegundosInt=0;
        //VALIDACION DE LOS Segundos DEL descanso
        if (descansoSegundos.getText().toString().equalsIgnoreCase("")){
            descansoSegundos.setError("Debe ingresar los segundos");
            validacionDeErrores=1;

        } else{

            try {
                descansoSegundosInt=Integer.parseInt(descansoSegundos.getText().toString());
                if ((descansoSegundosInt>=61)||(descansoSegundosInt<0)){
                    descansoSegundos.setError("El numero debe ser mayor a cero y menor a 61");
                    validacionDeErrores=1;

                }
            }catch (NumberFormatException ex){
                descansoSegundos.setError("El numero ingresado es incorrecto");
            }
        }
        ///////////////////////////////////////////////////////// VALIDACION DEL Segundo DEL TRABAJO



          // SI NO HAY ERRORES SE ENVIA LA INFORMACION
        if (validacionDeErrores==0){

            Intent intent= new Intent();
            intent.putExtra("ciclos",numeroCiclosInt);
            intent.putExtra("trabajoMinutos",trabajoMinutosInt);
            intent.putExtra("trabajoSegundos",trabajoSegundosInt);
            intent.putExtra("descansoMinutos",descansoMinutosInt);
            intent.putExtra("descansoSegundos",descansoMinutosInt);

            setResult(RESULT_OK,intent);
             finish();

        }
/*
        int infoActivity = data.getIntExtra("ciclos");

        int infoActivity = data.getIntExtra("trabajoMinutos");
        int infoActivity = data.getIntExtra("trabajoSegundos");

        int infoActivity = data.getIntExtra("descansoMinutos");
        int infoActivity = data.getIntExtra("descansoSegundos");

*/

    }



}