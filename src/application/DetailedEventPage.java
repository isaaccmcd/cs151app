package application;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DetailedEventPage {
	
	//No-arguments constructor
	public DetailedEventPage() {}
	
	//Method builds a detailed-event-page for an event card within the events-window. 
	//
	//detailed-event-page has:
	//	- ALL details about an event
	//	- a goBack button that returns the user to the events-page
	public void Build(VBox inputCard, EventsPage eventsPage, BorderPane eventPageContainer, Scene homeScene) {
		
		//Build labels for all event attributes given the event inputCard
		//	- event attributes are the children of the inputCard (VBox)
		Label eventName = (Label) inputCard.getChildren().get(0);
		//Label spacer = (Label) inputCard.getChildren().get(1);	<- this line is reference so you're aware of what line 1 is.
		Label eventDesc = (Label) inputCard.getChildren().get(2);
		Label eventClass = (Label) inputCard.getChildren().get(3);
		Label eventLocate = (Label) inputCard.getChildren().get(4);
		Label eventDate = (Label) inputCard.getChildren().get(5);
		Label eventTime = (Label) inputCard.getChildren().get(6);
		
		//Add event handling to event inputCard
		inputCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			
			//Top-level layout-pane (VBox)
			VBox detailedEventPageSurfaceStack = new VBox();
			detailedEventPageSurfaceStack.setAlignment(Pos.TOP_LEFT);
			detailedEventPageSurfaceStack.setPadding(new Insets(30, 0, 40, 0));
			detailedEventPageSurfaceStack.setSpacing(20);
			detailedEventPageSurfaceStack.setPrefHeight(homeScene.getHeight());
			
			//Set background of top-level layout-pane
			ImageLoader mainBackground = new ImageLoader();
			Background bckgrd = mainBackground.mainBackground();
			detailedEventPageSurfaceStack.setBackground(bckgrd);
			
			//Build layout-pane for logo, event title, and classification
			VBox logoTitleAndClassContainer = new VBox();
			logoTitleAndClassContainer.setPadding(new Insets(0, 40, 20, 40));
			logoTitleAndClassContainer.setSpacing(20);
			logoTitleAndClassContainer.getStyleClass().add("big-bord-bot");
			logoTitleAndClassContainer.setAlignment(Pos.TOP_LEFT);
			
			//Build layout-pane for logo and event title
			HBox logoAndTitle = new HBox();
			logoAndTitle.setSpacing(20);
			logoAndTitle.setAlignment(Pos.TOP_LEFT);
			
			//Build layout-pane for event details (description, classification, location, date, time
			VBox detailsContainer = new VBox();
			detailsContainer.setPadding(new Insets(40, 163, 40, 163));
			detailsContainer.setSpacing(20);
			detailsContainer.setAlignment(Pos.TOP_LEFT);
			
			//Build smalllogoImage for event title and icons for event attributes
			ImageLoader detailedEventPageImageLoader = new ImageLoader();
			//smalllogoImage
			ImageView smalllogoImageView = detailedEventPageImageLoader.smallerLogoImageView();
			//icons
			ImageView detailsImageView = detailedEventPageImageLoader.detailsImageView();
			ImageView locateImageView = detailedEventPageImageLoader.locateImageView();
			ImageView dateImageView = detailedEventPageImageLoader.dateImageView();
			ImageView timeImageView = detailedEventPageImageLoader.timeImageView();
			
			//Build labels for detailed-event-page to display, load text from inputCard labels
			Label name = new Label(eventName.getText());
			Label desc = new Label(eventDesc.getText());
			Label classif = new Label(eventClass.getText());
			Label locate = new Label(eventLocate.getText());
			Label date = new Label(eventDate.getText());
			Label time = new Label(eventTime.getText());
			
			
			//Test to see if date or time values are null
			if(date.getText().contentEquals("null null, null") && time.getText().contentEquals("null null")) {
				date.setText("No Date");
				time.setText("No Time");
			}
			else if(date.getText().contentEquals("null null, null")) {
				date.setText("No Date");
			}
			else if(time.getText().contentEquals("null null")) {
				time.setText("No Time");
			}
			
			if(classif.getText() == null) {
				classif.setText("No Classification");
			}
			if(desc.getText().contentEquals("")) {
				desc.setText("No Description");
			}
			if(locate.getText().contentEquals("")) {
				locate.setText("No Location");
			}
			
			
			Label descSubheading = new Label("Details:");
			Label locateSubheading = new Label("Location:");
			Label dateSubheading = new Label("Date:");
			Label timeSubheading = new Label("Time:");
			//descSubheading.setMinWidth(117);	// <-- minimum size for "Details" to not be cut off
			descSubheading.setMinWidth(140);
			dateSubheading.setMinWidth(140);
			timeSubheading.setMinWidth(140);
			descSubheading.getStyleClass().add("detailed-events-page-subheadings");
			locateSubheading.getStyleClass().add("detailed-events-page-subheadings");
			dateSubheading.getStyleClass().add("detailed-events-page-subheadings");
			timeSubheading.getStyleClass().add("detailed-events-page-subheadings");
			
			
			//Edit properties and set style class for labels
			name.setWrapText(true);
			desc.setWrapText(true);
			classif.setPadding(new Insets(0, 0, 0, 118));
			name.getStyleClass().add("detailed-events-page-event-title");
			desc.getStyleClass().add("detailed-events-page-elements");
			classif.getStyleClass().add("detailed-events-page-elements");
			locate.getStyleClass().add("detailed-events-page-elements");
			date.getStyleClass().add("detailed-events-page-elements");
			time.getStyleClass().add("detailed-events-page-elements");
			
			//Build effects
			Glow glowEffect = new Glow();
			DropShadow dropShadowEffect = new DropShadow();
			dropShadowEffect.setInput(glowEffect);
			
			//Add effects to labels
			name.setEffect(dropShadowEffect);
			desc.setEffect(dropShadowEffect);
			classif.setEffect(dropShadowEffect);
			locate.setEffect(dropShadowEffect);
			date.setEffect(dropShadowEffect);
			time.setEffect(dropShadowEffect);
			descSubheading.setEffect(dropShadowEffect);
			locateSubheading.setEffect(dropShadowEffect);
			dateSubheading.setEffect(dropShadowEffect);
			timeSubheading.setEffect(dropShadowEffect);

			
			//Build layout-panes for main meat of detailed-events-page
			HBox descContainer = new HBox();
			HBox locateContainer = new HBox();
			HBox dateContainer = new HBox();
			HBox timeContainer = new HBox();
			
			//Style layout-panes for main meat
			descContainer.setSpacing(20);
			descContainer.setAlignment(Pos.CENTER_LEFT);
			locateContainer.setSpacing(20);
			locateContainer.setAlignment(Pos.CENTER_LEFT);
			dateContainer.setSpacing(20);
			dateContainer.setAlignment(Pos.CENTER_LEFT);
			timeContainer.setSpacing(20);
			timeContainer.setAlignment(Pos.CENTER_LEFT);
			
			
			//Build layout-pane for goBack button
			VBox buttonContainer = new VBox();
			buttonContainer.setPadding(new Insets(40, 40, 0, 161));
			buttonContainer.getStyleClass().add("big-bord-top");
			buttonContainer.setAlignment(Pos.TOP_LEFT);
			
			//Build goBack button
			Button goBack = new Button("< Back");
			goBack.setPrefSize(250, 60);
			
			//Add event handle to goBack button (send user back to events-page)
			goBack.addEventHandler(MouseEvent.MOUSE_CLICKED, e2 -> {
				
				//This is weird but necessary for some reason
				//BEGIN:
				Node eventsPageBorderPaneLeft = eventsPage.getBorderNode();
				eventsPage.REanimateBorderPaneLeftSide(eventsPageBorderPaneLeft);
				//END
				BorderPane eventPageSurfaceStack = eventsPage.eventPageSurface();
				eventsPage.eventPageStartUpAnimations();
				homeScene.setRoot(eventPageSurfaceStack);
			});
			
			
			
			//Animations for elements and layout-panes for detailed-event-page (animating all labels and/or their containers/layout-panes)
			
			TranslateTransition showNameBorder = new TranslateTransition();
			showNameBorder.setNode(logoAndTitle); showNameBorder.setDuration(Duration.millis(1000)); showNameBorder.setFromY(-1000); showNameBorder.setToY(0); showNameBorder.setCycleCount(1);
			showNameBorder.play();
			
			TranslateTransition showNameLogoClassContainerBorder = new TranslateTransition();
			showNameLogoClassContainerBorder.setNode(logoTitleAndClassContainer); showNameLogoClassContainerBorder.setDuration(Duration.millis(1000)); showNameLogoClassContainerBorder.setFromY(-1000); showNameLogoClassContainerBorder.setToY(0); showNameLogoClassContainerBorder.setCycleCount(1);
			showNameLogoClassContainerBorder.play();
			
			TranslateTransition showClassif = new TranslateTransition();
			showClassif.setNode(classif); showClassif.setDuration(Duration.millis(900)); showClassif.setFromY(-1000); showClassif.setToY(0); showClassif.setCycleCount(1);
			showClassif.play();
			
			TranslateTransition showDesc = new TranslateTransition();
			showDesc.setNode(descContainer); showDesc.setDuration(Duration.millis(800)); showDesc.setFromY(-1000); showDesc.setToY(0); showDesc.setCycleCount(1);
			showDesc.play();
			
			TranslateTransition showLocate = new TranslateTransition();
			showLocate.setNode(locateContainer); showLocate.setDuration(Duration.millis(700)); showLocate.setFromY(-1000); showLocate.setToY(0); showLocate.setCycleCount(1);
			showLocate.play();
			
			TranslateTransition showDate = new TranslateTransition();
			showDate.setNode(dateContainer); showDate.setDuration(Duration.millis(600)); showDate.setFromY(-1000); showDate.setToY(0); showDate.setCycleCount(1);
			showDate.play();
			
			TranslateTransition showTime = new TranslateTransition();
			showTime.setNode(timeContainer); showTime.setDuration(Duration.millis(500)); showTime.setFromY(-1000); showTime.setToY(0); showTime.setCycleCount(1);
			showTime.play();
			
			TranslateTransition showGoBackButton = new TranslateTransition();
			showGoBackButton.setNode(buttonContainer);showGoBackButton.setDuration(Duration.millis(400)); showGoBackButton.setFromY(-1000); showGoBackButton.setToY(0); showGoBackButton.setCycleCount(1);
			showGoBackButton.play();
			
			
			//Load all labels, imageViews, etc. to their respective layout-panes
			
			//Logo title classification
			logoAndTitle.getChildren().addAll(smalllogoImageView, name);
			logoTitleAndClassContainer.getChildren().addAll(logoAndTitle, classif);
			
			//Description (logic dictates whether description should be in a scroll-pane)
			Boolean largeDesc = false;
			if(eventDesc.getText().length() > 300) {
			//if(eventDesc.getText().length() > 150) { //for 154
				largeDesc = true;
				ScrollPane largeDescScrollable = new ScrollPane();
				largeDescScrollable.setContent(desc);
				largeDescScrollable.setFitToWidth(true);
				largeDescScrollable.setPadding(new Insets(20, 20, 20, 20));
				largeDescScrollable.setPannable(true);
				largeDescScrollable.setMinHeight(300);		//****THIS IS TIED TO THE IF STATEMENT, if this value is increased, then you must test and update the length requirement in the if statement
				largeDescScrollable.getStyleClass().add("big-bord-all-sides");
				
				descContainer.getChildren().addAll(detailsImageView, descSubheading, largeDescScrollable);
			}
			else
				descContainer.getChildren().addAll(detailsImageView, descSubheading, desc);
			
			//Location date time
			locateContainer.getChildren().addAll(locateImageView, locateSubheading, locate);
			dateContainer.getChildren().addAll(dateImageView, dateSubheading, date);
			timeContainer.getChildren().addAll(timeImageView, timeSubheading, time);
			detailsContainer.getChildren().addAll(descContainer, locateContainer, dateContainer, timeContainer);
			
			//Return to events-page
			buttonContainer.getChildren().addAll(goBack);
			
			
			//Load detailed-event-page elements to top-level layout-pane
			detailedEventPageSurfaceStack.getChildren().addAll(logoTitleAndClassContainer, detailsContainer, buttonContainer);
			
			//Set homeScene Scene to the detailed-event-page-surface-stack
			
			//If description IS large, then set homeScene to default detailedEventPageSurfaceStack created at the top
			if(largeDesc == true) {
				
				//Top-level layout-pane for scroll-able detailed-event-page (VBox)
				VBox scrollableDetailedEventPageSurfaceStack = new VBox();
				scrollableDetailedEventPageSurfaceStack.setAlignment(Pos.TOP_LEFT);
				scrollableDetailedEventPageSurfaceStack.setPadding(new Insets(30, 0, 40, 0));
				scrollableDetailedEventPageSurfaceStack.setSpacing(20);
				scrollableDetailedEventPageSurfaceStack.getChildren().addAll(logoTitleAndClassContainer, detailsContainer, buttonContainer);
				
				//layout-pane for scroll-pane to sit on (VBox)
				VBox scrollPaneSurface = new VBox();
				scrollPaneSurface.setPrefHeight(homeScene.getHeight());
				scrollPaneSurface.setBackground(bckgrd);
				
				//Scroll=pane for detailed-event-page
				ScrollPane detailedPageSurfaceScrollable = new ScrollPane();
				detailedPageSurfaceScrollable.setContent(scrollableDetailedEventPageSurfaceStack);
				detailedPageSurfaceScrollable.setMinHeight(homeScene.getHeight()-400);
				detailedPageSurfaceScrollable.setFitToWidth(true);
				detailedPageSurfaceScrollable.setPannable(true);
				
				scrollPaneSurface.getChildren().addAll(detailedPageSurfaceScrollable);
				
				//Add annimation to scroll-bar
				TranslateTransition showScrollPane = new TranslateTransition();
				showScrollPane.setNode(detailedPageSurfaceScrollable);showScrollPane.setDuration(Duration.millis(400)); showScrollPane.setFromY(-1000); showScrollPane.setToY(0); showScrollPane.setCycleCount(1);
				showScrollPane.play();
				
				homeScene.setRoot(scrollPaneSurface);
			}
			//If description IS NOT large, then set homeScene to default detailedEventPageSurfaceStack created at the top
			else
				homeScene.setRoot(detailedEventPageSurfaceStack);
		});
	}
}