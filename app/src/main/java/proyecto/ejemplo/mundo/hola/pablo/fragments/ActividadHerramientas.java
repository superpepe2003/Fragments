package proyecto.ejemplo.mundo.hola.pablo.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ActividadHerramientas extends AppCompatActivity implements ComunicaMenu, IManejaFlashCamara {

    private Fragment[] misFragmentos;

    private CameraManager miCamara;

    private String idCamara;

    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos = new Fragment[3];

        misFragmentos[0] =  new Linterna();
        misFragmentos[1]= new Musica();
        misFragmentos[2]=new Nivel();


        Bundle extra=getIntent().getExtras();

        menu(extra.getInt("BOTONPULSADO"));

        miCamara = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            idCamara = miCamara.getCameraIdList()[0];
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void menu(int queboton) {
        FragmentManager miManejador= getFragmentManager();

        FragmentTransaction miTransaccion= miManejador.beginTransaction();

        Fragment menu_iluminado= new Menu();

        Bundle datos= new Bundle();

        datos.putInt("BOTONPRESIONADO", queboton);

        menu_iluminado.setArguments(datos);

        miTransaccion.replace(R.id.menu, menu_iluminado);

        miTransaccion.replace(R.id.herramientas, misFragmentos[queboton]);

        miTransaccion.commit();
    }

    @Override
    public void enciendeApaga(boolean estado) {
        try {
            if (estado) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                    miCamara.setTorchMode(idCamara, true);

                Toast.makeText(this, "Flash Prendido", Toast.LENGTH_SHORT).show();
            } else {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                    miCamara.setTorchMode(idCamara, false);

                Toast.makeText(this, "Flash Apagado", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
