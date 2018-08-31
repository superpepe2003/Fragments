package proyecto.ejemplo.mundo.hola.pablo.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {

    private final int[] BOTONESMENU = {R.id.linterna,R.id.musica, R.id.nivel};

    private final int[] BOTONESILUMINADOS = {R.drawable.linterna2, R.drawable.musica2, R.drawable.nivel2};

    private int boton;

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miMenu = inflater.inflate(R.layout.fragment_menu, container, false);
        boton = -1;

        if(getArguments()!=null)
            boton = getArguments().getInt("BOTONPRESIONADO");

        ImageButton botonmenu;

        for(int i=0;i<BOTONESMENU.length;i++)
        {
            botonmenu = (ImageButton) miMenu.findViewById(BOTONESMENU[i]);

            if(boton==i)
                botonmenu.setImageResource(BOTONESILUMINADOS[i]);

            final int queBoton=i;

            botonmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity estaActividad = getActivity();
                    ((ComunicaMenu)estaActividad).menu(queBoton);
                }
            });
        }

        return miMenu;
    }

}
