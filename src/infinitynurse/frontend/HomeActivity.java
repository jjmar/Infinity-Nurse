package infinitynurse.frontend;

import infinitynurse.backend.Nurse;
import infinitynurse.backend.Physician;
import infinitynurse.managers.PatientManager;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Activity for the home screen
 * @author InfinityNurse Team
 *
 */
public class HomeActivity extends Activity {
	
	private static final String FILENAME = "patients.txt";
	private PatientManager manager;
	
	// Prevents duplication by user repeatedly hitting save
	private boolean outdated = false;
	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Intent intent = getIntent();
		boolean fromLogin = intent.getBooleanExtra("fromLogin", false);
		isNurse = intent.getBooleanExtra("isNurse", true);
		outdated = intent.getBooleanExtra("outdated", false);

		if (!isNurse){
			Button b = (Button) findViewById(R.id.add_patient_button);
			b.setEnabled(false);
			b.setVisibility(View.INVISIBLE);
		}
		
		if (fromLogin) {
			try {
				manager = new PatientManager(this.getApplicationContext().getFilesDir().getPath(), FILENAME);
				if (isNurse){
					Nurse.setPatients(manager.read());
				} else {
					Physician.setPatients(manager.read());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	/**
	 * Moves user from current activity to ListPatientsActivity
	 * @param view
	 */
	public void listPatients(View view){
		Intent intent = new Intent(this, ListPatientsActivity.class);
		intent.putExtra("isNurse", isNurse);
		startActivity(intent);
	}
	
	/**
	 * Moves user from current activity to NewPatientActivity
	 * @param view the current view
	 */
	public void addPatient(View view){
		Intent intent = new Intent(this, NewPatientActivity.class);
		intent.putExtra("isNurse", isNurse);
		startActivity(intent);
	}

	/**
	 * Saves all current unsaved data & displays a quick message
	 * @param view the current view
	 */
	public void saveAll(View view){

		if (!outdated) {
			return;
		}
		
		try {
			manager = new PatientManager(this.getApplicationContext().getFilesDir().getPath(), FILENAME);
			if (isNurse){
				manager.write(Nurse.getPatients());;
			} else {
				manager.write(Physician.getPatients());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		CharSequence saveText = "Save Complete";
		Toast save = Toast.makeText(getApplicationContext(), saveText,
									 Toast.LENGTH_SHORT);
		save.show();
		
		outdated = false;
	}

	/**
	 * Saves all current unsaved data & exits the application
	 * @param view the current view
	 */
	public void quit(View view){
		Intent intent = new Intent(this, LoginActivity.class);
		intent.putExtra("isNurse", isNurse);
		saveAll(view);
		if (isNurse){
			Nurse.clearInfo();
		} else {
			Physician.clearInfo();
		}
		startActivity(intent);
	}
	
	/**
	 * Moves user from current activity to LookupActivity
	 * @param view the current view
	 */
	public void toLookupActivity(View view){
		Intent intent = new Intent(this, LookupActivity.class);
		intent.putExtra("isNurse", isNurse);
		startActivity(intent);
	}

}
