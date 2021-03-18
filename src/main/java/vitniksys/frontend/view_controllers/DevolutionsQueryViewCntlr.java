package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
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

    private PreferentialClient prefClient;

    // FXML Varibales
    @FXML private Label total;
    @FXML private Label nameLastnameId;

    @FXML private TextField filter;
    
    @FXML private TableView<DevolutionsRowTable> devolutions;

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
    protected PreferentialClient getPrefClient()
    {
        return this.prefClient;
    }

    protected void setPrefClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
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
        this.devolutions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);

        this.filter.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.DEVOLUTIONS_TABLE_NUMBER, new Predicate<DevolutionsRowTable>()
            {
                @Override
                public boolean test(DevolutionsRowTable devRow)
                {
                    boolean ret;
                    if (newValue.isBlank() || (""+devRow.getCampNumber()).contains(newValue) || (""+devRow.getPrefClientId()).contains(newValue) || 
                        (""+devRow.getUnitCode()).contains(newValue) || (""+devRow.getDeliveryNumber()).contains(newValue) || 
                        (""+devRow.getCost()).contains(newValue) || devRow.getArticleId().contains(newValue) || 
                        devRow.getArticleName().contains(newValue) || (""+devRow.getOrderType()).contains(newValue.toUpperCase()) || 
                        (""+devRow.getReason()).contains(newValue))
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

    @Override
    protected void manualInitialize()
    {
        this.nameLastnameId.setText(this.prefClient.getName()+" "+this.prefClient.getLastName()+" - "+this.getPrefClient().getId());
        try
        {
            ((PreferentialClientService)this.getService(0)).searchDevolutions(this.prefClient);  
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

        float total = 0;
        Iterator<Devolution> devsIterator =  devolutions.iterator();
        while(devsIterator.hasNext())
            total += devsIterator.next().getCost();
        
        this.total.setText(""+total);
    }

    @Override
    public void showObservation(Observation observation) throws Exception
    {
        // TODO Auto-generated method stub
    }
    // =========================================== service subscriber methods ===========================================
}