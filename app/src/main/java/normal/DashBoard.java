package normal;

import com.apps.sams.R;
import com.apps.sams.ClassesActivity;
import com.apps.sams.GetClassesListAsync;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.preference.PreferenceManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import autoButton.GetStudentsListAsync;

public class DashBoard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash_board);
		
		Button auto = (Button)findViewById(R.id.autobtn);
		Button reserverbtn = (Button)findViewById(R.id.reserverbtn);
		Button offlinebtn = (Button)findViewById(R.id.offlinebtn);
		Button settingbtn2 = (Button)findViewById(R.id.settingbtn2);
		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		int TID = prefs.getInt("TID", -1);
		
		/*
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(this);
		prefs2.edit().putInt("TID", TID).commit();
	*/			
		
		
		// ··«Ê Ê„« ﬂ , „Õ «Ã ›ﬁÿ —ﬁ„ «·„œ—”
		auto.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {

				 ProgressDialog progressDialog = new ProgressDialog(DashBoard.this);
				 progressDialog.setMessage("Ã«—Ì  Õ„Ì· «·»Ì«‰«  ° «·—Ã«¡ «·«‰ Ÿ«—");
				 progressDialog.show();
				 	
				 GetStudentsListAsync asynTask = new GetStudentsListAsync(DashBoard.this,getApplicationContext() ,progressDialog);
				 asynTask.execute();
				
				 
			 }
			  });
		 
		
		
		reserverbtn.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
						 
				 
				 final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						DashBoard.this);

					alertDialog.setTitle(" Õ–Ì—");
					alertDialog.setMessage("«‰   œŒ· ·’›ÕÂ „œ—”Ì‰ «·«Õ Ì«ÿ ° Â·  Êœ «·«” ﬂ„«· ø ");
					// Retry Button Action
					alertDialog.setPositiveButton("‰⁄„",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									
									 ProgressDialog progressDialog = new ProgressDialog(DashBoard.this);
									 progressDialog.setMessage("Ã«—Ì  Õ„Ì· «·»Ì«‰«  ° «·—Ã«¡ «·«‰ Ÿ«—");
									 progressDialog.show();			
									 GetClassesListAsync asynTask = new GetClassesListAsync(DashBoard.this,progressDialog,getApplicationContext());
									 asynTask.execute();
									 
								 }
								  });
					alertDialog.setNegativeButton("·«",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
								
								}
							});

					alertDialog.show();
								}
			 
			 
							});

				 

		offlinebtn.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				// Intent intent = new Intent(DashBoard.this, ClassesActivity.class);
				 //startActivity(intent);
				 Toast.makeText(getApplicationContext(), "√·Œ«’Ì… „⁄ÿ·… „ƒﬁ «", Toast.LENGTH_SHORT).show();
			 }
			  });
		 
		
		settingbtn2.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
						 
			//	 Intent intent = new Intent(DashBoard.this, SettingActivity.class);
				 //startActivity(intent);
				 
				 Toast.makeText(getApplicationContext(), "√·Œ«’Ì… „⁄ÿ·… „ƒﬁ «", Toast.LENGTH_SHORT).show();
				 
				 
				 
			 }
			  });

	}



}
