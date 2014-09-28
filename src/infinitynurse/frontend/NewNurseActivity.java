package infinitynurse.frontend;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Activity for registering a new nurse
 * @author InfinityNurse Team
 *
 */
public class NewNurseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_nurse);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_nurse, menu);
		return true;
	}
	
	/**
	 * Creates a new nurse using the inputed data. Due to time constraints,
	 * this method has no relevant use other than for testing purposes.
	 * @param view the current view
	 */
	public void createNurse(View view){
		
		CharSequence createNurse = "New Nurse Created";
		Toast newNurse = Toast.makeText(getApplicationContext(), createNurse,
									 Toast.LENGTH_SHORT);
		newNurse.show();

		finish();
	}

}
