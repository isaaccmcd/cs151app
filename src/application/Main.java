package application;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

////LOCAL IMPORTS
import application.events.BuildEvent;
import application.events.DeleteEvent;
import application.events.EditEvent;
import application.events.LoadEventsFromDb;

public class Main extends Application
{
	
	private int eventCount;
	private EventsPage recallEvents;
	
	@Override
	public void start(Stage primaryStage)
	{
		
		eventCount = -1;
		
		try
		{
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//APP SCENES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			HomePage recallHome = new HomePage();
			VBox homePage = recallHome.getSurfaceStack();
			Scene homeWindow = new Scene(homePage,1600,900);
			homeWindow.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			recallEvents = new EventsPage();
			BorderPane eventsPage = recallEvents.eventPageSurface();
			//Scene viewEventsWindow = new Scene(eventsPage,1600,900);
			//viewEventsWindow.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			AddEventPage recallAddEvent = new AddEventPage();
			VBox addEventsWindow = recallAddEvent.getSurfaceStack();
			Scene addEventWindow = new Scene(addEventsWindow,800,560);
			addEventWindow.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			EditEventPage recallEditEvent = new EditEventPage();
			VBox editEventsWindow = recallEditEvent.getSurfaceStack();
			//Scene editEventWindow = new Scene(editEventsWindow,800,665);
			Scene editEventWindow = new Scene(editEventsWindow,800,690);
			editEventWindow.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			DeleteEventPage recallDeleteEvent = new DeleteEventPage();
			VBox deleteEventWindow = recallDeleteEvent.getSurfaceStack();
			Scene removeEventWindow = new Scene(deleteEventWindow,700,520);
			removeEventWindow.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			VerifyDeleteAllEventsPage recallVerification = new VerifyDeleteAllEventsPage();
			VBox verificationWindow = recallVerification.getSurfaceStack();
			Scene verifyDeleteAllEvents = new Scene(verificationWindow,550,175);
			verifyDeleteAllEvents.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//END OF APP SCENES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//LOAD EVENTS FROM DATABASE ON STARTUP
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			LoadEventsFromDb loadAllEventsOnStartup = new LoadEventsFromDb(recallEvents, eventsPage, homeWindow, eventCount);
			loadAllEventsOnStartup.LoadAllData();
			loadAllEventsOnStartup.GiveEventHandleAndAddToEventContainerContainer();
			eventCount = loadAllEventsOnStartup.getNumberOfEvents();
			
			//sysout for debugging purposes
			int numbOfEventsLoaded = eventCount + 1;
			System.out.println("\nLOADED ON STARTUP: " + "eventCount = " + numbOfEventsLoaded);
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//END OF LOAD EVENTS FROM DATABASE ON STARTUP
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//STAGES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			ImageLoader appIcon = new ImageLoader();
			Image appIconImage = appIcon.appIconImage();
			
			//CREATE SECOND STAGE FOR ADD EVENT FEATURE
			Stage secondStage = new Stage();
			secondStage.setScene(addEventWindow);
			secondStage.setTitle("Recall - Add Event");
			secondStage.setResizable(false);
			secondStage.getIcons().add(appIconImage);
			
			//CREATE THIRD STAGE FOR REMOVE EVENT FEATURE
			Stage thirdStage = new Stage();
			thirdStage.setScene(editEventWindow);
			thirdStage.setTitle("Recall - Edit Event");
			thirdStage.setResizable(false);
			thirdStage.getIcons().add(appIconImage);
			
			//CREATE THIRD STAGE FOR REMOVE EVENT FEATURE
			Stage fourthStage = new Stage();
			fourthStage.setScene(removeEventWindow);
			fourthStage.setTitle("Recall - Delete Event");
			fourthStage.setResizable(false);
			fourthStage.getIcons().add(appIconImage);
			
			Stage fifthStage = new Stage();
			fifthStage.setScene(verifyDeleteAllEvents);
			fifthStage.setTitle("Recall - Verify Delete ALL Events");
			fifthStage.setResizable(false);
			fifthStage.getIcons().add(appIconImage);
			
			//HOME PAGE STAGE PROPERTIES
			primaryStage.setScene(homeWindow);
			primaryStage.setTitle("Recall");
			primaryStage.setResizable(true);
			primaryStage.setMinWidth(1600);
			primaryStage.setMinHeight(900);
			primaryStage.getIcons().add(appIconImage);
			
			//SHOW HOME PAGE
			primaryStage.show();
			primaryStage.setWidth(primaryStage.getWidth());		//IMPORTANT: to retain width of window when scene changes
			primaryStage.setHeight(primaryStage.getHeight());	//IMPORTANT: to retain width of window when scene changes
																//NOTE: these two lines placed AFTER "primaryStage.show()" to prevent weird initial startup
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//END OF STAGES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//EVENT HANDLING
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
//Events That Deal With Leaving Home Page
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//open "Events" scene if anywhere on home page is clicked
			homeWindow.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				if(homeWindow.getRoot() == homePage) {
					//This is weird but necessary for some reason
					//BEGIN:
					Node eventsPageBorderPaneLeft = recallEvents.getBorderNode();
					recallEvents.REanimateBorderPaneLeftSide(eventsPageBorderPaneLeft);
					//END
					recallEvents.eventPageStartUpAnimations();
					homeWindow.setRoot(eventsPage);
					primaryStage.setTitle("Recall - Events");
				}
			});
			
