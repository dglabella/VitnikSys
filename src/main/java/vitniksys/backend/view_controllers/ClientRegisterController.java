package vitniksys.backend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.interfaces.IFunctionalities;
import vitniksys.backend.model.ClienteBase;
import vitniksys.backend.model.ClientePreferencial;
import vitniksys.backend.model.ClienteSubordinado;
import vitniksys.backend.functionality_triggers.Functionalities;

public class ClientRegisterController extends VitnikController implements Initializable
{
    private ExpressionChecker expressionChecker;

    // ================================= FXML variables =================================
    @FXML private TextField id;
    @FXML private TextField dni;
    @FXML private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField location;
    @FXML private TextField email;
    @FXML private DatePicker birthdate;
    @FXML private TextField phoneNumber;
    @FXML private TextField leaderId;

    @FXML private Label invalidId;
    @FXML private Label invalidDni;
    @FXML private Label invalidName;
    @FXML private Label invalidLastName;
    @FXML private Label invalidEmail; 
    @FXML private Label invalidPhoneNumber;
    @FXML private Label invalidLeaderId;

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
        IFunctionalities functionalities = new Functionalities();
        ClientePreferencial client;
        if(this.leaderId.getText().isEmpty())
        {
            client =  new ClienteBase(Integer.parseInt(this.id.getText()), this.name.getText().toUpperCase(), this.lastName.getText().toUpperCase());
        }
        else
        {
            client =  new ClienteSubordinado(Integer.parseInt(this.id.getText()), this.name.getText().toUpperCase(), this.lastName.getText().toUpperCase());
        }

        client.setDni(!this.dni.getText().isEmpty()? Long.parseLong(this.dni.getText()) : null);

        functionalities.registrarCliente(client);
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
        expressionChecker = ExpressionChecker.getExpressionChecker();
    }
}