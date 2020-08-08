package vitniksys.frontend.views;

import vitniksys.backend.model.entities.Campaign;

public interface CampQueryRegisterView
{
    public void showNoResult();

    public void showQueriedCamp(Campaign Camp);
}