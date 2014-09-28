package infinitynurse.frontend;

import infinitynurse.backend.Patient;
import infinitynurse.backend.VitalSigns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Activity which shows a list of all records of the current patient
 * @author Justin
 */
public class RecordListActivity extends Activity {

	private List<Map<String,String>> records;
	private boolean isNurse;
	private List<VitalSigns> previousVitals;
	private List<String> pre_info;
	private List<String> pre_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_list);
		Intent intent = getIntent();
		isNurse = intent.getBooleanExtra("isNurse", true);
		Patient p = (Patient) intent.getSerializableExtra("patient");
		previousVitals = p.getPastVS();
		pre_name = p.getPastPre();
		pre_info = p.getPastIntr();
		initListView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record_list, menu);
		return true;
	}

	/**
	 * Initializes the ListView item within the activity filling it with
	 * all the records of the current patient
	 */
	private void initListView() {
		
		// Get list view object
		ListView list_obj = (ListView) findViewById(R.id.record_list);
		
		// Populate patients
		populateList();
		
		// Add items to list view
		ListAdapter adapter = new SimpleAdapter(this, records, android.R.layout.simple_list_item_2,
								new String[]{"record"}, new int[] {android.R.id.text1});
		
		// Set adapter for list object
		list_obj.setAdapter(adapter);
		
		// Set listener for clicking a list item
		list_obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adtView, View clicked, 
									int position, long id) {
				toViewRecordActivity(position);
				return;
			}
			
		});
		
	}

	/**
	 * Fills the member list with maps created from mapRecord
	 */
	private void populateList() {
		records = new ArrayList<Map<String, String>>();
		for (int i = 0; i < previousVitals.size(); i++ ){
			records.add(mapRecord(i));
		}
	}
	
	/**
	 * Creates a single element hash map mapping "record" to record number
	 * @param int the record number
	 * @return Single element hash map mapping "record" to record number
	 */
	private Map<String, String> mapRecord(int recordNumber){
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("record", String.format("Record %s", Integer.toString(recordNumber)));
		return temp;
	}
	
	/**
	 * Sends user to ViewRecordActivity
	 * @param position the position of the item clicked within the list
	 */
	private void toViewRecordActivity(int position){
		Intent intent = new Intent(this, ViewRecordActivity.class);
		intent.putExtra("isNurse", isNurse);
		intent.putExtra("vitalSign", previousVitals.get(position));
		intent.putExtra("pre_name", pre_name.get(position));
		intent.putExtra("pre_info", pre_info.get(position));
		intent.putExtra("recordNumber", position);
		startActivity(intent);
	}
	
}
