package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ImageLoader
{
	private Background mainAppBackground;
	private Background altAppBackground;
	private ImageView mainLogo;
	private ImageView smallerLogo;
	private ImageView detailsIcon;
	private ImageView locateIcon;
	private ImageView dateIcon;
	private ImageView timeIcon;
	private ImageView whiteLogoIcon;
	private Image appIcon;
	
	//Method builds background for main application windows
	public Background mainBackground() {
	
		try{
			//Input stream for main background
			FileInputStream inputImage = new FileInputStream("appImages/0_mainAppBckgrd.jpg");
			Image img = new Image(inputImage);
			BackgroundSize fit = new BackgroundSize(1600, 900, false, false, true, true);
			BackgroundImage bckgrdImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, fit);
			
			mainAppBackground = new Background(bckgrdImg);
		}
		catch(IOException mainBackgroundBuilderException){
			mainBackgroundBuilderException.printStackTrace();
		}
		return mainAppBackground;
	}
	
	//Method builds background for alternate application windows
	public Background altBackground() {
		
		try{
			//Input stream for alternate background
			FileInputStream inputImage = new FileInputStream("appImages/1_altAppBckgrd.jpg");
			Image img = new Image(inputImage);
			BackgroundSize fit = new BackgroundSize(1600, 900, false, false, true, true);
			BackgroundImage bckgrdImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, fit);
			
			altAppBackground = new Background(bckgrdImg);
		}
		catch(IOException altBackgroundBuilderException){
			altBackgroundBuilderException.printStackTrace();
		}
		return altAppBackground;
	}
	
	//Method builds main logo image (currently used on Home Page)
	public ImageView mainLogoImageView() {
		
		try{
			//Input stream for main logo image
			FileInputStream inputImage = new FileInputStream("appImages/3_logo_0.png");
			
			mainLogo = new ImageView(new Image(inputImage));
		}
		catch(IOException mainLogoImageViewException){
			mainLogoImageViewException.printStackTrace();
		}
		return mainLogo;
	}
	
	//Method builds smaller logo image (currently used on Events Page)
	public ImageView smallerLogoImageView() {
		
		try{
			//Input stream for smaller logo image
			FileInputStream inputImage = new FileInputStream("appImages/4_logo_1.png");
			
			smallerLogo = new ImageView(new Image(inputImage));
		}
		catch(IOException smallerLogoImageViewException){
			smallerLogoImageViewException.printStackTrace();
		}
		return smallerLogo;
	}
	
	//Method builds application icon image
	public Image appIconImage() {
		
		try{
			//Input stream for application icon image
			FileInputStream inputImage = new FileInputStream("appImages/2_appIcon.png");
			
			appIcon = new Image(inputImage);
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
		return appIcon;
	}
	
	//Method builds details icon image
	public ImageView detailsImageView() {
		
		try{
			//Input stream for details icon image
			FileInputStream inputImage = new FileInputStream("appImages/6_details.png");
			
			detailsIcon = new ImageView(new Image(inputImage));
		}
		catch(IOException detailsIconImageViewException){
			detailsIconImageViewException.printStackTrace();
		}
		return detailsIcon;
	}
	
	//Method builds location icon image
	public ImageView locateImageView() {
		
		try{
			//Input stream for location icon image
			FileInputStream inputImage = new FileInputStream("appImages/7_locate.png");
			
			locateIcon = new ImageView(new Image(inputImage));
		}
		catch(IOException locateIconImageViewException){
			locateIconImageViewException.printStackTrace();
		}
		return locateIcon;
	}
	
	//Method builds date icon image
	public ImageView dateImageView() {
		
		try{
			//Input stream for date icon image
			FileInputStream inputImage = new FileInputStream("appImages/8_date.png");
			
			dateIcon = new ImageView(new Image(inputImage));
		}
		catch(IOException dateIconImageViewException){
			dateIconImageViewException.printStackTrace();
		}
		return dateIcon;
	}
	
	//Method builds time icon image
	public ImageView timeImageView() {
		
		try{
			//Input stream for time icon image
			FileInputStream inputImage = new FileInputStream("appImages/9_time.png");
			
			timeIcon = new ImageView(new Image(inputImage));
		}
		catch(IOException timeIconImageViewException){
			timeIconImageViewException.printStackTrace();
		}
		return timeIcon;
	}

	//Method builds smaller black and white logo image
	public ImageView whiteLogoImageView() {
		
		try{
			//Input stream for time icon image
			FileInputStream inputImage = new FileInputStream("appImages/10_logo.png");
			
			whiteLogoIcon = new ImageView(new Image(inputImage));
		}
		catch(IOException whiteLogoIconImageViewException){
			whiteLogoIconImageViewException.printStackTrace();
		}
		return whiteLogoIcon;
	}
}