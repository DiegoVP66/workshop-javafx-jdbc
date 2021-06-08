package application;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		launch(args);
		
		
		// testing implementation
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		Department dep = depDao.findById(2);
		System.out.println(dep);
		
	
		
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
