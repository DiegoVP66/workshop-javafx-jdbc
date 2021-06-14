package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

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
import model.exceptions.ValidationException;
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
		catch(ValidationException e) {
			setErrorMessage(e.getErros());
		}

	}

	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListener) {
			listener.onDataChanged();
		}
		
	}

	private Department getFormData() {
		Department obj = new Department();
		ValidationException exception = new ValidationException("Validation error");
		obj.setId(Utils.tryParseInt(txtFieldId.getText()));
		
		if(txtFieldName.getText() == null || txtFieldName.getText().trim().equals("")) {
			exception.addErrors("name", "Field can't be empty");
		}
		
		obj.setName(txtFieldName.getText());
		
		if(exception.getErros().size() > 0) {
			throw exception;
		}

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
	
	private void setErrorMessage(Map<String, String> erros) {
		Set<String> fields = erros.keySet();
		if(fields.contains("name")) {
			labelError.setText(erros.get("name"));
		}
	}

}
