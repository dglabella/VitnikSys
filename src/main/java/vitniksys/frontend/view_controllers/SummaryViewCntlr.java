package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import java.util.function.Predicate;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.util.SummaryCampsTableRow;
import vitniksys.backend.model.entities.Observation;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.util.SummaryPrefClientTableRow;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.view_subscribers.CampaignBLServiceSubscriber;
import vitniksys.backend.model.business_logic.PreferentialClientBLService;
import vitniksys.frontend.view_subscribers.PreferentialClientBLServiceSubscriber;

public class SummaryViewCntlr extends TableViewCntlr implements PreferentialClientBLServiceSubscriber, CampaignBLServiceSubscriber
{
    private final String UNPAID_CAMPS_FILTER_COMMAND = App.ConstraitConstants.FILTER_COMMAND_PREFIX+"-";
    private final String PAID_CAMPS_FILTER_COMMAND = App.ConstraitConstants.FILTER_COMMAND_PREFIX+"+";

    private int PREF_CLIENTS_TABLE_NUMBER;
    private int CAMPS_TABLE_NUMBER;

    @FXML private Label nameLastName;

    @FXML private TextField searchClient;
    @FXML private TextField searchCamp;

    @FXML private TableView<SummaryPrefClientTableRow> prefClients;

    @FXML private TableView<SummaryCampsTableRow> camps;

    @FXML private TableColumn<SummaryPrefClientTableRow, Integer> id;
    @FXML private TableColumn<SummaryPrefClientTableRow, Long> dni;
    @FXML private TableColumn<SummaryPrefClientTableRow, String> name;
    @FXML private TableColumn<SummaryPrefClientTableRow, String> lastName;
    @FXML private TableColumn<SummaryPrefClientTableRow, Float> balance;

    @FXML private TableColumn<SummaryCampsTableRow, String> camp;
    @FXML private TableColumn<SummaryCampsTableRow, Float> campBalance;

    // ============================================ FXML methods ============================================

    @FXML
    private void selectPrefClient(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.clearTable(this.CAMPS_TABLE_NUMBER);

            SummaryPrefClientTableRow row = this.prefClients.getSelectionModel().getSelectedItem();

            if(row != null)
            {
                this.nameLastName.setText(""+row.getName()+" "+row.getLastName());
                this.loadData(this.CAMPS_TABLE_NUMBER, SummaryCampsTableRow.generateRows(row.getPrefClient()));
            }
        }
    }

    // ============================================ private methods ============================================

    // ============================================ protected methods ============================================
    
    // ============================================ public methods ============================================
    @Override
    public void refresh()
    {
        // TODO Auto-generated method stub
    }

    @Override
    protected void manualInitialize()
    {
        ((PreferentialClientBLService)this.getBLService(0)).searchPreferentialClientsWithBalancesAndCamps();
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        List<TableColumn> columns = new ArrayList<>();
        List<PropertyValueFactory> propertiesValues = new ArrayList<>();

        columns.add(this.id);
        columns.add(this.dni);
        columns.add(this.name);
        columns.add(this.lastName);
        columns.add(this.balance);
        
        propertiesValues.add(new PropertyValueFactory<>("id"));
        propertiesValues.add(new PropertyValueFactory<>("dni"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("lastName"));
        propertiesValues.add(new PropertyValueFactory<>("balance"));

        this.registerTable(this.prefClients);
        this.PREF_CLIENTS_TABLE_NUMBER = 0; //Because is the first table registered.

        columns.add(this.camp);
        columns.add(this.campBalance);

        propertiesValues.add(new PropertyValueFactory<>("camp"));
        propertiesValues.add(new PropertyValueFactory<>("campBalance"));

        this.registerTable(this.camps);
        this.CAMPS_TABLE_NUMBER = 1;
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);

        this.searchClient.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.PREF_CLIENTS_TABLE_NUMBER, new Predicate<SummaryPrefClientTableRow>()
            {
                @Override
                public boolean test(SummaryPrefClientTableRow prefClient)
                {
                    boolean ret;
                    if (
                        newValue.isBlank() || 
                        (prefClient.getBalance() < 0 && newValue.equals(UNPAID_CAMPS_FILTER_COMMAND)) ||
                        (prefClient.getBalance() > 0 && newValue.equals(PAID_CAMPS_FILTER_COMMAND)) ||
                        (""+prefClient.getId()).contains(newValue) ||
                        (prefClient.getDni() != null && (""+prefClient.getDni()).contains(newValue)) ||
                        prefClient.getName().contains(newValue.toUpperCase()) ||
                        prefClient.getLastName().contains(newValue.toUpperCase())
                        )
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

        this.prefClients.setRowFactory(tv -> new TableRow<SummaryPrefClientTableRow>()
        {
            @Override
            protected void updateItem(SummaryPrefClientTableRow row, boolean empty)
            {
                super.updateItem(row, empty);
                if(row != null)
                {
                    if (row.getBalance() < 0 )
                    {
                        setStyle("-fx-background-color: #FF2C2C;");
                    }
                    else if (row.getBalance() > 0)
                    {
                        setStyle("-fx-background-color: #52FF28;");
                    }
                    else
                    {
                        setStyle(null);
                    }
                }
                else
                {
                    setStyle(null);
                }  
            }
        });

        this.searchCamp.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.CAMPS_TABLE_NUMBER, new Predicate<SummaryCampsTableRow>()
            {
                @Override
                public boolean test(SummaryCampsTableRow camp)
                {
                    boolean ret;
                    if (
                        newValue.isBlank() || 
                        (camp.getCampBalance() < 0 && newValue.equals(UNPAID_CAMPS_FILTER_COMMAND)) ||
                        (camp.getCampBalance() >= 0 && newValue.equals(PAID_CAMPS_FILTER_COMMAND)) || 
                        (""+camp.getCamp()).contains(newValue.toUpperCase()) ||
                        (""+camp.getCampBalance()).contains(newValue)
                        )
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

        this.camps.setRowFactory(tv -> new TableRow<SummaryCampsTableRow>()
        {
            @Override
            protected void updateItem(SummaryCampsTableRow row, boolean empty)
            {
                super.updateItem(row, empty);
                if(row != null)
                {
                    if (row.getCampBalance() < 0 )
                    {
                        setStyle("-fx-background-color: #FF2C2C;");
                    }
                    else if (row.getCampBalance() > 0)
                    {
                        setStyle("-fx-background-color: #52FF28;");
                    }
                    else
                    {
                        setStyle(null);
                    }
                }
                else
                {
                    setStyle(null);
                }  
            }
        });
    }

    // ============================================ BL service subscriber methods ============================================
    @Override
    public void showQueriedCamp(Campaign camp) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showQueriedCamps(List<Campaign> camps) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {
        this.clearTables();
        this.loadData(this.PREF_CLIENTS_TABLE_NUMBER, SummaryPrefClientTableRow.generateRows(prefClients));
    }

    @Override
    public void showTotalBalance(float total) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showDevolutions(List<Devolution> devolutions) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showObservation(Observation observation) throws Exception
    {
        // TODO Auto-generated method stub
        
    }
}