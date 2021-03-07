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
    private Campaign camp;
    private PreferentialClient prefClient;
    private Observation observation;

    // ============================================= FXML variables =============================================
    @FXML private Label campLabel;
    @FXML private Label prefClientLabel;

    @FXML private TextArea observationText;


    // Getters && Setters
    public Campaign getCamp()
    {
        return this.camp;
    }

    public void setCamp(Campaign camp)
    {
        this.camp = camp;
    }

    public PreferentialClient getPrefClient()
    {
        return this.prefClient;
    }

    public void setPrefClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
    }

    public Observation getObservation()
    {
        return this.observation;
    }

    public void setObservation(Observation observation)
    {
        this.observation = observation;
    }
    
    // ============================================= FXML methods =============================================
    @FXML
    private void saveButtonPressed()
    {
        try
        {
            ((PreferentialClientService)this.getService(0)).registerObservation(this.prefClient.getId(), this.camp.getNumber(), this.observationText.getText());
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
        // TODO Auto-generated method stub
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
        this.observationText.setText(""+this.observation.getObservation());
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