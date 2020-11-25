package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.model.services.ClientService;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.services.CatalogueService;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class MainMenuViewCntlr extends TableViewCntlr implements PreferentialClientServiceSubscriber
{
    private int PREF_CLIENTS_TABLE_NUMBER;

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
    private void selectPrefClient(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.createStage("Gestion de Cliente", "clientManagament", new CampaignService()).getStage().show();
        }
    }

    @FXML
    private void idOnKeyTyped()
    {

    }

    @FXML
    private void idOnAction() throws Exception
    {
        searchButtonPressed();
    }

    @FXML
    private void textFieldCatOnAction() throws Exception
    {
        searchButtonPressed();
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
        this.createStage("Consultar Cátalogo", "catalogueQuery", new CatalogueService()).getStage().show();
    }

    @FXML
    private void searchButtonPressed() throws Exception
    {
        
    }

    @FXML
    private void searchCampButtonPressed()
    {
        ViewCntlr viewCntlr = this.createStage("Consultar campaña", "searchCamps", new CampaignService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void newCpButtonPressed()
    {
        this.createStage("Formulario de registro de Cliente preferencial", "clientRegister", new ClientService()).getStage().show();
    }

    @FXML
    private void newCampButtonPressed()
    {
        ViewCntlr viewCntlr = this.createStage("Crear campaña", "campRegister", new CampaignService());
        viewCntlr.getStage().show();
        viewCntlr.manualInitialize();
    }

    @FXML
    private void addDetailFileButtonPressed()
    {
        
    }

    // ================================= private methods =================================


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {
        ((ClientService)this.getService()).searchPreferentialClients();
    }

    // ================================= public methods ==================================
    public void init()
    {
        this.manualInitialize();
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
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

    // ================================= preferential client service subscriber methods =================================
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {

    }

    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {
        this.loadData(this.PREF_CLIENTS_TABLE_NUMBER, prefClients);
    }
}