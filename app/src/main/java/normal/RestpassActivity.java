package normal;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.apps.sams.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RestpassActivity extends Activity {
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restpass);
		
		
		final TextView nid_get = (TextView) findViewById(R.id.nid_get);
		Button GetPass = (Button)findViewById(R.id.getPass);	
		Typeface font = Typeface.createFromAsset(getAssets(), "extrafine.ttf");  
		nid_get.setTypeface(font);
	    
	        GetPass.setOnClickListener(new OnClickListener() {
	   		 @Override
	   	 			public void onClick(View v) {
	   			 
					ProgressDialog progressDialog = new ProgressDialog(
							RestpassActivity.this);
					progressDialog.setMessage("Ã«—Ì  ”ÃÌ· «·œŒÊ· «·—Ã«¡ «·«‰ Ÿ«—");
					progressDialog.show();
					
	   			RestpassAsyncTask MyTask = new RestpassAsyncTask(
	   					RestpassActivity.this, progressDialog,
							getApplicationContext(), nid_get.getText().toString());
					MyTask.execute();
					
	   			
 
	   		 }
	   		}); 
	        
	}
}
	
