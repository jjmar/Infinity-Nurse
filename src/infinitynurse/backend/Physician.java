package infinitynurse.backend;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Physician extends Person implements Serializable{
	
	private static final long serialVersionUID = -2851815731669464361L;
	
	/**
	 * Holds all the patients in a list that all physicians can access
	 */
	private static List<Patient> patients = new ArrayList<Patient>();
	
	/**
	 * Creates a new Physician using name, date of birth, 
	 * and Healthcard
	 * @param name String for the physician's name
	 * @param dob String for the physician's Birthday
	 * @param Healthcard String for the physician's Health card number
	 */
	public Physician(String name, String dob, String Healthcard) {
		super(name, dob, Healthcard);
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
	 * sets the prescription for a patient.
	 * The past prescriptions are recorded unless it is the first prescription
	 * @param p the patient
	 * @param name the prescription name
	 * @param i the prescription instructions
	 */
	public static void prescibe(Patient p, String name, String i){
		if ((p.getPrescription() != "N/A") || (p.getInstruction() != "N/A")){
			p.getPastPre().add(p.getPrescription());
			p.getPastIntr().add(p.getInstruction());
			p.getPastVS().add(p.getVitalsigns());
		}

		p.setPrescription(name);
		p.setInstruction(i);
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
	 * Clears the Physicians patient list
	 */
	public static void clearInfo(){
		patients = new ArrayList<Patient>();
	}
	
	/**
	 * Returns the list of patients that Physician holds
	 * @return the list of patients that Physician holds
	 */
	public static List<Patient> getPatients(){
		return patients;
	}
	
}
