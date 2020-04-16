package kon.workout.remind.view.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import kon.workout.remind.controller.ConfigModule;
import kon.workout.remind.controller.TimingController;
import kon.workout.remind.model.interfaces.IExcersise;
import kon.workout.remind.model.interfaces.IExcersiseProvider;

/**
 *
 * @author Konstantin Naryshkin
 */
public class JavaFxUi extends Application
{

	IExcersiseProvider exersiseProvider;
	
	private TimingController timing;

	private Text excersiseName;

	private Text nextTime;

	private Timeline flash;

	private Stage stage;
	
	/**
	 * 
	 */
	public JavaFxUi()
	{
		Injector inj = Guice.createInjector(new ConfigModule());
		this.exersiseProvider = inj.getInstance(IExcersiseProvider.class);
		this.timing = inj.getInstance(TimingController.class);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.timing.scheduleNext(new Callback());
		this.stage = primaryStage;
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		this.excersiseName = new Text("LOADING");
		this.excersiseName.setFont(Font.font(20));
		grid.add(this.excersiseName, 0, 0, 3, 1);
		
		Button anotherBtn = new Button("ANOTHER");
		anotherBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event)
			{
				updateExcersise();
			}
		});
		grid.add(anotherBtn, 1, 1);
		
		this.nextTime = new Text("LOADING");
		this.nextTime.setFont(Font.font(15));
		grid.add(this.nextTime, 0, 2, 3, 1);
		
		Label repsLbl = new Label("Reps: ");
		grid.add(repsLbl, 0, 3);
		
		TextField repsIn = new TextField();
		grid.add(repsIn, 1, 3);
		
		Button repsBtn = new Button("Send it");
		repsBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				reset();
			}
		});
		grid.add(repsBtn, 2, 3);
		
		Scene scene = new Scene(grid, 1000, 1000);
		
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new Cleanup(primaryStage.getOnCloseRequest()));
		primaryStage.show();
		
		this.flash = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> this.nextTime.setVisible(false)),
        new KeyFrame(Duration.seconds( 0.1), evt -> this.nextTime.setVisible(true)));
		this.flash.setCycleCount(Animation.INDEFINITE);
		
		updateExcersise();
	}
	
	private void updateExcersise() {
		IExcersise ex = this.exersiseProvider.next();
		this.excersiseName.setText(ex.getName());
		this.nextTime.setText(this.timing.getNextRun().toString());
		this.nextTime.setVisible(true);
	}
	
	
	/**
	 * 
	 */
	private void reset()
	{
		this.flash.stop();
		this.timing.scheduleNext(new Callback());
		updateExcersise();
	}


	private class Callback implements Runnable {
		/**
		 * 
		 */
		public Callback()
		{
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run()
		{
			JavaFxUi.this.flash.play();
			JavaFxUi.this.stage.toFront();
		}
	}
	
	private class Cleanup implements EventHandler<WindowEvent> {

		private EventHandler<WindowEvent> closeWindow;

		/**
		 * @param onCloseRequest
		 */
		public Cleanup(EventHandler<WindowEvent> onCloseRequest)
		{
			this.closeWindow = onCloseRequest;
		}

		@Override
		public void handle(WindowEvent event)
		{
			timing.close();
			if (closeWindow != null)
			{
				this.closeWindow.handle(event);
			}
		}
		
	}
	
}
