package com.pfm.iremoteudp;

import android.os.*;
import java.net.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import android.util.*;

class UDP_class extends AsyncTask<String , Void, Integer>
{

    private Exception exception;
	protected void onPreExecute()
	{


	}

	protected void onProgressUpdate(String... values)
	{
	}

    protected Integer doInBackground(String... urls)
	{
		
		
		try
		{

			InetAddress address = InetAddress.getByName(urls[0]);
			DatagramSocket socket = new DatagramSocket(Integer.parseInt(urls[1]));
            DatagramPacket packet = new DatagramPacket("qwerty".getBytes(), "qwerty".length(), address, Integer.parseInt(urls[2]));
			//Log.e("data",urls[0]);
			Log.e("data",urls[1]);
			socket.send(packet);
			//socket.disconnect();
			socket.close();




			return 1;  
        }
		catch (Exception e)
		{
			this.exception = e;
			return 0; 
        }

	}

    protected void onPostExecute(Void param)
	{
        // TODO: check this.exception 
        // TODO: do something with the feed
    }
}
