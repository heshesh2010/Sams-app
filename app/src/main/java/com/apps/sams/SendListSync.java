package com.apps.sams;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import normal.LoginAsync;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class SendListSync extends AsyncTask<Void, Void, String> {

	
	HashMap<Integer, Integer> checkBoxState = new HashMap<Integer, Integer>();
	/*	final String NAMESPACE = "http://ws.sams.net";
	String URL = "http://control.sams-app.net/webService/services/InsertDataWS?WSDL";
	final String METHOD_NAME = "insertApsentData";
	final String SOAP_ACTION = "http://ws.sams.net/insertApsentData";
	*/
	//test
	final String NAMESPACE = "http://ws.sams.net";
	String URL = "http://control.sams-app.net/webService/services/InsertDataWS?WSDL";
	final String METHOD_NAME = "insertApsentData";
	final String SOAP_ACTION = "http://ws.sams.net/insertApsentData";	
	
	private Activity activity;
	private  ProgressDialog progressDialog;
	 Context context;
	
	 
	public SendListSync(Context context, Activity activity,
			HashMap<Integer, Integer> checkBoxState, ProgressDialog progressDialog) {
		super();
		this.checkBoxState = checkBoxState ;
		this.activity = activity;
		this.context = context;
		this.progressDialog = progressDialog ;
		
	}




	
	@Override
	protected String doInBackground(Void... voids) {
		

		
		
		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		int TID = prefs.getInt("TID", 1);
		String class_name = prefs.getString("Class_Name", "0");
		
		Object resultRequestSOAP = null;
		
		for (Map.Entry<Integer, Integer> mapEntry : checkBoxState
				.entrySet()) {
			int SID = mapEntry.getKey();
			int status = mapEntry.getValue();

			try {
				SoapObject request = new SoapObject(NAMESPACE,
						METHOD_NAME);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						URL);
				PropertyInfo pi = new PropertyInfo();
				pi.setName("SID");
				pi.setValue(SID);
				pi.setType(Integer.class);
				request.addProperty(pi);

				PropertyInfo pi2 = new PropertyInfo();
				pi2.setName("status");
				pi2.setValue(status);
				pi2.setType(Integer.class);
				request.addProperty(pi2);
			
				PropertyInfo pi3 = new PropertyInfo();
				pi3.setName("TID");
				pi3.setValue(TID);
				pi3.setType(Integer.class);
				request.addProperty(pi3);
				
				PropertyInfo pi4 = new PropertyInfo();
				pi4.setName("class_name");
				pi4.setValue(class_name);
				pi4.setType(String.class);
				request.addProperty(pi4);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				androidHttpTransport.call(SOAP_ACTION, envelope);


			}catch (UnknownHostException e) {//POST & GET section
				return "error";
		    }
		    catch (ConnectException e) {//POST & GET section
		    	return "error";
		    }
		    catch (NullPointerException e) {//POST & GET section
		    	return "error";
		    }
		    catch (ClientProtocolException e) {//POST section
		        e.printStackTrace();
		    	return "error";
		    }	
				
			 catch (SocketTimeoutException e) {
					return "error";

			}

			catch (ConnectTimeoutException e) {
				return "error";

			} catch (Exception e) {
				return "error";
			}
			
			
	
			}
		return "ok";
	}

	
	
	@Override
	protected void onPostExecute(String reslut) {
		if(reslut.equalsIgnoreCase("ok")){
			
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);

			alertDialog.setTitle(" „ «—”«· «·»Ì«‰«  »‰Ã«Õ ");
			alertDialog.setMessage("Â·  Êœ €·ﬁ «·»—‰«„Ã ø");
			// Retry Button Action
			alertDialog.setPositiveButton("‰⁄„",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					});

			// No Button Action
			alertDialog.setNegativeButton("·«",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							progressDialog.dismiss();
						}
					});
			progressDialog.dismiss();
			alertDialog.show();
			
			
		}
		
		
		
		else if (reslut.equalsIgnoreCase("error")){
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);

			alertDialog.setTitle("ÌÊÃœ „‘ﬂ·… »«·« ’«· «Ê «·”Ì—›—");
			alertDialog.setMessage("Â·  Êœ «·„Õ«Ê·… „Ãœœ« ø ");
			// Retry Button Action
			alertDialog.setPositiveButton("‰⁄„",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					});

			// No Button Action
			alertDialog.setNegativeButton("·«",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							progressDialog.dismiss();
						}
					});
			progressDialog.dismiss();
			alertDialog.show();
			
			
			
		}
		progressDialog.dismiss();
	
	}
	
	
}
