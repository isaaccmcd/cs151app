package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DbEventRemover {
	
	private File newDb;
	private FileWriter aFileWriter;
	
	//Constructor
	//	- Creates temporary database and input stream to that database
	public DbEventRemover() {
		
		try {
			newDb = new File("eventsDb", "TEMPEventsDb.txt");
			aFileWriter = new FileWriter(newDb);
		} catch(IOException errorCreatingTempFileOrTempFileWriter) {
			errorCreatingTempFileOrTempFileWriter.printStackTrace();
		}
	}
	
	//Method rewrites old database to temporary new database and skips the input event (eventToDelete), which we want to delete
	public void deleteEventAndRewriteDb(int eventToDelete) {
		
		//Build database reader
		DbReader readFromOldDb = new DbReader();
		//get number of lines from database
		int numbOfLinesToIterateThrough = readFromOldDb.getNumbOfLines();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//This loop iterates as many times as there are lines within the database
		for(int numLines = 0; numLines < numbOfLinesToIterateThrough; numLines++) {
			
			//Get a line from the database
			String aDbLine = readFromOldDb.getLineFromDb();
			int numbIDFromALine;
			//Get the ID of line retrieved from database
			String IDFromALine = aDbLine.substring(0, 11);
			numbIDFromALine = Integer.parseInt(IDFromALine);
			
			//NOTE THIS IF STATEMENT AND LOOP
			//	- IF THE ID-OF-LINE-WE-ARE-READING == ID-INPUT-VALUE
			//		- then immediately skip the next 9 lines
			//
			if(numbIDFromALine == eventToDelete) {
				
				//skip through next 9 lines
				for(int linesToSkip = 0; linesToSkip < 8; linesToSkip++) {	//NOTE IF NUMBER OF ATTRIBUTES PER EVENT IS ALTERED, MAX FOR LOOP NUMBER (8) MAY NEED TO CHANGE!!!
					aDbLine = readFromOldDb.getLineFromDb();
					//decrement the total number of lines we need to iterate through as we skip each line
					numbOfLinesToIterateThrough--;
				}
			}
			
			//NOTE THIS ELSE STATEMENT
			//	- if the if statement fails then: id-of-line-we-are-reading != id-input-value
			//		- then write that whole line to the new temporary database
			else {
				try {
					aFileWriter.write(aDbLine + "\n");
				} catch(IOException deleteEventAndRewriteDbError1) {
					deleteEventAndRewriteDbError1.printStackTrace();
				}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Close the temporary database file-writer
		try {
			aFileWriter.close();
		} catch(IOException deleteEventAndRewriteDbError2) {
			deleteEventAndRewriteDbError2.printStackTrace();
		}
		
		//Close database reader
		readFromOldDb.closeReader();
	}
	
	//Method renames temporary Database "TEMPEventsDb.txt" to appropriate name "EventsDb.txt"
	public void renameDbToOriginal() {
		newDb.renameTo(new File("eventsDb", "EventsDb.txt"));
	}
}