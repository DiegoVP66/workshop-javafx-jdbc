package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable{
	
	private  Department entity;
	
	@FXML
	private Label labelError;
	
	@FXML
	private TextField txtFieldId;
	
	@FXML
	private TextField txtFieldName;
	
	@FXML
	private Button buttonSave;
	
	@FXML
	private Button buttonCancel;
	
	@FXML
	public void onButtonSaveAction() {
		System.out.println("Save");
	}
	
	@FXML
	public void onButtonCancelAction() {
		System.out.println("Cancel");
	}
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtFieldId);
		Constraints.setTextFieldMaxLenght(txtFieldName, 30);
		Constraints.setTextFieldMaxLenght(txtFieldId, 1);
		
	}
	
	public void updateDepartmentForm() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		txtFieldId.setText(String.valueOf(entity.getId()));
		txtFieldName.setText(entity.getName());
	}

}
