package vitniksys.frontend.views;

import java.util.List;
import vitniksys.backend.model.entities.Campaign;

public interface CampaignView extends View
{
    void showQueriedCamp(Campaign camp) throws Exception;

    void showQueriedCamp(List<Campaign> camps) throws Exception;
}