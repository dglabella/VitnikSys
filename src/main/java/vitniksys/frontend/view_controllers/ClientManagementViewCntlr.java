package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import java.util.function.Predicate;
import javafx.scene.control.Tooltip;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.util.OrdersRowTable;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.util.PaymentsRowTable;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.util.AutoCompletionTool;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.util.RepurchasesRowTable;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.Observation;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.services.CommissionService;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class ClientManagementViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber, CampaignServiceSubscriber, CommissionServiceSubscriber
{
    private Campaign actualCampaign;
    private PreferentialClient prefClient;
    private Commission actualCommission;

    private int ORDERS_TABLE_NUMBER;
    private int PAYMENTS_TABLE_NUMBER;
    private int REPURCHASES_TABLE_NUMBER;

    private AutoCompletionTool campAutoCompletionTool;

    // ================================= FXML variables =================================
    @FXML private TitledPane paymentsPane;
    @FXML private TitledPane repurchasesPane;

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
    @FXML private Label comLvl;

    @FXML private Tooltip ordersQuantity;
    @FXML private Tooltip commissionableOrdersQuantity;

    @FXML private TextField camp;
    @FXML private TextField ordersFilter;
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
    @FXML private TableColumn<OrdersRowTable, String> cost;
    @FXML private TableColumn<OrdersRowTable, String> commissionCost;
    @FXML private TableColumn<OrdersRowTable, String> commission;
    @FXML private TableColumn<OrdersRowTable, String> articleName;
    @FXML private TableColumn<OrdersRowTable, String> orderType;
    @FXML private TableColumn<OrdersRowTable, String> articleId;
    @FXML private TableColumn<OrdersRowTable, String> unitPrice;
    @FXML private TableColumn<OrdersRowTable, String> withdrawalDate;
    @FXML private TableColumn<OrdersRowTable, String> isCommissionable;

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

    @FXML
    private void observationsMenuItemSelected()
    {

    }

    @FXML
    private void cataloguesDeliveryMenuItemSelected()
    {

    }

    @FXML
    private void commissionLvlMenuItemSelected()
    {
        ViewCntlr viewCntlr = this.createStage("Comisión", "commissionRegister", new CommissionService());

        //load prefClientId and campNumber because sometimes actualCommission can be null
        viewCntlr.getStage().show();
        ((CommissionRegisterViewCntlr)viewCntlr).loadPrefClient(this.prefClient);
        ((CommissionRegisterViewCntlr)viewCntlr).loadCamp(this.actualCampaign);
        viewCntlr.manualInitialize();
    }

    @FXML
    private void updateCommissionablesOrders()
    {
        if(this.actualCommission != null)
        {
            List<Order> ordersToUpdate = new ArrayList<>();
            Iterator<OrdersRowTable> it = this.orders.getItems().iterator();
            while(it.hasNext())
                ordersToUpdate.add(it.next().getOrder());

            ((CommissionService)this.getService(2)).updateCommissionableOrders(ordersToUpdate);
            ((PreferentialClientService)this.getService(0)).searchLeader(this.prefClient.getId());
        }
    }

    // ================================= private methods ===================================
    private void showTotalsForActualCamp()
    {
        Balance balance = this.prefClient.getBalances().locateWithCampNumb(this.actualCampaign.getNumber());
        this.campBalance.setText(""+balance.getBalance());
        this.totalInCampaignOrders.setText(""+ balance.getTotalInOrders());
        this.paymentsPane.setText("Pagos: "+ balance.getTotalInPayments());
        this.repurchasesPane.setText("Recompras: "+ balance.getTotalInRepurchases());

        this.balance.setText(""+ this.prefClient.calculateBalance());
    }

    private void insertDataIntoTables()
    {
        Float com = 0f;

        this.loadData(this.ORDERS_TABLE_NUMBER, OrdersRowTable.generateRows(this.prefClient.getOrders().locateAllWithCampNumb(this.actualCampaign.getNumber()), com));
        this.loadData(this.PAYMENTS_TABLE_NUMBER, this.prefClient.getPayments().locateAllWithCampNumb(this.actualCampaign.getNumber()));
        this.loadData(this.REPURCHASES_TABLE_NUMBER, this.prefClient.getRepurchases().locateAllWithCampNumb(this.actualCampaign.getNumber()));
    }

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            //((CampaignService)this.getService(1)).searchLastCamp();
            ((CampaignService)this.getService(1)).searchCamps(null, null, null, null, null);

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
        this.orders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<TableColumn> columns = new ArrayList<>();
        List<PropertyValueFactory> propertiesValues = new ArrayList<>();

        columns.add(this.deliveryNumber);
        columns.add(this.quantity);
        columns.add(this.cost);
        columns.add(this.commissionCost);
        columns.add(this.commission);
        columns.add(this.articleName);
        columns.add(this.orderType);
        columns.add(this.articleId);
        columns.add(this.unitPrice);
        columns.add(this.withdrawalDate);
        columns.add(this.isCommissionable);
        
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("quantity"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("commissionCost"));
        propertiesValues.add(new PropertyValueFactory<>("commission"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("type"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("unitPrice"));
        propertiesValues.add(new PropertyValueFactory<>("withdrawalDate"));
        propertiesValues.add(new PropertyValueFactory<>("commissionable"));

        this.registerTable(this.orders);
        this.ORDERS_TABLE_NUMBER = 0; //Because is the first table registered.

        columns.add(this.code);
        columns.add(this.paymentDescriptor);
        columns.add(this.paymentAmount);
        columns.add(this.paymentItem);
        columns.add(this.paymentType);
        columns.add(this.bank);
        columns.add(this.paymentStatus);
        columns.add(this.paymentRegistrationTime);

        propertiesValues.add(new PropertyValueFactory<>("code"));
        propertiesValues.add(new PropertyValueFactory<>("descriptor"));
        propertiesValues.add(new PropertyValueFactory<>("amount"));
        propertiesValues.add(new PropertyValueFactory<>("item"));
        propertiesValues.add(new PropertyValueFactory<>("paymentMethod"));
        propertiesValues.add(new PropertyValueFactory<>("bank"));
        propertiesValues.add(new PropertyValueFactory<>("paymentStatus"));
        propertiesValues.add(new PropertyValueFactory<>("registrationTime"));

        this.registerTable(this.payments);
        this.PAYMENTS_TABLE_NUMBER = 1;

        columns.add(this.devolutionCode);
        columns.add(this.deliveryNumberRep);
        columns.add(this.articleIdRep);
        columns.add(this.priceRep);
        columns.add(this.repurchasePrice);
        columns.add(this.nameRep);
        columns.add(this.typeRep);
        columns.add(this.repurchaseRegistrationTime);

        propertiesValues.add(new PropertyValueFactory<>("devCode"));
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("repurchaseCost"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("articleType"));
        propertiesValues.add(new PropertyValueFactory<>("registrationTime"));

        this.registerTable(this.repurchases);
        this.REPURCHASES_TABLE_NUMBER = 2;
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);
        
        this.campAutoCompletionTool = new AutoCompletionTool(this.camp, new ArrayList<>());

        this.ordersFilter.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.ORDERS_TABLE_NUMBER, new Predicate<OrdersRowTable>()
            {
                @Override
                public boolean test(OrdersRowTable orderRow)
                {
                    boolean ret;
                    if (newValue.isBlank() || (""+orderRow.getDeliveryNumber()).contains(newValue) || (""+orderRow.getCost()).contains(newValue) || 
                        orderRow.getName().contains(newValue.toUpperCase()) || (""+orderRow.getType()).contains(newValue.toUpperCase()) || 
                        (""+orderRow.getArticleId()).contains(newValue.toUpperCase()) || (""+orderRow.getUnitPrice()).contains(newValue) ||
                        (""+orderRow.getWithdrawalDate()).contains(newValue) )
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
                    }
                    return ret;
                }
            });
        });
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
        List<String> campsAsString = new ArrayList<>();
        Iterator<Campaign> campsAsStringIterator = camps.iterator();
        while(campsAsStringIterator.hasNext())
            campsAsString.add(campsAsStringIterator.next().toString());

        //In the first position is supposed to be the last camp
        this.actualCampaign = camps.get(0);
        this.campAutoCompletionTool.setSuggestions(campsAsString);
    }

    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        this.prefClient = prefClient;
        this.prefClientName.setText(prefClient.getName() + " " + prefClient.getLastName());
        this.prefClientId.setText(prefClient.getId().toString());
        this.ordersQuantity.setText("Artículos: "+ this.prefClient.getArticlesQuantity(this.actualCampaign.getNumber()));
        this.commissionableOrdersQuantity.setText("Comisionables: "+this.prefClient.getCommissionablesQuantity(this.actualCampaign.getNumber()));

        if(prefClient instanceof Leader)
        {
            this.actualCommission = ((Leader)this.prefClient).getCommissions().locateWithCampNumb(this.actualCampaign.getNumber());

            if(this.actualCommission != null)
            {
                this.comLvl.setText("% "+this.actualCommission.getActualRate());
            }
            else
            {
                this.suggestCommisionCreation();
            }
        }

        if(prefClient instanceof SubordinatedClient)
        {
            this.leader.setText(""+((SubordinatedClient)prefClient).getLeaderId());
        }
        
        showTotalsForActualCamp();
        insertDataIntoTables();
    }

    @Override
    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {
        //do nothing
    }

    @Override
    public void showObservation(List<Observation> observations)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showCommission(Commission commission)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void suggestCommisionCreation()
    {
        new CustomAlert(AlertType.CONFIRMATION, "Crear Comisión", "Este líder no tiene niveles de comisión asignado. Desea crear uno?")
        .customShow().ifPresent(response ->
        {
            if(response == ButtonType.OK)
            {
                this.commissionLvlMenuItemSelected();
            }
        });
    }
}