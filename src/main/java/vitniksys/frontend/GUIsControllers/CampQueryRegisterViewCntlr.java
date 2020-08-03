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
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.entities.ClientePreferencial;
import vitniksys.backend.controllers.CampManagementController;

public class CampQueryRegisterViewCntlr extends VitnikViewCntlr implements Initializable, CampQueryRegisterView, OperationResultView
{
    //Changing YEAR_MIN and YEAR_MAX values only affect the frontend view.
    private final int YEAR_MIN = 2020;
    private final int YEAR_MAX = 2038;
    
    private PedidosObtainer ordersObtainer;

    private ExpressionChecker expressionChecker;

    private CampManagementController campManagementController;

    // ================================= FXML variables =================================
    @FXML private TextField campNumber;
    @FXML private TextField campName;
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
    @FXML private Label processWorking;
    @FXML private Label fileSelected;
    @FXML private Label filePath;
    @FXML private Label campNumberInvalid;
    @FXML private Label noResultMessage;
    @FXML private Label orders;

    @FXML private ChoiceBox campMonth;
    @FXML private ChoiceBox campYear;

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
        *THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD.
        *ACTUALLY IS HARDCODED FOR FILE SELECTING METHOD (DETALLE.CSV FILE),
        *BUT IF "PEDIDOS" OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT
        *HAS TO BE IMPLEMENTED.
        */

        //FILE SELECTING METHOD.
        FileChooser fileChooser = new FileChooser();
        File detalle = fileChooser.showOpenDialog(null);
        this.ordersObtainer = new DetailFileInterpreter(detalle);
        
        if (detalle != null)
        {
            this.fileSelected.setText("Archivo seleccionado:");
            this.fileSelected.setVisible(true);
            
            if(FilenameUtils.getExtension(detalle.getName()).equalsIgnoreCase("csv"))
            {
                this.filePath.setTextFill(Color.web("#000000")); //Black
                this.filePath.setText(detalle.getAbsolutePath());
                this.filePath.setVisible(true);

                //Executing the information gathering process.
                this.campManagementController.obtainOrders(this.ordersObtainer);
            }
            else
            {
                this.filePath.setTextFill(Color.web("#ff0000")); //Red
                this.filePath.setText("el archivo no tiene extension csv");
                this.filePath.setVisible(true);
            }
        }
    }
    
    @FXML
    private void registerButtonPressed() throws Exception
    {
        if(this.campManagementController.getCustomersWithNewOrders().isDone())
        {
            try
            {
                //Triggering "Agregar Pedidos" use case.
                    //getCustomersWithNewOrders().get() is a blockig execution method
                    //block for specified timeout with get(long timeout, TimeUnit unit)
                    this.campManagementController.registerOrders(this.campManagementController.getCustomersWithNewOrders().get());   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            this.processWorking.setText("Espere un momento a que finalize la obtención de pedidos.");
            this.processWorking.setTextFill(Color.web("#ff0000")); //Red
            this.processWorking.setVisible(true);
        }
    }

    @FXML
    private void cancelButtonPressed()
    {
        this.search.setVisible(true);
        this.register.setVisible(false);
        this.cancel.setVisible(false);
        this.orders.setVisible(false);
        this.select.setVisible(false);
        this.plusCatalogue.setVisible(false);
        clearStage();
    }

    @FXML
    private void searchButtonPressed()
    {
        
    }

    @FXML
    private void plusCatalogueButtonPressed() throws IOException
    {
        String fileName = "catalogueQuery";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
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
        clearStage();
        this.campName.setDisable(true); //camp name is automatically set.
        this.register.setVisible(true);
        this.cancel.setVisible(true);
        this.plusCatalogue.setVisible(true);
        this.select.setVisible(true);
        this.orders.setVisible(true);

        this.search.setVisible(false);
    }

    @FXML
    private void nroCampCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.campNumber.getText(), true))
        {
            this.campNumberInvalid.setVisible(false);
        }
        else
        {
            this.campNumberInvalid.setText("Dato invalido");
            this.campNumberInvalid.setVisible(true);
        }     
    }

    // ================================= private methods =================================
    private void clearStage()
    {
        this.campNumber.clear();
        this.campName.clear();
        this.campMonth.setValue(null);
        this.campYear.setValue(null);
        this.catalogoCode.clear();
        this.filePath.setText(null);
        this.fileSelected.setText(null);
        this.artDevueltosQuantity.setText(null);
        this.artPedidosQuantity.setText(null);
        this.artRecompradosQuantity.setText(null);
        this.artRetiradosQuantity.setText(null);
        this.totalInCatalogos.setText(null);
        this.totalInDevoluciones.setText(null);
        this.totalInPedidos.setText(null);
        this.totalInRecompras.setText(null);
        this.totalInRetiros.setText(null);
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
        //Creating the expresssion checker object for checking inputs.
        expressionChecker = ExpressionChecker.getExpressionChecker();
        this.campManagementController = new CampManagementController();
        this.campManagementController.setOperationResultView(this);

        //Setting values for campMonth choice box.
        ObservableList<Mes> months = FXCollections.observableArrayList(null, Mes.ENERO, Mes.FEBRERO, Mes.MARZO, Mes.ABRIL, Mes.MAYO, Mes.JUNIO,
        Mes.JULIO, Mes.AGOSTO, Mes.SEPTIEMBRE, Mes.OCTUBRE, Mes.NOVIEMBRE, Mes.DICIEMBRE);
        this.campMonth.setItems(months);

        //Setting values for campYear choice box.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for(int i = YEAR_MIN; i<= YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);

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
        this.noResultMessage.setDisable(false);
    }

    @Override
    public void showQueriedCamp()
    {
           
    }

    @Override
    public void showResult(OperationResult operationResult)
    {
        new CustomAlert().defaultShow(operationResult);
        this.getStage().close();
    }
}