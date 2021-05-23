package vitniksys.frontend.view_controllers;

import java.io.File;
import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import java.util.function.Predicate;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.util.DetailFileInterpreter;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.business_logic.CampaignBLService;
import vitniksys.backend.model.business_logic.CatalogueBLService;
import vitniksys.backend.model.business_logic.CommissionBLService;
import vitniksys.backend.model.business_logic.PreferentialClientBLService;
import vitniksys.frontend.view_subscribers.PreferentialClientBLServiceSubscriber;

public class MainMenuViewCntlr extends TableViewCntlr implements PreferentialClientBLServiceSubscriber
{
    private int PREF_CLIENTS_TABLE_NUMBER;
    private String COMMAND = "";

    // ================================= FXML variables =================================
    @FXML private TableView<PreferentialClient> prefClients;

    @FXML private TableColumn<PreferentialClient, Integer> id;
    @FXML private TableColumn<PreferentialClient, Long> dni;
    @FXML private TableColumn<PreferentialClient, String> name;
    @FXML private TableColumn<PreferentialClient, String> lastName;
    @FXML private TableColumn<PreferentialClient, String> email;
    @FXML private TableColumn<PreferentialClient, Long> phoneNumber;

    @FXML private TextField searchField;
    @FXML private ImageView catButton;

