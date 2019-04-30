package normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Intent;

import android.os.IBinder;
import android.os.PowerManager;

import android.widget.Toast;

public class MyAlarmService extends BroadcastReceiver 
{
	@Override
	public void onReceive(Context context, Intent intent) {
	 // TODO Auto-generated method stub

	 Intent scheduledIntent = new Intent(context, Alarm.class);
	 scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 context.startActivity(scheduledIntent);

	}



}
