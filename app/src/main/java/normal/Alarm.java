package normal;

import java.io.IOException;

import com.apps.sams.R;
import com.apps.sams.R.layout;
import com.apps.sams.R.menu;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Alarm extends Activity {
	 private MediaPlayer mMediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 // TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_splash);

	//Button buttonDismiss = (Button)findViewById(R.id.stopbtn);

	 /*buttonDismiss.setOnClickListener(new Button.OnClickListener(){

		
		 
	  @Override
	  public void onClick(View arg0) {

		  mMediaPlayer.stop();
		  
		  
		  
		  
	   finish();
	  }});
	 
	 this.playSound(this, getAlarmUri());
	}
	
	
    private void playSound(Context context, Uri alert) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
	
        } catch (IOException e) {
            System.out.println("OOPS");
        }*/
    }	
	
    private Uri getAlarmUri() {
        Uri alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert == null) {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }
	
}
