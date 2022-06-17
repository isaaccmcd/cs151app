package application;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class DeleteEventPage implements ApplicationPage{
	
	private VBox deleteEventPageStack;
	private VBox eventNameInputTextFieldContainer;
	
	private Label eventNameToDeleteLabel;
	private Label pleaseEnterValidEventTitleLabel;
	private TextField eventNameInputTextField;
	private Button deleteSingleEventButton;
	private Button deleteAllEventsButton;
	private Button closeDeleteEventWindowButton;
	
	//Constructor for delete-event-page
	//	- Builds delete-event-page layout-pane (VBox)
	//	- Builds delete-event-page elements
	//		- eventNameToDeleteLabel, eventNameInputTextField, deleteSingleEventButton, deleteAllEventsButton, closeDeleteEventWindowButton
	//	- Establishes boolean-binding for deleteSingleEventButtonButton
	public DeleteEventPage() {
		
		//Build delete-event layout-pane (VBox)
		deleteEventPageStack = new VBox();
		//deleteEventPageStack.setPadding(new Insets(20, 100, 20, 100));
		deleteEventPageStack.setPadding(new Insets(20, 0, 20, 0));
		deleteEventPageStack.setSpacing(15);
		deleteEventPageStack.setAlignment(Pos.CENTER);
		deleteEventPageStack.getStyleClass().add("");
		
		//Build eventNameToDeleteLabel
		eventNameToDeleteLabel = new Label("Enter Event Name To Delete");
		
		//Build pleaseEnterValidEventTitleLabel
		pleaseEnterValidEventTitleLabel = new Label("Please enter an existing event title.");
		pleaseEnterValidEventTitleLabel.getStyleClass().add("alt-req-lab");
		
		//Build layout-pane for event-name-input-text-field
		eventNameInputTextFieldContainer = new VBox();
		eventNameInputTextFieldContainer.setSpacing(10);
		eventNameInputTextFieldContainer.setAlignment(Pos.CENTER);
		eventNameInputTextFieldContainer.setPadding(new Insets(10, 150, 0, 150));
		
		//Build eventNameInputTextField
		eventNameInputTextField = new TextField();
		eventNameInputTextField.setPrefWidth(400);
		eventNameInputTextField.setPrefHeight(40);
		eventNameInputTextField.setPromptText("cs151 Project Presentation...");		
		
		eventNameInputTextFieldContainer.getChildren().addAll(eventNameToDeleteLabel, eventNameInputTextField);
		
		//Build deleteSingleEventButton
		deleteSingleEventButton = new Button("Delete Event");
		deleteSingleEventButton.setDisable(true);
		deleteSingleEventButton.setPrefSize(400, 80);
		deleteSingleEventButton.getStyleClass().add("deletebutton");
		
		//Build deleteAllEventsButton
		deleteAllEventsButton = new Button("Delete ALL Events");
		deleteAllEventsButton.setPrefSize(400, 80);
		deleteAllEventsButton.getStyleClass().add("deletebutton");
		
		//Build closeDeleteEventWindowButton
		closeDeleteEventWindowButton = new Button("Close");
		//closeDeleteEventWindowButton.setPrefSize(127, 50);
		closeDeleteEventWindowButton.setPrefSize(170, 80);
		closeDeleteEventWindowButton.getStyleClass().add("closebutton");
		
		//Establish boolean-binding for delete-single-event-button
		BooleanBinding deleteEConditioalAccess = new BooleanBinding() {
			{
				super.bind(eventNameInputTextField.textProperty());
			}
			@Override
			protected boolean computeValue() {
				return (eventNameInputTextField.getText().isEmpty());
			}
		};
		deleteSingleEventButton.disableProperty().bind(deleteEConditioalAccess);
		
		//Build add-event-page title
		Label deleteEventsPageTitle = new Label("Delete Event");
		deleteEventsPageTitle.getStyleClass().add("secondary-title");
		
		//layout-pane for border purposes only
		VBox forBorderPurposeOnly = new VBox();
		forBorderPurposeOnly.setPadding(new Insets(0, 0, 0, 0));
		forBorderPurposeOnly.getStyleClass().add("big-bord-bot");
		
		//Build effects
		Glow glowEffect = new Glow();
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setInput(glowEffect);
		
		//Add effects to labels
		deleteEventsPageTitle.setEffect(dropShadowEffect);
		
		//Add all elements to delete-event-page layout-pane
		deleteEventPageStack.getChildren().addAll(deleteEventsPageTitle, forBorderPurposeOnly, eventNameInputTextFieldContainer, deleteSingleEventButton, deleteAllEventsButton, closeDeleteEventWindowButton);
	}
	
	//Method builds a top level layout-pane for delete-event-page
	public VBox getSurfaceStack() {
		
		//Top level layout-pane (VBox)
		VBox deleteEventPageSurfaceStack = new VBox();
		deleteEventPageSurfaceStack.setAlignment(Pos.CENTER);
		
		//Set background of top-level layout-pane
		ImageLoader altBackground = new ImageLoader();
		Background bckgrd = altBackground.altBackground();
		deleteEventPageSurfaceStack.setBackground(bckgrd);
		
		//Load delete-event-page elements to top level layout-pane
		deleteEventPageSurfaceStack.getChildren().addAll(deleteEventPageStack);
		
		return deleteEventPageSurfaceStack;
	}
	
	//Method adds please-enter-valid-event-title-label to delete-events window
	public void showInvalidTitleInputErrorMessage() {
		deleteEventPageStack.getChildren().add(3, pleaseEnterValidEventTitleLabel);
	}
	
	//Method removes please-enter-valid-event-title-label to delete-events window
	public void hideInvalidTitleInputErrorMessage() {
		deleteEventPageStack.getChildren().remove(3);
	}
	
	//Method returns delete-single-event button
	public Button getDeleteEventBtn() {
		return deleteSingleEventButton;
	}
	
	//Method returns delete-all-events button
	public Button getDeleteAllEventBtn() {
		return deleteAllEventsButton;
	}
	
	//Method returns close-delete-event-page-window button
	public Button getCloseDeleteEventBtn() {
		return closeDeleteEventWindowButton;
	}
	
	//Method returns event-name-input text-field
	public TextField getEventNameInput() {
		return eventNameInputTextField;
	}
	
	//Method returns delete-event-page-surface-stack (layout-pane)
	public VBox getDeleteEventPageSurfaceStack() {
		return deleteEventPageStack;
	}
	
	//Method returns event-name-input-text-field-container (layout-pane)
	public VBox getEventNameInputTextFieldContainer() {
		return eventNameInputTextFieldContainer;
	}
	
	//Method clears any user input from enter-event-name text-field
	public void resetEventNameInputTF() {
		eventNameInputTextField.clear();
	}
	
	
	//Method resets delete-event-page window to default state (empty input field for event-name-to-be-deleted text-field)
	public void clearAndResetAllFields(){
		eventNameInputTextField.clear();
	}
}