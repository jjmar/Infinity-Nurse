package infinitynurse.frontend;

import java.io.IOException;

import infinitynurse.managers.PasswordManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for the log in screen
 * @author InfinityNurse Team
 *
 */
public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	/**
	 * Moves user from the current activity to the NewNurseActivity.
	 * @param view the current view.
	 */
	public void toNewNurseActivity(View view){
		Intent intent = new Intent(this, NewNurseActivity.class);
		startActivity(intent);
	}
		
	/**
	 * Moves user from the current activity to the HomeActivity
	 * @param view the current view
	 */
	public void toHomeActivity(View view) {
		
		Intent intent = new Intent(this, HomeActivity.class);
		
		EditText username_obj = (EditText) findViewById(R.id.username_field);
		EditText password_obj = (EditText) findViewById(R.id.password_field);
		
		String username = username_obj.getText().toString();
		String password = password_obj.getText().toString();
		
		intent.putExtra("username", username);
		intent.putExtra("fromLogin", true);
		
		try {
			
			PasswordManager p = new PasswordManager(this.getApplicationContext().getFilesDir(), "passwords.txt");

			if (p.getNursePasswords().contains(password)) {
				intent.putExtra("isNurse", true);
				startActivity(intent);
			} else if (p.getPhysicianPasswords().contains(password)){
				intent.putExtra("isNurse", false);
				startActivity(intent);
			} else {
				CharSequence errorText = "Invalid Login Credentials";
				Toast error = Toast.makeText(getApplicationContext(), errorText,
											 Toast.LENGTH_SHORT);
				error.show();
				username_obj.setText(null);
				password_obj.setText(null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}


