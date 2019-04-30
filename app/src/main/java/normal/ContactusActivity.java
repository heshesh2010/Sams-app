package normal;


import java.util.Properties;
import android.view.ViewGroup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.apps.sams.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ContactusActivity extends Activity  {
	
	 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactus);

		// Declaring TextVies and edit boxes .
		final TextView subjectTxt = (TextView) findViewById(R.id.nid_get);
		final TextView emailTxtusfrom = (TextView) findViewById(R.id.emailfromTxtus);
		final TextView messageTxt = (TextView) findViewById(R.id.messageTxt);
		
		Button lButton = (Button)findViewById(R.id.sendbtn);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "extrafine.ttf");  
		subjectTxt.setTypeface(font);
		emailTxtusfrom.setTypeface(font);
		messageTxt.setTypeface(font);
		
		String array_spinner[];
		array_spinner=new String[3];
		array_spinner[0]="general";
		array_spinner[1]="quisiton";
		array_spinner[2]="suggsution";
		
		final Spinner spinner = (Spinner) findViewById( R.id.spinner1 ); 
	 
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.special_spinner_dropdown, array_spinner) {

     public View getView(int position, View convertView, ViewGroup parent) {
             View v = super.getView(position, convertView, parent);

             Typeface externalFont=Typeface.createFromAsset(getAssets(), "extrafine.ttf");
             ((TextView) v).setTypeface(externalFont);
             

             return v;
     }


     public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
              View v =super.getDropDownView(position, convertView, parent);

             Typeface externalFont=Typeface.createFromAsset(getAssets(), "extrafine.ttf");
             ((TextView) v).setTypeface(externalFont);
             return v;
     }
};
spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );		
spinner.setAdapter(spinnerArrayAdapter);


					    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

					   StrictMode.setThreadPolicy(policy); 

	
	lButton.setOnClickListener(new OnClickListener() {
				
			public void onClick(View v) {
				
				 Properties props = new Properties();
				  props.put("mail.smtp.host", "smtp.gmail.com");
				  props.put("mail.smtp.socketFactory.port", "465");
				  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				  props.put("mail.smtp.auth", "true");
				  props.put("mail.smtp.port", "465");

				 
				  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				                    protected PasswordAuthentication getPasswordAuthentication() {
				  return new PasswordAuthentication("sams.app.com@gmail.com", "hashoma2020@");// Email Sender Account info  .
				  }
				  });
				  
				  ;  
	  
				  
				  try {
				  Message message = new MimeMessage(session);
				  message.setFrom(new InternetAddress(emailTxtusfrom.getText().toString()));
				
				  // sending each type of message to vairty emails . 
				  switch(spinner.getSelectedItemPosition()) {
				    case 1:
				    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hesham.elnemr@gmail.com"));
				        break;
				    case 2:
				    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("heshesh2010@yahoo.com"));
				        break;
				    default:
				    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("heshesh2010@hotmail.com"));
				}
				 
	
				message.setSubject(subjectTxt.getText().toString());		  
				message.setContent("Type is : " +  spinner.getSelectedItem().toString() +"\n Full Mesg  : " + messageTxt.getText().toString() , "text/html; charset=utf-8");
				
				  Transport.send(message);

				  } catch (MessagingException e) {
				  throw new RuntimeException(e);
				  }
				  
		}
		});
	

	} // end of OnCreate
} // End of the class

