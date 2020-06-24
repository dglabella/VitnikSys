package vitniksys.backend.view_controllers;

import java.net.URL;
import java.io.File;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vitniksys.backend.enums.Mes;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FilenameUtils;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.ClientePreferencial;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.backend.interfaces.IFunctionalities;
import vitniksys.backend.functionality_triggers.Functionalities;

public class CampQueryRegisterController extends VitnikController implements Initializable
{
    //Changing YEAR_MIN and YEAR_MAX values only affect the frontend view.
    private final int YEAR_MIN = 2020;
    private final int YEAR_MAX = 2038;
    
    private PedidosObtainer pedidosObtainer;

    //The list for save the result of "pedidosObtainer".
    private List<ClientePreferencial> customersWithNewOrders;

    private ExpressionChecker expressionChecker;

    /**
     * FXML Objects and variables.
     */
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

    @FXML private ChoiceBox campMonth;
    @FXML private ChoiceBox campYear;

    // ================================= FXML methods =================================
    @FXML private void selectMethodButtonPressed() throws Exception
    {
        /**
        *THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD.
        *ACTUALLY IS HARDCODED FOR FILE SELECTING METHOD (DETALLE.CSV FILE),
        *BUT IF "PEDIDOS" OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT
        *HAS TO BE IMPLEMENTED.
        */
        IFunctionalities functionalities = new Functionalities();

        //FILE SELECTING METHOD.
        FileChooser fileChooser = new FileChooser();
        File detalle = fileChooser.showOpenDialog(null);
        this.pedidosObtainer = new DetailFileInterpreter(detalle);
        
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
                functionalities.obtenerPedidos(this.pedidosObtainer);
            }
            else
            {
                this.filePath.setTextFill(Color.web("#ff0000")); //Red
                this.filePath.setText("el archivo no tiene extension csv");
                this.filePath.setVisible(true);
            }
        }
    }

    @FXML private void registerButtonPressed() throws Exception
    {
        IFunctionalities functionalities = new Functionalities();
        if(functionalities.getCustomersWithNewOrders().isDone())
        {
            try
            {
                //Triggering "Agregar Pedidos" use case.
                    //getCustomersWithNewOrders().get() is a blockig execution method
                    //block for specified timeout with get(long timeout, TimeUnit unit)
                functionalities.agregarPedidos(functionalities.getCustomersWithNewOrders().get());   
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

    @FXML private void plusCatButtonPressed() throws IOException
    {
        String fxml = "ConsultarCatalogo";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        catalogueQueryController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevController(this);
        ctrller.getStage().show();
    }

    @FXML private void plusCampButtonPressed()
    {
        clearStage();
        //camp name is automatically set.
        this.campName.setDisable(true);
        this.customersWithNewOrders.clear();
    }

    @FXML private void nroCampCheck()
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

        //Setting values for campMonth choice box.
        ObservableList<Mes> months  = FXCollections.observableArrayList(null, Mes.ENERO, Mes.FEBRERO, Mes.MARZO, Mes.ABRIL, Mes.MAYO, Mes.JUNIO,
        Mes.JULIO, Mes.AGOSTO, Mes.SEPTIEMBRE, Mes.OCTUBRE, Mes.NOVIEMBRE, Mes.DICIEMBRE);
        this.campMonth.setItems(months);

        //Setting values for campYear choice box.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for(int i = YEAR_MIN; i<= YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);
    }
}