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

public class EditEventPage implements ApplicationPage{
	
	private VBox leftPane;
	private VBox rightPane;
	private HBox editEventPageNodeContainer;
	
	private TextField eventNameTextField;
	private TextField eventClassTextField;
	private TextField eventLocationTextField;
	private TextField eventDayTextField;
	private TextField eventYearTextField;
	private TextArea eventDescriptionTextArea;
	private ChoiceBox<String> eventClassChoiceBox;
	private ChoiceBox<String> eventMonthChoiceBox;
	private ChoiceBox<String> eventTimeChoiceBox;
	
	private ChoiceBox<String> currentExistingEvents;
	
	private RadioButton AMRadioButton;
	private RadioButton PMRadioButton;
	private RadioButton noDateOptionButton;
	private Button editEventButton;
	private Button closeEditEventWindowButton;
	
	private Button editEventBegin;
	
	//Constructor for edit-event-page
	//	- Builds LOTS of elements, refer to constructor comments
	public EditEventPage() {
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Left layout-pane for edit-event-page window
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Build left edit-event-page layout-pane (VBox)
		leftPane = new VBox();
		leftPane.setPadding(new Insets(20, 20, 20, 20));
		leftPane.setSpacing(10);
		//left.setAlignment(Pos.CENTER_LEFT);
		
		//Build layout-pane for edit-event-page elements: eventNameLabel, requiredLabel
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
		
		//Add all elements to left edit-event-page layout-pane
		leftPane.getChildren().addAll(containerOne, eventNameTextField, eventDescriptionLabel, eventDescriptionTextArea, eventClassificationLabel, eventClassChoiceBox, eventClassTextField);
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Right layout-pane for edit-event-page window
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Build right edit-event-page layout-pane (VBox)
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
		
		//Build layout-pane for edit-event-page elements: eventMonthLabel, eventMonthChoiceBox, eventDayLabel, eventDayTextField
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
		
		//Build layout-pane for edit-event-page elements: eventYearLabel, eventYearTextField
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
		
		//Build layout-pane for edit-event-page elements: eventTimeLabel, eventTimeChoiceBox, AMRadioButton, PMRadioButton
		HBox containerFour = new HBox();
		containerFour.setPadding(new Insets(20, 0, 0, 0));
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
		
		//Build layout-pane for edit-event-page elements: createEventButton, closeCreateEventWindowButton
		HBox containerFive = new HBox();
		containerFive.setPadding(new Insets(20, 0, 0, 0));
		containerFive.setSpacing(20);
		containerFive.setAlignment(Pos.CENTER_LEFT);
		
		//Build createEventButton
		editEventButton = new Button("Finish Editing");
		editEventButton.setPrefSize(170, 80);
		//createEventButton.setDisable(true);
		editEventButton.getStyleClass().add("addEventbutton");
		
		//Build closeCreateEventWindowButton
		closeEditEventWindowButton = new Button("Close");
		//closeCreateEventWindowButton.setPrefSize(127, 50);
		closeEditEventWindowButton.setPrefSize(170, 80);
		closeEditEventWindowButton.getStyleClass().add("closebutton");
		
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
		editEventButton.disableProperty().bind(createECondition);
		
		//Add elements to containerFive: createEventButton, closeCreateEventWindowButton
		containerFive.getChildren().addAll(editEventButton, closeEditEventWindowButton);
		
		
		//Add all elements to right edit-event-page layout-pane
		rightPane.getChildren().addAll(eventLocationLabel, eventLocationTextField, eventDateLabel, containerTwo, containerThree, containerFour, containerFive);
		
		
		//Build container for left and right element containers
		editEventPageNodeContainer = new HBox();
		
		//Load add-event-page elements to top-level layout-pane
		editEventPageNodeContainer.getChildren().addAll(leftPane, rightPane);
		

	}
	
	
	//Method builds a top level layout-pane for edit-event-page
	public VBox getSurfaceStack() {
		
		//Top-level layout-pane (VBox)
		VBox EEWindow = new VBox();
		EEWindow.setSpacing(10);
		EEWindow.setPadding(new Insets(20, 0, 0, 0));
		EEWindow.setAlignment(Pos.CENTER);
		
		//Set background of top-level layout-pane
		ImageLoader secondbckgrd = new ImageLoader();
		Background secbckgrd = secondbckgrd.altBackground();
		EEWindow.setBackground(secbckgrd);
		
		//Build edit-event-page title
		Label editEventsPageTitle = new Label("Edit Event");
		editEventsPageTitle.getStyleClass().add("secondary-title");
		
		//Build effects
		Glow glowEffect = new Glow();
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setInput(glowEffect);
		
		//Add effects to labels
		editEventsPageTitle.setEffect(dropShadowEffect);
		
		
		//layout-pane for selecting event to edit
		VBox selectEventArea = new VBox();
		selectEventArea.setSpacing(10);
		selectEventArea.setPadding(new Insets(0, 0, 20, 0));
		selectEventArea.setAlignment(Pos.CENTER);
		selectEventArea.getStyleClass().add("big-bord-bot");
		
		Label selectAnExistingEvent = new Label("Select An Event To Edit");
		editEventBegin = new Button("Edit");
		editEventBegin.setPrefSize(100, 50);
		currentExistingEvents = new ChoiceBox<String>();
		currentExistingEvents.setPrefSize(300, 50);
		
		HBox selectEditEventNodes = new HBox();
		selectEditEventNodes.setSpacing(15);
		selectEditEventNodes.setAlignment(Pos.CENTER);
		
		selectEditEventNodes.getChildren().addAll(currentExistingEvents, editEventBegin);
		
		selectEventArea.getChildren().addAll(selectAnExistingEvent, selectEditEventNodes);
		
		//Binding left and right layout-pane to resize with window
		rightPane.prefWidthProperty().bind(EEWindow.widthProperty());
		//rightPane.prefHeightProperty().bind(EEWindow.heightProperty());
		leftPane.prefWidthProperty().bind(EEWindow.widthProperty());
		//leftPane.prefHeightProperty().bind(EEWindow.heightProperty());
		
		EEWindow.getChildren().addAll(editEventsPageTitle, selectEventArea, editEventPageNodeContainer);
		
		return EEWindow;
	}
	
	
	//Getters and Resetters and Clearers for all edit-event-page attributes
	
