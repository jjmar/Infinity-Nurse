package infinitynurse.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person implements Serializable {
	
	private static final long serialVersionUID = 3752648832700014283L;
	/**
	 * Holds all the patients in a list that all nurses can access
	 */
	private static List<Patient> patients = new ArrayList<Patient>();
	
	/**
	 * Creates a new Instance of nurse using name, date of birth,
	 * and healthcard
	 * @param name String for the nurse's name
	 * @param dob String for the Nurse's Birthday
	 * @param Healthcard String for the nurse's Health card number
	 */
	public Nurse(String name, String dob, String Healthcard){
		super(name, dob, Healthcard);
	}
	
	/**
	 * Creates a new Patient and adds it to the patients list
	 * by entering name, dob, healthcard, vs, and time
	 * @param name name of the newly created patient
	 * @param dob date of birth for the newly created patient
	 * @param Healthcard healthcard number for the newly created patient
	 * @param vs vital signs of the newly created patient
	 * @param time arrival time for the newly created patient
	 */
	public static void addPatient(String name, String dob, String Healthcard, 
								  VitalSigns vs, String time, String doc){
		Patient P = new Patient(name, dob, Healthcard, vs, time, doc);
		patients.add(P);
	}
	
	/**
	 * Allows Nurse to update patients information
	 * by setting their new data into the patient fields 
	 * @param P the patient that will be updated
	 * @param name parameter to replace the previous field of Patient P
	 * @param dob parameter to replace the previous field of Patient P
	 * @param Healthcard parameter to replace the previous field of Patient P
	 * @param temp parameter to replace the previous field of Patient P
	 * @param heart parameter to replace the previous field of Patient P
	 * @param sy parameter to replace the previous field of Patient P
	 * @param dia parameter to replace the previous field of Patient P
	 */
	public static void updatePatient(Patient P, String name, String dob, 
									 String Healthcard, String DocVisit, 
									 double temp, double heart, double sy, 
									 double dia){
		VitalSigns vs = new VitalSigns(temp, heart, sy, dia);
		if (!(vs.equals(P.getVitalsigns()))){
			P.getPastVS().add(P.getVitalsigns());
			P.setVitalsigns(vs);
			P.getPastIntr().add(P.getInstruction());
			P.getPastPre().add(P.getPrescription());
		}
		P.setName(name);
		P.setDob(dob);
		P.setHealthcard(Healthcard);
		P.setDoctor(DocVisit);
		P.setUrgency(P.calculateUrgency(P));
	}
	
	/**
	 * Creates a list of the names of every patient from the patients list
	 * @return a list of the names of every patient
	 */
	public static List<String> listPatientNames(){
		int x;
		ArrayList<String> L = new ArrayList<String>();
		for (x = 0; x < patients.size(); x++){
			L.add(patients.get(x).getName());
		}
		return L;
	}
	
	/**
	 * Sets the member list of patients with the inputed list
	 * @param list List of Patient objects
	 */
	public static void setPatients(List<Patient> list){
		patients = list;
	}

	/**
	 * Clears the Nurses patient list
	 */
	public static void clearInfo(){
		patients = new ArrayList<Patient>();
	}
	
	/**
	 * Returns the list of patients that nurse holds
	 * @return the list of patients that nurse holds
	 */
	public static List<Patient> getPatients(){
		return patients;
	}
	
	/**
	 * Returns a specific patient based on the location they reside within
	 * the array list
	 * @param position the index of the patient
	 * @return a patient object
	 */
	public static Patient getPatient(int position) {
		return patients.get(position);
	}
	
	/**
	 * Finds a patient based on a health card number HC
	 * @param HC the health card number
	 * @return the patient with the health card number HC
	 */
	public static Patient lookUpPatient(String HC){
		for (Patient p: patients){
			if (p.getHealthcard().equals(HC)){
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Sorts a list of patients who have not seen a doctor by 
	 * decreasing urgency, uses a helper class sortByUrgency
	 * @return a sorted list of patients 
	 */
	public static List<Patient> orderUrgent(){
		List<Patient> noDoctorpatients = new ArrayList<Patient>();
		for(Patient p: patients){
			if (p.getDoctor().equals("N/A")){
				noDoctorpatients.add(p);
			}
		}
		List<Patient> L = sortByUrgency(noDoctorpatients);
		return L;
	}
	
	/**
	* Helper class for orderUrgent
	* Sorts a list of patients by comparing urgency points
	* @return a sorted lists of patients based on urgency points
	*/
	private static List<Patient> sortByUrgency(List<Patient> L){
		List<Patient> urgentpatients = new ArrayList<Patient>();
		for (int x=0; x<6; x++){
			for (Patient p: L){
				if (p.getUrgency() == (5 - x) ){
					urgentpatients.add(p);
				}
			}
			if (urgentpatients.size() == L.size()){
				x = 6;
			}
		}
		return urgentpatients;
	}
}