/*****************************************************************************

 *****************************************************************************/
package com.pfm.iremoteudp;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.TextView;
import java.util.*;
import android.util.*;
import android.preference.*;

public class Configuracion_ir extends AppCompatActivity
{


	/** Called when the activity is first created. */

	
	Button botonanadir,botoneliminareleccion,botoneliminartodos;
	ArrayList<String> botonesrecibidos;
	Tabla tabla;
	//ArrayList<String> elementos;
	ArrayAdapter<String> adapter;
	ArrayAdapter <String>adapter2;
	Spinner s,s2;


	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
			Toast.makeText(getApplicationContext(), "Guardando", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		
		// TODO: Implement this method
		super.onPause();
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);        



        setContentView(R.layout.configuracion_ir);

		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		//texto para ingresar la ip y filtro para validar el ingreso
		

		
		botonesrecibidos = new ArrayList<String>();
		if (getIntent() != null && getIntent().hasExtra("botonespasados"))
		{
			botonesrecibidos  = getIntent().getStringArrayListExtra("botonespasados");
		}
		s = (Spinner) findViewById(R.id.spinnerboton);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, botonesrecibidos);
		s.setAdapter(adapter);

		s2 = (Spinner) findViewById(R.id.spinnercomado);
		String[] usersList=getResources().getStringArray(R.array.comandos_control);

		List<String> data = new ArrayList<String>();
		for (String item : usersList)
		{
			data.add(item);

		}

		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

		s2.setAdapter(adapter2);


		TableLayout tablita=(TableLayout)findViewById(R.id.tablabotones);

		tabla = new Tabla(this, (tablita));
		tabla.agregarCabecera(R.array.cabecera_tabla);

		//adapter2.remove((CharSequence)s2.getItemAtPosition(s2.getSelectedItemPosition()).toString());
		adapter2.notifyDataSetChanged();


		//elementos.add(s.getItemAtPosition(s.getSelectedItemPosition()).toString());
		//elementos.add(s2.getItemAtPosition(s2.getSelectedItemPosition()).toString());
		//   elementos.add("Casilla [" + i + ", 1]");
		//  elementos.add("Casilla [" + i + ", 2]");
		//  elementos.add("Casilla [" + i + ", 3]");
		//tabla.agregarFilaTabla(elementos);


		/*	for(int i = 0; i < 29; i++)
		 {
		 ArrayList<String> elementos = new ArrayList<String>();

		 elementos.add(Integer.toString(i));
		 elementos.add("Casilla [" + i + ", 0]");
         //   elementos.add("Casilla [" + i + ", 1]");
		 //  elementos.add("Casilla [" + i + ", 2]");
		 //  elementos.add("Casilla [" + i + ", 3]");
		 tabla.agregarFilaTabla(elementos);
		 }*/


		botonanadir = (Button)findViewById(R.id.anadir);
		botoneliminareleccion = (Button)findViewById(R.id.eliminar_seleccion);
		botoneliminartodos = (Button)findViewById(R.id.eliminar_todo);
		botonanadir.setOnClickListener(listenerbotones);
		botoneliminareleccion.setOnClickListener(listenerbotones);
		botoneliminartodos.setOnClickListener(listenerbotones);



		//ipaccesspoint.setText( getIntent().getExtras().getString("parametro"));

		
		
			
		
		
		


		//private void setupFloatingLabelError() {

		




	}
	

	private View.OnClickListener listenerbotones = new View.OnClickListener() {
        public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.anadir:
				ArrayList<String>	elementos = new ArrayList<String>();
					//   elementos.add("Casilla [" + i + ", 1]");
					//  elementos.add("Casilla [" + i + ", 2]");
					//  elementos.add("Casilla [" + i + ", 3]");

					if ((adapter.getCount() != 0) & (adapter2.getCount() != 0))
					{
						elementos.add(s.getItemAtPosition(s.getSelectedItemPosition()).toString());	
						elementos.add(s2.getItemAtPosition(s2.getSelectedItemPosition()).toString());
						tabla.agregarFilaTabla(elementos);
						adapter.remove(s.getItemAtPosition(s.getSelectedItemPosition()).toString());
						adapter.notifyDataSetChanged();

						adapter2.remove(s2.getItemAtPosition(s2.getSelectedItemPosition()).toString());
						adapter2.notifyDataSetChanged();

					}
					break;
				case R.id.eliminar_seleccion:
					String [] eliminado=tabla.eliminarFilaretornaelementos(1);
					if (eliminado[0] != "" & eliminado[1] != "")
					{
						Log.e("filaeliminada[1]" , eliminado[0]);
						Log.e("filaeliminada[2]" , eliminado[1]);
						adapter.insert(eliminado[0],0);
						adapter2.insert(eliminado[1],0); 
						adapter.notifyDataSetChanged();
						adapter2.notifyDataSetChanged();
					}
					break;
				case R.id.eliminar_todo:
					tabla.eliminarTabla();
					break;

				default:
					break;
			}
		}
	};




}
