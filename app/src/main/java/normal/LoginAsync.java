package normal;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.apps.sams.GetClassesListAsync;

public class LoginAsync extends AsyncTask<Void, Void, String> {

	Activity mActivity;
	Context context;
	ProgressDialog progressDialog;

	public LoginAsync(Activity activity, ProgressDialog progressDialog,
			Context context) {
		super();
		this.progressDialog = progressDialog;
		this.mActivity = activity;
		this.context = context;
	}
	final String NAMESPACE = "http://ws.sams.net";
	final String URL = "http://control.sams-app.net/webService/services/LoginActvityWs?WSDL"; 
	final String METHOD_NAME = "login";
	final String SOAP_ACTION = "http://ws.sams.net/login";
	private Object resultRequestSOAP = null;
	@Override
	protected String doInBackground(Void... voids) {

		try {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			String user = prefs.getString("login1", "-1");
			String pass = prefs.getString("password1", "-1");
			// Calling Login Method
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			// First Reques for USER NAME .
			PropertyInfo pi = new PropertyInfo();
			pi.setName("username");
			pi.setValue(user);
			pi.setType(String.class);
			request.addProperty(pi);

			// Second Reques for USER NAME .
			PropertyInfo pi2 = new PropertyInfo();
			pi2.setName("password");
			pi2.setValue(pass);
			pi2.setType(String.class);
			request.addProperty(pi2);

			// Getting Request Result , Will get TID .
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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

		// If any error oceeared duaring get TID
		if (result.equalsIgnoreCase("error")) {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					mActivity);

			alertDialog.setTitle("íæÌÏ ãÔßáÉ ÈÇáÇÊÕÇá Çæ ÇáÓíÑÝÑ");
			alertDialog.setMessage("åá ÊæÏ ÇáãÍÇæáÉ ãÌÏÏÇ ¿ ");
			// Retry Button Action
			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							LoginAsync asynTask = new LoginAsync(mActivity,
									progressDialog, mActivity
											.getApplicationContext());
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
		// IF pass or user Filed .
		else if (Integer.parseInt(result.toString()) == -1) {

			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					mActivity);

			alertDialog.setTitle("ÇÓã ÇáãÓÊÎÏã Çæ ßáãÉ ÇáãÑæÑ ÎÇØÆÉ");
			alertDialog.setMessage("åá ÊæÏ ÇÚÇÏÉ ÊÓÌíá ÇáÏÎæá ");

			alertDialog.setPositiveButton("äÚã",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							progressDialog.dismiss();

						}
					});
			alertDialog.setNegativeButton("áÇ",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							progressDialog.dismiss();
							Toast.makeText(mActivity.getApplicationContext(),
									"thank you for using SAMS app",
									Toast.LENGTH_LONG).show();
							mActivity.finish();
						}
					});

			alertDialog.show();

		}

	else if (Integer.parseInt(result.toString()) >= 100000) {

			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			prefs.edit().putInt("TID", Integer.parseInt(result.toString()))
					.commit();

			GetClassesListAsync asynTask = new GetClassesListAsync(mActivity,
					progressDialog, context);
			asynTask.execute();

		}

		// For correct Login !
		else {
			progressDialog.dismiss();
			if (Integer.parseInt(result.toString()) >= 1 && Integer.parseInt(result.toString()) <= 99999) {

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				prefs.edit().putInt("TID", Integer.parseInt(result.toString()))
						.commit();

				Intent intent1 = new Intent(context, DashBoard.class);
				mActivity.startActivity(intent1);

			}
		}

	}
}