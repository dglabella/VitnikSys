package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.io.File;
import vitniksys.App;
import java.util.List;
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
import vitniksys.backend.model.enums.Mes;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.controllers.CampManagementController;

public class CampQueryRegisterViewCntlr extends VitnikViewCntlr implements Initializable, CampQueryRegisterView, OperationResultView
{
    // Changing YEAR_MIN and YEAR_MAX values only affect the frontend view.
    private static final int YEAR_MIN = 2020;
    private static final int YEAR_MAX = 2038;
    private static final int MAX_LENGTH_CAMP_ALIAS = 60;

    private boolean isSearching;

    private PedidosObtainer ordersObtainer;

    private ExpressionChecker expressionChecker;

    private List<PreferentialClient> orderMakers;

    private CampManagementController campManagementController;

    // ================================= FXML variables =================================
    @FXML private TextField campNumber;
    @FXML private TextField campAlias;
    @FXML private TextField catalogoCode;

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
    @FXML private Label processWorking;
    @FXML private Label fileSelected;
    @FXML private Label filePath;
    @FXML private Label campNumberInvalid;
    @FXML private Label campAliasInvalid;
    @FXML private Label catalogoCodeInvalid;

    @FXML private Label noResultMessage;
    @FXML private Label orders;

    @FXML private ChoiceBox<Mes> campMonth;
    @FXML private ChoiceBox<Integer> campYear;

    @FXML private Button register;
    @FXML private Button cancel;
    @FXML private Button search;
    @FXML private Button select;
    @FXML private Button plusCamp;
    @FXML private Button plusCatalogue;

    // ================================= FXML methods =================================
    @FXML
    private void selectMethodButtonPressed() throws Exception
    {
        /**
         * THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD. ACTUALLY IS
         * HARDCODED FOR FILE SELECTING METHOD (DETALLE.CSV FILE), BUT IF "PEDIDOS"
         * OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT HAS TO BE IMPLEMENTED.
         */

        // FILE SELECTING METHOD.
        FileChooser fileChooser = new FileChooser();
        File detalle = fileChooser.showOpenDialog(null);
        this.ordersObtainer = new DetailFileInterpreter(this, detalle);

        if (detalle != null)
        {
            if (FilenameUtils.getExtension(detalle.getName()).equalsIgnoreCase("csv"))
            {
                this.register.setDisable(true);

                this.fileSelected.setText("Archivo seleccionado:");
                this.fileSelected.setVisible(true);
                this.filePath.setTextFill(Color.web("#000000")); // Black
                this.filePath.setText(detalle.getAbsolutePath());
                this.filePath.setVisible(true);
                this.processWorking.setText("Espere un momento a que finalize la obtención de pedidos.");
                this.processWorking.setTextFill(Color.web("#3f5fff")); // blue like
                this.processWorking.setVisible(true);

                // Executing the information gathering process.
                this.campManagementController.obtainOrders(this.ordersObtainer);
            }
            else
            {
                this.filePath.setTextFill(Color.web("#ff0000")); // Red
                this.filePath.setText("el archivo no tiene extension csv");
                this.filePath.setVisible(true);
            }
        }
    }

    @FXML
    private void registerButtonPressed() throws Exception
    {
        if(allFieldsOkForRegistration())
        {
            this.campManagementController.registerCamp(Integer.parseInt(this.campNumber.getText()),
                                                        this.campAlias.getText().toUpperCase(),
                                                        this.campMonth.getValue(),
                                                        this.campYear.getValue(),
                !this.catalogoCode.getText().isEmpty()?Integer.parseInt(this.catalogoCode.getText()):null);
        }

        if(this.orderMakers != null)
            this.campManagementController.registerOrders(this.orderMakers);
    }

    @FXML
    private void cancelButtonPressed()
    {
        this.orderMakers = null;
        this.search.setVisible(true);
        this.register.setVisible(false);
        this.cancel.setVisible(false);
        this.orders.setVisible(false);
        this.select.setVisible(false);
        this.plusCatalogue.setVisible(false);
        clearStage();
        this.isSearching = true;
    }

    @FXML
    private void searchButtonPressed() throws Exception
    {
        if (expressionChecker.onlyNumbers(this.campNumber.getText(), false))
        {
            this.campManagementController.searchCamp(Integer.parseInt(this.campNumber.getText()));
        }
        else if(this.campAlias.getText().length() <= CampQueryRegisterViewCntlr.MAX_LENGTH_CAMP_ALIAS)
        {
            this.campManagementController.searchCamp(this.campAlias.getText());
        }
        else if(this.campMonth.getValue() != null && this.campYear.getValue() != null)
        {
            this.campManagementController.searchCamp(this.campMonth.getValue(), this.campYear.getValue());
        }
        else if(this.campMonth.getValue() != null && this.campYear.getValue() == null)
        {
            this.campManagementController.searchCamps(this.campMonth.getValue());
        }
        else if(this.campMonth.getValue() == null && this.campYear.getValue() != null)
        {
            this.campManagementController.searchCamps(this.campYear.getValue());
        }
        else if(this.expressionChecker.isCatalogueCode(this.catalogoCode.getText(), false))
        {
            this.campManagementController.searchCampsByCatalogue(Integer.parseInt(this.catalogoCode.getText()));
        }
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
        this.select.setVisible(true);
        this.orders.setVisible(true);

        this.search.setVisible(false);
    }

