package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.io.File;
import vitniksys.App;
import java.util.List;
import java.time.Month;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.controllers.CampManagementController;

public class CampRegisterViewCntlr extends VitnikViewCntlr implements Initializable, CampQueryRegisterView
{
    // Changing YEAR_MIN and YEAR_MAX values only affect the frontend view.
    private static final int YEAR_MIN = 2020;
    private static final int YEAR_MAX = 2038;

    private boolean isSearching;

    private File detail;

    private ExpressionChecker expressionChecker;

    private CampManagementController campManagementController;

    // ================================= FXML variables =================================
    @FXML private TextField campNumber;
    @FXML private TextField campAlias;
    @FXML private TextField catalogueCode;
    @FXML private Label artPedidosQuantity;
    @FXML private Label artRetiradosQuantity;
    @FXML private Label artDevueltosQuantity;
    @FXML private Label artRecompradosQuantity;
    @FXML private Label catEntregadosQuantity;
    @FXML private Label totalInPedidos;
    @FXML private Label totalInRetiros;
    @FXML private Label totalInDevoluciones;
    @FXML private Label totalInRecompras;
    @FXML private Label totalInCatalogos;
    @FXML private Label artPedidosQuantityFixed;
    @FXML private Label artRetiradosQuantityFixed;
    @FXML private Label artDevueltosQuantityFixed;
    @FXML private Label artRecompradosQuantityFixed;
    @FXML private Label catEntregadosQuantityFixed;
    @FXML private Label totalInPedidosFixed;
    @FXML private Label totalInRetirosFixed;
    @FXML private Label totalInDevolucionesFixed;
    @FXML private Label totalInRecomprasFixed;
    @FXML private Label totalInCatalogosFixed;
    @FXML private Label fileSelected;
    @FXML private Label filePath;
    @FXML private Label campNumberInvalid;
    @FXML private Label campAliasInvalid;
    @FXML private Label catalogueCodeInvalid;
    @FXML private Label noResultMessage;
    @FXML private Label orders;
    @FXML private ChoiceBox<Month> campMonth;
    @FXML private ChoiceBox<Integer> campYear;
    @FXML private Button register;
    @FXML private Button cancel;
    @FXML private Button search;
    @FXML private Button addOrders;
    @FXML private Button plusCamp;
    @FXML private Button plusCatalogue;

    // ================================= FXML methods =================================
    @FXML
    private void addOrdersButtonPressed() throws Exception
    {
        /**
         * THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD. ACTUALLY IS
         * HARDCODED FOR FILE SELECTING METHOD (Detail.csv FILE), BUT IF "PEDIDOS"
         * OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT HAS TO BE IMPLEMENTED.
         */

        // METHOD: FILE SELECTING .
        FileChooser fileChooser = new FileChooser();
        this.detail = fileChooser.showOpenDialog(null);
        
        if (this.detail != null)
        {
            if (FilenameUtils.getExtension(this.detail.getName()).equalsIgnoreCase(DetailFileInterpreter.FILE_EXTENSION))
            {
                this.fileSelected.setText("Archivo seleccionado:");
                this.fileSelected.setVisible(true);
                this.filePath.setTextFill(Color.web("#000000")); // Black
                this.filePath.setText(detail.getAbsolutePath());
                this.filePath.setVisible(true);
            }
            else
            {
                this.filePath.setTextFill(Color.web("#ff0000")); // Red
                this.filePath.setText("El archivo no tiene extension csv."+"\nSelecciones nuevamente o el archivo será descartado.");
                this.filePath.setVisible(true);
                this.detail = null;
            }
        }
    }

