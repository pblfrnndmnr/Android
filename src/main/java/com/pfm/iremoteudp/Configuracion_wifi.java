/*****************************************************************************
PFM 2016
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

public class Configuracion_wifi extends AppCompatActivity
{


	/** Called when the activity is first created. */

	EditText ipstation;
	EditText ipaccesspoint;
    EditText port_station_remote;
	EditText port_station_local;
	//CheckBox checkboxlocal,checkboxremoto;
	
	


	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		SharedPreferences settings = getSharedPreferences("IPs", MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		if (!ipstation.getText().toString().equals(""))
		{
			editor.putString("ip_station", ipstation.getText().toString());
		}
		if (!port_station_local.getText().toString().equals(""))
		{
			editor.putString("port_station_local", port_station_local.getText().toString());
		}
		if (!port_station_remote.getText().toString().equals(""))
		{
			editor.putString("port_station_remote", port_station_remote.getText().toString());
		}
		if (!ipaccesspoint.getText().toString().equals(""))
		{
			editor.putString("ip_accesspoint", ipaccesspoint.getText().toString());
		}
		
		
		editor.commit();
		Toast.makeText(getApplicationContext(), "Guardando", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		SharedPreferences settings = getSharedPreferences("IPs", MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		if (!ipstation.getText().toString().equals(""))
		{
			editor.putString("ip_station", ipstation.getText().toString());
		}
		if (!port_station_local.getText().toString().equals(""))
		{
			editor.putString("port_station_local", port_station_local.getText().toString());
		}
		if (!port_station_remote.getText().toString().equals(""))
		{
			editor.putString("port_station_remote", port_station_remote.getText().toString());
		}
		if (!ipaccesspoint.getText().toString().equals(""))
		{
			editor.putString("ip_accesspoint", ipaccesspoint.getText().toString());
		}
		editor.commit();
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



        setContentView(R.layout.configuracion_wifi);

		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		//texto para ingresar la ip y filtro para validar el ingreso
		ipstation  = (EditText)findViewById(R.id.dirip);
		port_station_remote = (EditText)findViewById(R.id.port_station_remote);
		port_station_local = (EditText)findViewById(R.id.port_station_local);
		ipaccesspoint  = (EditText)findViewById(R.id.dirip2);
		

		SharedPreferences settings = getSharedPreferences("IPs", MODE_PRIVATE);
		String nombre = settings.getString("ip_station", "192.168.80.6");
        ipstation.setText(nombre);
		nombre = settings.getString("port_station_local", "1234");
        port_station_local.setText(nombre);
		nombre = settings.getString("port_station_remote", "1234");
        port_station_remote.setText(nombre);
		nombre = settings.getString("ip_accesspoint", "192.168.1.4");
        ipaccesspoint.setText(nombre);
        
		


		//ipaccesspoint.setText( getIntent().getExtras().getString("parametro"));

		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter() {
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
			{
				if (end > start)
				{
					String destTxt = dest.toString();
					String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
					if (!resultingTxt.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?"))
					{ 
						return "";
					}
					else
					{

						String[] splits = resultingTxt.split("\\.");
						for (int i=0; i < splits.length; i++)
						{
							if (Integer.valueOf(splits[i]) > 255)
							{
								return "";
							}
						}
					}
				}
				return null;
			}
		};
		ipstation.setFilters(filters);
		ipaccesspoint.setFilters(filters);

		InputFilter[] filters_port = new InputFilter[1];
		filters_port[0] = new InputFilter() {
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
			{
				if (end > start)
				{
					String destTxt = dest.toString();
					String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
					if (!resultingTxt.matches("^\\d{1,5}?"))
					{ 
						return "";
					}
					else
					{
						if (Integer.valueOf(resultingTxt) > 65535)//splits[i]) > 65535)
						{
							return "";
						}
					}
				}
				return null;
			}
		};
		port_station_local.setFilters(filters_port);
		port_station_remote.setFilters(filters_port);

		final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.input_layout_name);
		ipstation.setOnFocusChangeListener(new OnFocusChangeListener() {          
				@Override
				public void onFocusChange(View v, boolean hasFocus)
				{
					if (!hasFocus)
					{


						if (floatingUsernameLabel.isErrorEnabled())
						{
							//floatingUsernameLabel.requestFocus();
							//	ip_accesspoint.clearFocus();
							//requestFocus(ip_station);

							//floatingUsernameLabel.setError("lo");
						}
						//ip_station.setError("ggggg");
						// code to execute when EditText loses focus

						//requestFocus( ip_station);
						//ip_accesspoint.clearFocus();
					}
				}
			});


		//private void setupFloatingLabelError() {

		floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
				// ...
				@Override
				public void onTextChanged(CharSequence text, int start, int count, int after)
				{
					if (!text.toString().matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})+)+)+)+)+)+"))
					{


						floatingUsernameLabel.setError(("IP no válida"));
						floatingUsernameLabel.setErrorEnabled(true);
					}
					else
					{
						floatingUsernameLabel.setErrorEnabled(false);
					}
				}
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, 
											  int after)
				{
					// TODO Auto-generated method stub
				}

				@Override
				public void afterTextChanged(Editable s)
				{
					//floatingUsernameLabel.setErrorEnabled(false);
				}
			});





	}
	

	




}
