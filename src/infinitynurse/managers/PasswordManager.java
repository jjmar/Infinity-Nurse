package infinitynurse.managers;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordManager {
	
	private List<String> NursePasswords;
	private List<String> PhysicianPasswords;
	
	/**
	 * Creates a new instance of Password manager
	 * using a file directory as well as the file name.
	 * if the file exisits if fills the lists holding 
	 * passwords. otherwise it initializes the passwords
	 * and creates the file.
	 * @param dir the directory where the file is located
	 * @param fileName the name of the file of patients
	 * @throws IOException if an issue with the input is found
	 */
	public PasswordManager(File dir, String fileName) throws IOException {
        this.NursePasswords = new ArrayList<String>();
        this.PhysicianPasswords = new ArrayList<String>();
        
        File file = new File(dir, fileName);
        if (file.exists()) {
            this.populate(file.getPath());
        } else {
            file.createNewFile();
            NursePasswords.add("Jacob");
            NursePasswords.add("Justin");
            NursePasswords.add("Hayden");
            NursePasswords.add("Mike");
            PhysicianPasswords.add("Pecile");
            PhysicianPasswords.add("Martin");
            PhysicianPasswords.add("Whitney");
            PhysicianPasswords.add("He");
            FileOutputStream out = new FileOutputStream(file.getPath());
            this.saveToFile(out);
        }
	}
	
	/**
	 * returns the list consisting of nurse passwords
	 * @return the list consisting of nurse passwords
	 */
	public List<String> getNursePasswords() {
		return NursePasswords;
	}
	
	/**
	 * returns the list consisting of physician passwords
	 * @return the list consisting of physician passwords
	 */
	public List<String> getPhysicianPasswords() {
		return PhysicianPasswords;
	}
	
	/**
	 * Saves to the text file. wries nurse passwords on the 
	 * first line and physician passwords on the sedond
	 * @param outputStream where to save it to
	 */
	public void saveToFile(FileOutputStream outputStream) {
        try {
        	for (String s : NursePasswords) {
        		outputStream.write((s + ",").getBytes());
            }
        	outputStream.write(("\n").getBytes());
        	for (String s : PhysicianPasswords) {
        		outputStream.write((s + ",").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Used to avoid writing out duplicate passwords in passwords.txt
	 */
	public void clearInfo(){
		NursePasswords = new ArrayList<String>();
		PhysicianPasswords = new ArrayList<String>();
	}
	
	/**
	 * Finds the text file using filepath, 
	 * reads the text file, and fills the nurse and physician
	 * password lists with the file information
	 * @param filePath the directory to the file
	 * @throws FileNotFoundException thrown if file doesn't exist
	 */
	private void populate(String filePath) throws FileNotFoundException{
		
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String record[];
        int count = 0;

        while(scanner.hasNextLine()){
        	record = scanner.nextLine().split(",");
        	
        	if (count == 0){
        		for (String c: record){
        			NursePasswords.add(c);
        		}
        	}else{
        		for (String c: record){
        			PhysicianPasswords.add(c);
        		};
        	}
        	count++;
        }
        scanner.close();
    } 
	
	
}