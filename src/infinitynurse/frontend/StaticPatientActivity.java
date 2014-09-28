package infinitynurse.frontend;

import infinitynurse.backend.Patient;
import infinitynurse.backend.VitalSigns;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Activity which displays all current info about a patient
 * @author Justin
 */
public class StaticPatientActivity extends Activity {

	private Patient patient;
	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_static_patient);
		Intent intent = getIntent();
		isNurse = intent.getBooleanExtra("isNurse", true);
		patient = (Patient) intent.getSerializableExtra("patient");
		
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
		TextView pre_name_obj = (TextView) findViewById(R.id.pre_name_display);
		TextView pre_info_obj = (TextView) findViewById(R.id.pre_info_display);
		
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
		pre_name_obj.setText(patient.getPrescription());
		pre_info_obj.setText(patient.getInstruction());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.static_patient, menu);
		if (isNurse){
			menu.findItem(R.id.pre_action).setVisible(false);
		} else {
			menu.findItem(R.id.edit_action).setVisible(false);
		}
		return true;
	}
	
	/**
	 * Listener for the action bar items.
	 * @param item The MenuItem clicked
	 * @return If the item click handling was successful
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.record_action){
			Intent intent = new Intent(this, RecordListActivity.class);
			intent.putExtra("isNurse", isNurse);
			intent.putExtra("patient", patient);
			startActivity(intent);
			return true;
		} else if (item.getItemId() == R.id.pre_action){
			Intent intent = new Intent(this, AddPrescriptionActivity.class);
			intent.putExtra("isNurse", isNurse);
			intent.putExtra("patient", patient);
			startActivity(intent);
			return true;
		} else if (item.getItemId() == R.id.edit_action){
			Intent intent = new Intent(this, ViewPatientActivity.class);
			intent.putExtra("isNurse", isNurse);
			intent.putExtra("patient", patient);
			startActivity(intent);
			return true;
	    } else {
	        return super.onOptionsItemSelected(item);
	    }
	}
	

}
