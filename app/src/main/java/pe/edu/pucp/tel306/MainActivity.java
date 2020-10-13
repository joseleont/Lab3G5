package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
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

                break;
            case R.id.resetear:

                break;
        }
        return super.onContextItemSelected(item);
    }



}// FIN DEL MAIN