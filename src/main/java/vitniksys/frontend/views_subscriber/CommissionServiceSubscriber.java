package vitniksys.frontend.views_subscriber;

import vitniksys.backend.model.entities.Commission;

public interface CommissionServiceSubscriber
{
    void suggestCommisionCreation();

    void showCommission(Commission commission);
}