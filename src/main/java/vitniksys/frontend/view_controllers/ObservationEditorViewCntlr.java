package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.services.PreferentialClientService;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class ObservationEditorViewCntlr extends ViewCntlr implements PreferentialClientServiceSubscriber
{
    private Integer prefClientId;
    private Integer campNumber;

    // ============================================= FXML variables =============================================
    @FXML private Label camp;
    @FXML private Label prefClient;
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
        manualInitialize();        
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub
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
    public void showObservation(Observation observation)
    {
        this.camp.setText(""+observation.getCampaign().toString());
        this.prefClient.setText(""+observation.getPrefClient().getName()+" "+observation.getPrefClient().getLastName()+" - "+observation.getPrefClient().getId());
        this.observation.setText(observation.getObservation());
    }
}