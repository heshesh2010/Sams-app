package normal;

import java.util.Calendar;
import java.util.GregorianCalendar;
import com.apps.sams.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class SettingActivity extends Activity  {
	private PendingIntent pendingIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		final Activity activity = this;
		final CheckBox mm =	(CheckBox) findViewById(R.id.CheckBox01);
		String array_spinner[];
		array_spinner=new String[3];
		array_spinner[0]="15 Min";
		array_spinner[1]="10 Min";
		
		final Spinner spinner = (Spinner) findViewById(R.id.spinner2 ); 
		 
		   /* ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
		            this, android.R.layout.simple_spinner_item, array_spinner);
		    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );*/

		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.special_spinner_dropdown, array_spinner) {

     public View getView(int position, View convertView, ViewGroup parent) {
             View v = super.getView(position, convertView, parent);

             Typeface externalFont=Typeface.createFromAsset(getAssets(), "extrafine.ttf");
             ((TextView) v).setTypeface(externalFont);
             

             return v;
     }


     public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
              View v =super.getDropDownView(position, convertView, parent);

             Typeface externalFont=Typeface.createFromAsset(getAssets(), "extrafine.ttf");
             ((TextView) v).setTypeface(externalFont);
             return v;
     }
};

spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );		
 spinner.setAdapter(spinnerArrayAdapter);
		
		

	
	
  mm.setOnClickListener(new OnClickListener() {
    	
		@Override
		 public void onClick(View v) {

			  Intent myIntent = new Intent(SettingActivity.this, MyAlarmService.class);

			  pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, myIntent, 0);



			           AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

			           Calendar calendar = Calendar.getInstance();

			           calendar.setTimeInMillis(System.currentTimeMillis());

			           calendar.add(Calendar.SECOND, 10);
			           long interval = 30 * 1000; //
			           alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);

			  Toast.makeText(SettingActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
			  finish();
		}
	});
  
	}


}
