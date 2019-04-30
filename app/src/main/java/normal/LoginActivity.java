package normal;

import com.apps.sams.R;
import com.apps.sams.ClassesActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {

	public static Context contextOfApplication;

	// ›Õ’ «–« ﬂ«‰ ›Ì « ’«· »«·«‰ —‰  «Ê ·«
	boolean isOnline() {
		ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

		if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

			return false;
		}
		return true;
	}

	
	// For shared prfences .
	String PREFS = "MyPrefs";
	SharedPreferences mPrefs;

	private void saveLoginDetails() {
		final EditText LoginId = (EditText) findViewById(R.id.IDLogin);
		final EditText LoginPass = (EditText) findViewById(R.id.LoginPass);

		String login = LoginId.getText().toString();
		String pass = LoginPass.getText().toString();

		Editor e = mPrefs.edit();
		e.putBoolean("rememberMe", true);
		e.putString("login", login);
		e.putString("password", pass);

		e.commit();

	}

	private void removeLoginDetails() {
		Editor e = mPrefs.edit();
		e.putBoolean("rememberMe", false);
		e.remove("login");
		e.remove("password");
		e.commit();
	}

	public static Context getContextOfApplication() {
		return contextOfApplication;
	}



	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		contextOfApplication = getApplicationContext();
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	
		
		alertDialog.setTitle("·« ÌÊÃœ « ’«· »«·«‰ —‰ ");
		alertDialog.setMessage("Â·  —Ìœ «·⁄„· «Ê› ·«Ì‰ø ");
		alertDialog.setPositiveButton("‰⁄„",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(LoginActivity.this,
								ClassesActivity.class));
					}
				});
		
		alertDialog.setNegativeButton("·«",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						// Note Empty HERE !!!
					}
				});

		if (isOnline() == false) {

			alertDialog.show();

		}

		Button loginBtn = (Button) findViewById(R.id.loginbtn);
		Button restPass = (Button) findViewById(R.id.forgetpassordbtn);
		final CheckBox RembmberMe = (CheckBox) findViewById(R.id.radioGroup1);
		final EditText LoginId = (EditText) findViewById(R.id.IDLogin);
		final EditText LoginPass = (EditText) findViewById(R.id.LoginPass);
		contextOfApplication = getApplicationContext();
		mPrefs = getSharedPreferences(PREFS, 0);
		boolean rememberMe = mPrefs.getBoolean("rememberMe", false);

		final String login1 = LoginId.getText().toString();
		final String pass1 = LoginPass.getText().toString();

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(LoginActivity.this);
		prefs.edit().putString("login1", login1).apply();
		prefs.edit().putString("password1", pass1).apply();

		// using coustom font
		Typeface font = Typeface.createFromAsset(getAssets(), "extrafine.ttf");
		LoginId.setTypeface(font);
		LoginPass.setTypeface(font);

		if (rememberMe == true) {
			// get previously stored login details
			String login = mPrefs.getString("login", null);
			String pass = mPrefs.getString("password", null);

			if (login != null && pass != null) {
				// fill input boxes with stored login and pass

				LoginId.setText(login);
				LoginPass.setText(pass);
				RembmberMe.setChecked(true);
			}
		}

		// This for Remember PassWord RadioButton !
		RembmberMe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (RembmberMe.isChecked()) {
					saveLoginDetails();
				} else {
					removeLoginDetails();
				}
			}
		});

		
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				final EditText LoginId = (EditText) findViewById(R.id.IDLogin);
				final EditText LoginPass = (EditText) findViewById(R.id.LoginPass);

				String login = LoginId.getText().toString();
				String pass = LoginPass.getText().toString();

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(LoginActivity.this);
				prefs.edit().putString("login1", login).commit();
				prefs.edit().putString("password1", pass).commit();
						
				

				ProgressDialog progressDialog = new ProgressDialog(
						LoginActivity.this);
				progressDialog.setMessage("Ã«—Ì  ”ÃÌ· «·œŒÊ· «·—Ã«¡ «·«‰ Ÿ«—");
				progressDialog.show();
// ··»œ¡ »⁄„·Ì… «·»«ﬂ Ã—«Ê‰œ 
				LoginAsync MyTask = new LoginAsync(
						LoginActivity.this, progressDialog,
						getApplicationContext());
				MyTask.execute();

			}
		});

		// To Going to rest Pass Activity when clicked .
		restPass.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,
						RestpassActivity.class));
			}
		});

	} // End of OnCreate Method

} // End of the Class LoginActivity