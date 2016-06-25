package com.pfm.iremoteudp;



import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.*;
import android.widget.*;
import android.util.*;
import android.view.*;

/**
 * Created by francis on 14/10/14.
 * Esta clase representa una tabla
 */
public class Tabla
{
    // Variables de la clase

    private TableLayout tabla;          // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas;  // Array de las filas de la tabla
   private CheckBox seleccion;
	private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS,Idcheckboxcabecera;        // Filas y columnas de nuestra tabla

    /**
     * Constructor de la tabla
     * @param actividad Actividad donde va a estar la tabla
     * @param tabla TableLayout donde se pintará la tabla
     */
    public Tabla(Activity actividad, TableLayout tabla) 
    {
        this.actividad = actividad;
        this.tabla = tabla;
		rs = this.actividad.getResources();
        FILAS = COLUMNAS = 0;
        filas = new ArrayList<TableRow>();

	}

    /**
     * Añade la cabecera a la tabla
     * @param recursocabecera Recurso (array) donde se encuentra la cabecera de la tabla
     */
    public void agregarCabecera(int recursocabecera)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow fila = new TableRow(actividad);
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
         
		fila.setLayoutParams(layoutFila);
		
		String[] arraycabecera = rs.getStringArray(recursocabecera);
        COLUMNAS = arraycabecera.length;

		seleccion = new CheckBox(actividad);
        seleccion.setId(123);
		Idcheckboxcabecera = seleccion.getId();
		seleccion.setOnClickListener(mylistenerseleccion);
		fila.addView(seleccion);
		

		for (int i = 0; i < arraycabecera.length; i++)
        {
            TextView texto = new TextView(actividad);
			//  layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(arraycabecera[i]), TableRow.LayoutParams.WRAP_CONTENT);
			layoutCelda = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
			texto.setText(arraycabecera[i]);
            texto.setGravity(Gravity.CENTER_HORIZONTAL);
            texto.setTextAppearance(actividad, R.style.estilo_celda);
			texto.setBackgroundResource(R.drawable.tabla_celda_cabecera);
			texto.setLayoutParams(layoutCelda);

			fila.addView(texto);
        }

        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }

    /**
     * Agrega una fila a la tabla
     * @param elementos Elementos de la fila
     */
    public void agregarFilaTabla(ArrayList<String> elementos)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(actividad);
        fila.setLayoutParams(layoutFila);
		seleccion = new CheckBox(actividad);

		seleccion.setId(FILAS);
		seleccion.setOnClickListener(mylistenerseleccion);
		Log.e("tagcheck", Integer.toString(seleccion.getId()));
		fila.addView(seleccion);
        for (int i = 0; i < elementos.size(); i++)
        {
            TextView texto = new TextView(actividad);
            texto.setText(String.valueOf(elementos.get(i)));
            texto.setGravity(Gravity.CENTER_HORIZONTAL);
			texto.setTextAppearance(actividad, R.style.estilo_celda);
			texto.setBackgroundResource(R.drawable.tabla_celda);
			// layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
			layoutCelda = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
			texto.setLayoutParams(layoutCelda);
			//texto.setFocusable(true);
			//texto.setFocusableInTouchMode(true);

            fila.addView(texto);
        }
		//fila.setFocusable(true);
		//fila.setFocusableInTouchMode(true);
		//fila.setClickable(true);
		tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }

    /**
     * Elimina una fila de la tabla
     * @param indicefilaeliminar Indice de la fila a eliminar
     */
    public void eliminarFila(int indicefilaeliminar)
    {
       
		if (indicefilaeliminar > 0 && indicefilaeliminar < FILAS)
        {

			tabla.removeViewAt(indicefilaeliminar);
            
			FILAS--;
			
		}
   
		}

		
		
	/**
     * Elimina una fila de la tabla
     * @param indicefilaeliminar Indice de la fila a eliminar
     */
    public String [] eliminarFilaretornaelementos(int indicefilaeliminar)
    {
String[] retorno=new String [2];
retorno[0]="";
retorno[1]="";
		if (indicefilaeliminar > 0 && indicefilaeliminar < FILAS)
        {

			tabla.removeViewAt(indicefilaeliminar);
			retorno[0]=((TextView)(filas.get(indicefilaeliminar)).getChildAt(1)).getText().toString();
			retorno[1]=((TextView)(filas.get(indicefilaeliminar)).getChildAt(2)).getText().toString();
			filas.remove(indicefilaeliminar);
			FILAS--;

		}
return retorno;
	}
		
	/**
     * Elimina una fila de la tabla
     * @param indicefilaeliminar Indice de la fila a eliminar
     */
    public void eliminarTabla()
    {
        for(int i=FILAS;i>1;i--){
			tabla.removeViewAt(i-1);
			
			
		}

			//tabla.removeViewAt(indicefilaeliminar);
            FILAS=1;
		
    }
	
    /**
     * Devuelve las filas de la tabla, la cabecera se cuenta como fila
     * @return Filas totales de la tabla
     */
    public int getFilas()
    {
        return FILAS;
    }

    /**
     * Devuelve las columnas de la tabla
     * @return Columnas totales de la tabla
     */
    public int getColumnas()
    {
        return COLUMNAS;
    }

    /**
     * Devuelve el número de celdas de la tabla, la cabecera se cuenta como fila
     * @return Número de celdas totales de la tabla
     */
    public int getCeldasTotales()
    {
        return FILAS * COLUMNAS;
    }


	private View.OnClickListener mylistenerseleccion = new View.OnClickListener() {
        public void onClick(View v)
		{


			boolean checked = ((CheckBox) v).isChecked();
			Log.e("tagcheckcase", Integer.toString(v.getId()));
			if (v.getId() == Idcheckboxcabecera)
			{
				for (int i=1;i < FILAS;i++)
				{
					if (checked)
					{
						((CheckBox) tabla.findViewById(i)).setChecked(true);
					}
					else
					{
						((CheckBox) tabla.findViewById(i)).setChecked(false);
					}
				}
			}
		}
	};




	/**
	 * Obtiene el ancho en píxeles de un texto en un String
	 * @param texto Texto
	 * @return Ancho en píxeles del texto
	 */
	private int obtenerAnchoPixelesTexto(String texto)
	{
		Paint p = new Paint();
		Rect bounds = new Rect();
		p.setTextSize(59);

		p.getTextBounds(texto, 0, texto.length(), bounds);
		return bounds.width();
		//return 270;
	}
}
