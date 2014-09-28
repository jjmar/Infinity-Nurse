package infinitynurse.frontend;

import infinitynurse.backend.Patient;
import infinitynurse.backend.Physician;
import infinitynurse.backend.ValidInput;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows the user to add a new prescription (with instructions) to a specific
 * patient
 * @author Justin
 */
public class AddPrescriptionActivity extends Activity {

	private Patient patient;
	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_prescription);
		
		Intent intent = getIntent();
		patient = (Patient) intent.getSerializableExtra("patient");
		isNurse = intent.getBooleanExtra("isNurse", false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_prescription, menu);
		return true;
	}
	
	/**
	 * Obtains user entered data, if valid, and edits the current patient
	 * with that data. Upon completion, sends user to HomeActivity
	 * @param view the current view
	 */
	public void addPrescription(View view){
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("isNurse", false);
		
		EditText name_obj = (EditText) findViewById(R.id.add_pre_name_field);
		EditText info_obj = (EditText) findViewById(R.id.add_pre_info_field_2);
		
		String name = name_obj.getText().toString();
		String info = info_obj.getText().toString();
	
		CharSequence emptyField = "Please fill in all fields";
		Toast outputToast = Toast.makeText(getApplicationContext(), emptyField,
				 Toast.LENGTH_SHORT);
		
		if (ValidInput.isEmpty(name, info)){
			outputToast.show();
			return;
		} else {
			intent.putExtra("outdated", true);
			Physician.prescibe(Physician.lookUpPatient(patient.getHealthcard()), name, info);
			startActivity(intent);
		}
		
		
		
	}

}
