package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.Observation;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.services.CommissionService;
import vitniksys.backend.util.CustomAlert.CustomAlertType;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.services.StockAvailableService;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class ClientManagementViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber, CampaignServiceSubscriber, CommissionServiceSubscriber
{
    private Campaign actualCampaign;
    private PreferentialClient prefClient;
    private Commission actualCommission;
    private List<Order> actualOrders;

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
    @FXML private TableColumn<OrdersRowTable, String> articleType;
    @FXML private TableColumn<OrdersRowTable, String> articleId;
    @FXML private TableColumn<OrdersRowTable, String> unitPrice;
    @FXML private TableColumn<OrdersRowTable, String> withdrawalDate;
    @FXML private TableColumn<OrdersRowTable, String> devQuantity;
    @FXML private TableColumn<OrdersRowTable, String> isCommissionable;
    @FXML private TableColumn<OrdersRowTable, String> countForCommission;

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
    @FXML private TableColumn<RepurchasesRowTable, String> unitCode;
    @FXML private TableColumn<RepurchasesRowTable, String> deliveryNumberRep;
    @FXML private TableColumn<RepurchasesRowTable, String> articleIdRep;
    @FXML private TableColumn<RepurchasesRowTable, String> priceRep;
    @FXML private TableColumn<RepurchasesRowTable, String> repurchasePrice;
    @FXML private TableColumn<RepurchasesRowTable, String> nameRep;
    @FXML private TableColumn<RepurchasesRowTable, String> articleTypeRep;
    @FXML private TableColumn<RepurchasesRowTable, String> repurchaseRegistrationTime;
    @FXML private TableColumn<RepurchasesRowTable, String> isCommissionableRep;

    // ================================= FXML methods ===================================
    @FXML
    private void editPrefClientButtonPressed()
    {
        ClientRegisterViewCntlr viewCntlr = (ClientRegisterViewCntlr) this.createStage("Cliente preferencial "+this.prefClient.getId(), "clientRegister", new PreferentialClientService());
        viewCntlr.getStage().show();
        viewCntlr.setPrefClientId(this.prefClient.getId());
        viewCntlr.setDni(this.prefClient.getDni());
        viewCntlr.setName(this.prefClient.getName());
        viewCntlr.setLastName(this.prefClient.getLastName());
        viewCntlr.setLocation(this.prefClient.getLocation());
        viewCntlr.setEmail(this.prefClient.getEmail() != null? this.prefClient.getEmail() : "");
        viewCntlr.setPhoneNumber(this.prefClient.getPhoneNumber());
        viewCntlr.setBirthdate(this.prefClient.getBirthDate());
        viewCntlr.setIsLeader(this.prefClient instanceof Leader ? true : false);
        viewCntlr.setRegisterButtonText("Actualizar");
        if(this.prefClient instanceof SubordinatedClient)
            viewCntlr.setLeaderId(((SubordinatedClient)this.prefClient).getLeaderId());
        //Not supported yet for hierarchy changes. Disable for all
        viewCntlr.disableLeaderInfo(true);
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
    private void withdrawMenuItemSelected()
    {

    }

    @FXML
    private void withdrawAllMenuItemSelected()
    {

    }

    @FXML
    private void observationsMenuItemSelected()
    {
        if(this.prefClient instanceof BaseClient) // works also for Leader because extends from base client
        {
            ViewCntlr viewCntlr = this.createStage("Observación", "observationEditor", new PreferentialClientService());
            ((ObservationEditorViewCntlr)viewCntlr).setCampNumber(this.actualCampaign.getNumber());
            ((ObservationEditorViewCntlr)viewCntlr).setPrefClientId(this.prefClient.getId());
            viewCntlr.getStage().show();
            viewCntlr.manualInitialize();
        }
    }

    @FXML
    private void cataloguesDeliveryMenuItemSelected()
    {

    }

    @FXML
    private void payMenuItemSelected()
    {
        CustomAlert customAlert = new CustomAlert(CustomAlertType.PAYMENT, "PAGO", "Ingrese los datos necesarios para realizar el pago");
        customAlert.customShow().ifPresent(response ->
        {
            if(response == ButtonType.OK)
            {
                PaymentDialogContentViewCntlr cntlr = (PaymentDialogContentViewCntlr)customAlert.getDialogContentViewCntlr();
                try
                {
                    ((PreferentialClientService)this.getService(0)).registerPayment(this.prefClient, this.actualCampaign.getNumber(), cntlr.getDescriptor(), 
                        cntlr.getAmount(), cntlr.getItem(), cntlr.getPaymentMethod(), cntlr.getBank(), cntlr.getPaymentStatus());
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }                
            }
        });
    }

    @FXML
    private void correctionPaymentMenuItemSelected()
    {

    }

    @FXML
    private void devolutionMenuItemSelected()
    {
        OrdersRowTable orderRowTable = this.orders.getSelectionModel().getSelectedItem();
        if(orderRowTable != null)
        {
            CustomAlert customAlert = new CustomAlert(CustomAlertType.DEVOLUTION , "DEVOLUCIÓN", "Ingrese el motivo de la devolución");

            customAlert.customShow().ifPresent(response ->
            {
                if(response == ButtonType.OK)
                {                    
                    try
                    {
                        ((PreferentialClientService)this.getService(0)).registerDevolution(this.prefClient, this.actualCampaign.getNumber(), orderRowTable.getCode(), 
                                                                                            ((DevolutionDialogContentViewCntlr)customAlert.getDialogContentViewCntlr()).getReason());
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            });
        }
        else
        {
            new CustomAlert(AlertType.INFORMATION, "DEVOLUCIÓN", "Debe seleccionar un pedido.").customShow();
        }
    }

    @FXML
    private void devolutionRepMenuItemSelected()
    {
        RepurchasesRowTable repurchasesRowTable = this.repurchases.getSelectionModel().getSelectedItem();
        if(repurchasesRowTable != null)
        {
            CustomAlert customAlert = new CustomAlert(AlertType.CONFIRMATION , "DEVOLUCIÓN", "Desea devolver esta recompra?");

            customAlert.customShow().ifPresent(response ->
            {
                if(response == ButtonType.OK)
                {
                    try
                    {
                        ((PreferentialClientService)this.getService(0)).registerDevolution(this.prefClient, this.actualCampaign.getNumber(), repurchasesRowTable.getCod());
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            });
        }
        else
        {
            new CustomAlert(AlertType.INFORMATION, "DEVOLUCIÓN", "Debe seleccionar una recompra.").customShow();
        }
    }

    @FXML
    private void seeStockMenuItemSelected()
    {
        ViewCntlr viewCntlr = this.createStage("Stock de artículos devueltos", "stockAvailable", new StockAvailableService());
        viewCntlr.getStage().show();
        ((StockAvailableViewCntlr)viewCntlr).setPrefClient(this.prefClient);
        ((StockAvailableViewCntlr)viewCntlr).setCamp(this.actualCampaign);

        viewCntlr.manualInitialize();
    }

    @FXML
    private void commissionLvlMenuItemSelected()
    {
        if(this.prefClient instanceof Leader)
        {
            ViewCntlr viewCntlr = this.createStage("Comisión", "commissionRegister", new CommissionService());
            viewCntlr.getStage().show();

            //load prefClientId and campNumber because sometimes actualCommission can be null
            ((CommissionRegisterViewCntlr)viewCntlr).loadPrefClient(this.prefClient);
            ((CommissionRegisterViewCntlr)viewCntlr).loadCamp(this.actualCampaign);
            ((CommissionRegisterViewCntlr)viewCntlr).loadOrders(this.actualOrders);
            ((CommissionRegisterViewCntlr)viewCntlr).loadRepurchases(this.prefClient.getRepurchases().locateAllWithCampNumb(this.actualCampaign.getNumber()));
            ((CommissionRegisterViewCntlr)viewCntlr).loadCommission(this.actualCommission);
            
            viewCntlr.manualInitialize();
        }
        else
        {
            new CustomAlert(AlertType.INFORMATION, "COMISIÓN", "Solo los líderes poseen niveles de comisión.").customShow();
        }
    }

    @FXML
    private void updateCommissionablesOrders()
    {
        List<Order> ordersToUpdate = new ArrayList<>();
        Iterator<OrdersRowTable> it1 = this.orders.getItems().iterator();
        while(it1.hasNext())
            ordersToUpdate.add(it1.next().getOrder());

        List<Repurchase> repurchasesToUpdate = new ArrayList<>();
        Iterator<RepurchasesRowTable> it2 = this.repurchases.getItems().iterator();
        while(it2.hasNext())
            repurchasesToUpdate.add(it2.next().getRepurchase());

        ((CommissionService)this.getService(2)).updateCommissionableOrders(this.actualCommission, ordersToUpdate, repurchasesToUpdate);
    }
    
    // ================================= private methods ===================================
    private void showTotalsForActualCamp()
    {
        Balance balance = this.prefClient.getBalances().locateWithCampNumb(this.actualCampaign.getNumber());
        this.campBalance.setText(balance != null ? ""+balance.getBalance() : ""+0);
        this.totalInCampaignOrders.setText(balance != null ? ""+balance.getTotalInOrders() : ""+0);
        this.paymentsPane.setText(balance != null ? "Pagos: "+ balance.getTotalInPayments() : ""+0);
        this.repurchasesPane.setText(balance != null ? "Recompras: "+ balance.getTotalInRepurchases() : ""+0);

        this.balance.setText(""+ this.prefClient.calculateBalance());
    }

    private void insertDataIntoTables()
    {
        int com = 0;
        int fpCom = 0;
        int otherCom = 0;

        if(this.actualCommission != null)
        {
            com = this.actualCommission.getActualRate();
            fpCom = this.actualCommission.getFpFactor();
            otherCom = this.actualCommission.getOtherFactor();
        }

        this.loadData(this.ORDERS_TABLE_NUMBER, OrdersRowTable.generateRows(this.actualOrders, com, fpCom, otherCom));
        this.loadData(this.PAYMENTS_TABLE_NUMBER, this.prefClient.getPayments().locateAllWithCampNumb(this.actualCampaign.getNumber()));
        this.loadData(this.REPURCHASES_TABLE_NUMBER, RepurchasesRowTable.generateRows(this.prefClient.getRepurchases().locateAllWithCampNumb(this.actualCampaign.getNumber())));
    }

    public void fillManagementView()
    {
        this.clearTables();

        if(this.prefClient instanceof Leader)
        {
            ((PreferentialClientService)this.getService(0)).searchLeader(this.prefClient.getId(), this.actualCampaign.getNumber());
        }
        else if(this.prefClient instanceof BaseClient)
        {
            ((PreferentialClientService)this.getService(0)).searchBaseClient(this.prefClient.getId(), this.actualCampaign.getNumber());
        }
        else
        {
            ((PreferentialClientService)this.getService(0)).searchSubordinatedClient(this.prefClient.getId(), this.actualCampaign.getNumber());
        }
    }

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            //((CampaignService)this.getService(1)).searchLastCamp();
            ((CampaignService)this.getService(1)).searchCamps(null, null, null, null, null);

            fillManagementView();
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
        columns.add(this.articleType);
        columns.add(this.articleId);
        columns.add(this.unitPrice);
        columns.add(this.withdrawalDate);
        columns.add(this.devQuantity);
        columns.add(this.isCommissionable);
        columns.add(this.countForCommission);
        
        
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("quantity"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("commissionCost"));
        propertiesValues.add(new PropertyValueFactory<>("commission"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("articleType"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("unitPrice"));
        propertiesValues.add(new PropertyValueFactory<>("withdrawalDate"));
        propertiesValues.add(new PropertyValueFactory<>("returnedQuantity"));
        propertiesValues.add(new PropertyValueFactory<>("commissionable"));
        propertiesValues.add(new PropertyValueFactory<>("countForCommission"));

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

        columns.add(this.unitCode);
        columns.add(this.deliveryNumberRep);
        columns.add(this.articleIdRep);
        columns.add(this.priceRep);
        columns.add(this.repurchasePrice);
        columns.add(this.nameRep);
        columns.add(this.articleTypeRep);
        columns.add(this.repurchaseRegistrationTime);
        columns.add(this.isCommissionableRep);

        propertiesValues.add(new PropertyValueFactory<>("devCode"));
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("repurchaseCost"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("articleType"));
        propertiesValues.add(new PropertyValueFactory<>("registrationTime"));
        propertiesValues.add(new PropertyValueFactory<>("commissionable"));

        this.registerTable(this.repurchases);
        this.REPURCHASES_TABLE_NUMBER = 2;
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);
        
        this.campAutoCompletionTool = new AutoCompletionTool(this.camp, new ArrayList<>());
        this.campAutoCompletionTool.getTextField().setOnKeyReleased
        (
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event)
                {
                    if(event.getCode() == KeyCode.ENTER)
                    {
                        actualCampaign = CampaignService.parseCamp(camp.getText());
                        fillManagementView();
                    }
                    else if(event.getCode() == KeyCode.DOWN)
                    {
                        //This trigger the list to visible
                        campAutoCompletionTool.getTextField().clear();  
                    }
                }
            }
        );

        this.ordersFilter.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.ORDERS_TABLE_NUMBER, new Predicate<OrdersRowTable>()
            {
                @Override
                public boolean test(OrdersRowTable orderRow)
                {
                    boolean ret;
                    if (newValue.isBlank() || (""+orderRow.getDeliveryNumber()).contains(newValue) || (""+orderRow.getCost()).contains(newValue) || 
                        orderRow.getName().contains(newValue.toUpperCase()) || (""+orderRow.getArticleType()).contains(newValue.toUpperCase()) || 
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
    public void refresh()
    {
        this.fillManagementView();
    }

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
        this.camp.setText(this.actualCampaign.toString());
        this.campAutoCompletionTool.getSuggestionsList().setVisible(false);
    }

    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        this.clearTables();
        
        this.prefClient = prefClient;
        this.actualOrders = this.prefClient.getOrders().locateAllWithCampNumb(this.actualCampaign != null ? this.actualCampaign.getNumber() : null);
        this.prefClientName.setText(prefClient.getName() + " " + prefClient.getLastName());
        this.prefClientId.setText(prefClient.getId().toString());
        this.ordersQuantity.setText("Artículos: "+ CommissionService.calculateArticlesQuantity(this.actualOrders));

        this.commissionableOrdersQuantity.setText("Comisionables: "+ CommissionService.calculateCommissionablesQuantity(this.actualOrders, this.prefClient.getRepurchases().locateAllWithCampNumb(this.actualCampaign.getNumber())));

        if(prefClient instanceof Leader)
        {
            this.actualCommission = ((Leader)this.prefClient).getCommissions().locateWithCampNumb(this.actualCampaign.getNumber());

            if(this.actualCommission != null)
            {
                this.comLvl.setText("% "+this.actualCommission.getActualRate());
            }
            else
            {
                this.comLvl.setText("% N/A");
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
    public void showObservation(Observation observation)
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