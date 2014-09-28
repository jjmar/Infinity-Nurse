package infinitynurse.backend;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@SuppressLint("SimpleDateFormat")
public class Patient extends Person implements Serializable {
	
	private static final long serialVersionUID = -4403982834865780891L;
	private VitalSigns Vitalsigns;
	private String arrival;
	private ArrayList<VitalSigns> PastVS;
	private ArrayList<String> PastPre;
	private ArrayList<String> PastIntr;
	private String prescription;
	private String Instruction;
	private String Doctor;
	private Integer Urgency;
	
	public static final String CURRENT_DATE = "mm/dd/yyyy";
	
	/**
	 * Creates a new instance of Patient using name, dob,
	 * Healthcard, vs, time, and doc. It also calculates urgency points
	 * @param name String for the Patient's name
	 * @param dob String for the Patient's date of birth
	 * @param Healthcard String for the Patient's health card number
	 * @param vs String for the Patient's vital signs
	 * @param time String for the Patient's arrival time
	 */
	public Patient(String name, String dob, String Healthcard, VitalSigns vs, 
				   String time, String doc){
		super(name, dob, Healthcard);
		this.Vitalsigns = vs;
		this.arrival = time;
		this.PastVS = new ArrayList<VitalSigns>();
		this.prescription = "N/A";
		this.Instruction = "N/A";
		this.PastPre = new ArrayList<String>();
		this.PastIntr = new ArrayList<String>();
		if (doc == null){
			this.Doctor = "N/A";
		}else{
			this.Doctor = doc;
		}
		this.Urgency = calculateUrgency(this);
	}
	
	/**
	* returns the urgency point of a patient based on the 
	* vital signs of P. It's not private because nurse needs to access it
	* @param P the patient's urgency point will be calcuated
	* @return the urgency point of a patient
	*/
	public int calculateUrgency(Patient p){
		int u = 0;
		if (p.getVitalsigns().getTemperature() >= 39){
			u ++;
		}
		if (p.getVitalsigns().getHeartRate() >= 100 || p.getVitalsigns().getHeartRate() <= 50){
			u ++;
		}
		if (p.getVitalsigns().getDiastolic() >= 90){
			u ++;
		}
		if (p.getVitalsigns().getSystolic() >= 140){
			u ++;
		}
		
		String birthYear = dob.substring(4, 8);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date today = new Date();
		
		String currentYear = dateFormat.format(today);

		if (Integer.valueOf(currentYear) - Integer.valueOf(birthYear) <= 2){
			u ++;
		}
		return u;
	}
	
	/**
	 * returns the list of past vital signs
	 * @return the list of past vital signs
	 */
	public ArrayList<VitalSigns> getPastVS() {
		return PastVS;
	}
	
	/**
	 * returns the list of past prescriptions
	 * @return the list of past prescriptions
	 */
	public ArrayList<String> getPastPre(){
		return PastPre;
	}
	
	/**
	 * returns the list of past prescription instructions
	 * @return the list of past prescription instructions
	 */
	public ArrayList<String> getPastIntr(){
		return PastIntr;
	}
	
	/**
	 * sets a new vital signs
	 * @param vitalsigns is what is being set
	 */
	public void setVitalsigns(VitalSigns vitalsigns) {
		Vitalsigns = vitalsigns;
	}
	
	/**
	 * returns the patient's vital signs
	 * @return the patient's vital signs
	 */
	public VitalSigns getVitalsigns() {
		return Vitalsigns;
	}
	
	/**
	 * returns the Patient's arrival time
	 * @return the Patient's arrival time
	 */
	public String getArrival() {
		return arrival;
	}
	
	/**
	 * Returns a string representation of this patient
	 * @return string representation used for writing patient to file
	 */
	public String toString(){
		String ret= name+","+dob+","+Healthcard+","+arrival+","+
					Vitalsigns.getTemperature()+","+Vitalsigns.getSystolic()+
					","+ Vitalsigns.getDiastolic()+","+
					Vitalsigns.getHeartRate()+","+Doctor;
		return ret;
	}
	
	/**
	 * returns the Patient's prescription name
	 * @return the Patient's prescription name
	 */
	public String getPrescription() {
		return prescription;
	}
	
	/**
	 * sets a new prescription name
	 * @param prescription is what is being set
	 */
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	/**
	 * returns the Patient's time that they saw the doctor
	 * @return the Patient's time that they saw the doctor
	 */
	public String getDoctor() {
		return Doctor;
	}
	
	/**
	 * sets a new time for the doctor visit
	 * @param doctime is what is being set
	 */
	public void setDoctor(String doctime) {
		this.Doctor = doctime;
	}
	
	/**
	 * returns the Patient's urgency level
	 * @return the Patient's urgency level
	 */
	public int getUrgency(){
		return Urgency;
	}
	
	/**
	 * sets a new urgency
	 * @param u is what is being set
	 */
	public void setUrgency(int u){
		this.Urgency = u;
	}
	
	/**
	 * Sets a new prescription instruction
	 * @param instruction is what is being set
	 */
	public void setInstruction(String instruction) {
		Instruction = instruction;
	}
	
	/**
	 * returns the Patient's prescription instruction
	 * @return the Patient's prescription instruction
	 */
	public String getInstruction() {
		return Instruction;
	}
	
}
