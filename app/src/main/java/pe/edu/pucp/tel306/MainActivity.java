package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REGISTRO DEL MENU EDITAR-RESETEAR
        registerForContextMenu(findViewById(R.id.tiempo));
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