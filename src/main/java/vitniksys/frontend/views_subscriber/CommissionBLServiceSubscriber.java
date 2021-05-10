package vitniksys.frontend.views_subscriber;

import vitniksys.backend.model.entities.Commission;

public interface CommissionBLServiceSubscriber
{
    void suggestCommisionCreation();

    void suggestCompensation();

    void showCommission(Commission commission);
}