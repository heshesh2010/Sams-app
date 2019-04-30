package yesterdayApsent;

import java.util.List;
import com.apps.sams.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.TableRow;
import android.widget.TextView;
import autoButton.StudentHelper;

public class yesterdayApsentAdapter extends ArrayAdapter<StudentHelper> implements
		Filterable {

//	final String NAMESPACE = "http://ws.sams.net";
	//String URL = "http://sams-app.net/sams1/services/InsertDataWS?WSDL";
	//final String METHOD_NAME = "insertApsentData";
	//final String SOAP_ACTION = "http://ws.sams.net/insertApsentData";

	private final List<StudentHelper> StudentNamesList; // for the student names .
	private final List<StudentHelper> StudentIdsList; // for the student ids .
	private final List<StudentHelper> StudentStatusList; // for the student ids .
	private final Activity context;

	public yesterdayApsentAdapter(Activity context,
			List<StudentHelper> StudentNamesList, List<StudentHelper> StudentIdsList, Context context2, List<StudentHelper> StudentStatusList) {
		super(context, R.layout.activity_list_apsent, StudentNamesList);
		this.context = context;
		this.StudentNamesList = StudentNamesList;
		this.StudentIdsList = StudentIdsList;
		this.StudentStatusList = StudentStatusList ; 
		
	}

	class ViewHolder {
		protected TextView StudentName;
		protected TextView StudentId;
		protected CheckBox checkbox;
		protected CheckBox checkbox1;
		protected CheckBox checkbox2;
		protected TableRow row;
	}



	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.rep, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.StudentName = (TextView) view
					.findViewById(R.id.TextView07);

			viewHolder.StudentId = (TextView) view
					.findViewById(R.id.TextView08);

			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.CheckBox);

			viewHolder.checkbox1 = (CheckBox) view
					.findViewById(R.id.CheckBox01);
			
			viewHolder.checkbox2 = (CheckBox) view
					.findViewById(R.id.CheckBox02);
			

			viewHolder.row = (TableRow) view.findViewById(R.id.TableRow05);

			Typeface font = Typeface.createFromAsset(context.getAssets(),
					"extrafine.ttf");

			Typeface fontar = Typeface.createFromAsset(context.getAssets(),
					"extrafinear.ttf");

			viewHolder.StudentName.setTypeface(fontar);

			viewHolder.StudentId.setTypeface(font);

			view.setTag(viewHolder);
			viewHolder.row.setTag(StudentNamesList.get(position));

			viewHolder.checkbox.setTag(StudentNamesList.get(position));

			viewHolder.checkbox1.setTag(StudentNamesList.get(position));

			viewHolder.checkbox2.setTag(StudentNamesList.get(position));
		} else {
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

		final ViewHolder holder = (ViewHolder) view.getTag();

		holder.StudentName.setText(StudentNamesList.get(position).getName());

		holder.StudentId.setText(StudentIdsList.get(position).getName());

		holder.checkbox.setChecked(StudentNamesList.get(position).isSelected());

		holder.checkbox1
				.setChecked(StudentNamesList.get(position).isSelected());

		holder.checkbox2
				.setChecked(StudentNamesList.get(position).isSelected());

	String test = StudentStatusList.get(position).getName();
	
	
		if(test.equalsIgnoreCase("0")){
			holder.checkbox.setChecked(true);
			holder.row.setBackgroundResource(R.drawable.green);
			holder.checkbox.setClickable(false);
		}
		
		else if(test.equalsIgnoreCase("1")){
			holder.checkbox1.setChecked(true);
			holder.row.setBackgroundResource(R.drawable.red);
			holder.checkbox.setClickable(false);
		}
		
		else{
			
			
			holder.checkbox2.setChecked(true);
			holder.row.setBackgroundResource(R.drawable.blue);
			holder.checkbox.setClickable(false);
		}
		
		
		
		return view;

	}

}
