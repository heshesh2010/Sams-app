package yesterdayApsent;

import android.os.Bundle;
import java.util.ArrayList;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import yesterdayApsent.yesterdayApsentAdapter;
import com.apps.sams.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import autoButton.StudentHelper;

public class ListApsentActivity extends ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_list_apsent);

		final yesterdayApsentAdapter adapter = new yesterdayApsentAdapter(
				this, getNames(), getids() , getApplicationContext() , getstatus() );

		setListAdapter(adapter);

		Button aboutUs = (Button) findViewById(R.id.getPass);
		Button contactUs = (Button) findViewById(R.id.contactusbtn);
		Button settingbtn = (Button) findViewById(R.id.settingbtn2);
        TextView date = (TextView) findViewById(R.id.textView1);
        TextView class_name = (TextView) findViewById(R.id.textView2);
        
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result = prefs.getString("date", null);
		
		SharedPreferences prefs2 = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());

		String result2 = prefs2.getString("Class_Name", null);
			
		class_name.setText(result2);
		
			 
		date.setText(result);
		
		settingbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(ListApsentActivity.this,
						normal.SettingActivity.class));
			}
		});

		aboutUs.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(ListApsentActivity.this,
						normal.AboutusActivity.class));
			}
		});

		contactUs.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(ListApsentActivity.this,
						normal.ContactusActivity.class));
			}
		});

	}


	public List<StudentHelper> getNames() {

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result = prefs.getString("StudentNamesList", null);

		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = parser.parse(result).getAsJsonArray();

		ArrayList<StudentHelper> lcs = new ArrayList<StudentHelper>();

		for (JsonElement obj : Jarray) {
			StudentHelper cse = gson.fromJson(obj, StudentHelper.class);
			lcs.add(cse);
		}

		

		return (List<StudentHelper>) lcs;

	} 

	
	public List<StudentHelper> getids() {
		
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result2 = prefs2.getString("StudentIdList", null);
		Gson gson2 = new Gson();
		JsonParser parser2 = new JsonParser();
		JsonArray Jarray2 = parser2.parse(result2).getAsJsonArray();

		ArrayList<StudentHelper> lcs2 = new ArrayList<StudentHelper>();

		for (JsonElement obj : Jarray2) {
			StudentHelper cse = gson2.fromJson(obj, StudentHelper.class);
			lcs2.add(cse);
		}

		return (List<StudentHelper>) lcs2;

	} 
	
		
	public List<StudentHelper> getstatus() {
		
		SharedPreferences prefs3 = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result3 = prefs3.getString("StudentStatusList", null);
		Gson gson2 = new Gson();
		JsonParser parser3 = new JsonParser();
		JsonArray Jarray3 = parser3.parse(result3).getAsJsonArray();

		ArrayList<StudentHelper> lcs3 = new ArrayList<StudentHelper>();

		for (JsonElement obj : Jarray3) {
			StudentHelper cse = gson2.fromJson(obj, StudentHelper.class);
			lcs3.add(cse);
		}

		return (List<StudentHelper>) lcs3;

	} 
	
	
	
	
	
	
	
	
	
	


	
	
	

} 
