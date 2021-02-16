package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.model.services.PreferentialClientService;

public class ClientRegisterViewCntlr extends ViewCntlr
{
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
        if(this.getExpressionChecker().onlyNumbers(this.id.getText(), false))
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
        if(this.getExpressionChecker().onlyNumbers(this.dni.getText(), true))
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
        if(this.getExpressionChecker().composedName(this.name.getText()))
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
        if(this.getExpressionChecker().composedName(this.lastName.getText()))
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
        if(this.getExpressionChecker().isEmail(this.email.getText(), true))
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
        if(this.getExpressionChecker().onlyNumbers(this.phoneNumber.getText(), true))
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
        if(this.getExpressionChecker().onlyNumbers(this.leaderId.getText(), true))
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
        ((PreferentialClientService)this.getService(0)).registerClient(this.id.getText(), this.dni.getText(), this.name.getText(),
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


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {

    }

    // ================================= public methods ==================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub
    }

    // ================================= service subscriber methods ==================================
    @Override
    public void refresh() 
    {
        
    }

    //Override this method in order to execute manualInitialize in the prev controller
    @Override
    public void showSucces(String message)
    {
        new CustomAlert(AlertType.INFORMATION, "Exito", "El Cliente se ha registrado exitosamente!").customShow();
        //Reinit the main menu pref clients table
        this.getPrevViewCntlr().manualInitialize();
    }
}