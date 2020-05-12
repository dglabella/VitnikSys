package vitniksys.backend.view_controllers;

import java.net.URL;
import java.io.File;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vitniksys.backend.model.Mes;
import vitniksys.backend.model.Pedido;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FilenameUtils;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.util.DetalleInterpreter;
import vitniksys.backend.interfaces.PedidosObtainer;
import vitniksys.backend.interfaces.IFunctionalitiesFacade;
import vitniksys.backend.functionality_triggers.FunctionalitiesFacade;

public class ConsultarCampController extends VitnikController implements Initializable
{
    //Changing YEAR_MIN and YEAR_MAX values only affect the frontend view
    private final int YEAR_MIN = 2020;
    private final int YEAR_MAX = 2038;

    private PedidosObtainer pedidosObtainer;

    //private File detalle;
    private ExpressionChecker expressionChecker;

    @FXML private TextField textField_nroCamp;
    @FXML private TextField textField_codCat;

    @FXML private Label label_cantArtPedidos;
    @FXML private Label label_cantArtRetirados;
    @FXML private Label label_cantDevueltos;
    @FXML private Label label_cantRecomprados;
    @FXML private Label label_cantCatEntregados;
    @FXML private Label label_totalPedidos;
    @FXML private Label label_totalRetiros;
    @FXML private Label label_totalDevol;
    @FXML private Label label_totalRecompras;
    @FXML private Label label_totalCat;
    @FXML private Label label_archivoSeleccionado;
    @FXML private Label label_path;
    @FXML private Label label_nroCampInvalido;

    @FXML private ChoiceBox choiceBox_mesCamp;
    @FXML private ChoiceBox choiceBox_yearCamp;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Creating the expresssion checker object for checking inputs
        expressionChecker = ExpressionChecker.getExpressionChecker();

        //Setting values for mesCamp choice box  
        ObservableList<Mes> months  = FXCollections.observableArrayList(null, Mes.ENERO, Mes.FEBRERO, Mes.MARZO, Mes.ABRIL, Mes.MAYO, Mes.JUNIO,
        Mes.JULIO, Mes.AGOSTO, Mes.SEPTIEMBRE, Mes.OCTUBRE, Mes.NOVIEMBRE, Mes.DICIEMBRE);
        this.choiceBox_mesCamp.setItems(months);

        //Setting values for yearCamp choice box
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for(int i = YEAR_MIN; i<= YEAR_MAX; i++)
            years.add(i);
        this.choiceBox_yearCamp.setItems(years);
    }

    @Override
    protected void refresh()
    {

    }

    @FXML private void seleccionarMetodoButtonPressed()
    {   /**
        *THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD.
        *ACTUALLY IS HARDCODED FOR FILE SELECTING METHOD (DETALLE.CSV FILE),
        *BUT IF "PEDIDOS" OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT
        *HAS TO BE IMPLEMENTED.
        */
        IFunctionalitiesFacade functionalities = FunctionalitiesFacade.getFunctionalities();

        //FILE SELECTING METHOD
        FileChooser fileChooser = new FileChooser();
        File detalle = fileChooser.showOpenDialog(null);
        this.pedidosObtainer = DetalleInterpreter.createInterpreter(detalle);

        if (detalle != null)
        {
            this.label_archivoSeleccionado.setText("Archivo seleccionado:");
            this.label_archivoSeleccionado.setVisible(true);
            
            if(FilenameUtils.getExtension(detalle.getName()).equalsIgnoreCase("csv") )
            {
                this.label_path.setTextFill(Color.web("#000000")); //Black
                this.label_path.setText(detalle.getAbsolutePath());
                this.label_path.setVisible(true);
            }
            else
            {
                this.label_path.setTextFill(Color.web("#ff0000")); //Red
                this.label_path.setText("el archivo no tiene extension csv");
                this.label_path.setVisible(true);
            }
        }
    }

    @FXML private void ingresarButtonPressed() throws Exception
    {
        IFunctionalitiesFacade functionalities = FunctionalitiesFacade.getFunctionalities();
        /*
        cps.add(new ClientePreferencialBase(1, "Danilo", "Labella"));
        cps.add(new Lider(2,"Danilo", "Labella"));
        cps.add(new ClientePreferencialBase(3,"Danilo", "Labella"));
        cps.add(new ClientePreferencialBase(4, "Danilo", "Labella"));
        cps.add(new ClientePreferencialBase(5, "Danilo", "Labella"));
        cps.add(new ClientePreferencialSubordinado(6, "Danilo", "Labella"));
        cps.add(new ClientePreferencialSubordinado(7, "Danilo", "Labella"));
        cps.add(new Lider(8, "Danilo", "Labella"));
        cps.add(new ClientePreferencialBase(9, "Danilo", "Labella"));
        cps.add(new ClientePreferencialSubordinado(10, "Danilo", "Labella"));
        */
        //Triggering "Obtener" use case then Triggering "Agregar Pedidos" use case
        functionalities.agregarPedidos(functionalities.obtenerPedidos(this.pedidosObtainer));
    }

    @FXML private void plusCatButtonPressed() throws IOException
    {
        String fxml = "ConsultarCatalogo";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ConsultarCatalogoController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevController(this);
        ctrller.getStage().show();
    }

    @FXML private void plusCampButtonPressed()
    {

    }

    @FXML private void nroCampCheck()
    {
        if(this.expressionChecker.onlyNumbers(this.textField_nroCamp.getText(), false))
        {
            this.label_nroCampInvalido.setVisible(false);
        }
        else
        {
            this.label_nroCampInvalido.setText("Dato invalido");
            this.label_nroCampInvalido.setVisible(true);
        }     
    }
}