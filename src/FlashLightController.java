import java.io.IOException;
import javafx.application.Application;

import com.sun.javafx.geom.Rectangle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FlashLightController extends Thread{


	@FXML
	private Canvas canv;

	private GraphicsContext gc;

	private boolean on1 = true;

	private double carRedTime;//duration of red light
	private double carGreenTime;//duration of red light
	private final int BREAK = 500; //green flickering rate
	private double WalkGreenTimeH ;//amount of times the green-light is flickering for the vertical lights 
	private double WalkGreenTimeW ;//amount of times the green-light is flickering for the horizontal lights
	
	//set the flashlight times
	   public void setCarTimes(int carRedTime, int carGreenTime) {
			
			this.carRedTime = carRedTime;
	        this.carGreenTime = carGreenTime;
	        this.WalkGreenTimeH = this.carRedTime / BREAK ; //
	        this.WalkGreenTimeW = this.carGreenTime / BREAK ;
	
	   }
	   
	public void initialize() {
		
		gc = canv.getGraphicsContext2D();

		start();

	}
	

	@Override
	public void run() {

		while(true) {

			double green; //amount of times the green-light is flickering 
			
			on1 =!on1;
			if(on1 == true) {

				green = WalkGreenTimeW;
			}

			else {

				green = WalkGreenTimeH;
			}

			draw();


			for(int i = 0; i < green; i++) {

				greenRect();


				try {

					sleep(BREAK);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}	

				if(on1 == false) {

					gc.clearRect(canv.getWidth()/2, canv.getHeight()/3+20, 10, 20);
					gc.clearRect(canv.getWidth()/2, canv.getHeight()/3*2+20, 10, 20);

					gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3+20, 10, 20);
					gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3*2+20, 10, 20);
				}
				if(on1 == true) {

					gc.clearRect(canv.getWidth()/3, canv.getHeight()/2+20, 10, 20);
					gc.clearRect(canv.getWidth()/3*2, canv.getHeight()/2+20, 10, 20);

					gc.strokeRect(canv.getWidth()/3, canv.getHeight()/2+20, 10, 20);
					gc.strokeRect(canv.getWidth()/3*2, canv.getHeight()/2+20, 10, 20);
				}
				try {

					sleep(BREAK);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}	

			}

		}


	}

	public void draw () {

		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		//draw rectangle
		gc.strokeRect(canv.getWidth()/3, canv.getHeight()/2, 10, 20);
		gc.strokeRect(canv.getWidth()/3, canv.getHeight()/2+20, 10, 20);

		gc.strokeRect(canv.getWidth()/3*2, canv.getHeight()/2, 10, 20);
		gc.strokeRect(canv.getWidth()/3*2, canv.getHeight()/2+20, 10, 20);

		gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3, 10, 20);
		gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3+20, 10, 20);

		gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3*2, 10, 20);
		gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3*2+20, 10, 20);


		//draw circles
		gc.strokeOval(canv.getWidth()/3-5, canv.getHeight()/2-40, 20, 20);
		gc.strokeOval(canv.getWidth()/3-5, canv.getHeight()/2-20, 20, 20);

		gc.strokeOval(canv.getWidth()/3*2-5, canv.getHeight()/2-40, 20, 20);
		gc.strokeOval(canv.getWidth()/3*2-5, canv.getHeight()/2-20, 20, 20);

		gc.strokeOval(canv.getWidth()/2-5, canv.getHeight()/3-40, 20, 20);
		gc.strokeOval(canv.getWidth()/2-5, canv.getHeight()/3-20, 20, 20);

		gc.strokeOval(canv.getWidth()/2-5, canv.getHeight()*2/3-40, 20, 20);
		gc.strokeOval(canv.getWidth()/2-5, canv.getHeight()*2/3-20, 20, 20);

		paint1();

		paint2();


	}

	public void greenRect() {


		if(on1 == false) {

			gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3+20, 10, 20);
			gc.strokeRect(canv.getWidth()/2, canv.getHeight()/3*2+20, 10, 20);

			gc.setFill(Color.LIGHTGREEN); 

			gc.fillRect(canv.getWidth()/2, canv.getHeight()/3+20, 10, 20);
			gc.fillRect(canv.getWidth()/2, canv.getHeight()/3*2+20, 10, 20);

		}

		if(on1 == true) {

			gc.strokeRect(canv.getWidth()/3, canv.getHeight()/2+20, 10, 20);
			gc.strokeRect(canv.getWidth()/3*2, canv.getHeight()/2+20, 10, 20);

			gc.setFill(Color.LIGHTGREEN); 

			gc.fillRect(canv.getWidth()/3, canv.getHeight()/2+20, 10, 20);
			gc.fillRect(canv.getWidth()/3*2, canv.getHeight()/2+20, 10, 20);





		}

	}



	public void paint1() {
		if(on1 == true) {
			gc.setFill(Color.RED);
			gc.fillRect(canv.getWidth()/2, canv.getHeight()/3, 10, 20);

			gc.fillRect(canv.getWidth()/2, canv.getHeight()/3*2, 10, 20);

			gc.setFill(Color.LIGHTGREEN); 
			gc.fillOval(canv.getWidth()/2-5, canv.getHeight()/3-20, 20, 20);
			gc.fillOval(canv.getWidth()/2-5, canv.getHeight()*2/3-20, 20, 20);
		}
		else {
			gc.setFill(Color.RED);
			gc.fillOval(canv.getWidth()/2-5, canv.getHeight()/3-40, 20, 20);

			gc.fillOval(canv.getWidth()/2-5, canv.getHeight()*2/3-40, 20, 20);


		}


	}

	public void paint2() { 


		if(on1 == true) {
			//	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); //reseting the screen
			gc.setFill(Color.RED); 

			gc.fillOval(canv.getWidth()/3-5, canv.getHeight()/2-40, 20, 20);

			gc.fillOval(canv.getWidth()/3*2-5, canv.getHeight()/2-40, 20, 20);



		}



		else {

			gc.setFill(Color.RED);
			gc.fillRect(canv.getWidth()/3, canv.getHeight()/2, 10, 20);

			gc.fillRect(canv.getWidth()/3*2, canv.getHeight()/2, 10, 20);

			gc.setFill(Color.LIGHTGREEN); 

			gc.fillOval(canv.getWidth()/3-5, canv.getHeight()/2-20, 20, 20);
			gc.fillOval(canv.getWidth()/3*2-5, canv.getHeight()/2-20, 20, 20);
		}

	}

	
}
