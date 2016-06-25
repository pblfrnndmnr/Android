/*****************************************************************************
 *  Copyright (c) 2004-2008, 2013 Digi International Inc., All Rights Reserved
 *
 *  This software contains proprietary and confidential information of Digi
 *  International Inc.  By accepting transfer of this copy, Recipient agrees
 *  to retain this software in confidence, to prevent disclosure to others,
 *  and to make no use of this software other than that for which it was
 *  delivered.  This is an unpublished copyrighted work of Digi International
 *  Inc.  Except as permitted by federal law, 17 USC 117, copying is strictly
 *  prohibited.
 *
 *  Restricted Rights Legend
 *
 *  Use, duplication, or disclosure by the Government is subject to
 *  restrictions set forth in sub-paragraph (c)(1)(ii) of The Rights in
 *  Technical Data and Computer Software clause at DFARS 252.227-7031 or
 *  subparagraphs (c)(1) and (2) of the Commercial Computer Software -
 *  Restricted Rights at 48 CFR 52.227-19, as applicable.
 *
 *  Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 *
 *****************************************************************************/
package com.pfm.iremoteudp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.support.v7.app.ActionBarActivity;
;
public class muestra_archivo extends ActionBarActivity {
 TextView txt1;

	public void recuperar()
	{
        String nomarchivo ="CLE-941.txt";// et1.getText().toString();
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
					todo = todo + linea + "\n";
					linea = br.readLine();
				}
				br.close();
				archivo.close();
				txt1.setText(todo);
//txt1.setText("yfhj");
			}
			catch (IOException e)
			{
			}
		}}
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);        



        setContentView(R.layout.muestra_archivo);
		txt1=(TextView)findViewById(R.id.muestraarchivo); 
		
		recuperar();
		}
}
