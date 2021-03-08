package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class ObservationEditorViewCntlr extends ViewCntlr implements PreferentialClientServiceSubscriber
{
    private Integer prefClientId;
    private Integer campNumber;

    // ============================================= FXML variables =============================================
    @FXML private Label campLabel;
    @FXML private Label prefClientLabel;

    @FXML private TextArea observation;


    // Getters && Setters
    public Integer getPrefClientId()
    {
        return this.prefClientId;
    }

    public void setPrefClientId(Integer prefClientId)
    {
        this.prefClientId = prefClientId;
    }

    public Integer getCampNumber()
    {
        return this.campNumber;
    }

    public void setCampNumber(Integer campNumber)
    {
        this.campNumber = campNumber;
    }
    
    // ============================================= FXML methods =============================================
    @FXML
    private void saveButtonPressed()
    {
        try
        {
            ((PreferentialClientService)this.getService(0)).registerObservation(this.prefClientId, this.campNumber, this.observation.getText());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ============================================= private methods =============================================

    // ============================================= protected methods =============================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            ((PreferentialClientService)this.getService(0)).searchObservation(this.prefClientId, this.campNumber);   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ============================================= public methods =============================================
    @Override
    public void refresh()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.campLabel.setText(camp.toString());
        this.prefClientLabel.setText(""+prefClient.getName()+" "+prefClient.getLastName()+" - "+prefClient.getId());
        this.observationText.setText(this.observation != null ?""+this.observation.getObservation():"");
    }


    // ============================================= service subscriber methods =============================================
    @Override
    public void showQueriedPrefClient(PreferentialClient prefClient) throws Exception
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showObservation(List<Observation> observations)
    {
        // TODO Auto-generated method stub
    }
}