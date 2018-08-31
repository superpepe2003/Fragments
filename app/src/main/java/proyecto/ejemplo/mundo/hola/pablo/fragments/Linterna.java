package proyecto.ejemplo.mundo.hola.pablo.fragments;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Linterna extends Fragment {

    private ImageView botonCamara;
    private boolean encendida;

    public Linterna() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View fragmento = inflater.inflate(R.layout.fragment_linterna, container, false);

        botonCamara= (ImageView) fragmento.findViewById(R.id.linterna);

        botonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(encendida)
                {
                    encendida=false;
                    botonApagaFlash();
                }
                else
                {
                    encendida=true;
                    botonEnciendeFlash();
                }

            }
        });

        return fragmento;
    }

    public void botonEnciendeFlash()
    {
        botonCamara.setImageResource(R.drawable.linterna2);

        Activity estaActividad = getActivity();
        ((IManejaFlashCamara)estaActividad).enciendeApaga(encendida);

    }

    public void botonApagaFlash()
    {
        botonCamara.setImageResource(R.drawable.linterna);

        Activity estaActividad = getActivity();
        ((IManejaFlashCamara)estaActividad).enciendeApaga(encendida);
    }

}
