package vitniksys.frontend.view_subscribers;

import vitniksys.backend.model.entities.Commission;

public interface CommissionBLServiceSubscriber
{
    void suggestCommisionCreation();

    void suggestCompensation();

    void showCommission(Commission commission);
}