import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UprootGUI1 {
	StackPane root = new StackPane();
	Canvas canvas = new Canvas(800,800);
	Text textPane = new Text();
	TextField playerInput = new TextField();
	
	Button north = new Button();
	Button east = new Button();
	Button south = new Button();
	Button west = new Button();
	Button up = new Button();
	Button down = new Button();
	
	ToolBar navigation = new ToolBar();
	
	BorderPane boarder = new BorderPane();
	
	HBox output = new HBox();
	
	


public void start(Stage primaryStage) {
	north.setText(" ^ ");
	east.setText(" > ");
	south.setText(" v ");
	west.setText(" < ");
	up.setText(" UP " );
	down.setText( " DOWN ");
	
	}

public static void main(String[] args) {
    launch(args);
}

}
