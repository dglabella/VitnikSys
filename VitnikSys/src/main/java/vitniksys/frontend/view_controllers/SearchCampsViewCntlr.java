package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import java.util.List;
import java.time.Month;
import javafx.fxml.FXML;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.function.Predicate;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import vitniksys.backend.util.CampsTableRow;
import vitniksys.backend.model.entities.Campaign;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.business_logic.CampaignBLService;
import vitniksys.frontend.view_subscribers.CampaignBLServiceSubscriber;

public class SearchCampsViewCntlr extends TableViewCntlr implements CampaignBLServiceSubscriber
{
    private final String UNPAID_CAMPS_FILTER_COMMAND = App.ConstraitConstants.FILTER_COMMAND_PREFIX+"-";
    private final String PAID_CAMPS_FILTER_COMMAND = App.ConstraitConstants.FILTER_COMMAND_PREFIX+"+";
    
    private int CAMPS_TABLE_NUMBER;

    private List<Campaign> selectedCamps;

    // ================================= FXML variables =================================
    @FXML private Label generalBalance;
    
    @FXML private TextField searchField;

    @FXML private TableView<CampsTableRow> camps;

    @FXML private TableColumn<CampsTableRow, Integer> idCamp;
    @FXML private TableColumn<CampsTableRow, Month> month;
    @FXML private TableColumn<CampsTableRow, Integer> year;
    @FXML private TableColumn<CampsTableRow, String> alias;
    @FXML private TableColumn<CampsTableRow, Float> balance;
    @FXML private TableColumn<CampsTableRow, Integer> catalogueCode;

    @FXML private Button accept;

    // ================================= FXML methods ===================================
    @FXML
    private void acceptButtonPressed() throws Exception
    {
        /*
         * Buscar camps de nuevo pero asociadas. porque? si puedo mandarlas
         * directamente. Pero entonces deberia consultar todas las campañas asociadas y
         * podria llegar a ser costoso. mejor consultar todas las camps no asociadas,
         * luego seleccionar y consultar las seleccionadas. Se da mas la situacion de
         * que se consulten pocas que muchas.
         */

        // this.resultTable.getSelectionModel().getSelectedItems()

        ViewCntlr viewCtrller = this.createStage("Consultar campaña", "infoQueriedCamps", new CampaignBLService());
        viewCtrller.getStage().show();

        ((InfoQueriedCampsViewCntlr) viewCtrller).loadQueriedCamps(selectedCamps);
    }

    // ================================= private methods ================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        ((CampaignBLService)this.getBLService(0)).searchCampsWithBalances();
    }
    

    // ================================= public methods =================================
    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(idCamp);
        columns.add(month);
        columns.add(year);
        columns.add(alias);
        columns.add(balance);
        columns.add(catalogueCode);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
        propertiesValues.add(new PropertyValueFactory<>("idCamp"));
        propertiesValues.add(new PropertyValueFactory<>("month"));
        propertiesValues.add(new PropertyValueFactory<>("year"));
        propertiesValues.add(new PropertyValueFactory<>("alias"));
        propertiesValues.add(new PropertyValueFactory<>("balance"));
        propertiesValues.add(new PropertyValueFactory<>("catalogueCode"));


        this.registerTable(this.camps);
        this.CAMPS_TABLE_NUMBER = 0; // because is the first table registered.
        

        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);


        //Setting the filter binding to the text field
        this.searchField.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.CAMPS_TABLE_NUMBER, new Predicate<CampsTableRow>()
            {
                @Override
                public boolean test(CampsTableRow row)
                {
                    boolean ret;
                    if (
                        newValue.isBlank() || 
                        (row.getBalance() < 0 && newValue.contains(UNPAID_CAMPS_FILTER_COMMAND)) ||
                        (row.getBalance() > 0 && newValue.contains(PAID_CAMPS_FILTER_COMMAND)) ||
                        (row.getAlias() != null && row.getAlias().contains(newValue.toUpperCase())) ||
                        String.valueOf(row.getIdCamp()).contains(newValue.toUpperCase()) ||
                        (row.getCatalogueCode() != null && String.valueOf(row.getCatalogueCode()).contains(newValue.toUpperCase())) ||
                        String.valueOf(row.getMonth()).contains(newValue.toUpperCase()) || 
                        String.valueOf(row.getYear()).contains(newValue.toUpperCase())
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

        this.camps.setRowFactory(tv -> new TableRow<CampsTableRow>()
        {
            @Override
            protected void updateItem(CampsTableRow row, boolean empty)
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
    }
    
    // ================================= campaign service subscriber methods =================================
    @Override
    public void refresh()
    {
        
    }
    
    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
        // Do nothing
    }

    @Override
    public void showQueriedCamps(List<Campaign> camps) throws Exception
    {
        float generalBalance = 0;
        List<CampsTableRow> campsRows = CampsTableRow.generateRows(camps);
        Iterator<CampsTableRow> it = campsRows.iterator();

        while(it.hasNext())
        {
            generalBalance += it.next().getBalance();
        }

        this.generalBalance.setText(""+generalBalance);
        this.loadData(this.CAMPS_TABLE_NUMBER, campsRows);
    }
}