package infinitynurse.frontend;

import infinitynurse.backend.VitalSigns;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/**
 * Shows the data of a patients specific record
 * @author Justin
 */
public class ViewRecordActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_record);
		
		Intent intent = getIntent();
		VitalSigns record = (VitalSigns) intent.getSerializableExtra("vitalSign");
		String pre_name_text = intent.getStringExtra("pre_name");
		String pre_info_text = intent.getStringExtra("pre_info");
		
		int recordNumber = intent.getIntExtra("recordNumber", 0);
		
		TextView recordTitle = (TextView) findViewById(R.id.record_number_title);
		recordTitle.setText(String.format("Record %s", Integer.toString(recordNumber)));
		
		TextView temp_obj = (TextView) findViewById(R.id.record_temp_display);
		TextView heart_obj = (TextView) findViewById(R.id.record_heart_display);
		TextView bloodSys_obj = (TextView) findViewById(R.id.record_bloodSys_display);
		TextView bloodDia_obj = (TextView) findViewById(R.id.record_bloodDia_display);
		TextView pre_name_obj = (TextView) findViewById(R.id.prescription_name_display);
		TextView pre_info_obj = (TextView) findViewById(R.id.prescription_info_display);
	
		temp_obj.setText(Double.toString(record.getTemperature()));
		heart_obj.setText(Double.toString(record.getHeartRate()));
		bloodSys_obj.setText(Double.toString(record.getSystolic()));
		bloodDia_obj.setText(Double.toString(record.getDiastolic()));
		pre_name_obj.setText(pre_name_text);
		pre_info_obj.setText(pre_info_text);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_record, menu);
		return true;
	}

}
