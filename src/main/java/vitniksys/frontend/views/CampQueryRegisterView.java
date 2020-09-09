package vitniksys.frontend.views;

import java.util.List;
import vitniksys.backend.model.entities.Campaign;

public interface CampQueryRegisterView
{
    void showNoResult();

    void showQueriedCamp(Campaign camp);

    void showQueriedCamp(List<Campaign> camps);
}