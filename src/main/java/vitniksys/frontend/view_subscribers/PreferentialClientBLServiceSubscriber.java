package vitniksys.frontend.view_subscribers;

import java.util.List;

import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.PreferentialClient;

public interface PreferentialClientBLServiceSubscriber extends BLServiceSubscriber
{
    void showQueriedPrefClient(PreferentialClient prefClient) throws Exception;

    void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception;

    void showTotalBalance(float total) throws Exception;

    void showDevolutions(List<Devolution> devolutions) throws Exception;

    void showObservation(Observation observation) throws Exception;
}