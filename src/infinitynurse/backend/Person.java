package infinitynurse.backend;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 4824920478964350071L;
	protected String name;
	protected String dob;
	protected String Healthcard;
	
	/**
	 * Creates a new instance of Person using name, dob,
	 * and healthcard
	 * @param name String for the Person's name
	 * @param dob String for the Person's date of birth
	 * @param Healthcard String for the Person's health card number
	 */
	public Person(String name, String dob, String Healthcard){
		this.name = name;
		this.dob = dob;
		this.Healthcard = Healthcard;
	}
	
	/**
	 * sets a new name
	 * @param name what is being set to name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * sets a new date of birth
	 * @param dob what is being set to dob
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	/**
	 * sets a new health card number
	 * @param healthcard what is being set to health card number
	 */
	public void setHealthcard(String healthcard) {
		Healthcard = healthcard;
	}
	
	/**
	 * returns the Person's name
	 * @return the Person's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the Person's date of birth
	 * @return the Person's date of birth
	 */
	public String getDob() {
		return dob;
	}
	
	/**
	 * returns the Person's health card number
	 * @return the Person's health card number
	 */
	public String getHealthcard() {
		return Healthcard;
	}
	
	
}
