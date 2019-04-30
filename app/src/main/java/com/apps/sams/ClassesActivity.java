package com.apps.sams;

import java.util.ArrayList;
import java.util.List;
import normal.AboutusActivity;
import normal.ContactusActivity;
import normal.DashBoard;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.apps.sams.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ClassesActivity extends Activity {
	GridView gridView;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_classtable);

		gridView = (GridView) findViewById(R.id.gridView1);

		final ClassAdapter adapter = new ClassAdapter(ClassesActivity.this,
				getClasses(), getApplicationContext() ,getClassesIDS() );

		gridView.setAdapter(adapter);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		int TID = prefs.getInt("TID", 1);

		SharedPreferences prefs2 = PreferenceManager
				.getDefaultSharedPreferences(this);
		prefs2.edit().putInt("TID", TID).commit();

		Button contactusbtn = (Button) findViewById(R.id.button2);
		Button aboutusbtn = (Button) findViewById(R.id.button3);
		Button dashbutton = (Button) findViewById(R.id.button1);

		contactusbtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(ClassesActivity.this,
						ContactusActivity.class));
			}

		});

		aboutusbtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(ClassesActivity.this,
						AboutusActivity.class));
			}
		});

		dashbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(ClassesActivity.this, DashBoard.class));
			}
		});

	}

	public List<ClassesHelper> getClasses() {

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result = prefs.getString("classes", null);

		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = parser.parse(result).getAsJsonArray();

		ArrayList<ClassesHelper> lcs = new ArrayList<ClassesHelper>();

		for (JsonElement obj : Jarray) {
			ClassesHelper cse = gson.fromJson(obj, ClassesHelper.class);
			lcs.add(cse);
		}

		return (List<ClassesHelper>) lcs;
	}
	
	
	public List<ClassesHelper> getClassesIDS() {

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		String result = prefs.getString("classesIDS", null);

		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = parser.parse(result).getAsJsonArray();

		ArrayList<ClassesHelper> lcs = new ArrayList<ClassesHelper>();

		for (JsonElement obj : Jarray) {
			ClassesHelper cse = gson.fromJson(obj, ClassesHelper.class);
			lcs.add(cse);
		}

		return (List<ClassesHelper>) lcs;
	}
	
	

}
