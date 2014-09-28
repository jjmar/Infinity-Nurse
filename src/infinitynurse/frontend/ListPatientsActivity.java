package infinitynurse.frontend;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import infinitynurse.backend.Nurse;
import infinitynurse.backend.Patient;
import infinitynurse.backend.Physician;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;

/**
 * Activity which lists all current patients.
 * @author InfinityNurse Team
 *
 */
public class ListPatientsActivity extends Activity {

	// Stores patients for output to ListView object
	private List<Map<String,String>> patients;
	private boolean sortUrgency = false;
	private boolean isNurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_patients);
			
		Intent intent = getIntent();
		isNurse = intent.getBooleanExtra("isNurse", true);
		sortUrgency = intent.getBooleanExtra("sortUrgency", false);
		
		initListView();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if (isNurse) {
			getMenuInflater().inflate(R.menu.list_patients, menu);
		}
		return true;
	}
	
	/**
	 * Listener for the action bar items. Refreshes the current activity
	 * depending on what option the user selection.
	 * @param item The MenuItem clicked
	 * @return If the item click handling was successful
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		Intent intent = new Intent(this, this.getClass());
		intent.putExtra("isNurse", true);
		
		if ((id == R.id.view_all_item && sortUrgency == false) || 
				  (id == R.id.view_urgency_item && sortUrgency == true)) {
			return true;
		} else if (id == R.id.view_all_item) {		
			intent.putExtra("sortUrgency", false);
			finish();
			startActivity(intent);
			return true;
		} else if (id == R.id.view_urgency_item){
			intent.putExtra("sortUrgency", true);
			finish();
			startActivity(intent);
			return true;
	    } else {
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * Initializes the ListView item within the activity filling it with
	 * either all the patients or those who still need to see a doctor
	 */
	private void initListView(){
		
		// Get list view object
		ListView list_obj = (ListView) findViewById(R.id.patient_list);
		
		// Populate patients
		populateList();
		
		// Add items to list view
		ListAdapter adapter = new SimpleAdapter(this, patients, android.R.layout.simple_list_item_2,
								new String[]{"name", "details"},
								new int[] {android.R.id.text1, android.R.id.text2});
		
		// Set adapter for list object
		list_obj.setAdapter(adapter);
		
		// Set listener for clicking a list item
		list_obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adtView, View clicked, 
									int position, long id) {
				toStaticPatientActivity(position);
				return;
			}
			
		});
	}
		
		
	/**
	 * Sends user from current activity to ViewPatientActivity
	 * @param position List item location which was clicked by the user.
	 */
	public void toStaticPatientActivity(int position){
		Intent intent = new Intent(this, StaticPatientActivity.class);
		
		if (isNurse){
			if (sortUrgency){
				intent.putExtra("patient", Nurse.orderUrgent().get(position));
			} else {
				intent.putExtra("patient", Nurse.getPatient(position));
			}
		} else {
			intent.putExtra("patient", Physician.getPatients().get(position));
		}
		intent.putExtra("patient_position", position);
		intent.putExtra("isNurse", isNurse);
		startActivity(intent);
	}
	
	/**
	 * Fills the member list with maps created from mapPatient
	 */
	private void populateList(){
		patients = new ArrayList<Map<String, String>>();
		List<Patient> list;
		
		if (isNurse) {
		list = sortUrgency ? Nurse.orderUrgent() : Nurse.getPatients();
		} else {
		list = Physician.getPatients();
		}
		
		for (Patient p : list){
			if (isNurse) {
				if (!sortUrgency){
					patients.add(mapPatient(p.getName(), 
								 String.format("Healthcard: %s", p.getHealthcard())));
				} else {
					patients.add(mapPatient(p.getName(),
							     String.format("Urgency: %s", 
							    		 		Integer.toString(p.getUrgency()))));
				}
			} else {
				patients.add(mapPatient(p.getName(), String.format("Healthcard: %s", p.getHealthcard())));
			}
		}
	}
	
	/**
	 * Creates a single element hash map mapping "patient" to patient name.
	 * @param name patient name
	 * @param details additional patient details 
	 * @return Single element hash map mapping "patient" to patient details
	 */
	private Map<String, String> mapPatient(String name, String details){
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("name", name);
		temp.put("details", details);
		return temp;
	}

}
