package vitniksys.frontend.view_subscribers;

import java.util.List;
import vitniksys.backend.model.entities.Campaign;

public interface CampaignBLServiceSubscriber extends BLServiceSubscriber
{
    void showQueriedCamp(Campaign camp) throws Exception;

    void showQueriedCamps(List<Campaign> camps) throws Exception;
}