			//open "Events" scene if any keyboard button is clicked
			homeWindow.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
				
				if(homeWindow.getRoot() == homePage) {
					//This is weird but necessary for some reason
					//BEGIN:
					Node eventsPageBorderPaneLeft = recallEvents.getBorderNode();
					recallEvents.REanimateBorderPaneLeftSide(eventsPageBorderPaneLeft);
					//END
					recallEvents.eventPageStartUpAnimations();
					homeWindow.setRoot(eventsPage);
					primaryStage.setTitle("Recall - Events");
				}
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//ALL Buttons on Event-Page Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//expand events-page editor if open button is clicked events-page editor is collapsed
			Button open = recallEvents.getOpenEditor();
			open.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				eventsPage.setLeft(recallEvents.setLeftOpenEditor());
			});
			
			//collapse events-page editor if close button is clicked when events-page editor is expanded
			Button close = recallEvents.getCloseEditor();
			close.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				eventsPage.setLeft(recallEvents.setLeftCloseEditor());
			});
			
			//open add-event-page window
			Button addEvent = recallEvents.getAddEventBtn();
			addEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				recallAddEvent.clearAndResetAllFields();
				secondStage.show();
			});
			
			//open add-event-page window
			Button editEvent = recallEvents.getEditEventBtn();
			editEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				recallEditEvent.clearCurrentExistingEvents();
				
				FlowPane eventContainer = recallEvents.getEventContainer();
				for(Node aNode : eventContainer.getChildren()) {
					
					VBox eventCard = (VBox) aNode;
					Label eventTitleNode = (Label) eventCard.getChildren().get(0);
					
					String eventTitle = eventTitleNode.getText();
					//System.out.println(eventTitle);
					
					
					recallEditEvent.populateCurrentExistingEvents(eventTitle);
				}
				
				recallEditEvent.clearAndResetAllFields();
				thirdStage.show();
			});
			
			//open delete-event-page window
			Button deleteEvent = recallEvents.getDeleteEventBtn();
			deleteEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				VBox recallDeleteEventSurfaceStack = recallDeleteEvent.getDeleteEventPageSurfaceStack();
				Node theSecondNode = recallDeleteEventSurfaceStack.getChildren().get(3);
				if(theSecondNode instanceof Label) {
					recallDeleteEvent.hideInvalidTitleInputErrorMessage();
				}
				
				recallDeleteEvent.clearAndResetAllFields();
				fourthStage.show();
			});
			
			//redirect user to home page WHEN home button 
			Button returnHome = recallEvents.getReturnHomeBtn();
			returnHome.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				recallHome.reloadHomeAnimations();
				homeWindow.setRoot(homePage);
				primaryStage.setTitle("Recall");
				secondStage.close();
				thirdStage.close();
				fourthStage.close();
				fifthStage.close();
				eventsPage.setLeft(recallEvents.setLeftCloseEditor());
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//Second and Third Stages Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//close the add event window if close button on add event page is clicked
			Button closeCreateE = recallAddEvent.getCloseCreateEventBtn();
			closeCreateE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				secondStage.close();
			});
			
			//close the add event window if "escape" button on keyboard is pressed when second stage is open
			secondStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				
				if(e.getCode() == KeyCode.ESCAPE){
					secondStage.close();
				}
			});
			
			//close the delete event window if close button on delete event page is clicked
			Button closeDeleteE = recallDeleteEvent.getCloseDeleteEventBtn();
			closeDeleteE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				fourthStage.close();
				fifthStage.close();
			});
			
			//close the delete event window if "escape" button on keyboard is pressed when second stage is open
			fourthStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				
				if(e.getCode() == KeyCode.ESCAPE){
					fourthStage.close();
					fifthStage.close();
				}
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//Create Event Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//creates new event from add-event-page window and displays event on events-page, stores new event to database
			//Trigger: mouse click
			BuildEvent buildOnButton = new BuildEvent();	// <-- Used for both event handles below
			
			Button createE = recallAddEvent.getCreateEventBtn();
			createE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				buildOnButton.buildAnEvent(recallAddEvent, recallEvents, eventCount, eventsPage, homeWindow);
				eventCount = buildOnButton.getNewNumberOfEvents();
				
				//sysout for debugging purposes
				int numbOfNewEvents = eventCount + 1;
				System.out.println("INCREMENT: " + "eventCount = " + numbOfNewEvents);
			});
			
			//creates new event from add-event-page window and displays event on events-page, stores new event to database
			//Trigger: "enter" key pressed
			secondStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				
				if(e.getCode() == KeyCode.ENTER){
					
					buildOnButton.buildAnEvent(recallAddEvent, recallEvents, eventCount, eventsPage, homeWindow);
					eventCount = buildOnButton.getNewNumberOfEvents();
					
					//sysout for debugging purposes
					int numbOfNewEvents = eventCount + 1;
					System.out.println("INCREMENT: " + "eventCount = " + numbOfNewEvents);
				}
			});
			
			//Event allows scroll-ability through time options in add-events-page
			//Trigger: mouse scroll
			ChoiceBox<String> addEventTimeChoiceBox = recallAddEvent.getTimeChoiceBox();
			addEventTimeChoiceBox.addEventHandler(ScrollEvent.SCROLL, e -> {
				recallAddEvent.setNextTimeInTimeChoiceBox();
			});
			
			//Event allows scroll-ability through classification options in add-events-page
			//Trigger: mouse scroll
			ChoiceBox<String> addEventClassChoiceBox = recallAddEvent.getClassChoiceBox();
			addEventClassChoiceBox.addEventHandler(ScrollEvent.SCROLL, e -> {
				recallAddEvent.setNextClassInClassChoiceBox();
			});
			
			//Event allows scroll-ability through month options in add-events-page
			//Trigger: mouse scroll
			ChoiceBox<String> addEventMonthChoiceBox = recallAddEvent.getMonthChoiceBox();
			addEventMonthChoiceBox.addEventHandler(ScrollEvent.SCROLL, e -> {
				recallAddEvent.setNextMonthInClassChoiceBox();
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//Edit Event Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
			//close the edit event window if close button on edit event page is clicked
			Button closeEditE = recallEditEvent.getCloseEditEventBtn();
			closeEditE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				thirdStage.close();
			});
			
			//close the edit event window if "escape" button on keyboard is pressed when third stage is open
			thirdStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				
				if(e.getCode() == KeyCode.ESCAPE){
					thirdStage.close();
				}
			});
			
			//fills all input fields in edit-event window with event user has selected to edit
			Button beginEditEvent = recallEditEvent.getEditEventBegin();
			beginEditEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				ChoiceBox<String> eventToEditName = recallEditEvent.getCurrentExistingEvents();
				String selected = eventToEditName.getValue();
				System.out.println("EVENT CURRENTLY SELECTED TO EDIT IS: " + selected);
				
				DbReader readFromDb = new DbReader();
				int numbOfLinesToIterateThrough = readFromDb.getNumbOfLines();
				
				for(int numLines = 0; numLines < numbOfLinesToIterateThrough; numLines++) {
					
					String aDbLine = readFromDb.getLineFromDb();
					String titleFromALine = aDbLine.substring(12);
					
					if(selected.contentEquals(titleFromALine)) {
						
						recallEditEvent.setEventName(titleFromALine);
						
						String desc = readFromDb.getLineFromDb();
						recallEditEvent.setEventDesc(desc.substring(12));
						
						String classif = readFromDb.getLineFromDb();
						String onlyClassif = classif.substring(12);
						if(onlyClassif.contentEquals("Personal") != true &&
						   onlyClassif.contentEquals("Family") != true &&
						   onlyClassif.contentEquals("Work") != true &&
						   onlyClassif.contentEquals("School") != true &&
						   onlyClassif.contentEquals("Volunteering") != true &&
						   onlyClassif.contentEquals("Club") != true &&
						   onlyClassif.contentEquals("Recreational") != true &&
						   onlyClassif.contentEquals("Vacation") != true &&
						   onlyClassif.length() > 0
						){
							
							recallEditEvent.setEventClass("Other");
							recallEditEvent.setEventClassOther(onlyClassif);
						}
						else if(onlyClassif.length() == 0) {
							recallEditEvent.setEventClass("None");
							recallEditEvent.setEventClassOther(null);
						}
						else {
							recallEditEvent.setEventClass(onlyClassif);
							recallEditEvent.setEventClassOther(null);
						}
						
						String locate = readFromDb.getLineFromDb();
						recallEditEvent.setEventLocation(locate.substring(12));
						
						String month = readFromDb.getLineFromDb();
						String day = readFromDb.getLineFromDb();
						String year = readFromDb.getLineFromDb();
						//System.out.println(month);
						if(month.substring(12).contentEquals("")) {
							recallEditEvent.resetEditPageEventMonth();
							recallEditEvent.clearEditPageEventDay();
							recallEditEvent.clearEditPageEventYear();
							recallEditEvent.setNoDateOption();
						}
						else {
							recallEditEvent.setEventMonth(month.substring(12));
							recallEditEvent.setEventDay(day.substring(12));
							recallEditEvent.setEventYear(year.substring(12));
							recallEditEvent.resetNoDateOption();
						}
						
						String time = readFromDb.getLineFromDb();
						String AMPM = readFromDb.getLineFromDb();
						//System.out.println(time);
						if(time.substring(12).contentEquals("")) {
							recallEditEvent.setEventTime("None");
							recallEditEvent.resetEditPageEventTimeAMPM();
						}
						else {
							recallEditEvent.setEventTime(time.substring(12));
							recallEditEvent.setEventTimeAMPM(AMPM.substring(12));
						}
						
						//decrement the total number of lines we need to iterate through as we skip each line
						numbOfLinesToIterateThrough = numbOfLinesToIterateThrough - 8;
					}
				}
				readFromDb.closeReader();
			});
			
			//finish editing event and update the event within the events page and database with new details
			Button finishEditEvent = recallEditEvent.getEditEventBtn();
			finishEditEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				EditEvent buildNewEventWithUpdatedDetails = new EditEvent();
				buildNewEventWithUpdatedDetails.editAnEvent(recallEditEvent, recallEvents, eventsPage, homeWindow, eventCount);
				
				//System.out.println(buildNewEventWithUpdatedDetails.getRemovedCardIndex());
				
				//DELETE THIS LATE BECAUSE YOULL BE REBUILDING THE EVENT
				eventCount = buildNewEventWithUpdatedDetails.getNumbOfEvents();
				//DELETE THIS LATE BECAUSE YOULL BE REBUILDING THE EVENT
				
				recallEditEvent.clearCurrentExistingEvents();
				
				//This loop repopulates the edit-event-page event options
				//NOTE: this might not be necessary later because we wont be deleting an event, we'll be replacing it
				FlowPane eventContainer = recallEvents.getEventContainer();
				for(Node aNode : eventContainer.getChildren()) {
					
					VBox eventCard = (VBox) aNode;
					Label eventTitleNode = (Label) eventCard.getChildren().get(0);
					
					String eventTitle = eventTitleNode.getText();
					//System.out.println(eventTitle);
					
					
					recallEditEvent.populateCurrentExistingEvents(eventTitle);
				}
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//Delete SINGLE Event Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Event deletes event based on event name input given in delete-event-page and removes event from events-page and database
			//Trigger: mouse click
			Button deleteE = recallDeleteEvent.getDeleteEventBtn();
			deleteE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				DeleteEvent deleteOnMouseClick = new DeleteEvent();
				deleteOnMouseClick.deleteAnEvent(fourthStage, recallDeleteEvent, recallEvents, eventCount);
				eventCount = deleteOnMouseClick.getNewNumberOfEvents();
				
				//sysout for debugging purposes
				int numbOfNewEvents = eventCount + 1;
				System.out.println("DECREMENT: " + "eventCount = " + numbOfNewEvents);
			});
			
			
			//Event deletes event based on event name input given in delete-event-page and removes event from events-page and database
			//Trigger: "enter" key pressed
			fourthStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				if(e.getCode() == KeyCode.ENTER)
				{
					DeleteEvent deleteOnEnterKeyPressed = new DeleteEvent();
					deleteOnEnterKeyPressed.deleteAnEvent(fourthStage, recallDeleteEvent, recallEvents, eventCount);
					eventCount = deleteOnEnterKeyPressed.getNewNumberOfEvents();
					
					//sysout for debugging purposes
					int numbOfNewEvents = eventCount + 1;
					System.out.println("DECREMENT: " + "eventCount = " + numbOfNewEvents);
				}
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
//Delete ALL Event Event Handling
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Event opens verification window
			//Trigger: mouse click
			Button deleteAllE = recallDeleteEvent.getDeleteAllEventBtn();
			deleteAllE.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				fifthStage.show();
			});
			
			//Event deletes ALL events from events-page and database
			//Trigger: mouse click
			Button yesButton = recallVerification.getYesButton();
			yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				DeleteEvent deleteAllOnMouseClick = new DeleteEvent();
				deleteAllOnMouseClick.deleteAllEvents(thirdStage, recallEvents, eventCount);
				eventCount = deleteAllOnMouseClick.getNewNumberOfEvents();
				
				//sysout for debugging purposes
				int numbOfNewEvents = eventCount + 1;
				System.out.println("REMOVED ALL: " + "eventCount = " + numbOfNewEvents);
				
				fourthStage.close();
				fifthStage.close();
			});
			
			//Event closes verification window
			//Trigger: mouse click
			Button noButton = recallVerification.getNoButton();
			noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				
				fifthStage.close();
			});
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//END OF EVENT HANDLING
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
		}
		catch(Exception mainClassError)
		{
			mainClassError.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}