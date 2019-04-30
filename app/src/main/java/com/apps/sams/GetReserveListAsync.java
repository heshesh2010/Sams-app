package com.apps.sams;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import autoButton.GetStudentsListAsync;
import autoButton.StudentHelper;

import com.apps.sams.Pair;

public class GetReserveListAsync extends AsyncTask<Void, Void, Pair> {

	private Activity activity;
	private  ProgressDialog progressDialog;
	 Context context;
	 
	
	 
	public GetReserveListAsync(Activity activity, Context context, ProgressDialog progressDialog) {
		super();
		this.progressDialog = progressDialog;
		this.activity = activity;
		this.context = context;
	}

	
	final String NAMESPACE = "http://ws.sams.net";
	final String URL = "http://control.sams-app.net/webService/services/listActivityWS?WSDL"; // usint																				// localhost
	final String METHOD_NAME = "getCurrentClassList2";
	final String SOAP_ACTION = "http://ws.sams.net/getCurrentClassList2";

	
	final String NAMESPACE2 = "http://ws.sams.net";
	final String URL2 = "http://control.sams-app.net/webService/services/listActivityWS?WSDL"; // usint																					// localhost
	final String METHOD_NAME2 = "getStudentId2";
	final String SOAP_ACTION2 = "http://ws.sams.net/getStudentId2";

	final String NAMESPACE3 = "http://ws.sams.net";
	final String URL3 = "http://control.sams-app.net/webService/services/listActivityWS?WSDL"; // usint
																						// localhost
	final String METHOD_NAME3 = "getCourseName";
	final String SOAP_ACTION3 = "http://ws.sams.net/getCourseName";

	final String NAMESPACE4 = "http://ws.sams.net";
	final String URL4 = "http://control.sams-app.net/webService/services/listActivityWS?WSDL"; // usint
																						// localhost
	final String METHOD_NAME4 = "getClassName";
	final String SOAP_ACTION4 = "http://ws.sams.net/getClassName";

	final String NAMESPACE5 = "http://ws.sams.net";
	final String URL5 = "http://control.sams-app.net/webService/services/listActivityWS?WSDL"; // usint
																						// localhost
	final String METHOD_NAME5 = "getStudentCode2";
	final String SOAP_ACTION5 = "http://ws.sams.net/getStudentCode2";
	
	
	private StudentHelper get(String s) {

		return new StudentHelper(s);

	}
	
	ArrayList<StudentHelper> StudentIdList = new ArrayList<StudentHelper>();
	ArrayList<StudentHelper> StudentNamesList = new ArrayList<StudentHelper>();
	ArrayList<StudentHelper> StudentCodeList = new ArrayList<StudentHelper>();
	Pair p = new Pair();
	
	@Override
	protected Pair doInBackground(Void... voids) {
		

		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		int TID = prefs.getInt("TID", 1);
		
		
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(context);
		String Class_ID = prefs2.getString("Class_ID", "1");
		
		// ///////////////////////////////////////////
		try {	
		SoapObject request4 = new SoapObject(NAMESPACE4, METHOD_NAME4);
		PropertyInfo pi4 = new PropertyInfo();
		pi4.setName("TID");
		pi4.setValue(TID);
		pi4.setType(Integer.class);
		request4.addProperty(pi4);
		SoapSerializationEnvelope envelope4 = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope4.setOutputSoapObject(request4);
		HttpTransportSE androidHttpTransport4 = new HttpTransportSE(URL4);
		
			androidHttpTransport4.call(SOAP_ACTION4, envelope4);
			SoapPrimitive response4 = (SoapPrimitive) envelope4.getResponse();

    p.setClassId(response4.toString());
			// ///////////////////////////////////////////////////////////////////////

			SoapObject request3 = new SoapObject(NAMESPACE3, METHOD_NAME3);
			PropertyInfo pi3 = new PropertyInfo();
			pi3.setName("TID");
			pi3.setValue(TID);
			pi3.setType(Integer.class);
			request3.addProperty(pi3);
			SoapSerializationEnvelope envelope3 = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope3.setOutputSoapObject(request3);
			HttpTransportSE androidHttpTransport3 = new HttpTransportSE(URL3);

			androidHttpTransport3.call(SOAP_ACTION3, envelope3);
			SoapPrimitive response3 = (SoapPrimitive) envelope3.getResponse();
			p.setCourseName(response3.toString());
			// /////////////////////////////////////////////////////////////////////////////////////////////////

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		PropertyInfo pi = new PropertyInfo();
		pi.setName("Class_ID");
		pi.setValue(Class_ID);
		pi.setType(String.class);
		request.addProperty(pi);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		
			androidHttpTransport.call(SOAP_ACTION, envelope);

			KvmSerializable result = (KvmSerializable) envelope.bodyIn;

			String str = null;
			for (int i = 0; i < result.getPropertyCount(); i++) {
				str = ((String) result.getProperty(i).toString());

				StudentNamesList.add(get(str));

			}			
			
			p.setnames(StudentNamesList);
			
		} 
		
		
		
		
		catch (SocketTimeoutException e) {
			p.seterror("error");
			return p;
			
		}
		
		catch (ConnectTimeoutException e) {
			p.seterror("error");
			return p ;
				
			}
		
		catch (Exception e) {	
			
			p.seterror("error");
			return p ;
		}
		


	
		//////////////////////////////////////////////////////////////////////////////////////
		SoapObject request5 = new SoapObject(NAMESPACE5, METHOD_NAME5);
		PropertyInfo pi = new PropertyInfo();
		pi.setName("Class_ID");
		pi.setValue(Class_ID);
		pi.setType(String.class);
		request5.addProperty(pi);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request5);
		HttpTransportSE androidHttpTransport5 = new HttpTransportSE(URL5);

