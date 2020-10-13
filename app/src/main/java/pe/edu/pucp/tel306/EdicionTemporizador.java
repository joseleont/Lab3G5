package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EdicionTemporizador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_temporizador);
    }


//METODO DEL BOTON
    public void enviarTemporizadorEditado(View v){

        int validacionDeErrores=0;

        EditText numeroCiclos=findViewById(R.id.textCiclos); //NUMERO DE CICLOS DEL POMODOROS


        //VALIDACION DE LOS NUMERO DE CICLOS
        if (numeroCiclos.getText().toString().trim().equalsIgnoreCase("")){
            numeroCiclos.setError("Debe ingresar un numero de ciclos");
            validacionDeErrores=1;

        } else{
            int numeroCiclosInt=Integer.parseInt(numeroCiclos.getText().toString());
            if (numeroCiclosInt<=0){
                numeroCiclos.setError("El numero debe ser mayor de cero");
                validacionDeErrores=1;

            }
        }
       ///////////////////////////////////////////////////////// VALIDACION DEL NUMEOR DE CICLOS

        //***************************************************************************************
        EditText trabajoMinutos=findViewById(R.id.trabajoMinutos); //Minutos del trabajo

        //VALIDACION DE LOS MINUTOS DEL TRABAJO
        if (trabajoMinutos.getText().toString().trim().equalsIgnoreCase("")){
            trabajoMinutos.setError("Debe ingresar los minutos");
            validacionDeErrores=1;

        } else{
            int numeroCiclosInt=Integer.parseInt(numeroCiclos.getText().toString());
            if ((numeroCiclosInt>=61)&&(numeroCiclosInt<0)){
                numeroCiclos.setError("El numero debe ser mayor a cero y menor a 61");
                validacionDeErrores=1;

            }
        }
        ///////////////////////////////////////////////////////// VALIDACION DEL MINUTOS DEL TRABAJO



        //***************************************************************************************
        EditText trabajoSegundos=findViewById(R.id.trabajoSegundos); //Segundos del trabajo
        //VALIDACION DE LOS Segundos DEL TRABAJO
        if (trabajoSegundos.getText().toString().trim().equalsIgnoreCase("")){
            trabajoSegundos.setError("Debe ingresar los segundos");
            validacionDeErrores=1;

        } else{
            int trabajoSegundosInt=Integer.parseInt(trabajoSegundos.getText().toString());
            if ((trabajoSegundosInt>=61)&&(trabajoSegundosInt<0)){
                trabajoSegundos.setError("El numero debe ser mayor a cero y menor a 61");
                validacionDeErrores=1;

            }
        }
        ///////////////////////////////////////////////////////// VALIDACION DEL Segundo DEL TRABAJO

        //***************************************************************************************
        EditText descansoMinutos=findViewById(R.id.descansoMinutos); //Minutos del descanso

        //VALIDACION DE LOS MINUTOS DEL descanso
        if (descansoMinutos.getText().toString().trim().equalsIgnoreCase("")){
            descansoMinutos.setError("Debe ingresar los minutos");
            validacionDeErrores=1;

        } else{
            int descansoMinutosInt=Integer.parseInt(descansoMinutos.getText().toString());
            if ((descansoMinutosInt>=61)&&(descansoMinutosInt<0)){
                numeroCiclos.setError("El numero debe ser mayor a cero y menor a 61");
                validacionDeErrores=1;

            }
        }
        ///////////////////////////////////////////////////////// VALIDACION DEL MINUTOS DEL descanso


        //***************************************************************************************
        EditText descansoSegundos=findViewById(R.id.descansoSegundos); //Segundos del descanso
        //VALIDACION DE LOS Segundos DEL descanso
        if (descansoSegundos.getText().toString().trim().equalsIgnoreCase("")){
            descansoSegundos.setError("Debe ingresar los segundos");
            validacionDeErrores=1;

        } else{
            int descansoSegundosInt=Integer.parseInt(trabajoSegundos.getText().toString());
            if ((descansoSegundosInt>=61)&&(descansoSegundosInt<0)){
                trabajoSegundos.setError("El numero debe ser mayor a cero y menor a 61");
                validacionDeErrores=1;

            }
        }
        ///////////////////////////////////////////////////////// VALIDACION DEL Segundo DEL TRABAJO



          // SI NO HAY ERRORES SE ENVIA LA INFORMACION
        if (validacionDeErrores==0){

             /*
            Intent intent= new Intent();
            intent.putExtra("d");
            setResult(RESULT_OK,intent);
             finish();*/

        }

    }



}