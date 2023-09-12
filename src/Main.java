import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{ 
	
	public void start(Stage stage) throws Exception{ 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FlashLight.fxml"));
        Parent root = loader.load();
        
        FlashLightController controller = loader.getController();
        final int CAR_RED = Integer.parseInt(getParameters().getRaw().get(0));
        final int CAR_GREEN = Integer.parseInt(getParameters().getRaw().get(1));
        controller.setCarTimes(CAR_RED, CAR_GREEN);

        Scene scene = new Scene(root);
        stage.setTitle("FlashLight");
        stage.setScene(scene);
        stage.show();
	} 
	
	public static void main(String[] args) { 		
		
		launch(args); 
		
		System.out.println("");

	} 
}