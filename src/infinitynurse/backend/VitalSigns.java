package infinitynurse.backend;

import java.io.Serializable;

public class VitalSigns implements Serializable{
	
	private static final long serialVersionUID = -5265578065409487148L;
	private double temperature;
	private double heartRate;
	private double systolic;
	private double diastolic;
	
	/**
	 * Creates a new instance of VitalSigns using
	 * temp, heart, sy, and dia
	 * @param temp String for temperature
	 * @param heart String for heart rate
	 * @param sy String for Systolic
	 * @param dia String for Diastolic
	 */
	public VitalSigns(double temp, double heart, double sy, double dia){
		this.temperature = temp;
		this.heartRate = heart;
		this.systolic = sy;
		this.diastolic = dia;
	}
	
	/**
	 * returns the Patient's temperature
	 * @return the Patient's temperature
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * returns the Patient's heart rate
	 * @return the Patient's heart rate
	 */
	public double getHeartRate() {
		return heartRate;
	}
	
	/**
	 * returns the Patient's systolic
	 * @return the Patient's systolic
	 */
	public double getSystolic() {
		return systolic;
	}
	
	/**
	 * returns the Patient's diastolic
	 * @return the Patient's diastolic
	 */
	public double getDiastolic() {
		return diastolic;
	}
	
	/**
	 * Returns whether the inputed object is identical in value to this
	 * VitalSigns instance.
	 * @param o Object to be compared for equivalence to this instance
	 * @return Whether the object is equivalent to this instance
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof VitalSigns){
			return ((temperature == ((VitalSigns) o).getTemperature()) &&
					(heartRate == ((VitalSigns) o).getHeartRate()) &&
					(systolic == ((VitalSigns) o).getSystolic()) &&
					(diastolic == ((VitalSigns) o).getDiastolic()));	
		} else {
			return false;
		}
		
		
	}
	
	
}
