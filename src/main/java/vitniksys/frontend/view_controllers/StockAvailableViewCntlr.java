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
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.services.StockAvailableService;
import vitniksys.frontend.views_subscriber.StockAvailableServiceSubscriber;

public class StockAvailableViewCntlr extends TableViewCntlr implements StockAvailableServiceSubscriber
{
    private int RETURNED_ARTICLES_TABLE_NUMBER;

    private Integer prefClientId;
    private Integer campNumber;
    private String prefClientName;
    private String prefClientLastName;

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

    public String getPrefClientName()
    {
        return this.prefClientName;
    }

    public void setPrefClientName(String prefClientName)
    {
        this.prefClientName = prefClientName;
    }

    public String getPrefClientLastName()
    {
        return this.prefClientLastName;
    }

    public void setPrefClientLastName(String prefClientLastName)
    {
        this.prefClientLastName = prefClientLastName;
    }

    // ================================= FXML methods ===================================
    @FXML
    private void repurchaseMenuItemSelected()
    {
        try
        {
            throw new Exception("Operation not suported yet");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ================================= private methods ===================================

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            ((StockAvailableService)this.getService(0)).getStockAvailable();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ================================= public methods ===================================
    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.nameLastnameId.setText(this.prefClientName+" "+this.prefClientLastName+" - "+this.prefClientId);

        List<TableColumn> columns = new ArrayList<>();
        columns.add(this.unitCode);
        columns.add(this.deliveryNumber);
        columns.add(this.price);
        columns.add(this.articleId);
        columns.add(this.articleName);
        columns.add(this.articleType);
        columns.add(this.reason);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
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
    public void showStockAvailable(List<ReturnedArticle> returnedArticles) throws Exception
    {
        this.loadData(this.RETURNED_ARTICLES_TABLE_NUMBER, returnedArticles);
    }
}