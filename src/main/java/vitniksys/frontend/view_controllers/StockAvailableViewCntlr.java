package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import vitniksys.backend.util.StockRowTable;
import vitniksys.backend.model.entities.Observation;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class StockAvailableViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber
{
    private int RETURNED_ARTICLES_TABLE_NUMBER;

    private Integer prefClientId;
    private Integer campNumber;

    // ================================= FXML variables ===================================
    @FXML private Label total;
    @FXML private Label nameLastnameId;

    @FXML private TableView<StockRowTable> returnedArticles;
    
    @FXML private TableColumn<StockRowTable, String> unitCode;
    @FXML private TableColumn<StockRowTable, String> deliveryNumber;
    @FXML private TableColumn<StockRowTable, String> price;
    @FXML private TableColumn<StockRowTable, String> articleId;
    @FXML private TableColumn<StockRowTable, String> articleName;
    @FXML private TableColumn<StockRowTable, String> articleType;
    @FXML private TableColumn<StockRowTable, String> reason;

    // Getters && Setters
    protected Integer getPrefClientId()
    {
        return this.prefClientId;
    }

    protected void setPrefClientId(Integer prefClientId)
    {
        this.prefClientId = prefClientId;
    }

    protected Integer getCampNumber()
    {
        return this.campNumber;
    }

    protected void setCampNumber(Integer campNumber)
    {
        this.campNumber = campNumber;
    }

    // ================================= FXML methods ===================================
    @FXML
    private void repurchaseMenuItemSelected()
    {

    }

    // ================================= private methods ===================================

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        // TODO Auto-generated method stub
    }

    // ================================= public methods ===================================
    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        List<TableColumn> columns = new ArrayList<>();
        List<PropertyValueFactory> propertiesValues = new ArrayList<>();

        columns.add(this.unitCode);
        columns.add(this.deliveryNumber);
        columns.add(this.price);
        columns.add(this.articleId);
        columns.add(this.articleName);
        columns.add(this.articleType);
        columns.add(this.reason);
        
        propertiesValues.add(new PropertyValueFactory<>("unitCode"));
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("price"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("articleName"));
        propertiesValues.add(new PropertyValueFactory<>("articleType"));
        propertiesValues.add(new PropertyValueFactory<>("reason"));

        this.registerTable(this.returnedArticles);
        this.RETURNED_ARTICLES_TABLE_NUMBER = 0; //Because is the first table registered.
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);
    }
    
    // ================================= service subscriber methods ===================================
    @Override
    public void refresh()
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
        // TODO Auto-generated method stub
    }

    @Override
    public void showObservation(List<Observation> observations)
    {
        // TODO Auto-generated method stub
    }
}