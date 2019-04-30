package com.apps.sams;

import android.os.Bundle;
import java.util.ArrayList;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import normal.AboutusActivity;
import normal.ContactusActivity;
import normal.SettingActivity;

import com.apps.sams.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import autoButton.StudentAdapter;
import autoButton.StudentHelper;

public class ListActivity1 extends ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_list);
		
		// Create an array of Strings, that will be put to our ListActivity
		CheckBox no = (CheckBox)findViewById(R.id.CheckBox20);
		boolean test = no.isChecked();	
		
		final StudentAdapter adapter = new StudentAdapter(
				this, getNames(), getcode(), getApplicationContext() ,ListActivity1.this, test , getids() );

		setListAdapter(adapter);
		Button aboutUs = (Button) findViewById(R.id.getPass);
		Button contactUs = (Button) findViewById(R.id.contactusbtn);
		Button settingbtn = (Button) findViewById(R.id.settingbtn2);
		TextView courseNameView = (TextView) findViewById(R.id.textView1);
		TextView classIdView = (TextView) findViewById(R.id.textView2);

		
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String me = prefs2.getString("Class_Name", "0");
		
		
		Intent intent = this.getIntent();
		String strdata = intent.getExtras().getString("Uniqid");
		 if(strdata.equals("From_Activity_C"))
         {
				
				
			    courseNameView.setText("Õ’… «Õ Ì«ÿ");
				classIdView.setText(me);
         }
		 else if (strdata.equals("From_Activity_A")){
				SharedPreferences prefs3 = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext());

				String result2 = prefs3.getString("Course_Name", null);
	    courseNameView.setText(result2);
		classIdView.setText(me);
		}
		
		
		Typeface font = Typeface.createFromAsset(getAssets(), "extrafine.ttf");
		Typeface fontar = Typeface.createFromAsset(getAssets(),
				"extrafinear.ttf");
		// searchEdit.setTypeface(fontar);
		courseNameView.setTypeface(fontar);
		classIdView.setTypeface(font);


		settingbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(), "«·«⁄œ«œ  ·«  ⁄„· „ƒﬁ «", Toast.LENGTH_SHORT).show(); 
				/*startActivity(new Intent(ListActivity1.this,
						SettingActivity.class));*/
				
			}
		});

		aboutUs.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(ListActivity1.this,
						AboutusActivity.class));
			}
		});

		contactUs.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(ListActivity1.this,
						ContactusActivity.class));
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
	
	public List<StudentHelper> getcode() {
		
		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result2 = prefs2.getString("StudentCodeList", null);
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
	

} // end of class
