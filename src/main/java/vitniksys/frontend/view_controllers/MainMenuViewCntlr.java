package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import vitniksys.backend.model.services.ClientService;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.services.CatalogueService;

public class MainMenuViewCntlr extends ViewCntlr
{
    // ================================= FXML variables =================================
    @FXML private TextField id;
    @FXML private ImageView catButton;

    // ================================== FXML methods ==================================
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

    // ================================= private methods =================================


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {

    }

    // ================================= public methods ==================================

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {

    }
}