	public String getEditPageEventName() {
		return eventNameTextField.getText();
	}
	
	public void clearEditPageEventName() {
		eventNameTextField.clear();
	}
	
	public String getEditPageEventClassOther() {
		return eventClassTextField.getText();
	}
	
	public void clearEditPageEventClassOther() {
		eventClassTextField.clear();
	}
	
	public String getEditPageEventLocation() {
		return eventLocationTextField.getText();
	}
	
	public void clearEditPageEventLocation() {
		eventLocationTextField.clear();
	}
	
	public String getEditPageEventDay() {
		return eventDayTextField.getText();
	}
	
	public void clearEditPageEventDay() {
		eventDayTextField.clear();
	}
	
	public String getEditPageEventYear() {
		return eventYearTextField.getText();
	}
	
	public void clearEditPageEventYear() {
		eventYearTextField.clear();
	}
	
	public String getEditPageEventDesc() {
		return eventDescriptionTextArea.getText();
	}
	
	public void clearEditPageEventDesc() {
		eventDescriptionTextArea.clear();
	}
	
	public String getEditPageEventClass() {
		return eventClassChoiceBox.getValue();
	}
	
	public void resetEditPageEventClass() {
		eventClassChoiceBox.setValue("Work");
	}
	
	public String getEditPageEventMonth() {
		return eventMonthChoiceBox.getValue();
	}
	
	public void resetEditPageEventMonth() {
		eventMonthChoiceBox.setValue("January");
	}
	
	public String getEditPageEventTime() {
		return eventTimeChoiceBox.getValue();
	}
	
	public void resetEditPageEventTime() {
		eventTimeChoiceBox.setValue("12:00");
	}
	
	public String getEditPageEventAMPM() {
		if(AMRadioButton.isSelected() == true)
			return AMRadioButton.getText();
		else
			return PMRadioButton.getText();
	}
	
	public void resetEditPageEventTimeAMPM() {
		AMRadioButton.setSelected(true);
	}
	
	public Boolean getNoDateOption() {
		return noDateOptionButton.isSelected();
	}
	
	public void resetNoDateOption() {
		noDateOptionButton.setSelected(false);
	}
	
	public void setNoDateOption() {
		noDateOptionButton.setSelected(true);
	}
	
	//Getter and alternate setter for time choice-box
	
	public ChoiceBox<String> getEditPageTimeChoiceBox() {
		return eventTimeChoiceBox;
	}
	
	public void setEditPageNextTimeInTimeChoiceBox() {
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
			eventTimeChoiceBox.setValue("12:00");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//IMPORTANT METHODS FOR THIS CLASS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getter and editor for "currentExistingEvents" choice-box and getter for "editEventBegin" button
	
	public ChoiceBox<String> getCurrentExistingEvents() {
		return currentExistingEvents;
	}
	
	public void populateCurrentExistingEvents(String input) {
		currentExistingEvents.getItems().add(input);
	}
	
	public void clearCurrentExistingEvents() {
		currentExistingEvents.getItems().clear();
	}
	
	public Button getEditEventBegin() {
		return editEventBegin;
	}
	
	
	//Setters for all add-event-page attributes
	
	
	public void setEventName(String input) {
		eventNameTextField.setText(input);
	}
	
	public void setEventClassOther(String input) {
		eventClassTextField.setText(input);
	}
	
	public void setEventLocation(String input) {
		eventLocationTextField.setText(input);
	}
	
	public void setEventDay(String input) {
		eventDayTextField.setText(input);
	}
	
	public void setEventYear(String input) {
		eventYearTextField.setText(input);
	}
	
	public void setEventDesc(String input) {
		eventDescriptionTextArea.setText(input);
	}
	
	public void setEventClass(String input) {
		eventClassChoiceBox.setValue(input);
	}
	
	public void setEventMonth(String input) {
		eventMonthChoiceBox.setValue(input);
	}
	
	public void setEventTime(String input) {
		eventTimeChoiceBox.setValue(input);
	}
	
	public void setEventTimeAMPM(String input) {
		if(input.contentEquals("AM"))
			AMRadioButton.setSelected(true);
		else
			PMRadioButton.setSelected(true);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Getters for createEventButton and closeCreateEventWindow
	
	public Button getEditEventBtn() {
		return editEventButton;
	}
	
	public Button getCloseEditEventBtn() {
		return closeEditEventWindowButton;
	}
	
	
	//Method resets edit-event-page window to default state (empty input fields and normal presets for choice-boxes)
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