    // ================================== FXML methods ==================================
    @FXML
    private void summaryButtonPressed()
    {
        ViewCntlr viewCntlr = this.createStage("Resumen de clientes", "summary", new PreferentialClientBLService(), new CampaignBLService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void prefClientsAutoLoadButtonPressed()
    {
        FileChooser fileChooser = new FileChooser();
        File cpsFile = fileChooser.showOpenDialog(null);
        
        if (cpsFile != null)
        {
            if (FilenameUtils.getExtension(cpsFile.getName()).equalsIgnoreCase(App.ConstraitConstants.DETAIL_FILE_EXTENSION))
            {
                new CustomAlert(AlertType.CONFIRMATION, "CONFIRMAR", "El archivo seleccionado es: " + cpsFile.getAbsolutePath() +
                    "\nEsta seguro que desea cargar este archivo de clientes preferenciales?").customShow().ifPresent(response ->
                {
                    if (response == ButtonType.OK)
                    {
                        try
                        {
                            ((PreferentialClientBLService)this.getBLService(0)).registerPrefClientsAuto(cpsFile);
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                });
            }
            else
            {
                new CustomAlert(AlertType.ERROR, "ERROR", "El archivo no tiene extension csv."+
                    "\nSelecciones nuevamente o el archivo será descartado.").customShow();
            }
        }
    }

    @FXML
    private void selectPrefClient(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.openManagementButtonPressed();
        }
    }

    @FXML
    private void catButtonOnMouseEntered()
    {
        catButton.setFitHeight(catButton.getFitHeight()+10);
        catButton.setFitWidth(catButton.getFitWidth()+10);
    }

    @FXML
    private void catButtonOnMouseExited()
    {
        catButton.setFitHeight(catButton.getFitHeight()-10);
        catButton.setFitWidth(catButton.getFitWidth()-10);
    }

    @FXML
    private void catButtonOnMousePressed() throws Exception
    {
        ViewCntlr viewCntlr = this.createStage("Consultar Cátalogo", "catalogueQuery", new CatalogueBLService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void searchCampButtonPressed()
    {
        ViewCntlr viewCntlr = this.createStage("Consultar campaña", "searchCamps", new CampaignBLService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void newCpButtonPressed()
    {
        this.createStage("Formulario de registro de Cliente preferencial", "clientRegister", new PreferentialClientBLService()).getStage().show();
    }

    @FXML
    private void newCampButtonPressed()
    {
        ViewCntlr viewCntlr = this.createStage("Crear campaña", "campRegister", new CampaignBLService(), new CatalogueBLService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void addDetailFileButtonPressed()
    {
        FileChooser fileChooser = new FileChooser();
        File detail = fileChooser.showOpenDialog(null);
        
        if (detail != null)
        {
            if (FilenameUtils.getExtension(detail.getName()).equalsIgnoreCase(App.ConstraitConstants.DETAIL_FILE_EXTENSION))
            {
                new CustomAlert(AlertType.CONFIRMATION, "CONFIRMAR", "El archivo seleccionado es: " + detail.getAbsolutePath() +
                    "\nEsta seguro que desea cargar este detalle?").customShow().ifPresent(response ->
                {
                    if (response == ButtonType.OK)
                    {
                        if(this.generateDataBaseBackUp())
                        {
                            ((CampaignBLService)this.getBLService(1)).registerOrders(detail);
                        }
                        else
                        {
                            new CustomAlert(AlertType.ERROR, "ERROR", "No se pudo realizar la copia de seguridad de la base de datos.").customShow();
                            new CustomAlert(AlertType.CONFIRMATION, "CONFIRMAR", "Desea cargar de todos modos el detalle?\n"
                                +"IMPORTANTE: Debe estar seguro que el archivo que esta cargando es correcto.\n(archivo detalle nunca antes cargado o "+
                                "archivo detalle sin falta de información)").customShow().ifPresent(otherResponse ->
                            {
                                if (otherResponse == ButtonType.OK)
                                {
                                    ((CampaignBLService)this.getBLService(1)).registerOrders(detail);
                                }
                            });
                        }
                    }
                });
            }
            else
            {
                new CustomAlert(AlertType.ERROR, "ERROR", "El archivo no tiene extension csv."+
                    "\nSelecciones nuevamente o el archivo será descartado.").customShow();
            }
        }
    }

    // ================================= private methods =================================
    private void openManagementButtonPressed()
    {
        if(this.prefClients.getSelectionModel().getSelectedIndex() > -1)
        {
            PreferentialClient selectedPrefClient = this.prefClients.getItems().get(this.prefClients.getSelectionModel().getSelectedIndex());

            ViewCntlr viewCntlr = this.createStage("Gestión de cliente preferencial", "clientManagement", new PreferentialClientBLService(), new CampaignBLService(), new CommissionBLService());
            viewCntlr.getStage().show();

            ((ClientManagementViewCntlr)viewCntlr).loadPreferentialClient(selectedPrefClient);

            viewCntlr.manualInitialize();
        }
    }

    private boolean generateDataBaseBackUp(String command)
    {
        boolean ret = false;

        Process process = null;
        Runtime runtime = null;
        try
        {
            runtime = Runtime.getRuntime();
            process = runtime.exec(command);

            ret = process.waitFor() == 0;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return ret;
    }

    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {
        try 
        {
            this.clearTables();
            ((PreferentialClientBLService)this.getBLService(0)).searchPreferentialClients();   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ================================= public methods ==================================
    public void init()
    {
        this.manualInitialize();
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.prefClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<TableColumn> columns = new ArrayList<>();
        columns.add(this.id);
        columns.add(this.dni);
        columns.add(this.name);
        columns.add(this.lastName);
        columns.add(this.email);
        columns.add(this.phoneNumber);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
        propertiesValues.add(new PropertyValueFactory<>("id"));
        propertiesValues.add(new PropertyValueFactory<>("dni"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("lastName"));
        propertiesValues.add(new PropertyValueFactory<>("email"));
        propertiesValues.add(new PropertyValueFactory<>("phoneNumber"));


        this.registerTable(this.prefClients);
        this.PREF_CLIENTS_TABLE_NUMBER = 0; // because is the first table registered.
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);
        

        //Setting the filter binding to the text field
        this.searchField.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.PREF_CLIENTS_TABLE_NUMBER, new Predicate<PreferentialClient>()
            {
                @Override
                public boolean test(PreferentialClient prefClient)
                {
                    boolean ret;
                    if (newValue.isBlank() || String.valueOf(prefClient.getId()).contains(newValue) ||
                        (prefClient.getDni() != null && String.valueOf(prefClient.getDni()).contains(newValue)) ||
                        prefClient.getName().contains(newValue.toUpperCase()) ||
                        String.valueOf(prefClient.getLastName()).contains(newValue.toUpperCase()) ||
                        (prefClient.getEmail() != null && prefClient.getEmail().contains(newValue)) ||
                        (prefClient.getPhoneNumber() != null && String.valueOf(prefClient.getPhoneNumber()).contains(newValue)))
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

    // ================================= service subscriber methods =================================
    @Override
    public void refresh()
    {
        this.manualInitialize();
    }
    
    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        
    }

    @Override
    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {
        this.loadData(this.PREF_CLIENTS_TABLE_NUMBER, prefClients);
    }

    @Override
    public void showDevolutions(List<Devolution> devolutions) throws Exception
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showTotalBalance(float total) throws Exception
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showObservation(Observation observation) throws Exception
    {
        // TODO Auto-generated method stub
    }
}