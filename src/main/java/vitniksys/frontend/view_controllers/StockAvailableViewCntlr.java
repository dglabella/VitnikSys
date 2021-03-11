package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.StockRowTable;
import vitniksys.backend.model.entities.Campaign;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.util.CustomAlert.CustomAlertType;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.services.StockAvailableService;
import vitniksys.frontend.views_subscriber.StockAvailableServiceSubscriber;

public class StockAvailableViewCntlr extends TableViewCntlr implements StockAvailableServiceSubscriber
{
    private int RETURNED_ARTICLES_TABLE_NUMBER;

    private PreferentialClient prefClient;
    private Campaign camp;

    // ================================= FXML variables ===================================
    @FXML private Label total;
    @FXML private Label nameLastnameId;
    @FXML private Label assignmentCampaign;

    @FXML private TableView<StockRowTable> returnedArticles;
    
    @FXML private TableColumn<StockRowTable, String> unitCode;
    @FXML private TableColumn<StockRowTable, String> deliveryNumber;
    @FXML private TableColumn<StockRowTable, String> price;
    @FXML private TableColumn<StockRowTable, String> articleId;
    @FXML private TableColumn<StockRowTable, String> articleName;
    @FXML private TableColumn<StockRowTable, String> articleType;
    @FXML private TableColumn<StockRowTable, String> reason;

    // Getters && Setters
    public PreferentialClient getPrefClient()
    {
        return this.prefClient;
    }

    public void setPrefClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
    }

    protected Campaign getCamp()
    {
        return this.camp;
    }

    protected void setCamp(Campaign camp)
    {
        this.camp = camp;
    }

    // ================================= FXML methods ===================================
    @FXML
    private void repurchaseMenuItemSelected()
    {
        try
        {
            StockRowTable stockRowTable = this.returnedArticles.getSelectionModel().getSelectedItem();
            if(stockRowTable != null)
            {
                CustomAlert customAlert = new CustomAlert(CustomAlertType.REPURCHASE , "RECOMPRA", "Ingrese el monto de reventa");

                customAlert.customShow().ifPresent(response ->
                {
                    if(response == ButtonType.OK)
                    {                    
                        try
                        {
                            ((StockAvailableService)this.getService(0)).registerRepurchase(this.prefClient, this.camp.getNumber(), stockRowTable.getUnitCode(), 
                                                                                            ((RepurchaseDialogContentViewCntlr)customAlert.getDialogContentViewCntlr()).getCost());
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
                new CustomAlert(AlertType.INFORMATION, "RECOMPRA", "Debe seleccionar un item de la tabla.").customShow();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @FXML
    private void resendVitnikMenuItemSelected()
    {
            new CustomAlert(AlertType.CONFIRMATION, "CONFIRMAR", "Desea registrar estos artículos como devueltos a VITNIK?")
            .customShow().ifPresent(response -> 
            {
                if(response == ButtonType.OK)
                {
                    try
                    {
                        Iterator<StockRowTable> it = this.returnedArticles.getSelectionModel().getSelectedItems().iterator();
                        List<Integer> unitCodes = new ArrayList<>();
                        while(it.hasNext())
                            unitCodes.add(it.next().getUnitCode());

                        ((StockAvailableService)this.getService(0)).registerVitnikResend(unitCodes);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            });
    }

    // ================================= private methods ===================================

    // ================================= protected methods ===================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            this.nameLastnameId.setText(this.prefClient.getName()+" "+this.prefClient.getLastName()+" - "+this.prefClient.getId());
            this.assignmentCampaign.setText(this.camp.getEnumMonth()+" - "+this.camp.getYear()+" - "+this.camp.getNumber());
            
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

        this.returnedArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        this.loadData(this.RETURNED_ARTICLES_TABLE_NUMBER, StockRowTable.generateRows(returnedArticles));

        float total = 0f;
        Iterator<ReturnedArticle> it = returnedArticles.iterator();
        while(it.hasNext())
            total += it.next().getOrder().getArticle().getUnitPrice();
        
        this.total.setText(""+total);
    }
}