    @FXML
    private boolean nroCampCheck()
    {
        boolean ret;

        //Clean others invalidity messages
        this.campAliasInvalid.setVisible(false);
        this.catalogoCodeInvalid.setVisible(false);
        
        //Clean fields if is Searching for a camp
        if(this.isSearching)
        {
            this.campAlias.clear();
            this.campMonth.setValue(null);
            this.campYear.setValue(null);
            this.catalogoCode.clear();
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
        this.catalogoCodeInvalid.setVisible(false);
        
        //Clean fields if is Searching for a camp
        if(this.isSearching)
        {
            this.campNumber.clear();
            this.campMonth.setValue(null);
            this.campYear.setValue(null);
            this.catalogoCode.clear();
        }
    
        if (this.campAlias.getText().length() <= CampQueryRegisterViewCntlr.MAX_LENGTH_CAMP_ALIAS)
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
    
        if (this.expressionChecker.isCatalogueCode(this.catalogoCode.getText(), true))
        {
            this.catalogoCodeInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.catalogoCodeInvalid.setText("Dato invalido");
            this.catalogoCodeInvalid.setVisible(true);
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
        this.catalogoCode.clear();

        this.filePath.setVisible(false);
        this.fileSelected.setVisible(false);
        this.processWorking.setVisible(false);

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

    private boolean allFieldsOkForRegistration()
    {
        boolean ret;

        if(this.expressionChecker.onlyNumbers(this.campNumber.getText(), false)
            && this.campAlias.getText().length() <= CampQueryRegisterViewCntlr.MAX_LENGTH_CAMP_ALIAS
            && this.campMonth.getValue() != null && this.campYear.getValue() != null
            && this.expressionChecker.isCatalogueCode(this.catalogoCode.getText(), true))
        {
            ret = true;
        }
        else
            ret = false;

        return ret;
    }

    // ================================= protected methods =================================
    @Override
    protected void refresh()
    {

    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Creating the expresssion checker object for checking inputs.
        expressionChecker = ExpressionChecker.getExpressionChecker();
        this.campManagementController = new CampManagementController(this, this);

        //Initiallly, search is active.
        this.isSearching = true;

        // Setting values and listener for campMonth choice box.
        ObservableList<Mes> months = FXCollections.observableArrayList(null, Mes.ENERO, Mes.FEBRERO, Mes.MARZO,
                Mes.ABRIL, Mes.MAYO, Mes.JUNIO, Mes.JULIO, Mes.AGOSTO, Mes.SEPTIEMBRE, Mes.OCTUBRE, Mes.NOVIEMBRE,
                Mes.DICIEMBRE);
        this.campMonth.setItems(months);

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
                    catalogoCodeInvalid.setVisible(false);
        
                    //Clean fields if is Searching for a camp
                    if(isSearching)
                    {
                        campNumber.clear();
                        campAlias.clear();
                        catalogoCode.clear();
                    }
                }
            }
        );
        

        // Setting values and listener for campYear choice box.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for (int i = CampQueryRegisterViewCntlr.YEAR_MIN; i <= CampQueryRegisterViewCntlr.YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);

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
                    catalogoCodeInvalid.setVisible(false);
        
                    //Clean fields if is Searching for a camp
                    if(isSearching)
                    {
                        campNumber.clear();
                        campAlias.clear();
                        catalogoCode.clear();
                    }
                }
            }
        );

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
        this.select.setVisible(false);
        this.orders.setVisible(false);
        this.cancel.setVisible(false);
    }

    @Override
    public void showNoResult()
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
    public void showQueriedCamp(Campaign campaign)
    {
        this.noResultMessage.setVisible(false);

        this.campNumber.setText(Integer.toString(campaign.getNumber()));
        this.campAlias.setText(campaign.getAlias());
        this.campMonth.setValue(campaign.getMonth());
        this.campYear.setValue(campaign.getYear());

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
    public void showQueriedCamp(List<Campaign> camps)
    {
        //show results
    }

    @Override
    public void showResult(OperationResult operationResult)
    {
        //new CustomAlert().defaultShow(operationResult);
        new CustomAlert().customShow(operationResult);
    }

    @Override
    public void orderObtentionCompleted(List<PreferentialClient> cps)
    {
        this.orderMakers = cps;
        this.register.setDisable(false);
        this.processWorking.setTextFill(Color.web("#06bd00"));
        this.processWorking.setText("Archivo detalle procesado exitosamente!");
    }
}