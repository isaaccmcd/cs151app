package application;

import java.io.File;
//import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DbWriter {
	
	private FileWriter aFileWriter;
	
	//Main Constructor
	//	- Builds input stream to database
	//	- ALLOWS FOR EDITING
	public DbWriter() {
		
		try {
			File Db = new File("eventsDb", "EventsDb.txt");
			aFileWriter = new FileWriter(Db, true);				//"true" tag allows to editing
		}
		catch(IOException cannotCreateFileWriterConstruct1) {
			cannotCreateFileWriterConstruct1.printStackTrace();
		}

	}
	
	//Secondary Constructor
	//	- Builds input stream to database
	//	- DOES NOT ALLOW FOR EDITING
	//	- **THIS CONSTRUCTOR IS FOR "DELETE ALL" BUTTON IN "delete-event-page" 
	//		- this constructor is important, the database cannot be rewritten without it
	public DbWriter(String forDeleteAllButton) {
		
		try {
			File Db = new File("eventsDb", "EventsDb.txt");
			aFileWriter = new FileWriter(Db);				//lack of "true" tag prevents editing and causes a rewrite
		}
		catch(IOException cannotCreateFileWriterConstruct2) {
			cannotCreateFileWriterConstruct2.printStackTrace();
		}

	}
	
	//Method writes a line to the database
	public void writeToFile(String input) {
		
		try {
			aFileWriter.write(input + "\n");
		}
		catch(IOException writeToDbWriterException) {
			writeToDbWriterException.printStackTrace();
		}
	}
	
	//Simple method closes database writer
	public void closeWriter() {
		
		try {
			aFileWriter.close();
		}
		catch(IOException cannotCloseFileWriter) {
			cannotCloseFileWriter.printStackTrace();
		}
	}
	
	//Complex method
	//	- Deletes database file
	//	- Creates new database file with same name
	public void resetFile() {
		
		File fileToDelete = new File("eventsDb", "EventsDb.txt");
		fileToDelete.delete();
		
		try {
			aFileWriter = new FileWriter(new File("eventsDb", "EventsDb.txt"), true);
		}
		catch(IOException cannotCreateFileWriter3) {
			cannotCreateFileWriter3.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	This code = instance variables:
//
//	//private Scanner aScanner;
//
//	END instance variables
	
	
//	//EXTRA METHOD: used for debugging and retrieving lines from database
//	public void printFileContents() {
//		
//		try {
//			aScanner = new Scanner(new File("eventsDb", "EventsDb.txt"));
//		}
//		catch(FileNotFoundException scannerCantFindFile) {
//			scannerCantFindFile.printStackTrace();
//		}
//		
//		while(aScanner.hasNextLine()) {
//			String data = aScanner.nextLine();
//			System.out.println(data);
//		}
//		aScanner.close();
//	}
}