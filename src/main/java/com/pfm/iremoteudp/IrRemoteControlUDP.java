/*****************************************************************************
 PFM 2016
 *****************************************************************************/
package com.pfm.iremoteudp;

import android.app.*;
import android.util.Log;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.support.v7.app.ActionBarActivity;
import java.io.*;
import java.net.*;
import org.apache.commons.logging.*;
import java.util.*;
import android.support.v7.app.*;

public class IrRemoteControlUDP extends ActionBarActivity
{

	TextView txt1;
	Button btmute,btpower,bt1,bt2,bt3,bt4,
	bt5,bt6,bt7,bt8,bt9,bt0,bt100,bttvvideo,
	btcanalanterior,btinfo,bttimer,btcanalup,
	btvoldown,btmenu,btvolup,btcanaldown,
	btimagen,btfuzzy,btmemoria,btautoscan,
	btaudio,bttvcatv;
	ArrayList<String> ArrayList ;
	String ipstation,portstationlocal,portstationremote,ipaccesspoint;
	private long mLastPress = 0;	// Cuándo se pulsó atrás por última vez
    private long mTimeLimit = 2000;	// Límite de tiempo entre pulsaciones, en ms

	@Override
    public void onBackPressed()
	{
        Toast onBackPressedToast = Toast.makeText(this, R.string.press_once_again_to_exit, Toast.LENGTH_SHORT);
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastPress > mTimeLimit)
		{
        	onBackPressedToast.show();
            mLastPress = currentTime;
        }
		else
		{
            onBackPressedToast.cancel();
            super.onBackPressed();
        }}

	//Manejo de archivo de texto
	public void grabar(View v)
	{
		String nomarchivo = "archivo.txt";//et1.getText().toString();
        String contenido ="arch leido";// et2.getText().toString();
		/* try
		 {
		 File tarjeta = Environment.getExternalStorageDirectory();
		 File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
		 OutputStreamWriter osw = new OutputStreamWriter(
		 new FileOutputStream(file));
		 osw.write(contenido);
		 osw.flush();
		 osw.close();
		 Toast.makeText(this, "Los datos fueron grabados correctamente",
		 Toast.LENGTH_SHORT).show();	
		 }
		 catch (IOException ioe)
		 {
		 }*/

		Context _context = this.getApplicationContext();

		File fileList2[] = _context.getExternalFilesDirs("Sources");

		if (fileList2.length == 1)
		{
			Log.d("extsdw", "external device is not mounted.");
			return;
		}
		else
		{
			Log.d("extsdw", "external device is mounted.");
			File extFile = fileList2[1];
			String absPath = extFile.getAbsolutePath(); 
			Log.d("extsdw", "external device download : " + absPath);
			//String appPath = getApplicationContext().getFilesDir().getAbsolutePath();
			String appPath = absPath.split("Sources")[0];

			Log.d("extsdw", "external device app path: " + appPath);

			File file = new File(absPath, nomarchivo);

			try
			{
				// Very simple code to copy a picture from the application's
				// resource into the external file.  Note that this code does
				// no error checking, and assumes the picture is small (does not
				// try to copy it in chunks).  Note that if external storage is
				// not currently mounted this will silently fail.
				//InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
				//Log.d("extsdw", "file bytes : " + is.available());
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
				//OutputStream os = new FileOutputStream(file);
				//byte[] data = new byte[is.available()];
				//is.read(data);
				osw.write(contenido);
				//is.close();
				osw.flush();
				osw.close();
			}
			catch (IOException e)
			{
				// Unable to create file, likely because external storage is
				// not currently mounted.
				Log.d("ExternalStorage", "Error writing " + file, e);
			}
		}





	}


	public void recuperar(View v)
	{
        String nomarchivo ="archivo.txt";// et1.getText().toString();
		// File tarjeta = Environment.getExternalStorageDirectory();

		Context _context = this.getApplicationContext();

		File fileList2[] = _context.getExternalFilesDirs("Sources");

		if (fileList2.length == 1)
		{
			Log.d("extsdw", "external device is not mounted.");
			return;
		}
		else
		{
			Log.d("extsdw", "external device is mounted.");
			File extFile = fileList2[1];
			String absPath = extFile.getAbsolutePath(); 
			Log.d("extsdw", "external device download : " + absPath);
			//String appPath = getApplicationContext().getFilesDir().getAbsolutePath();
			String appPath = absPath.split("Sources")[0];

			Log.d("extsdw", "external device app path: " + appPath);




			File file = new File(absPath, nomarchivo);
			try
			{
				FileInputStream fIn = new FileInputStream(file);
				InputStreamReader archivo = new InputStreamReader(fIn);
				BufferedReader br = new BufferedReader(archivo);
				String linea = br.readLine();
				String todo = "";
				while (linea != null)
				{
					todo = todo + linea + "";
					linea = br.readLine();
				}
				br.close();
				archivo.close();
				txt1.setText(todo);

			}
			catch (IOException e)
			{
			}
		}
	}



	/*public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
	 // TODO Auto-generated method stub

	 switch (keyCode)
	 {
	 case KeyEvent.KEYCODE_CAMERA:
	 Toast.makeText(this, "Obturador presionado",
	 Toast.LENGTH_SHORT).show();
	 return true;
	 case KeyEvent.KEYCODE_VOLUME_UP:
	 Toast.makeText(this, "Boton de Volumen Up presionado",
	 Toast.LENGTH_SHORT).show();
	 return true;
	 case KeyEvent.KEYCODE_VOLUME_DOWN:
	 Toast.makeText(this, "Boton de Volumen Down presionado",
	 Toast.LENGTH_SHORT).show();
	 return true;

	 case KeyEvent.KEYCODE_BACK:
	 onBackPressed();
	 return true;
	 }

	 return super.onKeyUp(keyCode, event);
	 }*/

	/*@Override
	 public boolean onTouchEvent(MotionEvent event)
	 {
	 // TODO Auto-generated method stub
	 float posX = event.getX();
	 float posY = event.getY();
	 Toast.makeText(this, "Acabas de tocar la pantalla en: " + posX + ", " + posY,
	 Toast.LENGTH_SHORT).show();

	 return true;
	 }*/


	/*@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	 MenuInflater  inflater = getMenuInflater();
	 inflater.inflate(R.menu.activity_menu , menu);
	 for (int i = 0; i < menu.size(); i++) {
	 menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	 }
	 return super.onCreateOptionsMenu(menu);
	 }*/



	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//Alternativa 1
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_settings:
				//Crear un nuevo intent
				Intent intent = new Intent(IrRemoteControlUDP.this, Configuracion_wifi.class);
				startActivity(intent);
				return true;
			case R.id.menu_settings2:
				//Crear un nuevo intent
				Intent intent2 = new Intent(IrRemoteControlUDP.this, Configuracion_ir.class);
				//Iniciar actividad
				intent2.putStringArrayListExtra("botonespasados", ArrayList);
				startActivity(intent2);
				return true;

			case R.id.menu_muestraarchivo:
				//Crear un nuevo intent
				Intent intenta = new Intent(IrRemoteControlUDP.this, muestra_archivo.class);
				//Iniciar actividad
				startActivity(intenta);
				return true;

			case R.id.menu_about:
				//acá colocar la ayuda
				return true;

			case R.id.menu_salir:
	    		finish();
				Intent intent1 = new Intent(Intent.ACTION_MAIN);
				intent1.addCategory(Intent.CATEGORY_HOME);
				intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent1);
				return true;

			default:
				return super.onOptionsItemSelected(item);

		}
	}


	@Override
    public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);        


        setContentView(R.layout.main_linearlayout);

		SharedPreferences settings = getSharedPreferences("IPs", MODE_PRIVATE);
		ipstation = settings.getString("ip_station", "192.168.80.6");
        portstationlocal = settings.getString("port_station_local", "1234");
		portstationremote = settings.getString("port_station_remote", "1234");  
		ipaccesspoint = settings.getString("ip_accesspoint", "192.168.1.4");


		txt1   = (TextView)findViewById(R.id.textViewultimocomando); 

        btmute = (Button) findViewById(R.id.buttonmute);
		//ArrayList loco= new ArrayList();
		//loco.add(btmute.getText().toString());
		//ArrayList = null;
		ArrayList = new ArrayList<String>();
		ArrayList.add(btmute.getText().toString());
		//Log.w("button", Integer.toString(btmute.getId()));
		btpower = (Button) findViewById(R.id.buttonpower);
		ArrayList.add(btpower.getText().toString());
		bt1 = (Button) findViewById(R.id.button1);
		ArrayList.add(bt1.getText().toString());
        bt2 = (Button) findViewById(R.id.button2);
		ArrayList.add(bt2.getText().toString());
		bt3 = (Button) findViewById(R.id.button3);
		ArrayList.add(bt3.getText().toString());

		bt4 = (Button) findViewById(R.id.button4);
		ArrayList.add(bt4.getText().toString());
		bt5 = (Button) findViewById(R.id.button5);
		ArrayList.add(bt5.getText().toString());
		bt6 = (Button) findViewById(R.id.button6);
		ArrayList.add(bt6.getText().toString());
		bt7 = (Button) findViewById(R.id.button7);
		ArrayList.add(bt7.getText().toString());
		bt8 = (Button) findViewById(R.id.button8);
		ArrayList.add(bt8.getText().toString());
		bt9 = (Button) findViewById(R.id.button9);
		ArrayList.add(bt9.getText().toString());
		bt0 = (Button) findViewById(R.id.button0);
		ArrayList.add(bt0.getText().toString());
		bt100 = (Button) findViewById(R.id.button100);
		ArrayList.add(bt100.getText().toString());
		btcanalup = (Button) findViewById(R.id.buttoncanalup);
		ArrayList.add(btcanalup.getText().toString());
		bttvvideo = (Button) findViewById(R.id.buttontvvideo);
		ArrayList.add(bttvvideo.getText().toString());
		btcanalanterior = (Button) findViewById(R.id.buttoncanalanterior);
		ArrayList.add(btcanalanterior.getText().toString());
		btinfo = (Button) findViewById(R.id.buttondisplay);
		ArrayList.add(btinfo.getText().toString());
		bttimer = (Button) findViewById(R.id.buttontimer);
		ArrayList.add(bttimer.getText().toString());
		btvoldown = (Button) findViewById(R.id.buttonvolmenos);
		ArrayList.add(btvoldown.getText().toString());
		btmenu = (Button) findViewById(R.id.buttonmenu);
		ArrayList.add(btmenu.getText().toString());
		btvolup = (Button) findViewById(R.id.buttonvolmas);
		ArrayList.add(btvolup.getText().toString());
		btcanaldown = (Button) findViewById(R.id.buttoncanaldown);
		ArrayList.add(btcanaldown.getText().toString());
		btimagen = (Button) findViewById(R.id.buttonimagen);
		ArrayList.add(btimagen.getText().toString());
		btfuzzy = (Button) findViewById(R.id.buttonfuzzy);
		ArrayList.add(btfuzzy.getText().toString());
		btmemoria = (Button) findViewById(R.id.buttonmemoria);
		ArrayList.add(btmemoria.getText().toString());
		btautoscan = (Button) findViewById(R.id.buttonautoscan);
		ArrayList.add(btautoscan.getText().toString());
		btaudio = (Button) findViewById(R.id.buttonaudio);
		ArrayList.add(btaudio.getText().toString());
		bttvcatv = (Button) findViewById(R.id.buttontvcatv);
		ArrayList.add(bttvcatv.getText().toString());

		//new UDP_class().execute();
		//textIn.setText("oncreate");

		bt1.setOnLongClickListener(new View.OnLongClickListener(){
				public  boolean onLongClick(View v)
				{
					txt1.setText("1largo");
					Toast.makeText(getApplicationContext(), "Hello 123", Toast.LENGTH_LONG).show();


					return true;
				}
			});

		btmute.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);

					// 	 str="test";
					/*try {
					 client();
					 //txt1.setText(modifiedSentence);
					 } catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 } */       	 	 
					txt1.setText(btmute.getText().toString());
					grabar(v);

				}  
			});
        btpower.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);

					// 	 str="test";
					/*try {
					 client();
					 //txt1.setText(modifiedSentence);
					 } catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 } */       	 	 
					txt1.setText(btpower.getText().toString());
					recuperar(v);

				}  
			});

        bt1.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					//str="temp";
					txt1.setText(bt1.getText().toString());
					new UDP_class().execute(ipstation, portstationlocal, portstationremote, ipaccesspoint);
					Log.e("datos", portstationremote);
					/*	try
					 {
					 sendPacket(1235, "192.168.80.255", 1234, "qwerty");
					 //ccliente.main1();
					 //txt1.setText(modifiedSentence);
					 }
					 catch (IOException e)
					 {
					 // TODO Auto-generated catch block
					 txt1.setText(e.getMessage().toString());
					 e.printStackTrace();
					 }  */

				}  

			}); 

        bt2.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);

					// 	 str="test";
					/*try {
					 client();
					 //txt1.setText(modifiedSentence);
					 } catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 } */       	 	 
					txt1.setText(bt2.getText().toString());


				}  
			}); 

        bt3.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);

					// 	str="humi";
					/*	try {
					 client();
					 //txt1.setText(modifiedSentence);
					 } catch (IOException e) {
					 // TODO Auto-generated catch block

					 txt5.setText(e.getMessage().toString());
					 e.printStackTrace();
					 } */       	 	 
					txt1.setText(bt3.getText().toString()); 

					/*	String manufacturer = Build.MANUFACTURER;
					 String model = Build.MODEL;
					 int version = Build.VERSION.SDK_INT;
					 String versionRelease = Build.VERSION.RELEASE;
					 Toast.makeText(getApplicationContext(),model+ " "+ manufacturer, Toast.LENGTH_LONG).show();*/


				}        	
			}); 

		bt4.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt4.getText().toString());


				} 
			}); 

        bt5.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt5.getText().toString());


				}        	
			}); 


		bt6.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt6.getText().toString());


				}  
			}); 

		bt7.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt7.getText().toString());


				}  
			});

		bt8.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt8.getText().toString());


				}  
			});

		bt9.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt9.getText().toString());


				}  
			});


		bt0.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt0.getText().toString());


				}  
			});

		bt100.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bt100.getText().toString());


				}  

			});

		bttvvideo.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bttvvideo.getText().toString());


				}  

			});

		btcanalanterior.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btcanalanterior.getText().toString());


				}  

			});	

		btinfo.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btinfo.getText().toString());


				}  

			});

		bttimer.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bttimer.getText().toString());


				}  

			});

		btcanalup.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btcanalup.getText().toString());


				}  

			});

		btvoldown.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btvoldown.getText().toString());


				}  

			});

		btmenu.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btmenu.getText().toString());


				}  

			});

		btvolup.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btvolup.getText().toString());


				}  

			});

		btcanaldown.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btcanaldown.getText().toString());


				}  

			});

		btimagen.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btimagen.getText().toString());


				}  

			});

		btfuzzy.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btfuzzy.getText().toString());


				}  

			});

		btmemoria.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btmemoria.getText().toString());


				}  

			});

		btautoscan.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btautoscan.getText().toString());


				}  

			});

		btaudio.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(btaudio.getText().toString());


				}  

			});

		bttvcatv.setOnClickListener(new View.OnClickListener(){             
				public void onClick(View v)
				{                 
					// Perform action on click 
					//textIn.setText("test"); 
					//txt2.setText("text2");
					//task.execute(null);
					txt1.setText(bttvcatv.getText().toString());


				}  

			});

    }


	/* public void client() throws IOException{


	 DatagramSocket client_socket = new DatagramSocket(1234);
	 InetAddress IPAddress =  InetAddress.getByName("localhost");   
	 send_data = str.getBytes();
	 //System.out.println("Type Something (q or Q to quit): ");             	
	 DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 1234);	
	 try{ 	
	 client_socket.send(send_packet);                       

	 //chandra 
	 //DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	 //client_socket.receive(receivePacket);
	 //   modifiedSentence = new String(receivePacket.getData());
	 //System.out.println("FROM SERVER:" + modifiedSentence);
	 if(modifiedSentence.charAt(2)=='%')
	 txt5.setText(modifiedSentence.substring(0, 3)); 
	 else
	 txt1.setText(modifiedSentence);
	 modifiedSentence=null;
	 client_socket.close();    	 		  
	 }
	 catch(SocketException e){
	 txt5.setText(e.getMessage().toString());
	 e.printStackTrace();  
	 }
	 }   */         
}
    
