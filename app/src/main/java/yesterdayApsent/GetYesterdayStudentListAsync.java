package yesterdayApsent;

import java.io.IOException;
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
import org.xmlpull.v1.XmlPullParserException;

import yesterdayApsent.ListApsentActivity;

import com.apps.sams.Pair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

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

public class GetYesterdayStudentListAsync extends AsyncTask<Void, Void, Pair> {

	private Activity activity;
	private  ProgressDialog progressDialog;
	 Context context;
	 
	
		private Object resultRequestSOAP4 = null;
	public GetYesterdayStudentListAsync(Activity activity, Context context, ProgressDialog progressDialog) {
		super();
		this.progressDialog = progressDialog;
		this.activity = activity;
		this.context = context;
	}

	
	final String NAMESPACE = "http://ws.sams.net";
	final String URL = "http://control.sams-app.net/webService/services/ApsentStudentNamesWS?WSDL"; 																				// localhost
	final String METHOD_NAME = "getCurrentClassListNames";
	final String SOAP_ACTION = "http://ws.sams.net/getCurrentClassListNames";
	
	final String NAMESPACE2 = "http://ws.sams.net";
	final String URL2 = "http://control.sams-app.net/webService/services/ApsentStudentNamesWS?WSDL"; 																					// localhost
	final String METHOD_NAME2 = "getApsentClassListCode";
	final String SOAP_ACTION2 = "http://ws.sams.net/getApsentClassListCode";

	final String NAMESPACE3 = "http://ws.sams.net";
	final String URL3 = "http://control.sams-app.net/webService/services/ApsentStudentNamesWS?WSDL"; 																		// localhost
	final String METHOD_NAME3 = "getApsentClassListStatus";
	final String SOAP_ACTION3 = "http://ws.sams.net/getApsentClassListStatus";

	
	final String NAMESPACE4 = "http://ws.sams.net";
	final String URL4 = "http://control.sams-app.net/webService/services/ApsentStudentNamesWS?WSDL"; 																		// localhost
	final String METHOD_NAME4 = "getdate";
	final String SOAP_ACTION4 = "http://ws.sams.net/getdate";
	
	
	
	Pair p = null;
	private StudentHelper get(String s) {

		return new StudentHelper(s);

	}

	ArrayList<StudentHelper> StudentIdList = new ArrayList<StudentHelper>();
	ArrayList<StudentHelper> StudentNamesList = new ArrayList<StudentHelper>();
	ArrayList<StudentHelper> StudentStatusList = new ArrayList<StudentHelper>();
	
	
	
	@Override
	protected Pair doInBackground(Void... voids) {
		p = new Pair();
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(context);
		String Class_ID = prefs2.getString("Class_ID", "1");
		
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

		try {
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
				
				
			}catch (SocketTimeoutException e) {
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
	
			////////////////////////////////////////////////////
			
			SoapObject request3 = new SoapObject(NAMESPACE3, METHOD_NAME3);
			PropertyInfo pi3 = new PropertyInfo();
			pi3.setName("Class_ID");
			pi3.setValue(Class_ID);
			pi3.setType(String.class);
			request3.addProperty(pi3);
			SoapSerializationEnvelope envelope3 = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope3.setOutputSoapObject(request3);
			HttpTransportSE androidHttpTransport3 = new HttpTransportSE(URL3);

			try {

				androidHttpTransport3.call(SOAP_ACTION3, envelope3);

				KvmSerializable result = (KvmSerializable) envelope3.bodyIn;

				String str = null;
				for (int i = 0; i < result.getPropertyCount(); i++) {
					str = ((String) result.getProperty(i).toString());

					StudentStatusList.add(get(str));

				}
	
				p.setstatus(StudentStatusList);
				
				
			}catch (SocketTimeoutException e) {
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
			////////////////////////////////////////////
			try {
			SoapObject request4 = new SoapObject(NAMESPACE4, METHOD_NAME4);
			SoapSerializationEnvelope envelope4 = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope4.setOutputSoapObject(request4);

			HttpTransportSE androidHttpTransport4 = new HttpTransportSE(URL4);

			androidHttpTransport4.call(SOAP_ACTION4, envelope4);
			resultRequestSOAP4 = (Object) envelope4.getResponse();
			
p.setdate(resultRequestSOAP4.toString());

			return p;
			
			
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
			
	
			

	}

	@Override
	protected void onPostExecute(Pair reslut) {
		if (p.geterror()=="error") {

			progressDialog.dismiss();
			
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);

			alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÇÊÕÇá Çæ ÇáÓíÑÝÑ");
			alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");

			// Retry Button Action
			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							GetYesterdayStudentListAsync asynTask = new GetYesterdayStudentListAsync(
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

			alertDialog.show();

		}
		
		else if (reslut.getnames().isEmpty()) {

			
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);
			
			alertDialog.setTitle("áÇ íæÌÏ ØáÇÈ ÛÇÆÈæä áåÐÇ ÇáÝÕá");
			alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿");
			
			// Retry Button Action
			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							GetYesterdayStudentListAsync asynTask = new GetYesterdayStudentListAsync(
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

			alertDialog.show();
			
			
			
		}
		
		else{
			
		try {
		GsonBuilder gsonb = new GsonBuilder();
		GsonBuilder gsonb2 = new GsonBuilder();
		GsonBuilder gsonb3 = new GsonBuilder();
		GsonBuilder gsonb4 = new GsonBuilder();
		Gson gson = gsonb.create();
		Gson gson2 = gsonb2.create();
	    Gson gson3 = gsonb3.create();
	    Gson gson4 = gsonb4.create();
		String json = gson.toJson(p.getids());
		
		String json2 = gson2.toJson(p.getnames());
		
		String json3 = gson3.toJson(p.getstatus());
			
		String json4 = gson4.toJson(p.getdate());
		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs.edit().putString("StudentIdList", json).commit();
		
		
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs2.edit().putString("StudentNamesList", json2).commit();
		
		SharedPreferences prefs3 = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs3.edit().putString("StudentStatusList", json3).commit();
		 
		SharedPreferences prefs4 = PreferenceManager
				.getDefaultSharedPreferences(context);
		prefs4.edit().putString("date", json4).commit();
		
		 activity.startActivity(new Intent(activity, ListApsentActivity.class));
		
		progressDialog.dismiss();
	
		}catch (Exception e) {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					activity);
			
			alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÓíÑÝÑ");
			alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
			
			// Retry Button Action
			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							GetYesterdayStudentListAsync asynTask = new GetYesterdayStudentListAsync(
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

			alertDialog.show();
			
		}
		}
}
}