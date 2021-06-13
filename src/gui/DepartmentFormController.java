package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DBException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private DepartmentService service;

	private Department entity;
	
	private List<DataChangeListener> dataChangeListener = new ArrayList<>();
	

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
	public void onButtonSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListener();
			Utils.currentStage(event).close();

		} catch (DBException e) {
			Alerts.showAlert("Error Saving object", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListener) {
			listener.onDataChanged();
		}
		
	}

	private Department getFormData() {
		Department obj = new Department();

		obj.setId(Utils.tryParseInt(txtFieldId.getText()));
		obj.setName(txtFieldName.getText());

		return obj;
	}

	@FXML
	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListener.add(listener);
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtFieldId);
		Constraints.setTextFieldMaxLenght(txtFieldName, 30);
	}

	public void updateDepartmentForm() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}

		txtFieldId.setText(String.valueOf(entity.getId()));
		txtFieldName.setText(entity.getName());
	}

}
