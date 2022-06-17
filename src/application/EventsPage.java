package application;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EventsPage{
	
	private Button openEditorButton;
	private Button closeEditorButton;
	private Button addEventButton;
	private Button editEventButton;
	private Button deleteEventButton;
	private Button returnToHome;
	private VBox defaultEditorPane;
	private VBox openEditorPane;
	private VBox closeEditorPane;
	private FlowPane eventContainer;
	private ScrollPane viewEventContainer;
	private BorderPane viewEventsSurfaceStack;
	
	private TranslateTransition moveEventsPageTopIntoView;
	private TranslateTransition moveEventsPageCenterIntoView;
	private TranslateTransition moveEventsPageLeftIntoView;
	
	//Constructor for events-page
	//	- Builds events-page layout-pane (BorderPane)
	//	- Builds events-page elements
	//	- Build effects
	//		- Add effects to labels
	//	- Creates events-page animations for elements
	public EventsPage() {
		
		//Build layout-pane (HBox) for top of border-pane
		HBox viewETop = new HBox();
		viewETop.setAlignment(Pos.CENTER_LEFT);
		viewETop.setPadding(new Insets(20, 20, 20, 20));
		viewETop.setSpacing(10);
		viewETop.getStyleClass().add("big-bord-bot");
		
		//Build viewEventsPageTitle
		Label viewEventsPageTitle = new Label("Events");
		viewEventsPageTitle.getStyleClass().add("events-title");
		
		//Build effects
		Glow glowEffect = new Glow();
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setInput(glowEffect);
		
		//Add effects to labels
		viewEventsPageTitle.setEffect(dropShadowEffect);
		
		//Build smalllogoImageView
		ImageLoader smalllogoImage = new ImageLoader();
		ImageView smalllogoImageView = smalllogoImage.smallerLogoImageView();
		
		//Add all elements to top of events-page layout-pane (top of border-pane)
		viewETop.getChildren().addAll(smalllogoImageView, viewEventsPageTitle);
		
		
		//Build openEditorButton
		openEditorButton = new Button(">>");
		//openEditorButton.setPrefSize(60, 60);		//smallest possible size
		openEditorButton.setPrefSize(60, 250);		//size of one event card
		//openEditorButton.setPrefSize(60, 510);	//size of two event cards + gap between
		//Build openEditorButton tool tip
		Tooltip openEditorButtonTT = new Tooltip("Open Editor Menu");
		openEditorButtonTT.getStyleClass().add("tool-tip");
		openEditorButton.setTooltip(openEditorButtonTT);
		
		
		//Build closeEditorButton
		closeEditorButton = new Button("<<");
		closeEditorButton.setPrefSize(250, 60);
		//Build closeEditorButton tool tip
		Tooltip closeEditorButtonTT = new Tooltip("Close Editor Menu");
		closeEditorButtonTT.getStyleClass().add("tool-tip");
		closeEditorButton.setTooltip(closeEditorButtonTT);
		
		//Build addEventButton
		addEventButton = new Button("Add Event");
		addEventButton.setPrefSize(250, 60);
		
		//Build editEventButton
		editEventButton = new Button("Edit Event");
		editEventButton.setPrefSize(250, 60);
		
		//Build deleteEventButton
		deleteEventButton = new Button("Delete Event");
		deleteEventButton.setPrefSize(250, 60);
		
		//Build returnToHome
		returnToHome = new Button("Home");
		returnToHome.setPrefSize(250, 60);
		
		//Set editEventButton, deleteEventButton disabled by default
		//editEventButton.setDisable(true);
		deleteEventButton.setDisable(true);
		
		//Build defaultEditorPane
		defaultEditorPane = new VBox();
		defaultEditorPane.setAlignment(Pos.TOP_CENTER);
		defaultEditorPane.setPadding(new Insets(25, 0, 20, 20));
		defaultEditorPane.setSpacing(10);
		defaultEditorPane.getChildren().addAll(openEditorButton);
		
		
		//Build layout-pane (FlowPane) for center of border-pane
		eventContainer = new FlowPane();
		eventContainer.setAlignment(Pos.TOP_LEFT);
		eventContainer.setPadding(new Insets(25, 20, 20, 20));
		eventContainer.setHgap(10);
		eventContainer.setVgap(10);
		
		
		//Build layout-pane (ScrollPane) to hold eventContainer (FlowPane)
		//Allows user to scroll through events if number of events exceeds that which can fit within window
		viewEventContainer = new ScrollPane();
		viewEventContainer.setContent(eventContainer);
		viewEventContainer.setFitToWidth(true);
		viewEventContainer.setPannable(true);
		
		
		//Build top level layout-pane for events-page
		viewEventsSurfaceStack = new BorderPane();
		
		//Set background of top-level layout-pane
		ImageLoader mainbckgrd2 = new ImageLoader();
		Background bckgrd = mainbckgrd2.mainBackground();
		viewEventsSurfaceStack.setBackground(bckgrd);
		
		//Set each section of border-pane to appropriate layout-pane
		//	- top, center, left
		viewEventsSurfaceStack.setTop(viewETop);
		viewEventsSurfaceStack.setLeft(defaultEditorPane);	// <-- Note, set the left node initially to closed
		viewEventsSurfaceStack.setCenter(viewEventContainer);
		
		
		
		
		//Animations for top level layout-pane for events-page (animating border-pane: top, center, left)
		
		moveEventsPageTopIntoView = new TranslateTransition();
		moveEventsPageTopIntoView.setNode(viewEventsSurfaceStack.getTop());
		moveEventsPageTopIntoView.setDuration(Duration.millis(800));
		moveEventsPageTopIntoView.setFromY(-1000);
		moveEventsPageTopIntoView.setToY(0);
		moveEventsPageTopIntoView.setCycleCount(1);
		
		moveEventsPageCenterIntoView = new TranslateTransition();
		moveEventsPageCenterIntoView.setNode(viewEventsSurfaceStack.getCenter());
		moveEventsPageCenterIntoView.setDuration(Duration.millis(700));
		moveEventsPageCenterIntoView.setFromY(-1000);
		moveEventsPageCenterIntoView.setToY(0);
		moveEventsPageCenterIntoView.setCycleCount(1);
		
		moveEventsPageLeftIntoView = new TranslateTransition();
		moveEventsPageLeftIntoView.setNode(viewEventsSurfaceStack.getLeft());
		moveEventsPageLeftIntoView.setDuration(Duration.millis(400));
		moveEventsPageLeftIntoView.setFromY(-1000);
		moveEventsPageLeftIntoView.setToY(0);
		moveEventsPageLeftIntoView.setCycleCount(1);
	}
	
	//Methods set border-pane view-Events-Surface-Stack left side to open or close
	
	public VBox setLeftOpenEditor() {
		
		//Layout-pane for border-pane left when editor is open
		VBox containerForCloseButton = new VBox();
		containerForCloseButton.setAlignment(Pos.TOP_RIGHT);
		containerForCloseButton.getChildren().addAll(closeEditorButton);
		
		//Layout-pane for border-pane left when editor is opened
		openEditorPane = new VBox();
		openEditorPane.setAlignment(Pos.TOP_CENTER);
		openEditorPane.setPadding(new Insets(20, 20, 20, 20));
		openEditorPane.setSpacing(10);
		openEditorPane.getStyleClass().add("big-bord-right");
		
		
		//Animate entry of each element within open-editor-pane
		
		TranslateTransition zero = new TranslateTransition();
		zero.setNode(openEditorPane);
		zero.setDuration(Duration.millis(500)); zero.setFromX(-300); zero.setToX(openEditorPane.getWidth()/2); zero.setCycleCount(1);
		zero.play();
		
		TranslateTransition one = new TranslateTransition();
		one.setNode(containerForCloseButton);
		one.setDuration(Duration.millis(500)); one.setFromX(-300); one.setToX(openEditorPane.getWidth()/2); one.setCycleCount(1);
		one.play();
		
		TranslateTransition two = new TranslateTransition();
		two.setNode(addEventButton);
		two.setDuration(Duration.millis(550)); two.setFromX(-300); two.setToX(openEditorPane.getWidth()/2); two.setCycleCount(1);
		two.play();
		
		TranslateTransition three = new TranslateTransition();
		three.setNode(editEventButton);
		three.setDuration(Duration.millis(600)); three.setFromX(-300); three.setToX(openEditorPane.getWidth()/2); three.setCycleCount(1);
		three.play();
		
		TranslateTransition four = new TranslateTransition();
		four.setNode(deleteEventButton);
		four.setDuration(Duration.millis(650)); four.setFromX(-300); four.setToX(openEditorPane.getWidth()/2); four.setCycleCount(1);
		four.play();
		
		TranslateTransition five = new TranslateTransition();
		five.setNode(returnToHome);
		five.setDuration(Duration.millis(700)); five.setFromX(-300); five.setToX(openEditorPane.getWidth()/2); five.setCycleCount(1);
		five.play();
		
		//Add elements to open-editor layout-pane
		openEditorPane.getChildren().addAll(containerForCloseButton, addEventButton, editEventButton, deleteEventButton, returnToHome);
		
		return openEditorPane;
	}
	
	public VBox setLeftCloseEditor() {
		
		//Layout-pane for border-pane left when editor is closed
		closeEditorPane = new VBox();
		closeEditorPane.setAlignment(Pos.TOP_CENTER);
		closeEditorPane.setPadding(new Insets(25, 20, 20, 20));
		closeEditorPane.setSpacing(10);
		//closeEditorPane.getStyleClass().add("viewE-bord-right");
		
		//Animate re-entry of closed-editor button
		TranslateTransition swapToCloseEditor = new TranslateTransition();
		swapToCloseEditor.setNode(closeEditorPane);
		swapToCloseEditor.setDuration(Duration.millis(500)); swapToCloseEditor.setFromX(-300); swapToCloseEditor.setToX(0); swapToCloseEditor.setCycleCount(1);
		//swapToCloseEditor.setDuration(Duration.millis(500)); swapToCloseEditor.setFromY(1000); swapToCloseEditor.setToY(0); swapToCloseEditor.setCycleCount(1);
		//swapToCloseEditor.setDuration(Duration.millis(500)); swapToCloseEditor.setFromY(2000); swapToCloseEditor.setToY (0); swapToCloseEditor.setCycleCount(1);
		//swapToCloseEditor.setDuration(Duration.millis(500)); swapToCloseEditor.setFromX(openEditorPane.getWidth()/2); swapToCloseEditor.setToX(-0); swapToCloseEditor.setCycleCount(1);
		swapToCloseEditor.play();
		
		//Add openEditorButton to closed-editor layout-pane
		closeEditorPane.getChildren().addAll(openEditorButton);
		
		return closeEditorPane;
	}
	
	//Getters for events-page attributes
	
	public void setEventsPageCenter(Node aNode) {
		viewEventsSurfaceStack.setCenter(aNode);
	}
	
//	public void setEventPageLeft(Node aNode) {
//		viewEventsSurfaceStack.setLeft(aNode);
//	}
	
	public BorderPane eventPageSurface() {
		return viewEventsSurfaceStack;
	}
	
	public Button getOpenEditor() {
		return openEditorButton;
	}
	
	public Button getCloseEditor() {
		return closeEditorButton;
	}
	
	public Button getAddEventBtn() {
		return addEventButton;
	}
	
	public Button getEditEventBtn() {
		return editEventButton;
	}
	
	public Button getDeleteEventBtn() {
		return deleteEventButton;
	}
	
	public Button getReturnHomeBtn() {
		return returnToHome;
	}
	
	public FlowPane getEventContainer() {
		return eventContainer;
	}
	
//	public ScrollPane getViewEventContainer() {
//		return viewEventContainer;
//	}
	
	//Method provides toggle option for delete-event-button
	
	public void setDeleteEventButtonDisabled(Boolean toggle) {
		deleteEventButton.setDisable(toggle);
	}
	
	//Methods play-animations-on-start-up, RE-animate-Border-Pane-Left-Side, get-Border-Node handle event-page animations
	
	public void eventPageStartUpAnimations() {
		moveEventsPageTopIntoView.play();
		moveEventsPageCenterIntoView.play();
		moveEventsPageLeftIntoView.play();
	}

	public void REanimateBorderPaneLeftSide(Node aNode) {
		moveEventsPageLeftIntoView = new TranslateTransition();
		moveEventsPageLeftIntoView.setNode(aNode);
		moveEventsPageLeftIntoView.setDuration(Duration.millis(400));
		moveEventsPageLeftIntoView.setFromY(-1000);
		moveEventsPageLeftIntoView.setToY(0);
		moveEventsPageLeftIntoView.setCycleCount(1);
	}
	
	public Node getBorderNode() {
		return viewEventsSurfaceStack.getLeft();
	}
}