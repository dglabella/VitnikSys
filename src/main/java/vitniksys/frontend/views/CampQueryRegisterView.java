package vitniksys.frontend.views;

import java.util.List;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.PreferentialClient;

public interface CampQueryRegisterView
{
    public void showNoResult();

    public void showQueriedCamp(Campaign camp);

    public void showQueriedCamp(List<Campaign> camps);

    public void orderObtentionCompleted(List<PreferentialClient> cps);
}