package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.frontend.views.CampQueryRegisterView;
import javafx.scene.control.cell.PropertyValueFactory;
import vitniksys.backend.controllers.CampManagementController;

public class SearchCampsViewCntlr extends VitnikTableViewCntlr<Campaign> implements CampQueryRegisterView
{
    private CampManagementController campManagementController;

    // ================================= FXML variables  =================================
    @FXML private Spinner<Integer> campNumber;

    @FXML private AutoCompleteTextField<?> campAlias;
    @FXML private AutoCompleteTextField<?> catalogueCode;

    @FXML private ChoiceBox<?> campMonth;
    @FXML private ChoiceBox<?> campYear;

    @FXML private TableView<?> resultTable;

    @FXML private TableColumn<?, ?> column1;
    @FXML private TableColumn<?, ?> column2;
    @FXML private TableColumn<?, ?> column3;
    @FXML private TableColumn<?, ?> column4;
    @FXML private TableColumn<?, ?> column5;
    @FXML private TableColumn<?, ?> column6;

    // ================================= FXML methods ===================================
    @FXML private void acceptButtonPressed()
    {
        
    }

    @FXML private void backButtonPressed()
    {

    }

    @FXML private void monthComboBoxPressed()
    {

    }

    @FXML private void yearComboBoxPressed()
    {

    }

    // ================================= private methods ================================

    // ================================= protected methods ==============================

    // ================================= public methods =================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources)
    {
        this.campManagementController = new CampManagementController(this);
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

    }

    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
       
    }

    @Override
    public void showQueriedCamp(List<Campaign> camps) throws Exception
    {

    }
}