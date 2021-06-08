package application;

import java.io.IOException;
import java.util.List;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

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

	public static void main(String[] args) {
		launch(args);
		
		// testing implementation
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> list = sellerDao.findAll();
		System.out.println("FindAll method");
		for(Seller sellers : list) {
			System.out.println(sellers);
		}
		
		DB.closeConnection();
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
