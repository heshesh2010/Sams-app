package normal;

import com.apps.sams.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;


	public class SplashActivity extends Activity {
		// Delay by ms For 2 Seconds !
			    private final static int MSG_CONTINUE = 1234;
			    private final static long DELAY = 2000;

			    @Override
			    protected void onCreate(Bundle args)
			    {
			        super.onCreate(args);
			        setContentView(R.layout.activity_splash);

			        mHandler.sendEmptyMessageDelayed(MSG_CONTINUE, DELAY);
			    }//

			    @Override
			    protected void onDestroy()
			    {
			        mHandler.removeCallbacksAndMessages(MSG_CONTINUE);
			        super.onDestroy();
			    }

			    private void _continue()
			    {
			        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
			        finish();
			    }

			    private final Handler mHandler = new Handler()
			    {
			        public void handleMessage(android.os.Message msg)
			        {
			            switch(msg.what){
			                case MSG_CONTINUE:
			                    _continue();
			                    break;
			            }
			        }
			    };

}
