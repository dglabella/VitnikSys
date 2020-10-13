package vitniksys.frontend.GUIsControllers;

import java.lang.invoke.StringConcatException;
import java.net.URL;
import vitniksys.App;
import java.util.List;
import java.time.Month;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import org.controlsfx.control.textfield.TextFields;
import vitniksys.frontend.views.CampQueryRegisterView;
import javafx.scene.control.cell.PropertyValueFactory;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import vitniksys.backend.controllers.CampManagementController;
import org.controlsfx.control.textfield.AutoCompletionBinding;

public class SearchCampsViewCntlr extends VitnikTableViewCntlr<Campaign> implements CampQueryRegisterView
{
    public static final int YEAR_MIN = 2020, YEAR_MAX = 2038;

    private int RESULT_TABLE_NUMBER;

    private ExpressionChecker expressionChecker;

    private CampManagementController campManagementController;

    private List<Campaign> selectedCamps;

    // ================================= FXML variables  =================================
    @FXML private Spinner<Integer> campNumber;

    @FXML private TextField campAlias, catalogueCode;

    @FXML private ChoiceBox<Month> campMonth;
    @FXML private ChoiceBox<Integer> campYear;

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
        Buscar camps de nuevo pero asociadas.
        porque? si puedo mandarlas directamente.
        Pero entonces deberia consultar todas las campañas asociadas
        y podria llegar a ser costoso.
        mejor consultar todas las camps no asociadas, luego seleccionar
        y consultar las seleccionadas. Se da mas la situacion de que se
        consulten pocas que muchas.
        */

        //this.resultTable.getSelectionModel().getSelectedItems()

        VitnikViewCntlr viewCtrller = this.createStage("Consultar campaña", "infoQueriedCamps");
        viewCtrller.getStage().show();

        ((InfoQueriedCampsViewCntlr)viewCtrller).loadQueriedCamps(selectedCamps);
    }

    @FXML private void monthComboBoxPressed()
    {
        
    }

    @FXML private void yearComboBoxPressed()
    {

    }

    // ================================= private methods ================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize() throws Exception
    {
        //all null for get all the camps
        this.campManagementController.searchCamps(null, null, null, null, null);        
    }

    // ================================= public methods =================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.campManagementController = new CampManagementController(this);

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
        this.RESULT_TABLE_NUMBER = 0; //because is the first table registered.

        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);


        //Setting months for the month ChoiceBox.
        ObservableList<Month> months = FXCollections.observableArrayList(null, Month.JANUARY, Month.FEBRUARY,
            Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER,
            Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        this.campMonth.setItems(months);

        //Setting years for the year ChoiceBox.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for (int i = SearchCampsViewCntlr.YEAR_MIN; i <= SearchCampsViewCntlr.YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);

        //Default button
        this.accept.setDefaultButton(true);
    }
    
    // ================================= view methods =================================
    @Override
    public CustomAlert showProcessIsWorking(String message)
    {
        return new CustomAlert(AlertType.NONE, "PROCESANDO", message).customShow();
    }

    @Override
    public void closeProcessIsWorking(CustomAlert customAlert)
    {
        customAlert.customClose();
    }

    @Override
    public void showSucces(String message)
    {
        new CustomAlert(AlertType.INFORMATION, "EXITO", message).customShow();
    }

    @Override
    public void showError(String message)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message).customShow();
    }

    @Override
    public void showError(String message, String description, Exception exception)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message, description, exception).customShow();
    }

    @Override
    public void showNoResult(String message)
    {
        new CustomAlert(AlertType.INFORMATION, "SIN RESULTADOS", message);
    }

    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
        // Do nothing
    }

    @Override
    public void showQueriedCamp(List<Campaign> camps) throws Exception
    {
        this.loadData(this.RESULT_TABLE_NUMBER, camps);

        /*
        // load suggestions to auto completion textfields
        List<String> aliasList = new ArrayList<>();
        Iterator<Campaign> campsIterator = this.resultTable.getItems().iterator();
        while(campsIterator.hasNext())
            aliasList.add(campsIterator.next().getAlias());
        
        */

        String[] suggestions = {"Danilo","Daniel","dolores"};
        TextFields.bindAutoCompletion(this.campAlias, suggestions);
    }
}