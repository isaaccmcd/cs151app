package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VerifyDeleteAllEventsPage implements ApplicationPage{
	
	private VBox verifyDeleteAllEventsPageStack;
	private Button yesButton;
	private Button noButton;
	
	//Constructor for verify-delete-all-events-page
	//	- Builds verify-delete-all-events-page layout-pane (VBox)
	//	- Builds verify-delete-all-events-page elements
	//		- verifyLabel, yesButton, noButton
	public VerifyDeleteAllEventsPage() {
		
		//Build verify-delete-all-events-page layout-pane (VBox)
		verifyDeleteAllEventsPageStack = new VBox();
		verifyDeleteAllEventsPageStack.setPadding(new Insets(20, 20, 20, 20));
		verifyDeleteAllEventsPageStack.setSpacing(20);
		verifyDeleteAllEventsPageStack.setAlignment(Pos.TOP_CENTER);
		
		//Build button layout-pane for yes-button and no-button
		HBox yesNoButtonBox = new HBox();
		yesNoButtonBox.setSpacing(20);
		yesNoButtonBox.setAlignment(Pos.CENTER);
		
		//Build verifyLabel
		Label verifyLabel = new Label("Are you sure you want to delete ALL events?");
		verifyLabel.getStyleClass().add("verify-lab");
		
		//Build yesButton
		yesButton = new Button("YES");
		yesButton.setPrefSize(200, 65);
		yesButton.getStyleClass().add("yes-button");
		
		//Build noButton
		noButton = new Button("NO");
		noButton.setPrefSize(200, 65);
		noButton.getStyleClass().add("no-button");
		
		//Add yes and no buttons to button layout-pane
		yesNoButtonBox.getChildren().addAll(yesButton, noButton);
		
		//Add all elements to verify-delete-all-events-page layout-pane
		verifyDeleteAllEventsPageStack.getChildren().addAll(verifyLabel, yesNoButtonBox);
	}
	
	//Method builds a top level layout-pane for verify-delete-all-events-page
	public VBox getSurfaceStack() {
		
		//Top-level layout-pane (VBox)
		VBox verifyDeleteAllEventsPageSurfaceStack = new VBox();
		verifyDeleteAllEventsPageSurfaceStack.setAlignment(Pos.CENTER);
		
		//Set background of top-level layout-pane
		ImageLoader altbckgrdImg = new ImageLoader();
		Background bckgrd = altbckgrdImg.altBackground();
		verifyDeleteAllEventsPageSurfaceStack.setBackground(bckgrd);
		
		//Load verify-delete-all-events-page elements to top-level layout-pane
		verifyDeleteAllEventsPageSurfaceStack.getChildren().addAll(verifyDeleteAllEventsPageStack);
		
		return verifyDeleteAllEventsPageSurfaceStack;
	}
	
	//Getters
	
	public Button getYesButton() {
		return yesButton;
	}
	
	public Button getNoButton() {
		return noButton;
	}
}