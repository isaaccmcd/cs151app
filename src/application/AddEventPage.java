package application;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddEventPage implements ApplicationPage{
	
	private VBox leftPane;
	private VBox rightPane;
	private HBox addEventPageNodeContainer;
	
	private TextField eventNameTextField;
	private TextField eventClassTextField;
	private TextField eventLocationTextField;
	private TextField eventDayTextField;
	private TextField eventYearTextField;
	private TextArea eventDescriptionTextArea;
	private ChoiceBox<String> eventClassChoiceBox;
	private ChoiceBox<String> eventMonthChoiceBox;
	private ChoiceBox<String> eventTimeChoiceBox;
	private RadioButton AMRadioButton;
	private RadioButton PMRadioButton;
	private RadioButton noDateOptionButton;
	private Button createEventButton;
	private Button closeCreateEventWindowButton;
	
	//Constructor for add-event-page
	//	- Builds LOTS of elements, refer to constructor comments
	public AddEventPage() {
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Left layout-pane for add-event-page window
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Build left add-event-page layout-pane (VBox)
		leftPane = new VBox();
		leftPane.setPadding(new Insets(20, 20, 20, 20));
		leftPane.setSpacing(10);
		//left.setAlignment(Pos.CENTER_LEFT);
		
		//Build layout-pane for add-event-page elements: eventNameLabel, requiredLabel
		HBox containerOne = new HBox();
		containerOne.setSpacing(10);
		
		//Build eventNameLabel
		Label eventNameLabel = new Label("Event Name");
		
		//Build requiredLabel
		Label requiredLabel = new Label("(Required Field)");
		requiredLabel.getStyleClass().add("req-lab");
		
		//Add elements to containerOne: eventNameLabel, requiredLabel
		containerOne.getChildren().addAll(eventNameLabel, requiredLabel);
		
		//Build eventNameTextField
		eventNameTextField = new TextField();
		eventNameTextField.setPrefHeight(40);
		eventNameTextField.setPromptText("cs151 Final Exam...");
		
		//Build eventDescriptionLabel
		Label eventDescriptionLabel = new Label("Description");
		
		//Build eventDescriptionTextArea
		eventDescriptionTextArea= new TextArea();
		eventDescriptionTextArea.setPromptText("Details about my event...");
		eventDescriptionTextArea.setWrapText(true);	//Makes text go-to new line, instead of continuing and creating scroll bar
		
		//Build eventClassificationLabel
		Label eventClassificationLabel = new Label("Classification");
		
		//Build eventClassChoiceBox
		eventClassChoiceBox = new ChoiceBox<>();
		eventClassChoiceBox.setPrefHeight(40);
		eventClassChoiceBox.getItems().addAll("Personal", "Family", "Work", "School", "Volunteering", "Club" , "Recreational", "Vacation", "Other" ,"None");
		eventClassChoiceBox.setValue("Work");
		
		//Build eventClassTextField
		eventClassTextField = new TextField();
		eventClassTextField.setPrefHeight(40);
		eventClassTextField.setPromptText("Other Classification...");
		eventClassTextField.setDisable(true);
		
		//EventHandler sets eventClassTextField to disabled unless eventClassChoiceBox has value "Other"
		eventClassChoiceBox.addEventHandler(ActionEvent.ACTION, e -> {
			if(eventClassChoiceBox.getValue() == "Other")
				eventClassTextField.setDisable(false);
			else
				eventClassTextField.setDisable(true);
		});
		
		//Add all elements to left add-event-page layout-pane
		leftPane.getChildren().addAll(containerOne, eventNameTextField, eventDescriptionLabel, eventDescriptionTextArea, eventClassificationLabel, eventClassChoiceBox, eventClassTextField);
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Right layout-pane for add-event-page window
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Build right add-event-page layout-pane (VBox)
		rightPane = new VBox();
		rightPane.setPadding(new Insets(20, 20, 20, 20));
		rightPane.setSpacing(10);
		//right.setAlignment(Pos.CENTER_LEFT);
		
		//Build eventLocationLabel
		Label eventLocationLabel = new Label("Location");
		
		//Build eventLocationTextField
		eventLocationTextField = new TextField();
		eventLocationTextField.setPrefHeight(40);
		eventLocationTextField.setPromptText("1 Washington Sq, San Jose, CA 95192");
		
		//Build eventDateLabel
		Label eventDateLabel = new Label("Date");
		eventDateLabel.setPadding(new Insets(10, 0, 0, 0));
		
		//Build layout-pane for add-event-page elements: eventMonthLabel, eventMonthChoiceBox, eventDayLabel, eventDayTextField
		HBox containerTwo = new HBox();
		containerTwo.setPadding(new Insets(20, 0, 0, 0));
		containerTwo.setAlignment(Pos.CENTER_LEFT);
		containerTwo.setSpacing(10);
		containerTwo.getStyleClass().add("small-bord-top");
		
		//Build eventMonthLabel
		Label eventMonthLabel = new Label("Month");
		
		//Build eventMonthChoiceBox
		eventMonthChoiceBox = new ChoiceBox<>();
		//eventMonthChoiceBox.setPrefWidth(110); //before changing label size
		eventMonthChoiceBox.setPrefWidth(135);
		eventMonthChoiceBox.setPrefHeight(40);
		eventMonthChoiceBox.getItems().addAll("January", "February", "March", "April", "May", "June" , "July", "August", "September", "October", "November", "December");
		eventMonthChoiceBox.setValue("January");
		
		//Build eventDayLabel
		Label eventDayLabel = new Label("Day");
		
		//Build eventDayTextField
		eventDayTextField = new TextField();
		eventDayTextField.setAlignment(Pos.CENTER);
		eventDayTextField.setPrefHeight(40);
		eventDayTextField.setPrefWidth(40);
		eventDayTextField.setPromptText("25");
		
		//Add elements to containerTwo: eventMonthLabel, eventMonthChoiceBox, eventDayLabel, eventDayTextField
		containerTwo.getChildren().addAll(eventMonthLabel, eventMonthChoiceBox, eventDayLabel, eventDayTextField);
		
		//Build layout-pane for add-event-page elements: eventYearLabel, eventYearTextField
		HBox containerThree = new HBox();
		containerThree.setPadding(new Insets(10, 0, 20, 0));
		containerThree.setAlignment(Pos.CENTER_LEFT);
		containerThree.setSpacing(20);
		containerThree.getStyleClass().add("small-bord-bot");
		
		//Build eventYearLabel
		Label eventYearLabel = new Label("Year");
		
		//Build eventYearTextField
		eventYearTextField = new TextField();
		eventYearTextField.setPrefHeight(40);
		eventYearTextField.setPrefWidth(110);
		eventYearTextField.setPromptText("2021");
		
		//Build noDateOptionButton
		noDateOptionButton = new RadioButton("No Date");
		
		//Add elements to containerThree: eventYearLabel, eventYearTextField
		containerThree.getChildren().addAll(eventYearLabel, eventYearTextField, noDateOptionButton);
		
		//Build layout-pane for add-event-page elements: eventTimeLabel, eventTimeChoiceBox, AMRadioButton, PMRadioButton
		HBox containerFour = new HBox();
		containerFour.setPadding(new Insets(25, 0, 0, 0));
		containerFour.setSpacing(10);
		containerFour.setAlignment(Pos.CENTER_LEFT);
		
		//Build eventTimeLabel
		Label eventTimeLabel = new Label("Time");
		
		//Build eventTimeChoiceBox
		eventTimeChoiceBox = new ChoiceBox<>();
		eventTimeChoiceBox.setPrefHeight(40);
		eventTimeChoiceBox.getItems().addAll(
				"12:00", "12:30",
				"1:00", "1:30",
				"2:00", "2:30",
				"3:00", "3:30",
				"4:00", "4:30",
				"5:00", "5:30",
				"6:00", "6:30",
				"7:00", "7:30",
				"8:00", "8:30",
				"9:00", "9:30",
				"10:00", "10:30",
				"11:00", "11:30",
				"None"
		);
		//eventTimeChoiceBox.getItems().addAll("12:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00");
		eventTimeChoiceBox.setValue("12:00");
		
		//Build AMRadioButton
		AMRadioButton = new RadioButton("AM");
		
		//Build PMRadioButton
		PMRadioButton = new RadioButton("PM");
		
		//Establish toggle group for AMRadioButton and PMRadioButton
		ToggleGroup AMPMToggleGroup = new ToggleGroup();
		AMRadioButton.setToggleGroup(AMPMToggleGroup);
		PMRadioButton.setToggleGroup(AMPMToggleGroup);
		
		//Set initial value for toggle group: AMPMToggleGroup
		AMRadioButton.setSelected(true);
		
		//Add elements to containerFour: eventTimeLabel, eventTimeChoiceBox, AMRadioButton, PMRadioButton
		containerFour.getChildren().addAll(eventTimeLabel, eventTimeChoiceBox, AMRadioButton, PMRadioButton);
		
		//Build layout-pane for add-event-page elements: createEventButton, closeCreateEventWindowButton
		HBox containerFive = new HBox();
		containerFive.setPadding(new Insets(20, 0, 0, 0));
		containerFive.setSpacing(20);
		containerFive.setAlignment(Pos.CENTER_LEFT);
		
		//Build createEventButton
		createEventButton = new Button("Create Event");
		createEventButton.setPrefSize(170, 80);
		//createEventButton.setDisable(true);
		createEventButton.getStyleClass().add("addEventbutton");
		
		//Build closeCreateEventWindowButton
		closeCreateEventWindowButton = new Button("Close");
		//closeCreateEventWindowButton.setPrefSize(127, 50);
		closeCreateEventWindowButton.setPrefSize(170, 80);
		closeCreateEventWindowButton.getStyleClass().add("closebutton");
		
		//IMPORTANT, READ!!!!!!!
		//
		//This method tests to see if the "Event Name" text field contains characters.
		//If it does NOT. Then the "Create Event" button will NOT be enabled.
		//If it DOES. Then the "Create Event" button is enabled.
		//
		BooleanBinding createECondition = new BooleanBinding() {
			{
				super.bind(eventNameTextField.textProperty());
			}
			@Override
			protected boolean computeValue() {
				return (eventNameTextField.getText().isEmpty());
			}
		};
		createEventButton.disableProperty().bind(createECondition);
		
		//Add elements to containerFive: createEventButton, closeCreateEventWindowButton
		containerFive.getChildren().addAll(createEventButton, closeCreateEventWindowButton);
		
		
		//Add all elements to right add-event-page layout-pane
		rightPane.getChildren().addAll(eventLocationLabel, eventLocationTextField, eventDateLabel, containerTwo, containerThree, containerFour, containerFive);
		
		
		//Build container for left and right element containers
		addEventPageNodeContainer = new HBox();
		
		//Load add-event-page elements to top-level layout-pane
		addEventPageNodeContainer.getChildren().addAll(leftPane, rightPane);
		

	}
	
	//Method builds a top level layout-pane for add-event-page
	public VBox getSurfaceStack() {
		
		//Top-level layout-pane (HBox)
		VBox AEWindow = new VBox();
		AEWindow.setPadding(new Insets(20, 0, 0, 0));
		AEWindow.setAlignment(Pos.CENTER);
		
		//layout-pane for border purposes only
		VBox forBorderPurposeOnly = new VBox();
		forBorderPurposeOnly.setPadding(new Insets(10, 0, 0, 0));
		forBorderPurposeOnly.getStyleClass().add("big-bord-bot");
		
		//Set background of top-level layout-pane
		ImageLoader secondbckgrd = new ImageLoader();
		Background secbckgrd = secondbckgrd.altBackground();
		AEWindow.setBackground(secbckgrd);
		
		//Build add-event-page title
		Label addEventsPageTitle = new Label("Add Event");
		addEventsPageTitle.getStyleClass().add("secondary-title");
		
		//Build effects
		Glow glowEffect = new Glow();
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setInput(glowEffect);
		
		//Add effects to labels
		addEventsPageTitle.setEffect(dropShadowEffect);
		
		//Binding left and right layout-pane to resize with window
		rightPane.prefWidthProperty().bind(AEWindow.widthProperty());
		rightPane.prefHeightProperty().bind(AEWindow.heightProperty());
		leftPane.prefWidthProperty().bind(AEWindow.widthProperty());
		leftPane.prefHeightProperty().bind(AEWindow.heightProperty());
		
		AEWindow.getChildren().addAll(addEventsPageTitle, forBorderPurposeOnly, addEventPageNodeContainer);
		
		return AEWindow;
	}
	
	
	
	
	//Getters and Resetters and Clearers for all add-event-page attributes
	
	
	public String getEventName() {
		return eventNameTextField.getText();
	}
	
	public void clearEventName() {
		eventNameTextField.clear();
	}
	
	public String getEventClassOther() {
		return eventClassTextField.getText();
	}
	
	public void clearEventClassOther() {
		eventClassTextField.clear();
	}
	
	public String getEventLocation() {
		return eventLocationTextField.getText();
	}
	
	public void clearEventLocation() {
		eventLocationTextField.clear();
	}
	
	public String getEventDay() {
		return eventDayTextField.getText();
	}
	
	public void clearEventDay() {
		eventDayTextField.clear();
	}
	
	public String getEventYear() {
		return eventYearTextField.getText();
	}
	
	public void clearEventYear() {
		eventYearTextField.clear();
	}
	
	public String getEventDesc() {
		return eventDescriptionTextArea.getText();
	}
	
	public void clearEventDesc() {
		eventDescriptionTextArea.clear();
	}
	
	public String getEventClass() {
		return eventClassChoiceBox.getValue();
	}
	
	public void resetEventClass() {
		eventClassChoiceBox.setValue("Work");
	}
	
	public String getEventMonth() {
		return eventMonthChoiceBox.getValue();
	}
	
	public void resetEventMonth() {
		eventMonthChoiceBox.setValue("January");
	}
	
	public String getEventTime() {
		return eventTimeChoiceBox.getValue();
	}
	
	public void resetEventTime() {
		eventTimeChoiceBox.setValue("12:00");
	}
	
	public String getEventAMPM() {
		if(AMRadioButton.isSelected() == true)
			return AMRadioButton.getText();
		else
			return PMRadioButton.getText();
	}
	
	public void resetEventTimeAMPM() {
		AMRadioButton.setSelected(true);
	}
	
	public Boolean getNoDateOption() {
		return noDateOptionButton.isSelected();
	}
	
	public void resetNoDateOption() {
		noDateOptionButton.setSelected(false);
	}
	
	//Getters and alternators setter for choice-boxes
	
	public ChoiceBox<String> getTimeChoiceBox() {
		return eventTimeChoiceBox;
	}
	
	public void setNextTimeInTimeChoiceBox() {
		if(eventTimeChoiceBox.getValue() == "12:00")
			eventTimeChoiceBox.setValue("12:30");
		else if(eventTimeChoiceBox.getValue() == "12:30")
			eventTimeChoiceBox.setValue("1:00");
		else if(eventTimeChoiceBox.getValue() == "1:00")
			eventTimeChoiceBox.setValue("1:30");
		else if(eventTimeChoiceBox.getValue() == "1:30")
			eventTimeChoiceBox.setValue("2:00");
		else if(eventTimeChoiceBox.getValue() == "2:00")
			eventTimeChoiceBox.setValue("2:30");
		else if(eventTimeChoiceBox.getValue() == "2:30")
			eventTimeChoiceBox.setValue("3:00");
		else if(eventTimeChoiceBox.getValue() == "3:00")
			eventTimeChoiceBox.setValue("3:30");
		else if(eventTimeChoiceBox.getValue() == "3:30")
			eventTimeChoiceBox.setValue("4:00");
		else if(eventTimeChoiceBox.getValue() == "4:00")
			eventTimeChoiceBox.setValue("4:30");
		else if(eventTimeChoiceBox.getValue() == "4:30")
			eventTimeChoiceBox.setValue("5:00");
		else if(eventTimeChoiceBox.getValue() == "5:00")
			eventTimeChoiceBox.setValue("5:30");
		else if(eventTimeChoiceBox.getValue() == "5:30")
			eventTimeChoiceBox.setValue("6:00");
		else if(eventTimeChoiceBox.getValue() == "6:00")
			eventTimeChoiceBox.setValue("6:30");
		else if(eventTimeChoiceBox.getValue() == "6:30")
			eventTimeChoiceBox.setValue("7:00");
		else if(eventTimeChoiceBox.getValue() == "7:00")
			eventTimeChoiceBox.setValue("7:30");
		else if(eventTimeChoiceBox.getValue() == "7:30")
			eventTimeChoiceBox.setValue("8:00");
		else if(eventTimeChoiceBox.getValue() == "8:00")
			eventTimeChoiceBox.setValue("8:30");
		else if(eventTimeChoiceBox.getValue() == "8:30")
			eventTimeChoiceBox.setValue("9:00");
		else if(eventTimeChoiceBox.getValue() == "9:00")
			eventTimeChoiceBox.setValue("9:30");
		else if(eventTimeChoiceBox.getValue() == "9:30")
			eventTimeChoiceBox.setValue("10:00");
		else if(eventTimeChoiceBox.getValue() == "10:00")
			eventTimeChoiceBox.setValue("10:30");
		else if(eventTimeChoiceBox.getValue() == "10:30")
			eventTimeChoiceBox.setValue("11:00");
		else if(eventTimeChoiceBox.getValue() == "11:00")
			eventTimeChoiceBox.setValue("11:30");
		else if(eventTimeChoiceBox.getValue() == "11:30")
			eventTimeChoiceBox.setValue("None");
		else if(eventTimeChoiceBox.getValue() == "None")
			eventTimeChoiceBox.setValue("12:00");
	}
	
	public ChoiceBox<String> getClassChoiceBox() {
		return eventClassChoiceBox;
	}
	
	public void setNextClassInClassChoiceBox() {
		if(eventClassChoiceBox.getValue() == "Personal")
			eventClassChoiceBox.setValue("Family");
		else if(eventClassChoiceBox.getValue() == "Family")
			eventClassChoiceBox.setValue("Work");
		else if(eventClassChoiceBox.getValue() == "Work")
			eventClassChoiceBox.setValue("School");
		else if(eventClassChoiceBox.getValue() == "School")
			eventClassChoiceBox.setValue("Volunteering");
		else if(eventClassChoiceBox.getValue() == "Volunteering")
			eventClassChoiceBox.setValue("Club");
		else if(eventClassChoiceBox.getValue() == "Club")
			eventClassChoiceBox.setValue("Recreational");
		else if(eventClassChoiceBox.getValue() == "Recreational")
			eventClassChoiceBox.setValue("Vacation");
		else if(eventClassChoiceBox.getValue() == "Vacation")
			eventClassChoiceBox.setValue("Other");
		else if(eventClassChoiceBox.getValue() == "Other")
			eventClassChoiceBox.setValue("None");
		else if(eventClassChoiceBox.getValue() == "None")
			eventClassChoiceBox.setValue("Personal");
	}
	
	public ChoiceBox<String> getMonthChoiceBox() {
		return eventMonthChoiceBox;
	}
	
	public void setNextMonthInClassChoiceBox() {
		if(eventMonthChoiceBox.getValue() == "January")
			eventMonthChoiceBox.setValue("February");
		else if(eventMonthChoiceBox.getValue() == "February")
			eventMonthChoiceBox.setValue("March");
		else if(eventMonthChoiceBox.getValue() == "March")
			eventMonthChoiceBox.setValue("April");
		else if(eventMonthChoiceBox.getValue() == "April")
			eventMonthChoiceBox.setValue("May");
		else if(eventMonthChoiceBox.getValue() == "May")
			eventMonthChoiceBox.setValue("June");
		else if(eventMonthChoiceBox.getValue() == "June")
			eventMonthChoiceBox.setValue("July");
		else if(eventMonthChoiceBox.getValue() == "July")
			eventMonthChoiceBox.setValue("August");
		else if(eventMonthChoiceBox.getValue() == "August")
			eventMonthChoiceBox.setValue("September");
		else if(eventMonthChoiceBox.getValue() == "September")
			eventMonthChoiceBox.setValue("October");
		else if(eventMonthChoiceBox.getValue() == "October")
			eventMonthChoiceBox.setValue("November");
		else if(eventMonthChoiceBox.getValue() == "November")
			eventMonthChoiceBox.setValue("December");
		else if(eventMonthChoiceBox.getValue() == "December")
			eventMonthChoiceBox.setValue("January");
	}
	
	//Getters for createEventButton and closeCreateEventWindow
	
	public Button getCreateEventBtn() {
		return createEventButton;
	}
	
	public Button getCloseCreateEventBtn() {
		return closeCreateEventWindowButton;
	}
	
	
	//Method resets add-event-page window to default state (empty input fields and normal presets for choice-boxes)
	public void clearAndResetAllFields(){
		eventNameTextField.clear();
		eventDescriptionTextArea.clear();
		eventClassChoiceBox.setValue("Work");
		eventClassTextField.clear();
		eventLocationTextField.clear();
		eventMonthChoiceBox.setValue("January");
		eventDayTextField.clear();
		eventYearTextField.clear();
		noDateOptionButton.setSelected(false);
		eventTimeChoiceBox.setValue("12:00");
		AMRadioButton.setSelected(true);
	}
}