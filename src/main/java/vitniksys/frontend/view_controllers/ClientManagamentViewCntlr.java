package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayStatus;

public class ClientManagamentViewCntlr extends TableViewCntlr
{
    @FXML private ChoiceBox<String> camp;
    @FXML private ChoiceBox<PayItem> payType;
    @FXML private ChoiceBox<PayItem> payItem;
    @FXML private ChoiceBox<Bank> payBank;
    @FXML private ChoiceBox<PayStatus> payStatus;

    @FXML private Label leader;
    @FXML private Label balance;
    @FXML private Label prefClient;
    @FXML private Label campBalance;
    @FXML private Label inCirculation;
    @FXML private Label catalogueQuantity;
    @FXML private Label totalInCampaignOrders;

    @FXML private TextField payAmount;
    @FXML private TextField payDescriptor;

    @FXML private JFXButton pay;
    @FXML private JFXButton stock;
    @FXML private JFXButton withdraw;
    @FXML private JFXButton devolution;
    @FXML private JFXButton withdrawAll;

    @FXML private Accordion otherTables;

    @FXML private TableView<?> orders;
    @FXML private TableView<?> payments;
    @FXML private TableView<?> repurchases;

    //orders columns
    @FXML private TableColumn<?, ?> deliveryNumber;
    @FXML private TableColumn<?, ?> quantity;
    @FXML private TableColumn<?, ?> price;
    @FXML private TableColumn<?, ?> commission;
    @FXML private TableColumn<?, ?> name;
    @FXML private TableColumn<?, ?> orderType;
    @FXML private TableColumn<?, ?> articleId;
    @FXML private TableColumn<?, ?> withdrawalDate;
    @FXML private TableColumn<?, ?> isCommissionable;

    //payments columns
    @FXML private TableColumn<?, ?> code;
    @FXML private TableColumn<?, ?> paymentDescriptor;
    @FXML private TableColumn<?, ?> paymentRegistrationTime;
    @FXML private TableColumn<?, ?> paymentAmount;
    @FXML private TableColumn<?, ?> paymentItem;
    @FXML private TableColumn<?, ?> paymentType;
    @FXML private TableColumn<?, ?> bank;
    @FXML private TableColumn<?, ?> paymentStatus;

    //repurchases columns
    @FXML private TableColumn<?, ?> devolutionCode;
    @FXML private TableColumn<?, ?> deliveryNumberRep;
    @FXML private TableColumn<?, ?> articleIdRep;
    @FXML private TableColumn<?, ?> priceRep;
    @FXML private TableColumn<?, ?> repurchasePrice;
    @FXML private TableColumn<?, ?> nameRep;
    @FXML private TableColumn<?, ?> typeRep;
    @FXML private TableColumn<?, ?> repurchaseRegistrationTime;

    @FXML
    private void backButtonPressed()
    {

    }

    @FXML
    private void devolutionButtonPressed()
    {

    }

    @FXML
    private void editPrefClientButtonPressed()
    {

    }

    @FXML
    private void labelCatOnMouseClicked()
    {

    }

    @FXML
    private void labelCatOnMouseEntered()
    {

    }

    @FXML
    private void labelCatOnMouseExited()
    {

    }

    @FXML
    private void labelCpLiderClicked()
    {

    }

    @FXML
    private void labelCpLiderMouseEntered()
    {
        
    }

    @FXML
    private void labelCpLiderMouseExited()
    {

    }

    @FXML
    private void labelSaldoCampClicked()
    {

    }

    @FXML
    private void labelSaldoCampMouseEntered()
    {

    }

    @FXML
    private void labelSaldoCampMouseExited()
    {

    }

    @FXML
    private void nombreCpOnMouseClicked()
    {

    }

    @FXML
    private void nombreCpOnMouseEntered()
    {

    }

    @FXML
    private void nombreCpOnMouseExited()
    {

    }

    @FXML
    private void payButtonPressed()
    {

    }

    @FXML
    private void stockButtonPressed()
    {

    }

    @FXML
    private void validarIdentificador()
    {

    }

    @FXML
    private void validarMonto()
    {

    }

    @FXML
    private void withdrawAllButtonPressed()
    {

    }

    @FXML
    private void withdrawButtonPressed()
    {

    }

    @Override
    protected void manualInitialize()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub
    }
}