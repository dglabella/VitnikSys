package vitniksys.frontend.views_subscriber;

import java.util.List;

import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.PreferentialClient;

public interface PreferentialClientServiceSubscriber extends ServiceSubscriber
{
    void showQueriedPrefClient(PreferentialClient prefClient) throws Exception;

    void showQueriedPrefClients(List<PreferentialClient> prefClients) throws Exception;

    void showTotalBalance(float total) throws Exception;

    void showObservation(Observation observation) throws Exception;
}