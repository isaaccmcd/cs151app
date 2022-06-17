package application.events;

import java.io.File;

import application.DbEventRemover;
import application.DbWriter;
import application.DeleteEventPage;
import application.EventsPage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteEvent {
	
	private int newNumbOfEvents;
	
	//No-arguments constructor
	public DeleteEvent() {}
	
	//Method takes event title input from delete-event-page window and deletes that event
	//	- deletes desired event card on events-page
	//	- deletes desired event details from database
	public void deleteAnEvent(Stage deleteEventStage, DeleteEventPage deleteEvent, EventsPage eventPage, int eventCount) {
		
		newNumbOfEvents = eventCount;
		
		//Build references to events-page event container and get the title of the event to be deleted
		FlowPane eventsContainer = eventPage.getEventContainer();
		String nameInput = deleteEvent.getEventNameInput().getText();
		Boolean foundEventToBeDeleted = false;
		
		//Loop through current number of events within the event container to find the event to be deleted
		for(int x = 0; x < newNumbOfEvents+1; x++) {
			
			//Get an event within the events-page event container 
			VBox test = (VBox) eventsContainer.getChildren().get(x);
			Label label = (Label) test.getChildren().get(0);
			
			//If the event title we wish to delete matches the title of the event we're...
			//...currently looking at, then enter the conditional statement
			if(nameInput.equals(label.getText())) {
				
				//Remove the event we wish to delete from the events-page
				eventPage.getEventContainer().getChildren().remove(x);
				//Decrement the number of total events
				newNumbOfEvents--;
				
				
				//DATABASE EVENT REMOVAL
				Label label2 = (Label) test.getChildren().get(7);
				String label2Contents = label2.getText();
				int numbIDToLookFor = Integer.parseInt(label2Contents);
				
				DbEventRemover eventRemover = new DbEventRemover();
				eventRemover.deleteEventAndRewriteDb(numbIDToLookFor);
				
				File f = new File("eventsDb", "EventsDb.txt");
				f.delete();
				
				eventRemover.renameDbToOriginal();
				deleteEvent.resetEventNameInputTF();
				
				//The event we wished to delete has been found and was removed from the database and events-page
				foundEventToBeDeleted = true;
			}
		}
		
		
		VBox recallDeleteEventSurfaceStack = deleteEvent.getDeleteEventPageSurfaceStack();
		Node theSecondNode = recallDeleteEventSurfaceStack.getChildren().get(3);
		
		//The event we wished to delete has NOT been found and was NOT removed from the database or events-page
		if(foundEventToBeDeleted == false) {
			
			//Display to user: "please enter an existing event title." label if it is not already showing
			if(theSecondNode instanceof Label != true) {
				deleteEvent.showInvalidTitleInputErrorMessage();
			}
			//Clear the text field in delete-event window to allow user to try entering the title again
			deleteEvent.clearAndResetAllFields();
		}
		else {
			
			//Hide the : "please enter an existing event title." label if it is showing but...
			//...the user has correctly input an event title
			if(theSecondNode instanceof Label == true) {
				deleteEvent.hideInvalidTitleInputErrorMessage();
			}
			deleteEvent.clearAndResetAllFields();
		}
		
		//If there are no more events to delete, close the delete-event-page window and disable the delete event button within the events page
		if(newNumbOfEvents == -1) {
			deleteEventStage.close();
			eventPage.setDeleteEventButtonDisabled(true);
		}
	}
	
	//Method deletes ALL EVENTS
	//	- deletes ALL event cards on events-page
	//	- deletes ALL event details from database
	public void deleteAllEvents(Stage thirdStage, EventsPage eventPage, int eventCount) {
		
		newNumbOfEvents = eventCount;
		
		//While the number of events is NOT 0, remove an event from the events-page event container
		int numberOfEventsToDelete = newNumbOfEvents;
		while(numberOfEventsToDelete > -1){
			eventPage.getEventContainer().getChildren().remove(numberOfEventsToDelete);
			numberOfEventsToDelete--;
			newNumbOfEvents--;
		}
		
		//Remove all events from the database
		DbWriter deleteAll = new DbWriter("TRASH");
		deleteAll.resetFile();
		
		//Since there are no more events to be deleted, close the delete-event-page window and disable the delete event button on the events-page
		if(newNumbOfEvents == -1) {
			thirdStage.close();
			eventPage.setDeleteEventButtonDisabled(true);
		}
	}
	
	//Method returns integer equal to number of events remaining after deleting an event 
	public int getNewNumberOfEvents() {
		return newNumbOfEvents;
	}
}