package normal;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class RestpassAsyncTask  extends AsyncTask<Void, Void, String> {
	final String NAMESPACE = "http://ws.sams.net";
	final String URL="http://control.sams-app.net/webService/services/RestpassActivityWS?WSDL"; // usint localhost
	final String METHOD_NAME = "returnInfo";
	final String SOAP_ACTION =  "http://ws.sams.net/returnInfo";	
	
	private Object resultRequestSOAP = null;
	Activity mActivity;
	Context context;
	ProgressDialog progressDialog;
	String value ; 
	
	public RestpassAsyncTask(RestpassActivity activity,
			ProgressDialog progressDialog, Context context,
			String value) {
		
		this.progressDialog = progressDialog;
		this.mActivity = activity;
		this.context = context;
		this.value = value ;
		
		
	}


	@Override
	protected String doInBackground(Void... voids) {
		
			 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
		 PropertyInfo pi = new PropertyInfo();
	        pi.setName("Tem");
	        pi.setValue(value);
	        pi.setType(String.class);
	        request.addProperty(pi);
		   
		   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		  
	        try {
				androidHttpTransport.call(SOAP_ACTION, envelope);
			
			  
			  resultRequestSOAP = (Object) envelope.getResponse();

		return resultRequestSOAP.toString();
				
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
	

	@Override
	protected void onPostExecute(String result) {
		
		
		
	}
	

	
	
}
