package application;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
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

	public static void main(String[] args) throws ParseException {
		launch(args);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// testing implementation
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department dep = new Department(2, null);
		System.out.println("insert");
		Seller seller = new Seller(null, "David Gilmour", "darkSideOfTheMoon@gmail.com",sdf.parse("10/10/1960"), 1000000.00, dep);
		sellerDao.insert(seller);
		
		System.out.println(seller);
	
		
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
