package com.apps.sams;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
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
import java.net.ConnectException;
import java.net.UnknownHostException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetClassesListAsync extends AsyncTask<Void, Void, Pair>{


	private Activity activity;
	private  ProgressDialog progressDialog;
	Context context;
	
	
		public GetClassesListAsync(Activity activity, ProgressDialog progressDialog, Context context) {
			super();
			this.progressDialog = progressDialog;
			this.activity = activity;
			this.context = context;
		}
		
		
		final String NAMESPACE = "http://ws.sams.net";
		final String URL = "http://control.sams-app.net/webService/services/AllClassesWS?WSDL"; // usint																				// localhost
		final String METHOD_NAME = "getallclasses";
		final String SOAP_ACTION = "http://ws.sams.net/getallclasses";
	
		final String NAMESPACE2 = "http://ws.sams.net";
		final String URL2 = "http://control.sams-app.net/webService/services/AllClassesWS?WSDL"; // usint																				// localhost
		final String METHOD_NAME2 = "getallclassesIDS";
		final String SOAP_ACTION2 = "http://ws.sams.net/getallclassesIDS";
		
	
		private ClassesHelper get(String s) {

			return new ClassesHelper(s);

		}
	
		ArrayList<ClassesHelper> ClassesList = new ArrayList<ClassesHelper>();
		ArrayList<ClassesHelper> ClassesListIDS = new ArrayList<ClassesHelper>();
		Pair p = new Pair();
	
		@Override
		protected Pair doInBackground(Void... voids) {
			
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);

			try {
				androidHttpTransport.call(SOAP_ACTION, envelope);

				KvmSerializable result = (KvmSerializable) envelope.bodyIn;

				String str = null;
				for (int i = 0; i < result.getPropertyCount(); i++) {
					str = ((String) result.getProperty(i).toString());

					ClassesList.add(get(str));

				}			
				
				p.setclasses(ClassesList);
				
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
			
			
			
			
			SoapObject request2 = new SoapObject(NAMESPACE2, METHOD_NAME2);
			HttpTransportSE androidHttpTransport2 = new HttpTransportSE(URL2);
			SoapSerializationEnvelope envelope2 = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope2.setOutputSoapObject(request2);

			try {
				androidHttpTransport2.call(SOAP_ACTION2, envelope2);

				KvmSerializable result2 = (KvmSerializable) envelope2.bodyIn;

				String str = null;
				for (int i = 0; i < result2.getPropertyCount(); i++) {
					str = ((String) result2.getProperty(i).toString());

					ClassesListIDS.add(get(str));

				}			
				
				p.setclassesIDS(ClassesListIDS);
				
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
				
				progressDialog.dismiss();
						final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								activity);
						
						alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÇÊÕÇá Çæ ÇáÓíÑÝÑ");
						alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
						// Retry Button Action
						alertDialog.setPositiveButton("äÚã",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
										GetClassesListAsync asynTask = new GetClassesListAsync(
												activity, progressDialog, context);
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
			
			else 
				try {
				GsonBuilder gsonb = new GsonBuilder();
				Gson gson = gsonb.create();
				String json = gson.toJson(p.getclasses());
				
				GsonBuilder gsonb2 = new GsonBuilder();
				Gson gson2 = gsonb2.create();
				String json2 = gson2.toJson(p.getclassesIDS());
				
				
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				prefs.edit().putString("classes", json).commit();
				prefs.edit().putString("classesIDS", json2).commit();
				
				 activity.startActivity(new Intent(activity, ClassesActivity.class));
				 
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
									
								GetClassesListAsync asynTask = new GetClassesListAsync(
										activity, progressDialog, context);
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

