package infinitynurse.frontend;

import infinitynurse.backend.Nurse;
import infinitynurse.backend.Patient;
import infinitynurse.backend.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity which allows the user to enter a valid healthcard to be shown
 * all recorded info about the patient with the entered healthcard.
 * @author Justin
 *
 */
public class LookupActivity extends Activity {

	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookup);
		Intent intent = getIntent();
		isNurse = intent.getBooleanExtra("isNurse", true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lookup, menu);
		return true;
	}
	
	/**
	 * Moves the current user to StaticPatientActivity if the entered healthcard
	 * is valid. Otherwise, show an error message.
	 * @param view the current view
	 */
	public void toStaticPatientActivity(View view){
		Intent intent = new Intent(this, StaticPatientActivity.class);
		EditText hc_obj = (EditText) findViewById(R.id.healthcard_lookup_field);
		String hc = hc_obj.getText().toString();
		Patient p;
		
		if (isNurse){
			p = Nurse.lookUpPatient(hc);
		} else {
			p = Physician.lookUpPatient(hc);
		}
		
		if (p != null){
			intent.putExtra("isNurse", isNurse);
			intent.putExtra("patient", p);
			startActivity(intent);
		} else {
			hc_obj.setText("");
			CharSequence errorText = "Patient not found";
			Toast error = Toast.makeText(getApplicationContext(), errorText,
										 Toast.LENGTH_SHORT);
			error.show();
		}
	}

}
