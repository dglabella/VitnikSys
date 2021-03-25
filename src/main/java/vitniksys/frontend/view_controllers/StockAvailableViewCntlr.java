package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import java.util.function.Predicate;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.enums.Reason;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.StockTableRow;
import vitniksys.backend.util.AutoCompletionTool;
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

    private AutoCompletionTool filterAutoCompletionTool;

    // ================================= FXML variables ===================================
    @FXML private Label total;
    @FXML private Label nameLastnameId;
    @FXML private Label assignmentCampaign;

    @FXML private TextField filter;

    @FXML private TableView<StockTableRow> returnedArticles;
    
    @FXML private TableColumn<StockTableRow, String> unitCode;
    @FXML private TableColumn<StockTableRow, String> deliveryNumber;
    @FXML private TableColumn<StockTableRow, String> price;
    @FXML private TableColumn<StockTableRow, String> articleId;
    @FXML private TableColumn<StockTableRow, String> articleName;
    @FXML private TableColumn<StockTableRow, String> orderType;
    @FXML private TableColumn<StockTableRow, String> reason;


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
            StockTableRow stockRowTable = this.returnedArticles.getSelectionModel().getSelectedItem();
            if(stockRowTable != null)
            {
                CustomAlert customAlert = new CustomAlert(CustomAlertType.REPURCHASE , "RECOMPRA", "Ingrese el monto de reventa");
                ((RepurchaseDialogContentViewCntlr)customAlert.getDialogContentViewCntlr()).setCost(stockRowTable.getCost());

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
                        Iterator<StockTableRow> it = this.returnedArticles.getSelectionModel().getSelectedItems().iterator();
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
            this.clearTable(this.RETURNED_ARTICLES_TABLE_NUMBER);

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
        columns.add(this.orderType);
        columns.add(this.reason);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
        propertiesValues.add(new PropertyValueFactory<>("unitCode"));
        propertiesValues.add(new PropertyValueFactory<>("deliveryNumber"));
        propertiesValues.add(new PropertyValueFactory<>("cost"));
        propertiesValues.add(new PropertyValueFactory<>("articleId"));
        propertiesValues.add(new PropertyValueFactory<>("articleName"));
        propertiesValues.add(new PropertyValueFactory<>("orderType"));
        propertiesValues.add(new PropertyValueFactory<>("reason"));

        this.registerTable(this.returnedArticles);
        this.RETURNED_ARTICLES_TABLE_NUMBER = 0; //Because is the first table registered.
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);

        this.returnedArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.filter.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.RETURNED_ARTICLES_TABLE_NUMBER, new Predicate<StockTableRow>()
            {
                @Override
                public boolean test(StockTableRow stockTableRow)
                {
                    boolean ret;
                    if (newValue.isBlank() || (""+stockTableRow.getUnitCode()).contains(newValue) ||(""+stockTableRow.getDeliveryNumber()).contains(newValue) || 
                        (""+stockTableRow.getCost()).contains(newValue) || stockTableRow.getArticleId().contains(newValue.toUpperCase()) || 
                        stockTableRow.getArticleName().contains(newValue.toUpperCase()) || (""+stockTableRow.getOrderType()).contains(newValue.toUpperCase()) || 
                        (""+stockTableRow.getReason()).contains(newValue.toUpperCase()))
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
        manualInitialize();
        this.getPrevViewCntlr().refresh();
    }

    @Override
    public void showStockAvailable(List<ReturnedArticle> returnedArticles) throws Exception
    {
        this.clearTable(this.RETURNED_ARTICLES_TABLE_NUMBER);
        this.loadData(this.RETURNED_ARTICLES_TABLE_NUMBER, StockTableRow.generateRows(returnedArticles));

        float total = 0f;
        Iterator<ReturnedArticle> it = returnedArticles.iterator();
        ReturnedArticle returnedArticle = null;
        while(it.hasNext())
        {
            returnedArticle = it.next();
            total += (returnedArticle.getOrder().getCost() / returnedArticle.getOrder().getQuantity());
        }
        
        this.total.setText(""+total);
    }
}