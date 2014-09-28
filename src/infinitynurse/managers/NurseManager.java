package infinitynurse.managers;

import infinitynurse.backend.Nurse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NurseManager {
	
	private ArrayList<Nurse> info;
	
	public NurseManager(File dir, String fileName) throws IOException {
        this.info = new ArrayList<Nurse>();
        
        // Populates the student list using stored data, if it exists.
        File file = new File(dir, fileName);
        if (file.exists()) {
            this.populate(file.getPath());
        } else {
            file.createNewFile();
        }
    }
	
	public void saveToFile(FileOutputStream outputStream) {
        try {
        	// write student info one per line into outputStream
        	for (Nurse s : info) {
        		outputStream.write((s.toString() + "\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public ArrayList<Nurse> getInfo() {
		return info; 
	}
	
	public String ToString(){
		return info.toString();
	}
	
private void populate(String filePath) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String [] record;
        
        while(scanner.hasNextLine()) {
        	record = scanner.nextLine().split(",");
        	String name = record[0];
        	String dob = record[1];
        	String Healthcard = record[2];
        	Nurse n = new Nurse(name, dob, Healthcard);
        	
            info.add(n);
        }
        scanner.close();
    }  
}
