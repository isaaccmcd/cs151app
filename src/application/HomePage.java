package application;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class HomePage implements ApplicationPage{
	
	private VBox homePageStack;
	
	private TranslateTransition moveHomePageLabelIntoView;
	private TranslateTransition moveHomePageGraphicIntoView;
	private TranslateTransition moveHomePageInstructionIntoView;
	
	private Label welcomeLabel;
	private ImageView logoImageView;
	private Label instructionLabel;
	
	//Constructor for home-page
	//	- Builds home-page layout-pane (VBox)
	//	- Builds home-page elements
	//		- welcomeLabel, logoImageView, instructionLabel
	//	- Build effects
	//		- Add effects to labels
	//	- Creates home-page animations for elements
	public HomePage() {
		
		//Build home-page layout-pane (VBox)
		homePageStack = new VBox();
		homePageStack.setPadding(new Insets(20, 20, 20, 20));
		homePageStack.setSpacing(40);
		homePageStack.setAlignment(Pos.TOP_CENTER);
		
		//Build welcomeLabel
		welcomeLabel = new Label("Welcome To Recall");
		welcomeLabel.getStyleClass().add("home-title");
		
		//Build instructionLabel
		instructionLabel = new Label("Click Anywhere Or Press Any Key To Start");
		instructionLabel.getStyleClass().add("home-redirect-text");
		
		//Build logoImageView
		ImageLoader homePageLogoLoader = new ImageLoader();
		logoImageView = homePageLogoLoader.mainLogoImageView();
		
		//Build effects
		Glow glowEffect = new Glow();
		DropShadow dropShadowEffect = new DropShadow();
		dropShadowEffect.setInput(glowEffect);
		
		//Add effects to labels
		welcomeLabel.setEffect(dropShadowEffect);
		instructionLabel.setEffect(dropShadowEffect);
		
		//Add all elements to home-page layout-pane
		homePageStack.getChildren().addAll(welcomeLabel, logoImageView, instructionLabel);
		
		
		//Build home-page entrance animations
		
		//animation for welcomLabel
		moveHomePageLabelIntoView = new TranslateTransition();
		moveHomePageLabelIntoView.setNode(welcomeLabel);
		moveHomePageLabelIntoView.setDuration(Duration.millis(900));
		moveHomePageLabelIntoView.setFromY(-1000);
		moveHomePageLabelIntoView.setToY(0);
		moveHomePageLabelIntoView.setCycleCount(1);
		moveHomePageLabelIntoView.play();
		
		//animation for logoImageView
		moveHomePageGraphicIntoView = new TranslateTransition();
		moveHomePageGraphicIntoView.setNode(logoImageView);
		moveHomePageGraphicIntoView.setDuration(Duration.millis(800));
		moveHomePageGraphicIntoView.setFromY(-1000);
		moveHomePageGraphicIntoView.setToY(0);
		moveHomePageGraphicIntoView.setCycleCount(1);
		moveHomePageGraphicIntoView.play();
		
		//animation for instructionLabel
		moveHomePageInstructionIntoView = new TranslateTransition();
		moveHomePageInstructionIntoView.setNode(instructionLabel);
		moveHomePageInstructionIntoView.setDuration(Duration.millis(700));
		moveHomePageInstructionIntoView.setFromY(-1000);
		moveHomePageInstructionIntoView.setToY(0);
		moveHomePageInstructionIntoView.setCycleCount(1);
		moveHomePageInstructionIntoView.play();
	}
	
	//Method builds a top level layout-pane for home-page
	public VBox getSurfaceStack() {
		
		//Top-level layout-pane (VBox)
		VBox homePageSurfaceStack = new VBox();
		homePageSurfaceStack.setAlignment(Pos.CENTER);
		
		//Set background of top-level layout-pane
		ImageLoader mainBackground = new ImageLoader();
		Background bckgrd = mainBackground.mainBackground();
		homePageSurfaceStack.setBackground(bckgrd);
		
		//Load home-page elements to top-level layout-pane
		homePageSurfaceStack.getChildren().addAll(homePageStack);
		
		return homePageSurfaceStack;
	}
	
	//Method reloads home-page animations when home-page is reloaded
	public void reloadHomeAnimations() {
		
		moveHomePageLabelIntoView.play();
		moveHomePageGraphicIntoView.play();
		moveHomePageInstructionIntoView.play();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	This code = instance variables:
//
//	private TranslateTransition moveHomePageLabelOut;
//	private TranslateTransition moveHomePageGraphicOut;
//	private TranslateTransition moveHomePageInstructionOut;
//
//	END instance variables
	
	
	
	
//	This code goes into constructor:
//
//	EXIT ANIMATIONS
//	moveHomePageLabelOut = new TranslateTransition();
//	moveHomePageLabelOut.setNode(welcome);
//	moveHomePageLabelOut.setDuration(Duration.millis(900));
//	moveHomePageLabelOut.setFromY(0);
//	moveHomePageLabelOut.setToY(1000);
//	moveHomePageLabelOut.setCycleCount(1);
//	
//	moveHomePageGraphicOut = new TranslateTransition();
//	moveHomePageGraphicOut.setNode(img3View);
//	moveHomePageGraphicOut.setDuration(Duration.millis(800));
//	moveHomePageGraphicOut.setFromY(0);
//	moveHomePageGraphicOut.setToY(1000);
//	moveHomePageGraphicOut.setCycleCount(1);
//	
//	moveHomePageInstructionOut = new TranslateTransition();
//	moveHomePageInstructionOut.setNode(startLbl);
//	moveHomePageInstructionOut.setDuration(Duration.millis(700));
//	moveHomePageInstructionOut.setFromY(0);
//	moveHomePageInstructionOut.setToY(1000);
//	moveHomePageInstructionOut.setCycleCount(1);
//
//	END constructor code
	
	
	
	
//	public void exitHomeAnimation() {
//		moveHomePageInstructionOut.play();
//		moveHomePageGraphicOut.play();
//		moveHomePageLabelOut.play();
//	}
//
//	public TranslateTransition getLastTransition() {
//		//use this method to see if homepage edit animations
//		//have finished before moving on to next instructions
//		//we only return "moveHomePageLabelOut" because this is
//		//the last instruction to execute.
//		return moveHomePageLabelOut;
//	}
//
//	public void endAnimation() {
//		moveHomePageLabelOut.stop();
//	}
//	
//	public Label getHomeTitle() {
//		return welcome;
//	}
//	
//	public Label getHomeInstruction() {
//		return startLbl;
//	}
//	
//	public ImageView getHomeLogo() {
//		return img3View;
//	}
//	
//	public TranslateTransition getLastHomePageExitTransition() {
//		return moveHomePageLabelOut;
//	}
}