		try {

			androidHttpTransport5.call(SOAP_ACTION5, envelope);

			KvmSerializable result = (KvmSerializable) envelope.bodyIn;

			String str = null;
			for (int i = 0; i < result.getPropertyCount(); i++) {
				str = ((String) result.getProperty(i).toString());

				StudentCodeList.add(get(str));

			}

			p.setcode(StudentCodeList);

		} catch (SocketTimeoutException e) {
			p.seterror("error");
			return p;

		}

		catch (ConnectTimeoutException e) {
			p.seterror("error");
			return p;

		} catch (Exception e) {

			p.seterror("error");
			return p;
		}	
		
			SoapObject request2 = new SoapObject(NAMESPACE2, METHOD_NAME2);
			PropertyInfo pi2 = new PropertyInfo();
			pi2.setName("Class_ID");
			pi2.setValue(Class_ID);
			pi2.setType(String.class);
			request2.addProperty(pi2);
			SoapSerializationEnvelope envelope2 = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope2.setOutputSoapObject(request2);
			HttpTransportSE androidHttpTransport2 = new HttpTransportSE(URL2);

			try {

				androidHttpTransport2.call(SOAP_ACTION2, envelope2);

				KvmSerializable result = (KvmSerializable) envelope2.bodyIn;

				String str = null;
				for (int i = 0; i < result.getPropertyCount(); i++) {
					str = ((String) result.getProperty(i).toString());

					StudentIdList.add(get(str));

				}
	
				p.setids(StudentIdList);
				
				
			}catch (UnknownHostException e) {//POST & GET section
		        e.printStackTrace();
		p.seterror("error");
		        return p;
		    }
		    catch (ConnectException e) {//POST & GET section
		        e.printStackTrace();
		p.seterror("error");
		        return p;
		    }
		    catch (NullPointerException e) {//POST & GET section
		        e.printStackTrace();
		p.seterror("error");
		        return p;
		    }
		    catch (ClientProtocolException e) {//POST section
		        e.printStackTrace();
		p.seterror("error");
		        return p;
		    }	
				
			 catch (SocketTimeoutException e) {
				p.seterror("error");
				return p;

			}

			catch (ConnectTimeoutException e) {
				p.seterror("error");
				return p;

			} catch (Exception e) {
				Log.e("rgrg", e.getMessage(), e);
				p.seterror("error");
				return p;
			}
			
	
			
			return p ;
	}

	@Override
	protected void onPostExecute(Pair reslut) {
	

		
		if(p.geterror().equals("error")){
			
	
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);
			
			alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÇÊÕÇá Çæ ÇáÓíÑÝÑ");
			alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
			// Retry Button Action
			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							GetReserveListAsync asynTask = new GetReserveListAsync(
									activity, context, progressDialog);
							asynTask.execute();
								
						}
					});

			// No Button Action
			alertDialog.setNegativeButton("áÇ",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							progressDialog.dismiss();
						}
					});
			progressDialog.dismiss();
			alertDialog.show();

}	
		
		
else if (reslut.getnames().isEmpty()) {
	
	final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
			activity);
	
	alertDialog.setTitle("ÇáÊæÞíÊ ÎÇØÆ Çæ íæÌÏ ãÔßáå ÈÌÏæá ÇáÝÕæá ÇáÎÇÕ Èß");
	alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
	
	// Retry Button Action
	alertDialog.setPositiveButton("äÚã",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					p.seterror("");
					GetReserveListAsync asynTask = new GetReserveListAsync(
							activity, context, progressDialog);
					asynTask.execute();
						
				}
			});

	// No Button Action
	alertDialog.setNegativeButton("áÇ",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//progressDialog.dismiss();
				}
			});
	progressDialog.dismiss();
	alertDialog.show();
	
	
	
}

else{
try {
			
			
		GsonBuilder gsonb = new GsonBuilder();
		GsonBuilder gsonb2 = new GsonBuilder();
		GsonBuilder gsonb3 = new GsonBuilder();
		Gson gson = gsonb.create();
		Gson gson2 = gsonb2.create();
		Gson gson3 = gsonb3.create();
		String json = gson.toJson(p.getids());
		String json2 = gson2.toJson(p.getnames());
		String json3 = gson3.toJson(p.getcode());
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs.edit().putString("StudentIdList", json).commit();
		prefs.edit().putString("Course_Name", p.getCourseName()).commit();
		prefs.edit().putString("Class_id", p.getClassId()).commit();
		prefs.edit().putString("StudentCodeList", json3).commit();
		
		
	
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs2.edit().putString("StudentNamesList", json2).commit();
		
		String me = prefs2.getString("Class_Name", "0");
		
		
		prefs2.edit().putString("Class_Name", me).commit();

		 activity.startActivity(new Intent(activity, ListActivity1.class).putExtra("Uniqid","From_Activity_C"));
		 progressDialog.dismiss();	

	
		}catch (Exception e) {
			   
				
				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						activity);
				
				alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÇÊÕÇá Çæ ÇáÓíÑÝÑ");
				alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
				
				// Retry Button Action
				alertDialog.setPositiveButton("äÚã",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								
								GetReserveListAsync asynTask = new GetReserveListAsync(
										activity, context, progressDialog);
								asynTask.execute();
									
							}
						});

				// No Button Action
				alertDialog.setNegativeButton("áÇ",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								progressDialog.dismiss();
							}
						});
				progressDialog.dismiss();
				alertDialog.show();
				
		
			
	
		}
		
		}
}
}