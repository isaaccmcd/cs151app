package application.events;

import java.io.File;

import application.DbEventRemover;
import application.DbWriter;
import application.DetailedEventPage;
import application.EditEventPage;
import application.Event;
import application.EventsPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class EditEvent {
	
	private int numbOfEvents;
	private int removedCardIndex;
	//private int newEventID;
	
	//No-arguments constructor
	public EditEvent() {}
	
	//Method deletes the event you have selected to edit and replaces it with a new event with updated attributes
	//	- replaces desired event card on events-page with updated event
	//	- replaces desired event details in the database
	public void editAnEvent(EditEventPage editEvent, EventsPage eventPage, BorderPane eventPageContainer, Scene homeScene, int eventCount) {
		
		numbOfEvents = eventCount;
		
		//Get event-page event container
		FlowPane eventsContainer = eventPage.getEventContainer();
		
		//Get the event chosen to be selected by the user
		ChoiceBox<String> eventToEditName = editEvent.getCurrentExistingEvents();
		String selected = eventToEditName.getValue();
		
		//Find the event chosen to be edited by user and delete it
		for(int x = 0; x < numbOfEvents+1; x++) {
			
			//
			//System.out.println(x);
			VBox test = (VBox) eventsContainer.getChildren().get(x);
			Label label = (Label) test.getChildren().get(0);
			
			//Label eventIDLabel = (Label) test.getChildren().get(6);
			//newEventID = Integer.parseInt(eventIDLabel.getText());
			
			//
			if(selected.equals(label.getText())) {
				
				removedCardIndex = x;
				
				eventPage.getEventContainer().getChildren().remove(x);
				numbOfEvents--;
				
				
				//DATABASE EVENT REMOVAL
				Label label2 = (Label) test.getChildren().get(7);
				String label2Contents = label2.getText();
				int numbIDToLookFor = Integer.parseInt(label2Contents);
				
				DbEventRemover editEventRemover = new DbEventRemover();
				editEventRemover.deleteEventAndRewriteDb(numbIDToLookFor);
				
				File f2 = new File("eventsDb", "EventsDb.txt");
				f2.delete();
				
				editEventRemover.renameDbToOriginal();
			}
		}
		
		//Build new event with the details filled into and or changed in edit event window
		//newNumbOfEvents = eventCount;
		
		//Capture details about new event from add-event window
		int eventID = Integer.MIN_VALUE + eventCount + 1000;
		String name = editEvent.getEditPageEventName();
		String desc= editEvent.getEditPageEventDesc();
		
		String classif = editEvent.getEditPageEventClass();
		String otherClassif = editEvent.getEditPageEventClassOther();
		
		String locate = editEvent.getEditPageEventLocation();
		String month = editEvent.getEditPageEventMonth();
		String day = editEvent.getEditPageEventDay();
		String year = editEvent.getEditPageEventYear();
		Boolean noDate = editEvent.getNoDateOption();
		String time = editEvent.getEditPageEventTime();
		String AMPM = editEvent.getEditPageEventAMPM();
		
		//Write details from edit-event window to database as a new event
		
		Event anEvent;
		
		//Load event to database then create event if classification is "other"
		if(classif.contentEquals("Other")) {
			if(noDate == true && time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + otherClassif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, otherClassif, locate, null, null, null, null, null, eventID);
				
			}
			else if(noDate == true) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + otherClassif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
				
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, otherClassif, locate, null, null, null, time, AMPM, eventID);
				
			}
			else if(time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + otherClassif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, otherClassif, locate, month, day, year, null, null, eventID);
				
			}
			else {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + otherClassif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, otherClassif, locate, month, day, year, time, AMPM, eventID);
			}
		}
		//Load event to database then create event if classification is "None"
		else if(classif.contentEquals("None")) {
			if(noDate == true && time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, null, locate, null, null, null, null, null, eventID);
				
			}
			else if(noDate == true) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
				
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, null, locate, null, null, null, time, AMPM, eventID);
				
			}
			else if(time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, null, locate, month, day, year, null, null, eventID);
				
			}
			else {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, null, locate, month, day, year, time, AMPM, eventID);
			}
		}
		//Load event to database then create event if classification is NOT "Other" or "None"
		else {
			if(noDate == true && time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + classif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, classif, locate, null, null, null, null, null, eventID);
				
			}
			else if(noDate == true) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + classif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
				
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, classif, locate, null, null, null, time, AMPM, eventID);
				
			}
			else if(time.contentEquals("None")) {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + classif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " +
						 "\n" + eventID + " ");
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, classif, locate, month, day, year, null, null, eventID);
				
			}
			else {
				
				DbWriter dbWriter = new DbWriter();
				dbWriter.writeToFile(
								eventID + " " + name +
						 "\n" + eventID + " " + desc +
						 "\n" + eventID + " " + classif +
						 "\n" + eventID + " " + locate +
						 "\n" + eventID + " " + month +
						 "\n" + eventID + " " + day +
						 "\n" + eventID + " " + year +
						 "\n" + eventID + " " + time +
						 "\n" + eventID + " " + AMPM);
				dbWriter.closeWriter();
			
				//Create new Event from details captured from add-event-window
				anEvent = new Event(name, desc, classif, locate, month, day, year, time, AMPM, eventID);
			}
		}
		
		if(classif.contentEquals("None")) {
			anEvent.setClassif(null);
			//anEvent.setClassif("No Classification");
		}
		
		//We are creating a new event, so we increment the event counter
		numbOfEvents = numbOfEvents + 1;
		
		//Enable delete event button on events-page if disabled
		//(this button is disabled if no events on events-page, once an event is added, the button is re-enabled)
		if(eventPage.getDeleteEventBtn().isDisabled() == true)
			eventPage.setDeleteEventButtonDisabled(false);
		
		
		//Create GUI card on events-page to display new event to the user
		VBox card = new VBox();
		card.setAlignment(Pos.CENTER);
		card.setPadding(new Insets(0, 10, 10, 10));
		card.getStyleClass().add("event-card");
		card.setPrefSize(250, 250);
		
		
		//Create new JavaFX elements (Labels) to display event details on GUI card
		Label theName = new Label(anEvent.getName());
		Label theDesc = new Label(anEvent.getDesc());
		Label theClassif = new Label(anEvent.getClassif());
		Label theLocate = new Label(anEvent.getLocate());
		Label theDate = new Label(anEvent.getMonth() + " " + anEvent.getDay() + ", " + anEvent.getYear());
		Label theTime = new Label(anEvent.getTime() + " " + anEvent.getAMPM());
		Label theID = new Label(Integer.toString(anEvent.getID()));
		Label spacer = new Label(" ");
		
		//Build strings as inputs for tooltip
		String ttName = name;
		String ttDesc;
		if(desc.contentEquals(""))
			ttDesc = "No Description";
		else if(desc.length() > 24)
			ttDesc = desc.substring(0, 24) + "...";
		else
			ttDesc = desc;
		String ttClassif;
		if(classif.contentEquals(""))
			ttClassif = "No Classification";
		else
			ttClassif = classif;
		String ttLocate;
		if(locate.contentEquals(""))
			ttLocate = "No Location";
		else
			ttLocate = locate;
		String ttDate;
		if(day.contentEquals(""))
			ttDate = "No Date";
		else
			ttDate = month + " " + day + ", " + year;
		String ttTime;
		if(time.contentEquals("None"))
			ttTime = "No Time";
		else
			ttTime = time + " " + AMPM;
		
		//Build tooltip to event card title
		Tooltip eventCardTooltip = new Tooltip(
				"Title: " + ttName + "\n" +
						"Classification: " + ttClassif + "\n" +
						"Description: " + ttDesc + "\n" +
						"Location: " + ttLocate + "\n" +
						"Date: " + ttDate + "\n" +
						"Time: " + ttTime
				);
		
		//Style the Labels
		eventCardTooltip.getStyleClass().add("tool-tip");
		eventCardTooltip.setOpacity(1.0);
		theName.setTooltip(eventCardTooltip);
		theID.getStyleClass().add("hidden-id");
		theName.setWrapText(true);
		theName.setTextAlignment(TextAlignment.CENTER);
		theName.getStyleClass().add("event-card-title");
		theDesc.setWrapText(true);
		theDesc.getStyleClass().add("hidden-id");
		theClassif.setWrapText(true);
		theClassif.setTextAlignment(TextAlignment.CENTER);
		theLocate.setWrapText(true);
		theLocate.getStyleClass().add("hidden-id");
		theDate.setWrapText(true);
		theTime.setWrapText(true);
		theID.setWrapText(true);
		
		//Build effects
		Glow glowEffect = new Glow();
		
		//Add effects to labels
		theName.setEffect(glowEffect);
		
		//Add elements to GUI card
		if(theDate.getText().contentEquals("null null, null") && theTime.getText().contentEquals("null null")) {
			theDate.getStyleClass().add("hidden-id");
			theTime.getStyleClass().add("hidden-id");
		}
		else if(theDate.getText().contentEquals("null null, null")) {
			theDate.getStyleClass().add("hidden-id");
		}
		else if(theTime.getText().contentEquals("null null")) {
			theTime.getStyleClass().add("hidden-id");
		}
		card.getChildren().addAll(theName, spacer, theDesc, theClassif, theLocate, theDate, theTime, theID);
		
		//Add event handling to GUI card, which takes the user to detailed-event page for that specific GUI card
		DetailedEventPage detailedEventPage = new DetailedEventPage();
		detailedEventPage.Build(card, eventPage, eventPageContainer, homeScene);
		
		
		//Add GUI card to events-page
		eventPage.getEventContainer().getChildren().add(card);
		//eventPage.getEventContainer().getChildren().add(removedCardIndex, card);	//This only works while the current instance of the app is running, once the app is relaunched, the database will position the edited card at the end of the card collection
		
		//Clear input fields in add-event window
		editEvent.clearEditPageEventName();
		editEvent.clearEditPageEventDesc();
		editEvent.clearEditPageEventLocation();
		editEvent.clearEditPageEventDay();
		editEvent.clearEditPageEventYear();
		editEvent.clearEditPageEventClassOther();
		editEvent.resetEditPageEventClass();
		editEvent.resetEditPageEventMonth();
		editEvent.resetEditPageEventTime();
		editEvent.resetNoDateOption();
		editEvent.resetEditPageEventTimeAMPM();
	}
	
	//NOTE: this method is not needed for final product b/c we delete + rebuild an event when it is edited, therefore total number of events does not change
	public int getNumbOfEvents() {
		return numbOfEvents;
	}
	
	//Method returns the index of the card to be removed and replaced
	public int getRemovedCardIndex() {
		return removedCardIndex;
	}
}