    @FXML
    private void registerButtonPressed()
    {
        try
        {
            this.campManagementController.registerCamp(this.campNumber.getText(), this.campAlias.getText(), 
                this.campMonth.getValue() != null? this.campMonth.getValue().getValue() : null, 
                this.campYear.getValue(), this.catalogueCode.getText(), this.detail);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelButtonPressed()
    {
        this.search.setVisible(true);
        this.register.setVisible(false);
        this.cancel.setVisible(false);
        this.orders.setVisible(false);
        this.addOrders.setVisible(false);
        this.plusCatalogue.setVisible(false);
        clearStage();
        this.isSearching = true;
    }

    @FXML
    private void searchButtonPressed() throws Exception
    {
        this.campManagementController.searchCamps(this.campNumber.getText(), this.campAlias.getText(),
                                                this.campMonth.getValue(), this.campYear.getValue(),
                                                this.catalogueCode.getText());
    }

    @FXML
    private void plusCatalogueButtonPressed() throws IOException
    {
        String fileName = "catalogueQuery";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION + fileName + App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        CatalogueQueryViewCntlr ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevViewCntlr(this);
        ctrller.getStage().show();
    }

    @FXML
    private void plusCampButtonPressed()
    {
        this.isSearching = false;
        clearStage();
        this.register.setVisible(true);
        this.cancel.setVisible(true);
        this.plusCatalogue.setVisible(true);
        this.addOrders.setVisible(true);
        this.orders.setVisible(true);

        this.search.setVisible(false);
    }

    @FXML
    private void monthComboBoxPressed()
    {
         //Clean others invalidity messages
         this.campNumberInvalid.setVisible(false);
         this.campAliasInvalid.setVisible(false);
         this.catalogueCodeInvalid.setVisible(false);
         
         //Clean fields if is Searching for a camp
         if(this.isSearching)
         {
             this.campNumber.clear();
             this.campAlias.clear();
             this.catalogueCode.clear();
         }
    }

    @FXML
    private void yearComboBoxPressed()
    {
         //Clean others invalidity messages
         this.campNumberInvalid.setVisible(false);
         this.campAliasInvalid.setVisible(false);
         this.catalogueCodeInvalid.setVisible(false);
         
         //Clean fields if is Searching for a camp
         if(this.isSearching)
         {
             this.campNumber.clear();
             this.campAlias.clear();
             this.catalogueCode.clear();
         }
    }

    @FXML
    private boolean nroCampCheck()
    {
        boolean ret;

        //Clean others invalidity messages
        this.campAliasInvalid.setVisible(false);
        this.catalogueCodeInvalid.setVisible(false);
        
        //Clean fields if is Searching for a camp
        if(this.isSearching)
        {
            this.campAlias.clear();
            this.campMonth.setValue(null);
            this.campYear.setValue(null);
            this.catalogueCode.clear();
        }

        if (this.expressionChecker.onlyNumbers(this.campNumber.getText(), true))
        {
            this.campNumberInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.campNumberInvalid.setText("Dato invalido");
            this.campNumberInvalid.setVisible(true);
            ret = false;
        }
        return ret;
    }

    @FXML
    private boolean campAliasCheck()
    {
        boolean ret;

        //Clean others invalidity messages
        this.campNumberInvalid.setVisible(false);
        this.catalogueCodeInvalid.setVisible(false);
        
        //Clean fields if is Searching for a camp
        if(this.isSearching)
        {
            this.campNumber.clear();
            this.campMonth.setValue(null);
            this.campYear.setValue(null);
            this.catalogueCode.clear();
        }
    
        if (this.campAlias.getText().length() <= CampManagementController.MAX_LENGTH_CAMP_ALIAS)
        {
            this.campAliasInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.campAliasInvalid.setText("Dato invalido");
            this.campAliasInvalid.setVisible(true);
            ret = false;
        }
        return ret;
    }

    @FXML
    private boolean catalogoCodeCheck()
    {
        boolean ret;

        //Clean invalidity messages
        this.campNumberInvalid.setVisible(false);
        this.campAliasInvalid.setVisible(false);
        
        //Clean fields if is Searching for a camp
        if(this.isSearching)
        {
            this.campNumber.clear();
            this.campAlias.clear();
            this.campMonth.setValue(null);
            this.campYear.setValue(null);
        }
    
        if (this.expressionChecker.isCatalogueCode(this.catalogueCode.getText(), true))
        {
            this.catalogueCodeInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.catalogueCodeInvalid.setText("Dato invalido");
            this.catalogueCodeInvalid.setVisible(true);
            ret = false;
        }
        return ret;
    }

    // ================================= private methods =================================
    private void clearStage()
    {
        this.campNumber.clear();
        this.campMonth.setValue(null);
        this.campYear.setValue(null);
        this.catalogueCode.clear();

        this.filePath.setVisible(false);
        this.fileSelected.setVisible(false);

        this.artDevueltosQuantity.setText("");
        this.artPedidosQuantity.setText("");
        this.artRecompradosQuantity.setText("");
        this.artRetiradosQuantity.setText("");
        this.totalInCatalogos.setText("");
        this.totalInDevoluciones.setText("");
        this.totalInPedidos.setText("");
        this.totalInRecompras.setText("");
        this.totalInRetiros.setText("");
    }

    // ================================= protected methods =================================
    @Override
    protected void manualInitialize() throws Exception
    {
        
    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Creating the expresssion checker object for checking inputs.
        expressionChecker = ExpressionChecker.getExpressionChecker();

        this.campManagementController = new CampManagementController(this);

        //Initiallly, search is active.
        this.isSearching = true;

        // Setting values and listener for campMonth choice box.
        ObservableList<Month> months = FXCollections.observableArrayList(null, Month.JANUARY, Month.FEBRUARY,
            Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER,
            Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);

        this.campMonth.setItems(months);

        /*
        this.campMonth.getSelectionModel().selectedIndexProperty().addListener
        (
            new ChangeListener<Number>()
            {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                {

                    //Clean others invalidity messages
                    campNumberInvalid.setVisible(false);
                    campAliasInvalid.setVisible(false);
                    catalogueCodeInvalid.setVisible(false);
        
                    //Clean fields if is Searching for a camp
                    if(isSearching)
                    {
                        campNumber.clear();
                        campAlias.clear();
                        catalogueCode.clear();
                    }
                }
            }
        );
        */

        // Setting values and listener for campYear choice box.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for (int i = CampRegisterViewCntlr.YEAR_MIN; i <= CampRegisterViewCntlr.YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);

        /*
        this.campYear.getSelectionModel().selectedIndexProperty().addListener
        (
            new ChangeListener<Number>()
            {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                {
                    //Clean others invalidity messages
                    campNumberInvalid.setVisible(false);
                    campAliasInvalid.setVisible(false);
                    catalogueCodeInvalid.setVisible(false);
        
                    //Clean fields if is Searching for a camp
                    if(isSearching)
                    {
                        campNumber.clear();
                        campAlias.clear();
                        catalogueCode.clear();
                    }
                }
            }
        );
        */

        Tooltip toolTipCreateCamp = new Tooltip();
        toolTipCreateCamp.setText("Nueva campaña");
        toolTipCreateCamp.setShowDelay(Duration.seconds(0));
        this.plusCamp.setTooltip(toolTipCreateCamp);

        Tooltip toolTipCreateCatalogue = new Tooltip();
        toolTipCreateCatalogue.setText("Nuevo catálogo");
        toolTipCreateCatalogue.setShowDelay(Duration.seconds(0));
        this.plusCatalogue.setTooltip(toolTipCreateCatalogue);

        this.register.setVisible(false);
        this.noResultMessage.setVisible(false);
        this.plusCatalogue.setVisible(false);
        this.addOrders.setVisible(false);
        this.orders.setVisible(false);
        this.cancel.setVisible(false);
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
        this.artDevueltosQuantity.setVisible(false);
        this.artPedidosQuantity.setVisible(false);
        this.artRecompradosQuantity.setVisible(false);
        this.artRetiradosQuantity.setVisible(false);
        this.catEntregadosQuantity.setVisible(false);
        this.totalInCatalogos.setVisible(false);
        this.totalInDevoluciones.setVisible(false);
        this.totalInPedidos.setVisible(false);
        this.totalInRecompras.setVisible(false);
        this.totalInRetiros.setVisible(false);
        this.artDevueltosQuantityFixed.setVisible(false);
        this.artPedidosQuantityFixed.setVisible(false);
        this.artRecompradosQuantityFixed.setVisible(false);
        this.artRetiradosQuantityFixed.setVisible(false);
        this.catEntregadosQuantityFixed.setVisible(false);
        this.totalInCatalogosFixed.setVisible(false);
        this.totalInDevolucionesFixed.setVisible(false);
        this.totalInPedidosFixed.setVisible(false);
        this.totalInRecomprasFixed.setVisible(false);
        this.totalInRetirosFixed.setVisible(false);

        this.noResultMessage.setVisible(true);
    }

    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
        this.noResultMessage.setVisible(false);

        //First, change the choicesBoxes values to avoid erase other fields.
        //(Both choiceBoxes has changedListener attached)
        this.campMonth.setValue(Month.of(campaign.getMonth()));
        this.campYear.setValue(campaign.getYear());

        this.campNumber.setText(Integer.toString(campaign.getNumber()));
        this.campAlias.setText(campaign.getAlias());

        this.addOrders.setVisible(true);

        this.artDevueltosQuantity.setVisible(true);
        this.artPedidosQuantity.setVisible(true);
        this.artRecompradosQuantity.setVisible(true);
        this.artRetiradosQuantity.setVisible(true);
        this.catEntregadosQuantity.setVisible(true);
        this.totalInCatalogos.setVisible(true);
        this.totalInDevoluciones.setVisible(true);
        this.totalInPedidos.setVisible(true);
        this.totalInRecompras.setVisible(true);
        this.totalInRetiros.setVisible(true);
        this.artDevueltosQuantityFixed.setVisible(true);
        this.artPedidosQuantityFixed.setVisible(true);
        this.artRecompradosQuantityFixed.setVisible(true);
        this.artRetiradosQuantityFixed.setVisible(true);
        this.catEntregadosQuantityFixed.setVisible(true);
        this.totalInCatalogosFixed.setVisible(true);
        this.totalInDevolucionesFixed.setVisible(true);
        this.totalInPedidosFixed.setVisible(true);
        this.totalInRecomprasFixed.setVisible(true);
        this.totalInRetirosFixed.setVisible(true);        
    }

    @Override
    public void showQueriedCamp(List<Campaign> camps) throws Exception
    {
        
    }
}