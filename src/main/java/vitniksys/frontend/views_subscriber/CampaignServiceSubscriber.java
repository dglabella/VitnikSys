package vitniksys.frontend.views_subscriber;

import java.util.List;
import vitniksys.backend.model.entities.Campaign;

public interface CampaignServiceSubscriber extends ServiceSubscriber
{
    void showQueriedCamp(Campaign camp) throws Exception;

    void showQueriedCamps(List<Campaign> camps) throws Exception;
}