package infinitynurse.managers;

import infinitynurse.backend.Patient;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PatientManager {

	private String path;
	private String fileName;
	
	/**
	 * Creates an instance of Patient manager using filepath,
	 * and filename
	 * @param path the directory of the file
	 * @param fileName the name of the file
	 */
	public PatientManager(String path, String fileName) {
		this.path = path;
		this.fileName = fileName;
	}
	
	/**
	 * Convert Patient information into bytes and save them to a file
	 * @throws IOException if input/output error occurs
	 */
	public void write(List<Patient> info) throws IOException {
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			
			fos = new FileOutputStream(path + '/' + fileName);
			out = new ObjectOutputStream(fos);
			
			for (Patient p: info){
				out.writeObject(p);
			}
			
			out.writeObject(null); // Used as EOF
			
		} finally {
			out.close();
		}
	}
	
	/**
	 * Read all patients from file and returns them
	 * @return the list of Patients
	 * @throws IOException if an input/output error occurs
	 */
	@SuppressWarnings("finally")
	public List<Patient> read() throws IOException {
		
		FileInputStream fis = null;
		ObjectInputStream in = null;
		List<Patient> ret = new ArrayList<Patient>();
		
		try {
			
			fis = new FileInputStream(path + '/' + fileName);
			in = new ObjectInputStream(fis);
			
			while (true) {
				
				Patient p = (Patient) in.readObject();
				
				if (p != null){
					ret.add(p);
				} else {
					break;
				}
			}
			
		} catch (EOFException e){
			e.printStackTrace();
		} catch (ClassNotFoundException f){
			f.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (in != null){
				in.close();
			}
			return ret;
		}
	}


}
