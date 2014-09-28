package infinitynurse.frontend;

import infinitynurse.backend.Nurse;
import infinitynurse.backend.Patient;
import infinitynurse.backend.ValidInput;
import infinitynurse.backend.VitalSigns;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Shows detailed info about a specific patient
 * @author InfinityNurse Team
 *
 */
public class ViewPatientActivity extends Activity {
	
	private Patient patient;
	private boolean isNurse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_patient);
		
		Intent intent = getIntent();
		patient = (Patient) intent.getSerializableExtra("patient");
		isNurse = intent.getBooleanExtra("isNurse", true);
		
		// Obtain all field objects
		
		EditText name_obj = (EditText) findViewById(R.id.patient_name_display);
		EditText health_obj = (EditText) findViewById(R.id.patient_healthcard_display);
		EditText dob_obj = (EditText) findViewById(R.id.patient_dob_display);
		EditText arrival_obj = (EditText) findViewById(R.id.patient_arrival_display);
		EditText heart_obj = (EditText) findViewById(R.id.patient_heart_display);
		EditText bloodSys_obj = (EditText) findViewById(R.id.patient_bloodSys_display);
		EditText bloodDia_obj = (EditText) findViewById(R.id.patient_bloodDia_display);
		EditText temp_obj = (EditText) findViewById(R.id.patient_temp_display);
		EditText doc_obj = (EditText) findViewById(R.id.doc_visit_display);
		
		// Set all field objects with patients data
		
		name_obj.setText(patient.getName());
		health_obj.setText(patient.getHealthcard());
		dob_obj.setText(patient.getDob());
		arrival_obj.setText(patient.getArrival());
		VitalSigns vitals = patient.getVitalsigns();
		heart_obj.setText(String.valueOf(vitals.getHeartRate()));
		bloodSys_obj.setText(String.valueOf(vitals.getSystolic()));
		bloodDia_obj.setText(String.valueOf(vitals.getDiastolic()));
		temp_obj.setText(String.valueOf(vitals.getTemperature()));
		doc_obj.setText(patient.getDoctor());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_patient, menu);
		return true;
	}
		
	/**
	 * Obtains info from text boxes and edits patients data with the (possibly)
	 * new data.
	 * @param view the current view
	 */
	public void updatePatient(View view){
		
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("fromLogin", false);
		intent.putExtra("outdated", true);
		intent.putExtra("isNurse", isNurse);
		
		CharSequence emptyField = "Please fill in all fields";
		Toast outputToast = Toast.makeText(getApplicationContext(), emptyField,
				 Toast.LENGTH_SHORT);
		
		// Obtain all field objects
		
		TextView name_obj = (TextView) findViewById(R.id.patient_name_display);
		TextView health_obj = (TextView) findViewById(R.id.patient_healthcard_display);
		TextView dob_obj = (TextView) findViewById(R.id.patient_dob_display);
		TextView arrival_obj = (TextView) findViewById(R.id.patient_arrival_display);
		TextView heart_obj = (TextView) findViewById(R.id.patient_heart_display);
		TextView bloodSys_obj = (TextView) findViewById(R.id.patient_bloodSys_display);
		TextView bloodDia_obj = (TextView) findViewById(R.id.patient_bloodDia_display);
		TextView temp_obj = (TextView) findViewById(R.id.patient_temp_display);
		TextView doc_obj = (TextView) findViewById(R.id.doc_visit_display);
		
		// Create a new patient will altered data
		try {
			
			String name = name_obj.getText().toString();
			String healthcard = health_obj.getText().toString();
			String dob = dob_obj.getText().toString();
			String arrival = arrival_obj.getText().toString();
			String docTime = doc_obj.getText().toString();
			double heart = Double.valueOf(heart_obj.getText().toString());
			double bloodSys = Double.valueOf(bloodSys_obj.getText().toString());
			double bloodDia = Double.valueOf(bloodDia_obj.getText().toString());
			double temp = Double.valueOf(temp_obj.getText().toString());
			
			if (docTime.trim().length() == 0){
				docTime = "N/A";
			}
				
			if (ValidInput.isEmpty(name, healthcard, dob, arrival) || dob.length() < 8) {
				outputToast.show();
				return;
			}
						
			Nurse.updatePatient(Nurse.lookUpPatient(patient.getHealthcard()), 
								name, dob, healthcard, docTime, temp, heart, 
								bloodSys, bloodDia);
			
		} catch (NumberFormatException e){
			outputToast.show();
			return;
		}

		CharSequence updateMessage = "Patient Updated";
		outputToast = Toast.makeText(getApplicationContext(), updateMessage,
									 Toast.LENGTH_SHORT);
		outputToast.show();
		
		startActivity(intent);
		
		
	}

}
