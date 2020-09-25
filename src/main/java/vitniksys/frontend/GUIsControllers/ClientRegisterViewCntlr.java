package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vitniksys.frontend.views.View;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.controllers.ClientManagementController;

public class ClientRegisterViewCntlr extends VitnikViewCntlr implements Initializable, View
{
    private ExpressionChecker expressionChecker;

    private CustomAlert customAlert;

    private ProcessingViewCntlr processingViewCtrller;

    //Controllers
    private ClientManagementController clientController;

    // ================================= FXML variables =================================
    @FXML private TextField id;
    @FXML private TextField dni;
    @FXML private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField location;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private TextField leaderId;

    @FXML private Label invalidId;
    @FXML private Label invalidDni;
    @FXML private Label invalidName;
    @FXML private Label invalidLastName;
    @FXML private Label invalidEmail; 
    @FXML private Label invalidPhoneNumber;
    @FXML private Label invalidLeaderId;

    @FXML private DatePicker birthdate;

    @FXML private CheckBox isLeader;

    // ================================= FXML methods =================================
    @FXML
    private void idCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.id.getText(), false))
        {
            this.invalidId.setVisible(false);
        }
        else
        {
            this.invalidId.setText("Dato invalido");
            this.invalidId.setVisible(true);
        }
    }

    @FXML
    private void dniCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.dni.getText(), true))
        {
            this.invalidDni.setVisible(false);
        }
        else
        {
            this.invalidDni.setText("Dato invalido");
            this.invalidDni.setVisible(true);
        }
    }

    @FXML
    private void nameCheck()
    {
        if(this.expressionChecker.composedName(this.name.getText()))
        {
            this.invalidName.setVisible(false);
        }
        else
        {
            this.invalidName.setText("Dato invalido");
            this.invalidName.setVisible(true);
        }  
    }

    @FXML
    private void lastNameCheck()
    {
        if(this.expressionChecker.composedName(this.lastName.getText()))
        {
            this.invalidLastName.setVisible(false);
        }
        else
        {
            this.invalidLastName.setText("Dato invalido");
            this.invalidLastName.setVisible(true);
        }
    }

    @FXML
    private void emailCheck()
    {
        if(this.expressionChecker.isEmail(this.email.getText(), true))
        {
            this.invalidEmail.setVisible(false);
        }
        else
        {
            this.invalidEmail.setText("Dato invalido");
            this.invalidEmail.setVisible(true);
        }  
    }

    @FXML
    private void phoneNumberCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.phoneNumber.getText(), true))
        {
            this.invalidPhoneNumber.setVisible(false);
        }
        else
        {
            this.invalidPhoneNumber.setText("Dato invalido");
            this.invalidPhoneNumber.setVisible(true);
        }  
    }

    @FXML
    private void leaderIdCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.leaderId.getText(), true))
        {
            this.invalidLeaderId.setVisible(false);
        }
        else
        {
            this.invalidLeaderId.setText("Dato invalido");
            this.invalidLeaderId.setVisible(true);
        }
    }

    @FXML
    private void registerButtonPressed() throws Exception
    {
        this.clientController.registerClient(this.id.getText(), this.dni.getText(), this.name.getText(),
            this.lastName.getText(), this.location.getText(), this.birthdate.getValue(), this.email.getText(),
            this.phoneNumber.getText(), this.isLeader.isSelected(), this.leaderId.getText());
    }

    @FXML
    private void isLeaderCheckBoxPressed() throws Exception
    {
        if(this.isLeader.isSelected())
        {
            this.leaderId.clear();
            this.leaderId.setDisable(true);
        }
        else
        {
            this.leaderId.setDisable(false);
        }
    }

    // ================================= private methods =================================

    // ================================= protected methods =================================
    @Override
    protected void refresh()
    {

    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.expressionChecker = ExpressionChecker.getExpressionChecker();
        this.clientController = new ClientManagementController(this);

        this.customAlert = new CustomAlert();
    }

    // ================================= view methods =================================
    @Override
    public void showProcessIsWorking(String message)
    {
        try
        {
            String fileName = "processing";
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
            Scene scene = new Scene(fxmlLoader.load());
            this.processingViewCtrller = fxmlLoader.getController();
            this.processingViewCtrller.setStage(new Stage());
            this.processingViewCtrller.getStage().setResizable(false);
            this.processingViewCtrller.getStage().setScene(scene);
            this.processingViewCtrller.getStage().setTitle("PROCESO EN CURSO");
            this.processingViewCtrller.setPrevViewCntlr(this);
            
            System.out.println(this.processingViewCtrller.getStage().getModality());
            this.processingViewCtrller.getStage().show();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @Override
    public void closeProcessIsWorking()
    {
        this.processingViewCtrller.getStage().close();
    }

    @Override
    public void showSucces(String message)
    {
        this.customAlert.setAlertType(AlertType.INFORMATION);
        this.customAlert.setTitle("EXITO");
        this.customAlert.setHeaderText(message);
        this.customAlert.customShow();
        this.getStage().close();
    }

    @Override
    public void showError(String message)
    {
        this.customAlert.setAlertType(AlertType.ERROR);
        this.customAlert.setTitle("ERROR");
        this.customAlert.setHeaderText(message);
        this.customAlert.customShow();
    }

    @Override
    public void showError(String message, Exception exception)
    {
        this.customAlert.setAlertType(AlertType.ERROR);
        this.customAlert.setTitle("ERROR");
        this.customAlert.setHeaderText(message);
        this.customAlert.setException(exception);
        this.customAlert.customShow();
    }

    @Override
    public void showNoResult(String message)
    {
        // Do nothing
    }
}