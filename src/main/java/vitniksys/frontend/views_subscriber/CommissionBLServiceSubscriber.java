package vitniksys.frontend.views_subscriber;

import vitniksys.backend.model.entities.Commission;

public interface CommissionBLServiceSubscriber
{
    void suggestCommisionCreation();

    void showCommission(Commission commission);
}