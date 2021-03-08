package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExpressionChecker;
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

    @FXML private JFXButton register;

    // ================================= FXML methods =================================
    @FXML
    private void idCheck()
    {
        if(ExpressionChecker.getExpressionChecker().onlyNumbers(this.id.getText(), false))
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
        if(ExpressionChecker.getExpressionChecker().onlyNumbers(this.dni.getText(), true))
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
        if(ExpressionChecker.getExpressionChecker().composedName(this.name.getText()))
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
        if(ExpressionChecker.getExpressionChecker().composedName(this.lastName.getText()))
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
        if(ExpressionChecker.getExpressionChecker().isEmail(this.email.getText(), true))
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
        if(ExpressionChecker.getExpressionChecker().onlyNumbers(this.phoneNumber.getText(), true))
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
        if(ExpressionChecker.getExpressionChecker().onlyNumbers(this.leaderId.getText(), true))
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
        System.out.println(" id ="+this.id.getText()+" dni ="+this.dni.getText()+" name ="+this.name.getText()+
        " lastname ="+this.lastName.getText()+" loc ="+this.location.getText()+" birth ="+this.birthdate.getValue()+" email ="+this.email.getText()+
        " phone ="+this.phoneNumber.getText()+" isLeader ="+ this.isLeader.isSelected()+"  leader ="+this.leaderId.getText());

        ((PreferentialClientService)this.getService(0)).registerClient(this.id.getText(), this.dni.getText(), this.name.getText(),
            this.lastName.getText(), this.location.getText(), this.birthdate.getValue(), this.email.getText(),
            this.phoneNumber.getText(), this.isLeader.isSelected(), this.leaderId.getText());
    }

    @FXML
    private void isLeaderCheckBoxPressed()
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
    protected void setRegisterButtonText(String text)
    {
        this.register.setText(text);
    }

    protected void disableLeaderInfo(boolean value)
    {
        this.leaderId.setDisable(value);
        this.isLeader.setDisable(value);
    }

    protected void setPrefClientId(Integer prefClientId)
    {
        this.id.setText(""+prefClientId);
    }

    protected void setDni(Long dni)
    {
        this.dni.setText(""+dni);
    }

    protected void setName(String name)
    {
        this.name.setText(name);
    }

    protected void setLastName(String lastName)
    {
        this.lastName.setText(lastName);
    }

    protected void setLocation(String location)
    {
        this.location.setText(location);
    }

    protected void setEmail(String email)
    {
        this.email.setText(email);
    }

    protected void setPhoneNumber(Long phone)
    {
        this.phoneNumber.setText(""+phone);
    }

    protected void setBirthdate(LocalDate birthDate)
    {
        this.birthdate.setValue(birthDate);
    }

    protected void setIsLeader(boolean isLeader)
    {
        this.isLeader.setSelected(isLeader);
    }

    protected void setLeaderId(Integer leaderId)
    {
        this.leaderId.setText(""+leaderId);
    }

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