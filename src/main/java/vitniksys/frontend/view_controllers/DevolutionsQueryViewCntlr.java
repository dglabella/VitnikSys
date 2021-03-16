package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.OrderType;
import vitniksys.backend.util.DevolutionsRowTable;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.Observation;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class DevolutionsQueryViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber
{
    private int DEVOLUTIONS_TABLE_NUMBER;

    private Integer clientId;
    private String prefCLientName;
    private String prefCLientLastName;

    // FXML Varibales
    @FXML private Label total;
    @FXML private Label nameLastnameId;
    @FXML private TableView<DevolutionsRowTable> devolutions;

    @FXML private TextField filter;
    
    @FXML private TableColumn<DevolutionsRowTable, Integer> campNumber;
    @FXML private TableColumn<DevolutionsRowTable, Integer> prefClientId;
    @FXML private TableColumn<DevolutionsRowTable, Integer> unitCode;
    @FXML private TableColumn<DevolutionsRowTable, Integer> deliveryNumber;
    @FXML private TableColumn<DevolutionsRowTable, Float> cost;
    @FXML private TableColumn<DevolutionsRowTable, String> articleId;
    @FXML private TableColumn<DevolutionsRowTable, String> articleName;
    @FXML private TableColumn<DevolutionsRowTable, OrderType> orderType;
    @FXML private TableColumn<DevolutionsRowTable, Reason> reason;

    // =========================================== FXML methods ===========================================
    
    
    // =========================================== private methods ===========================================

    // =========================================== protected methods ===========================================
    protected void setPrefClientId(Integer prefClientId)
    {
        this.clientId = prefClientId;
    }

    protected void setPrefCLientName(String prefCLientName)
    {
        this.prefCLientName = prefCLientName;
    }

    protected void setPrefCLientLastName(String prefCLientLastName)
    {
        this.prefCLientLastName = prefCLientLastName;
    }

    // =========================================== public methods ===========================================
    @Override
    public void refresh()
    {
        // TODO Auto-generated method stub    
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.nameLastnameId.setText(this.prefCLientName+" "+this.prefCLientLastName+" - "+this.clientId);
        
        List<TableColumn> columns = new ArrayList<>();
        List<PropertyValueFactory> propertiesValues = new ArrayList<>();

        columns.add(this.campNumber);
        columns.add(this.prefClientId);
        columns.add(this.unitCode);
        columns.add(this.deliveryNumber);
        columns.add(this.cost);
        columns.add(this.articleId);
        columns.add(this.articleName);
        columns.add(this.orderType);
        columns.add(this.reason);
        
        propertiesValues.add(new PropertyValueFactory<>("campNumber"));
        propertiesValues.add(new PropertyValueFactory<>("prefClientId"));
        propertiesValues.add(new PropertyValueFactory<>("unitCode"));
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("articleName"));
        propertiesValues.add(new PropertyValueFactory<>("orderType"));
        propertiesValues.add(new PropertyValueFactory<>("reason"));

        this.registerTable(this.devolutions);
        this.DEVOLUTIONS_TABLE_NUMBER = 0; //Because is the first table registered.
    }

    @Override
    protected void manualInitialize()
    {
        try
        {
            ((PreferentialClientService)this.getService(0)).searchDevolutions(this.clientId);  
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
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
    public void showTotalBalance(float total) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showDevolutions(List<Devolution> devolutions) throws Exception
    {

        this.loadData(DEVOLUTIONS_TABLE_NUMBER, DevolutionsRowTable.generateRows(devolutions));
    }

    @Override
    public void showObservation(Observation observation) throws Exception
    {
        // TODO Auto-generated method stub
        
    }
    // =========================================== service subscriber methods ===========================================
}