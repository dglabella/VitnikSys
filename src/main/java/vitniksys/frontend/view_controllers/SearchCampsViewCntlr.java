package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import java.time.Month;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import java.util.function.Predicate;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import vitniksys.backend.model.entities.Campaign;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;

public class SearchCampsViewCntlr extends TableViewCntlr implements CampaignServiceSubscriber
{
    public static final int YEAR_MIN = 2020, YEAR_MAX = 2038;

    private int RESULT_TABLE_NUMBER;

    private List<Campaign> selectedCamps;

    //private String searchFieldEntry = "";

    // ================================= FXML variables =================================
    @FXML private TextField searchField;

    @FXML private TableView<Campaign> resultTable;

    @FXML private TableColumn<Campaign, Integer> column1;
    @FXML private TableColumn<Campaign, String> column2;
    @FXML private TableColumn<Campaign, Month> column3;
    @FXML private TableColumn<Campaign, Integer> column4;
    @FXML private TableColumn<Campaign, Integer> column5;

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

        ViewCntlr viewCtrller = this.createStage("Consultar campaña", "infoQueriedCamps", new CampaignService());
        viewCtrller.getStage().show();

        ((InfoQueriedCampsViewCntlr) viewCtrller).loadQueriedCamps(selectedCamps);
    }

    // ================================= private methods ================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        try
        {
            ((CampaignService)this.getService()).searchCamps(null, null, null, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

    // ================================= public methods =================================
    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        columns.add(column4);
        columns.add(column5);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
        propertiesValues.add(new PropertyValueFactory<>("number"));
        propertiesValues.add(new PropertyValueFactory<>("alias"));
        propertiesValues.add(new PropertyValueFactory<>("EnumMonth"));
        propertiesValues.add(new PropertyValueFactory<>("year"));
        propertiesValues.add(new PropertyValueFactory<>("CatalogueCode"));


        this.registerTable(this.resultTable);
        this.RESULT_TABLE_NUMBER = 0; // because is the first table registered.
        

        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);


        //Setting the filter binding to the text field
        this.searchField.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.RESULT_TABLE_NUMBER, new Predicate<Campaign>()
            {
                @Override
                public boolean test(Campaign camp)
                {
                    boolean ret;
                    if (newValue.isBlank() || (camp.getAlias() != null && camp.getAlias().contains(newValue.toUpperCase())) ||
                        String.valueOf(camp.getNumber()).contains(newValue) ||
                        (camp.getCatalogueCode() != null && String.valueOf(camp.getCatalogueCode()).contains(newValue)) ||
                        String.valueOf(camp.getMonth()).contains(newValue) || String.valueOf(camp.getYear()).contains(newValue))
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
    
    // ================================= campaign service subscriber methods =================================
    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
        // Do nothing
    }

    @Override
    public void showQueriedCamps(List<Campaign> camps) throws Exception
    {
        this.loadData(this.RESULT_TABLE_NUMBER, camps);
    }
}