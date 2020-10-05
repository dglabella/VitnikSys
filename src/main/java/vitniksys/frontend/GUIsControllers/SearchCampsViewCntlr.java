package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
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

    // ================================= FXML methods ===================================
    @FXML
    private void acceptButtonPressed()
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