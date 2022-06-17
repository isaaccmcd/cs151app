package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class DbReader {
	
	private FileReader theFr;
	private BufferedReader theBr;
	private Scanner theScan;
	private String lineFromDb;
	private int numbOfLines;
	
	//Constructor
	//	- Method builds output stream for database
	//	- FileReader to read from database
	//	- BufferedReader to read individual line from database
	public DbReader() {
		
		try {
			theFr = new FileReader(new File("eventsDb", "EventsDb.txt"));
			theBr = new BufferedReader(theFr);
		}
		catch(IOException errorCreatingDbReaderFileOrBufferedReader) {
			errorCreatingDbReaderFileOrBufferedReader.printStackTrace();
		}
	}
	
	//Method gets the number of lines within database
	public int getNumbOfLines() {
		
		numbOfLines = 0;
		
		try {
			theScan = new Scanner(new File("eventsDb", "EventsDb.txt"));
		} catch(FileNotFoundException errorCreatingScannerInGettingNumberOfLines) {
			errorCreatingScannerInGettingNumberOfLines.printStackTrace();
		}
		
		while(theScan.hasNextLine()) {
			numbOfLines++;
			theScan.nextLine();
		}
		
		return numbOfLines;
	}
	
	//Method gets a line from the database
	public String getLineFromDb() {
		
		try {
			lineFromDb = theBr.readLine();
		} catch(IOException errorGettingLineFromDb) {
			errorGettingLineFromDb.printStackTrace();
		}
		
		return lineFromDb;
	}
	
	//Method closes buffered-reader, file-reader, and scanner
	public void closeReader() {
		
		theScan.close();
		try {
			theBr.close();
			theFr.close();
		} catch(IOException cannotCloseBufferedOrFileReader) {
			cannotCloseBufferedOrFileReader.printStackTrace();
		}
	}
}