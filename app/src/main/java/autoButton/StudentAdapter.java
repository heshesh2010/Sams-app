package autoButton;

import com.apps.sams.R;
import com.apps.sams.SendListSync;
import com.apps.sams.SendNoApsent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class StudentAdapter extends ArrayAdapter<StudentHelper> {

	private final List<StudentHelper> StudentNamesList; 						
	private final List<StudentHelper> StudentIdsList; 
	private final List<StudentHelper> StudentCodeList; 
	private final Activity activity;
	private ArrayList<Boolean> itemChecked = new ArrayList<Boolean>();
	private ArrayList<Boolean> itemChecked1 = new ArrayList<Boolean>();
	private ArrayList<Boolean> itemChecked2 = new ArrayList<Boolean>();
	boolean noAttendance = false;
	Context context;


	
	public StudentAdapter(Activity activity,
			List<StudentHelper> StudentNamesList,
			List<StudentHelper> StudentCodeList, Context context,
			Activity listActivity1, boolean test, List<StudentHelper> StudentIdsList) {
		super(activity, R.layout.activity_list, StudentNamesList);
		this.activity = listActivity1;
		this.StudentNamesList = StudentNamesList;
		this.context = context;
		this.StudentIdsList = StudentIdsList;
		this.StudentCodeList = StudentCodeList;

		
		 for (int i = 0; i < this.getCount(); i++) {
		        itemChecked.add(i, false); // initializes all items value with false
		    }
	
	
	 for (int h = 0; h < this.getCount(); h++) {
	        itemChecked1.add(h, false); // initializes all items value with false
	    }


for (int m = 0; m < this.getCount(); m++) {
    itemChecked2.add(m, false); // initializes all items value with false
}

}

	@Override
	public StudentHelper getItem(int position) {
		return super.getItem(position);
	}

	class ViewHolder {
		protected TextView StudentName;
		protected TextView StudentId;
		protected TextView Course_Name;
		protected TextView Class_id;
		protected CheckBox checkbox;
		protected CheckBox checkbox1;
		protected CheckBox checkbox2;

		protected TableRow row;
	}


	HashMap<Integer, Integer> checkBoxState = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> checkBoxStateBackUp = new HashMap<Integer, Integer>();

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		if (convertView == null) {

			LayoutInflater inflator = activity.getLayoutInflater();
			view = inflator.inflate(R.layout.rep, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.StudentName = (TextView) view
					.findViewById(R.id.TextView07);

			viewHolder.StudentId = (TextView) view
					.findViewById(R.id.TextView08);

			viewHolder.Course_Name = (TextView) view
					.findViewById(R.id.textView3);

			viewHolder.Class_id = (TextView) view.findViewById(R.id.textView2);

			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.CheckBox);

			viewHolder.checkbox1 = (CheckBox) view
					.findViewById(R.id.CheckBox01);

			viewHolder.checkbox2 = (CheckBox) view
					.findViewById(R.id.CheckBox02);

			
			
			viewHolder.row = (TableRow) view.findViewById(R.id.TableRow05);

			Typeface font = Typeface.createFromAsset(activity.getAssets(),
					"extrafine.ttf");

			Typeface fontar = Typeface.createFromAsset(activity.getAssets(),
					"extrafinear.ttf");

			viewHolder.StudentName.setTypeface(fontar);

			viewHolder.StudentId.setTypeface(font);

			view.setTag(viewHolder);
			viewHolder.row.setTag(StudentNamesList.get(position));

			viewHolder.checkbox.setTag(StudentNamesList.get(position));

			viewHolder.checkbox1.setTag(StudentNamesList.get(position));

			viewHolder.checkbox2.setTag(StudentNamesList.get(position));
		} else{
			view = convertView;

			((ViewHolder) view.getTag()).checkbox.setTag(StudentNamesList
					.get(position));

			((ViewHolder) view.getTag()).checkbox1.setTag(StudentNamesList
					.get(position));

			((ViewHolder) view.getTag()).checkbox2.setTag(StudentNamesList
					.get(position));

			((ViewHolder) view.getTag()).row.setTag(StudentNamesList
					.get(position));

		}

		
		Button sumbit = (Button) activity.findViewById(R.id.sumbitbtn);
		final CheckBox checkNoAttndance = (CheckBox) activity.findViewById(R.id.CheckBox20);
		final ViewHolder holder = (ViewHolder) view.getTag();
		
		holder.StudentName.setText(StudentNamesList.get(position).getName());

		holder.StudentId.setText(StudentCodeList.get(position).getName());
		 
		holder.checkbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							 itemChecked.set(position, true);
							holder.checkbox.setSelected(true);
							holder.checkbox1.setChecked(false);
							holder.checkbox2.setChecked(false);
							holder.row.setBackgroundResource(R.drawable.green);
							ViewGroup row1 = (ViewGroup) holder.checkbox
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								checkBoxState.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 0);
								checkBoxStateBackUp.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 0);

							}

						} else if (holder.checkbox.isChecked() == false) {
							 itemChecked.set(position, false);
							holder.row.setBackgroundResource(R.drawable.rep);
							holder.checkbox.setSelected(false);

							ViewGroup row1 = (ViewGroup) holder.checkbox
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								checkBoxState.remove(StudentIdsList.get(position).getName());
								checkBoxStateBackUp.remove(StudentIdsList.get(position).getName());
							}

						}

					}
				});

		holder.checkbox1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							 itemChecked1.set(position, true);
							holder.checkbox1.setSelected(true);
							holder.checkbox.setChecked(false);
							holder.checkbox2.setChecked(false);
							ViewGroup row1 = (ViewGroup) holder.checkbox1
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								
								
								checkBoxStateBackUp.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 1);
								
								checkBoxState.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 1);
								holder.row
										.setBackgroundResource(R.drawable.red);

							}

						} else if (holder.checkbox1.isChecked() == false) {
							 itemChecked.set(position, false);
							holder.row.setBackgroundResource(R.drawable.rep);
							holder.checkbox1.setSelected(false);

							ViewGroup row1 = (ViewGroup) holder.checkbox1
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								checkBoxState.remove(Integer
										.parseInt(StudentIdsList.get(position).getName()));
								checkBoxStateBackUp.remove(Integer
										.parseInt(StudentIdsList.get(position).getName()));
							}
						}

					}
				});

		holder.checkbox2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							 itemChecked2.set(position, true);
							holder.checkbox2.setSelected(true);
							holder.checkbox1.setChecked(false);
							holder.checkbox.setChecked(false);
							ViewGroup row1 = (ViewGroup) holder.checkbox1
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								checkBoxStateBackUp.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 2);
								
								checkBoxState.put(Integer
										.parseInt(StudentIdsList.get(position).getName()), 2);
								holder.row
										.setBackgroundResource(R.drawable.blue);
							}
						} else if (holder.checkbox2.isChecked() == false) {
							 itemChecked2.set(position, false);
							holder.row.setBackgroundResource(R.drawable.rep);
							holder.checkbox2.setSelected(false);

							ViewGroup row1 = (ViewGroup) holder.checkbox2
									.getParent();

							View view1 = ((ViewGroup) row1).getChildAt(4);
							if (view1 instanceof TextView) {

								holder.StudentId = (TextView) view1;

								checkBoxStateBackUp.remove(Integer
										.parseInt(StudentIdsList.get(position).getName()));
								
								checkBoxState.remove(Integer
										.parseInt(StudentIdsList.get(position).getName()));
							}

						}

					}
				});

		
		
		/*
		checkNoAttndance.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					
		noAttendance = true ;
		Toast.makeText(activity.getApplicationContext(), "‘€«·", Toast.LENGTH_LONG).show();
				}
				
				else if(checkNoAttndance.isChecked()==false){
					Toast.makeText(activity.getApplicationContext(), "„ﬁ›Ê·", Toast.LENGTH_LONG).show();
					noAttendance = false ;
					
				}
			}
			});
		
*/
		

		// holder.row.setBackgroundResource(R.drawable.green);

		//holder.checkbox1.setChecked(SparseBooleanArray1.get(position));
		// holder.row.setBackgroundResource(R.drawable.red);

		//holder.checkbox2.setChecked(SparseBooleanArray2.get(position));
		// holder.row.setBackgroundResource(R.drawable.blue);

		sumbit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(checkNoAttndance.isChecked()==true){

					ProgressDialog progressDialog = new ProgressDialog(activity);
					progressDialog
							.setMessage("Ã«—Ì «—”«· «·»Ì«‰«  ° «·—Ã«¡ «·«‰ Ÿ«—");
					progressDialog.show();
// hena
					SendNoApsent asynTask = new SendNoApsent(context, activity,
							progressDialog);
					asynTask.execute();
					
					checkBoxState.clear();
							  
				}
				else if (checkBoxStateBackUp.isEmpty()) {

					Toast.makeText(activity, "«·—Ã«¡ «Œ Ì«— «”„«¡ «·ÿ·»… «·€«∆»Ê‰ ",
							Toast.LENGTH_SHORT).show();

				}

				else {

					ProgressDialog progressDialog = new ProgressDialog(activity);
					progressDialog
							.setMessage("Ã«—Ì «—”«· «·»Ì«‰«  ° «·—Ã«¡ «·«‰ Ÿ«—");
					progressDialog.show();

					SendListSync asynTask = new SendListSync(context, activity,
							checkBoxStateBackUp, progressDialog);
					asynTask.execute();

				}

			}
		});

		holder.checkbox.setChecked(itemChecked.get(position));
		holder.checkbox1.setChecked(itemChecked1.get(position));
		holder.checkbox2.setChecked(itemChecked2.get(position));
		return view;

	}

}
