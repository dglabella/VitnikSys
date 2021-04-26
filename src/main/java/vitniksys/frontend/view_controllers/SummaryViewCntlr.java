package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
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
import vitniksys.frontend.views_subscriber.CampaignBLServiceSubscriber;
import vitniksys.backend.model.bussines_logic.PreferentialClientBLService;
import vitniksys.frontend.views_subscriber.PreferentialClientBLServiceSubscriber;

public class SummaryViewCntlr extends TableViewCntlr implements PreferentialClientBLServiceSubscriber, CampaignBLServiceSubscriber
{
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
            System.out.println("Double click!");
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
        ((PreferentialClientBLService)this.getBLService(0)).searchPreferentialClientsWithBalances();
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

        this.prefClients.setRowFactory(tv -> new TableRow<SummaryPrefClientTableRow>()
        {
            @Override
            protected void updateItem(SummaryPrefClientTableRow row, boolean empty)
            {
                if(row != null)
                {
                    super.updateItem(row, empty);
                    if (row.getBalance() < 0 )
                    {
                        setStyle("-fx-background-color: #FF0000;");   
                    }
                    else
                    {
                        setStyle("-fx-background-color: #FFFFFF;");   
                    }
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