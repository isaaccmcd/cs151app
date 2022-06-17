package application.events;

import java.util.ArrayList;

import application.DbReader;
import application.DetailedEventPage;
import application.Event;
import application.EventsPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class LoadEventsFromDb {
	
	private int loadFromDbEventCount;
	private EventsPage eventPage;
	private BorderPane eventPageContainer;
	private Scene homeScene;
	private ArrayList<VBox> eventContainerList;
	
	//Constructor
	//No-arguments constructor
	public LoadEventsFromDb(EventsPage inputEventsPage, BorderPane eventPageContainer, Scene homeScene, int eventCount) {
		
		this.loadFromDbEventCount = eventCount;
		this.eventPage = inputEventsPage;
		this.eventPageContainer = eventPageContainer;
		this.homeScene = homeScene;
		this.eventContainerList = new ArrayList<>();
	}
	
	//Method loads event data from database onto event cards (VBoxes), event cards are then stored to array-list
	//	- sets Integer to number of events loaded from database
	
	//Method loads lines from database and creates events from those lines
	public void LoadAllData() {
		
		//Create a database reader
		DbReader reader = new DbReader();
		
		//Number of lines currently in database
		int numbOfLines = reader.getNumbOfLines();
		
		//If the number of lines in database is more than 0, enable the delete-event-button on events-page
		if(numbOfLines != 0)
			eventPage.setDeleteEventButtonDisabled(false);
		
		//Loop retrieves 9 lines from database, or one whole event
		while(numbOfLines >= 9) {
			
			//Create String events attributes from each line in database
			String lineOne = reader.getLineFromDb();
			String stringID = lineOne.substring(0, 11);
			int intID = Integer.parseInt(stringID);
			String name = lineOne.substring(12);
			
			String lineTwo = reader.getLineFromDb();
			String desc = lineTwo.substring(12);
			
			String lineThree = reader.getLineFromDb();
			String classif = lineThree.substring(12);
			
			String lineFour = reader.getLineFromDb();
			String locate = lineFour.substring(12);
			
			String lineFive = reader.getLineFromDb();
			String month = lineFive.substring(12);
			
			String lineSix = reader.getLineFromDb();
			String day = lineSix.substring(12);
			
			String lineSeven = reader.getLineFromDb();
			String year = lineSeven.substring(12);
			
			
			String lineEight = reader.getLineFromDb();
			String time = lineEight.substring(12);
			
			String lineNine = reader.getLineFromDb();
			String AMPM = lineNine.substring(12);
			
			//Build an events with lines retrieved from database
			Event anEvent;
			
			if(month.contentEquals("") && time.contentEquals("")) {

				anEvent = new Event(name, desc, classif, locate, null, null, null, null, null, intID);

			}
			else if(month.contentEquals("")) {

				anEvent = new Event(name, desc, classif, locate, null, null, null, time, AMPM, intID);

			}
			else if(time.contentEquals("")) {

				anEvent = new Event(name, desc, classif, locate, month, day, year, null, null, intID);

			}
			else {

				anEvent = new Event(name, desc, classif, locate, month, day, year, time, AMPM, intID);
			}
			
			if(classif.contentEquals("")) {
				anEvent.setClassif(null);
				//anEvent.setClassif("No Classification");
			}
			
			//Create JavaFX labels with attributes from Event-Builder
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
			if(time.contentEquals(""))
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
			eventCardTooltip.getStyleClass().add("tool-tip");
			eventCardTooltip.setOpacity(1.0);
			theName.setTooltip(eventCardTooltip);
			
			//Style event card labels
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
			
			//Set label ids to hidden if they are null
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
			
			//Build effects
			Glow glowEffect = new Glow();
			
			//Add effects to labels
			theName.setEffect(glowEffect);
			
			//Create new layout-pane (event container) to house event attributes (in the form of labels)
			VBox eventContainerCard = new VBox();
			eventContainerCard.setAlignment(Pos.CENTER);
			eventContainerCard.setPadding(new Insets(0, 10, 10, 10));
			eventContainerCard.getStyleClass().add("event-card");
			eventContainerCard.setPrefSize(250, 250);
			
			//Add event attributes (in the form of labels) to the event container
			eventContainerCard.getChildren().addAll(theName, spacer, theDesc, theClassif, theLocate, theDate, theTime, theID);
			
			//Save event-container to array-list
			eventContainerList.add(eventContainerCard);
			
			//Decrement the number of lines needed to be processed by the loop
			numbOfLines = numbOfLines - 9;
		}
		reader.closeReader();
	}
	
	//Method gives each event card within event card array-list an event handle that allows user to view detailed-event-viewing page...
	//...and adds the card to the event page flow-pane
	public void GiveEventHandleAndAddToEventContainerContainer() {
		
		//Loop through all event cards (VBoxes) in the VBox array-list
		for(int x=0; x<eventContainerList.size(); x++) {
			
			//Create event card (VBox) from array-list of VBoxes
			VBox card = eventContainerList.get(x);
			
			//Build detailed event viewing page for each event card and add corresponding event handling
			DetailedEventPage detailedEventPage = new DetailedEventPage();
			detailedEventPage.Build(card, eventPage, eventPageContainer, homeScene);
			
			
			//Add event card to the event container
			eventPage.getEventContainer().getChildren().add(card);
			
			//Increment the number of events added
			loadFromDbEventCount++;
		}
	}
	
	//Method returns number of events loaded to application from database
	//Method returns integer equal to number of events added 
	public int getNumberOfEvents() {
		return loadFromDbEventCount;
	}
}