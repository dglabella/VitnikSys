package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.util.OrdersRowTable;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.util.PaymentsRowTable;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.util.RepurchasesRowTable;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class ClientManagementViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber, CampaignServiceSubscriber 
{
    private Campaign actualCampaign;
    private PreferentialClient prefClient;

    // ================================= FXML variables =================================
    @FXML private TitledPane paymentsPane;
    @FXML private TitledPane repurchasesPane;

    @FXML private ChoiceBox<String> camp;
    @FXML private ChoiceBox<PayItem> payType;
    @FXML private ChoiceBox<PayItem> payItem;
    @FXML private ChoiceBox<Bank> payBank;
    @FXML private ChoiceBox<PayStatus> payStatus;

    @FXML private Label leader;
    @FXML private Label balance;
    @FXML private Label prefClientId;
    @FXML private Label campBalance;
    @FXML private Label inCirculation;
    @FXML private Label prefClientName;
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

    @FXML private TableView<OrdersRowTable> orders;
    @FXML private TableView<PaymentsRowTable> payments;
    @FXML private TableView<RepurchasesRowTable> repurchases;

    //orders columns
    @FXML private TableColumn<OrdersRowTable, String> deliveryNumber;
    @FXML private TableColumn<OrdersRowTable, String> quantity;
    @FXML private TableColumn<OrdersRowTable, String> price;
    @FXML private TableColumn<OrdersRowTable, String> commission;
    @FXML private TableColumn<OrdersRowTable, String> articleName;
    @FXML private TableColumn<OrdersRowTable, String> orderType;
    @FXML private TableColumn<OrdersRowTable, String> articleId;
    @FXML private TableColumn<OrdersRowTable, String> withdrawalDate;
    @FXML private TableColumn<OrdersRowTable, CheckBox> isCommissionable;

    //payments columns
    @FXML private TableColumn<PaymentsRowTable, String> code;
    @FXML private TableColumn<PaymentsRowTable, String> paymentDescriptor;
    @FXML private TableColumn<PaymentsRowTable, String> paymentRegistrationTime;
    @FXML private TableColumn<PaymentsRowTable, String> paymentAmount;
    @FXML private TableColumn<PaymentsRowTable, String> paymentItem;
    @FXML private TableColumn<PaymentsRowTable, String> paymentType;
    @FXML private TableColumn<PaymentsRowTable, String> bank;
    @FXML private TableColumn<PaymentsRowTable, String> paymentStatus;

    //repurchases columns
    @FXML private TableColumn<RepurchasesRowTable, String> devolutionCode;
    @FXML private TableColumn<RepurchasesRowTable, String> deliveryNumberRep;
    @FXML private TableColumn<RepurchasesRowTable, String> articleIdRep;
    @FXML private TableColumn<RepurchasesRowTable, String> priceRep;
    @FXML private TableColumn<RepurchasesRowTable, String> repurchasePrice;
    @FXML private TableColumn<RepurchasesRowTable, String> nameRep;
    @FXML private TableColumn<RepurchasesRowTable, String> typeRep;
    @FXML private TableColumn<RepurchasesRowTable, String> repurchaseRegistrationTime;


    // ================================= FXML methods ===================================
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

    // ================================= private methods ===================================
    private void showTotalsForActualCamp()
    {
        Balance balance = this.prefClient.getBalances().get(this.prefClient.getBalances().locate(this.prefClient.getId(), this.actualCampaign.getNumber()));
        this.campBalance.setText(""+balance.getBalance());
        this.totalInCampaignOrders.setText(""+ (balance.getTotalInOrdersCom()+balance.getTotalInOrdersNonCom()));
        this.paymentsPane.setText("Pagos: "+ balance.getTotalInPayments());
        this.repurchasesPane.setText("Recompras: "+ balance.getTotalInRepurchases());

        //calculateBalance method does not query the database
        this.balance.setText(""+ ((PreferentialClientService)this.getService(0)).calculateBalance(this.prefClient.getBalances()));
    }

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            ((CampaignService)this.getService(1)).searchLastCamp();

            if(this.prefClient instanceof Leader)
            {
                ((PreferentialClientService)this.getService(0)).searchLeader(this.prefClient.getId());
            }
            else if(this.prefClient instanceof BaseClient)
            {
                ((PreferentialClientService)this.getService(0)).searchBaseClient(this.prefClient.getId());
            }
            else
            {
                ((PreferentialClientService)this.getService(0)).searchSubordinatedClient(this.prefClient.getId());
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    protected void loadPreferentialClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
    }

    // ================================= public methods ===================================
    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {

    }

    // ================================= service subscriber methods ===================================
    @Override
    public void showQueriedCamp(Campaign camp) throws Exception
    {
        this.actualCampaign = camp;
    }

    @Override
    public void showQueriedCamps(List<Campaign> camps) throws Exception
    {
        //do nothing
    }

    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        this.prefClient = prefClient;
        this.prefClientName.setText(prefClient.getName() + " " + prefClient.getLastName());
        this.prefClientId.setText(prefClient.getId().toString());

        if(prefClient instanceof SubordinatedClient)
        {
            this.leader.setText(""+((SubordinatedClient)prefClient).getLeaderId());
        }
        showTotalsForActualCamp();
    }

    @Override
    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {

    }
}