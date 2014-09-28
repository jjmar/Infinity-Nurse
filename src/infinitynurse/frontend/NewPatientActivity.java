package infinitynurse.frontend;

import java.text.ParseException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import infinitynurse.backend.Nurse;
import infinitynurse.backend.VitalSigns;
import infinitynurse.backend.ValidInput;

/**
 * Activity for creating a new patient
 * @author InfinityNurse Team
 *
 */
public class NewPatientActivity extends Activity {
	
	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_patient);
		Intent intent = getIntent();
		isNurse = intent.getBooleanExtra("isNurse", true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_patient, menu);
		return true;
	}
	
	/**
	 * Obtains all info entered by the user & creates a new Patient
	 * by calling on the Nurse class.
	 * @param view the current view
	 * @throws ParseException 
	 */
	public void createPatient(View view){
		
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("fromLogin", false);
		intent.putExtra("outdated", true);
		intent.putExtra("isNurse", isNurse);
		
		CharSequence emptyField = "Please fill in all fields";
		Toast outputToast = Toast.makeText(getApplicationContext(), emptyField,
				 Toast.LENGTH_SHORT);
		
		
		EditText name_obj = (EditText) findViewById(R.id.patient_name_field);
		EditText dob_obj = (EditText) findViewById(R.id.patient_dob_field);
		EditText healthcard_obj = (EditText) findViewById(R.id.patient_healthcard_field);
		EditText arrival_obj = (EditText) findViewById(R.id.patient_arrival_field);
		EditText temp_obj = (EditText) findViewById(R.id.patient_temp_field);
		EditText bloodSys_obj = (EditText) findViewById(R.id.patient_bloodSys_field);
		EditText bloodDia_obj = (EditText) findViewById(R.id.patient_bloodDia_field);
		EditText heart_obj = (EditText) findViewById(R.id.patient_heart_field);
		EditText doc_obj = (EditText) findViewById(R.id.doc_visit_field);
		
		try {
			
			String name = name_obj.getText().toString();
			String healthcard = healthcard_obj.getText().toString();
			String dob = dob_obj.getText().toString();
			String arrival = arrival_obj.getText().toString();
			double temp = Double.valueOf(temp_obj.getText().toString());
			double bloodSys = Double.valueOf(bloodSys_obj.getText().toString());
			double bloodDia = Double.valueOf(bloodDia_obj.getText().toString());
			double heart = Double.valueOf(heart_obj.getText().toString());
			String docTime = doc_obj.getText().toString();

			if (docTime.trim().length() == 0){
				docTime = "N/A";
			}
			
			if (ValidInput.isEmpty(name, healthcard, dob, arrival) || dob.length() < 8) {
				outputToast.show();
				return;
			}
			
			VitalSigns vs = new VitalSigns(temp, heart, bloodSys, bloodDia);
			Nurse.addPatient(name, dob, healthcard, vs, arrival, docTime);

			
		} catch (NumberFormatException e){
			outputToast.show();
			return;
		}
		
		CharSequence success = "Patient Created";
		outputToast = Toast.makeText(getApplicationContext(), success,
									 Toast.LENGTH_SHORT);
		outputToast.show();
		
		startActivity(intent);
		
	}

}
