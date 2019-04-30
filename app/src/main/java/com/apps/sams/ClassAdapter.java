package com.apps.sams;

import java.util.List;

import com.apps.sams.R;

import yesterdayApsent.GetYesterdayStudentListAsync;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ClassAdapter extends ArrayAdapter<ClassesHelper> {

	private final List<ClassesHelper> ClassList;
	private final List<ClassesHelper> ClassListIDS;
	private final Activity activity;
	Context context;

	public ClassAdapter(Activity activity, List<ClassesHelper> ClassList,
			Context context, List<ClassesHelper> ClassListIDS) {
		super(activity, R.layout.activity_list, ClassList);
		this.activity = activity;
		this.context = context;
		this.ClassList = ClassList;
		this.ClassListIDS = ClassListIDS;
	}

	class ViewHolder {
		protected Button Button1;
		protected Button Button2;
		protected Button Button3;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = activity.getLayoutInflater();
			view = inflator.inflate(R.layout.rep2, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.Button1 = (Button) view.findViewById(R.id.Button01);

			view.setTag(viewHolder);

			viewHolder.Button1.setTag(ClassList.get(position));

		} else {
			view = convertView;

			((ViewHolder) view.getTag()).Button1
					.setTag(ClassList.get(position));

		}

		final ViewHolder holder = (ViewHolder) view.getTag();

		holder.Button1.setText(ClassList.get(position).getName());

		holder.Button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Toast toast = Toast.makeText(context, ClassListIDS.get(position)
						.getName(), Toast.LENGTH_LONG);
				toast.show();

				ProgressDialog progressDialog = new ProgressDialog(activity);
				progressDialog
						.setMessage("ÌÇÑí ÊÍãíá ÇáÈíÇäÇÊ ¡ ÇáÑÌÇÁ ÇáÇäÊÙÇÑ");
				progressDialog.show();

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				int TID = prefs.getInt("TID", 1);

				SharedPreferences prefs2 = PreferenceManager
						.getDefaultSharedPreferences(context);
				prefs2.edit().putInt("TID", TID).commit();

				SharedPreferences prefs3 = PreferenceManager
						.getDefaultSharedPreferences(context);
				prefs3.edit()
						.putString("Class_ID",
								ClassListIDS.get(position).getName()).commit();
				prefs3.edit()
				.putString("Class_Name",
						ClassList.get(position).getName()).commit();
				

				if (TID >= 100000) {
					GetYesterdayStudentListAsync asynTask = new GetYesterdayStudentListAsync(
							activity, context, progressDialog);
					asynTask.execute();

				} else {
					GetReserveListAsync asynTask = new GetReserveListAsync(
							activity, context, progressDialog);
					asynTask.execute();
				}
			}
		});

		return view;

	}

}
